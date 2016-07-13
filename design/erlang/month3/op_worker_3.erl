-module(op_worker_3).
-compile(export_all).

start(UserId, Op, N) ->
	S = self(),
	%random:seed(now()),
	%N2 = case random:uniform(5) > 2 of
	%		true -> N;
	%		false -> not_a_num
	%	 end,
	spawn(fun() -> S ! {op_result, UserId, calc(Op, N)} end).
	
calc(double1, N) ->
	timer:sleep(5000),
	N*2;
calc(double2, N) ->
	timer:sleep(5000),
	N*2;
calc(double3, N) ->
	timer:sleep(5000),
	N*2;
calc(double4, N) ->
	timer:sleep(5000),
	N*2;
calc(double5, N) ->
	timer:sleep(5000),
	N*2.

	
	
	