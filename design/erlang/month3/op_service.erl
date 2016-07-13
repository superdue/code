-module(op_service).
-compile(export_all).

%% APIs
-spec begin_trans(UserId::integer(), N::integer()) -> ok | {error, Reason::term()}.
-spec op(UserId::integer(), Op::atom()) -> ok | {error, Reason::term()}.
-spec end_trans(UserId::integer()) -> {ok, integer()}.

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
			print("begin_trans", State),
			loop([{UserId,N} | State]);
		{op,From,UserId,double} ->
			case lists:keyfind(UserId,1,State) of
				false ->
					print("op false", State),
					pass;
				{UserId,N} ->
					print("op true", State),
					%
					%spawn(fun() -> calc(UserId,Op,N) end),
					%op_worker:start(UserId,Op,N),
					Ns = lists:keyreplace(UserId,1,State,{UserId,N*2}),
					From ! ok,
					loop(Ns)
			end;
		{end_trans,From,UserId} ->
			case lists:keyfind(UserId,1,State) of
				false ->
					print("end_trans false", State),
					pass;
				{UserId,N} ->
					From ! N,
					print("end_trans true", State),
					loop(lists:keydelete(UserId,1,State))
			end
		%
		%{op_result,UserId,R} ->
			
	end.

print(Msg, State) ->
	io:format(Msg),
	[io:format(" ~p", [X]) || X <- State],
	io:format(" ~n").

