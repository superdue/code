-module(op_service_2).
-compile(export_all).

%% APIs
-spec begin_trans(UserId::integer(), N::integer()) -> ok | {error, Reason::term()}.
-spec op(UserId::integer(), Op::atom()) -> ok | {error, Reason::term()}.
-spec end_trans(UserId::integer()) -> {ok, integer()}.

-record(user_info,{uid,num,pending_ops=[],receiver=none}).
%-record(state,{users=[]}).
% State :: [#user_info{}]

begin_trans(UserId, N) ->
	S = self(),
	?MODULE ! {begin_trans, S, #user_info{uid=UserId, num=N}},
	receive
		pass  -> {error, unknown};
		ok -> ok
	end.
	
op(UserId, Op) ->
	S = self(),
	?MODULE ! {op, S, #user_info{uid=UserId, pending_ops=[Op]}},
	receive
		pass  -> {error, unknown};
		ok -> ok
	end.
	
end_trans(UserId) ->
	S = self(),
	?MODULE ! {end_trans, S, #user_info{uid=UserId}},
	receive
		pass -> {error, unknown};
		N -> N
	end.
		
start() -> 
	Pid = spawn(fun() -> loop([]) end),
	register(?MODULE, Pid).

loop(State) ->
	receive
		{begin_trans,From,#user_info{uid=UserId,num=N}} ->
			From ! ok,
			print("begin_trans", State),
			loop([#user_info{uid=UserId,num=N} | State]);
		{op,From,#user_info{uid=UserId,pending_ops=[double]}} ->
			case lists:keyfind(UserId,#user_info.uid,State) of
				false ->
					print("op false", State),
					pass;
				#user_info{uid=UserId,num=N} ->
					print("op true", State),
					Ns = lists:keyreplace(UserId,#user_info.uid,State,#user_info{uid=UserId,num=N*2}),
					From ! ok,
					loop(Ns)
			end;
		{end_trans,From,#user_info{uid=UserId}} ->
			case lists:keyfind(UserId,#user_info.uid,State) of
				false ->
					print("end_trans false", State),
					pass;
				#user_info{uid=UserId,num=N} ->
					From ! N,
					print("end_trans true", State),
					loop(lists:keydelete(UserId,#user_info.uid,State))
			end
		%{op_result,UserId,R} ->
		%	case lists:keyfind(UserId,#user_info.uid,State) of
		%		false ->

		%		#user_info{uid=Uid}=E ->
		%			lists:keyreplace(UserId,#user_info.uid,State,E#user_info{num=R})
		%	end.
	end.

print(Msg, State) ->
	io:format(Msg),
	[io:format(" ~p", [X]) || X <- State],
	io:format(" ~n").

