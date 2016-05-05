-module(fib_service).
-compile(export_all).

%% APIs

start(N) ->
	% KEY: another spawn !!!
	Pid = spawn(fun() -> init(N) end),
	register(?MODULE, Pid),
	{ok, Pid}.
	 
calc(N) ->
	% pg2:get_members(fib_group),
	Pid = pg2:get_closest_pid(fib_group),
	calc(Pid, N).
	
%% inner functions
calc(Pid, N) ->
	rpc(Pid, {calc, N}).
	
rpc(Pid, Req) ->
	Ref = make_ref(),
	Pid !{Ref, self(), Req},
	receive
		{Ref, Reply} ->
			Reply
		after 2000 ->
			{error, timeout}
	end.
	
init(N) ->
	pg2:create(fib_group),
	[spawn_worker() || _ <- lists:seq(1,N)],
	loop().
	
loop() ->
	receive
		{'DOWN', _, process, _, _Reason} ->
			spawn_worker(),
			loop()
	end.
	
spawn_worker() ->
	{ok, Pid} = fib_worker:start(),
	pg2:join(fib_group, Pid),
	erlang:monitor(process, Pid).
	
	
%fib_service:start(4).
%[spawn(fun() -> fib_service:calc(44) end) || _ <- lists:seq(1,10)].
%fib_service:calc(3).




