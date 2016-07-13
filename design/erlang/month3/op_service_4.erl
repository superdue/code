-module(op_service_4).
-compile(export_all).

%% APIs
-spec begin_trans(UserId::integer(), N::integer()) -> ok | {error, Reason::term()}.
-spec op(UserId::integer(), Op::atom()) -> ok | {error, Reason::term()}.
-spec end_trans(UserId::integer()) -> {ok, integer()}.

-record(user_info,{uid,num,pending_ops=[],receiver=none}).
-record(worker_info, {pid, uid, op, num}).
-record(state,{users=[], worker=[]}).
% State :: [#user_info{}]

begin_trans(UserId, N) ->
	S = self(),
	?MODULE ! {begin_trans, S, UserId, N},
	receive
		pass  -> {error, unknown};
		ok -> ok
	end.
	
op(UserId, Op) ->
	S = self(),
	?MODULE ! {op, S, UserId, Op},
	receive
		pass  -> {error, unknown};
		ok -> ok
	end.
	
end_trans(UserId) ->
	S = self(),
	?MODULE ! {end_trans, S, UserId},
	receive
		pass -> {error, unknown};
		N -> N
	end.
		
start() -> 
	Pid = spawn(fun() -> loop([]) end),
	register(?MODULE, Pid).

loop(State) ->
	receive
		{begin_trans,From,UserId,N} ->
			From ! ok,
			%print("begin_trans", State),
			loop([#user_info{uid=UserId,num=N,pending_ops=[]} | State]);
		{op,From,UserId,Op} ->
			case lists:keyfind(UserId,#user_info.uid,State) of
				false ->
					%print("op false", State),
					From ! user_not_exist,
					loop(State);
				#user_info{num=N,pending_ops=[]}=U ->
					%print("op true", State),
					op_worker_3:start(UserId,Op,N),
					From ! ok,
					loop(lists:keyreplace(UserId,#user_info.uid,State,U#user_info{pending_ops=[Op]})); %
				#user_info{pending_ops=Pending}=U ->
					From ! ok,
					Pending2 = Pending++[Op], % FIXME
					print("Pending2", Pending2),
					loop(lists:keyreplace(UserId,#user_info.uid,State,U#user_info{pending_ops=Pending2}))
			end;
		{end_trans,From,UserId} ->
			case lists:keyfind(UserId,#user_info.uid,State) of
				false ->
					%print("end_trans false", State),
					From ! user_not_exist,
					loop(State);
				#user_info{num=N, pending_ops=[]} ->
					From ! N,
					%print("end_trans true", State),
					loop(lists:keydelete(UserId,#user_info.uid,State));
				U ->
					loop(lists:keyreplace(UserId,#user_info.uid,State, U#user_info{receiver=From}))
			end;
		{op_result,UserId,R} ->
			case lists:keyfind(UserId,#user_info.uid,State) of
				false ->
					loop(State);
				#user_info{pending_ops=[_],receiver=none}=U ->
					%timer:sleep(45000),
					loop(lists:keyreplace(UserId,#user_info.uid,State,U#user_info{num=R,pending_ops=[]})); %
				#user_info{pending_ops=[_],receiver=Receiver} ->
					Receiver ! R,
					loop(lists:keydelete(UserId,#user_info.uid,State));
				#user_info{pending_ops=[Op1,Op2|T]}=U ->
					print("pending_ops", [Op1,Op2|T]),
					op_worker_3:start(UserId, Op2, R),
					loop(lists:keyreplace(UserId,#user_info.uid,State,U#user_info{num=R}))
			end
	end.

print(Msg, State) ->
	io:format(Msg),
	[io:format(" ~p", [X]) || X <- State],
	io:format(" ~n").

