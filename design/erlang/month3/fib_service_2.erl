-module(fib_service_2).
-compile(export_all).

%% APIs
start() ->
	Pid = spawn(fun() -> loop() end),
	register(?MODULE, Pid),
	{ok, Pid}.
	
calc(N) ->
	Ref = make_ref(),
	?MODULE ! {Ref, self(), {calc, N}},
	receive
		{Ref, Reply} ->
			Reply
		after 2000 ->
			{error, timeout}
	end.
	
%% inner functions
loop() ->
	receive
		{Ref, From, Req} ->
			spawn(fun() -> fib_worker_2:start(Ref, From, Req) end),
			loop()
	end.
	
	
%fib_service_2:start().
%[spawn(fun() -> fib_service_2:calc(44) end) || _ <- lists:seq(1,10)].
%fib_service_2:calc(3).
