-module(fib_worker).
-compile(export_all).

%% APIs
start() ->
	Pid = spawn(fun() -> loop() end),
	{ok, Pid}.
	
calc(Pid, N) ->
	rpc(Pid, {calc, N}).
	
%% inner functions
rpc(Pid, Req) ->
	Ref = make_ref(),
	Pid ! {Ref, self(), Req},
	receive
		{Ref, Reply} ->
			Reply
	end.
	
loop() ->
	receive
		{Ref, From, {calc, N}} ->
			From ! {Ref, {ok, fib:calc(N)}},
			loop()
	end.
