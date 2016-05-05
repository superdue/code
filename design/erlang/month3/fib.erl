-module(fib).
-compile(export_all).

fib(N) ->
	case N of
		0 -> 0;
		1 -> 1;
		M -> fib(M-2) + fib(M-1)
	end.
	
fib2(0) -> 0;
fib2(1) -> 1;
fib2(N) -> fib2(N-1) + fib2(N-2).
	
future(Fun) ->
	Ref = make_ref(),
	Owner = self(),
	Pid = spawn(fun() ->
					V = Fun(),
					Owner ! {Ref, V}
				end),
	{Pid, Ref}.
	
future_get({_, Ref}) -> 
	receive
		{Ref, V} -> V
	end.
	
fib_para(0) -> 0;
fib_para(1) -> 1;
fib_para(N) ->
	F1 = future(fun() -> fib2(N-1) end),
	F2 = future(fun() -> fib2(N-2) end),
	future_get(F1) + future_get(F2).
	
fib_para2(0) -> 0;
fib_para2(1) -> 1;
fib_para2(N) ->
	fib_para2(N, N).
fib_para2(M, N) when N-4>0 andalso M>N-4 ->
	F1 = future(fun() -> fib_para2(M-1, N) end),
	F2 = future(fun() -> fib_para2(M-2, N) end),
	future_get(F1) + future_get(F2);
fib_para2(M, _N) ->
	F1 = future(fun() -> fib2(M-1) end),
	F2 = future(fun() -> fib2(M-2) end),
	future_get(F1) + future_get(F2).
	
% timer:tc(fun() -> fib:fib_para(40), ok end).

fib_iter(N) ->
	fib_iter(N, 0, 1).
	
fib_iter(0, A1, _) -> A1;
fib_iter(N, A1, A2) -> fib_iter(N-1, A2, A1+A2).

% timer:tc(fun() -> fib:fib_para(400000), ok end).

fib_2(0) -> 0;
fib_2(1) -> 1;
fib_2(N) when N rem 2 =:= 0 ->
	M = N div 2,
	R1 = fib_2(M),
	R2 = fib_2(M-1),
	R1*(R1 + 2*R2);
fib_2(N) ->
	M = (N-1) div 2,
	R1 = fib_2(M+1),
	R2 = fib_2(M),
	R1*R1 + R2*R2.
	
% timer:tc(fun() -> fib:fib_2(400000), ok end).

my_fib_para3(0) -> 0;
my_fib_para3(1) -> 1;
my_fib_para3(N) when N rem 2 =:= 0 ->
	M = N div 2,
	F1 = future(fun() -> fib_2(M) end),
	F2 = future(fun() -> fib_2(M-1) end),
	R1 = future_get(F1),
	R2 = future_get(F2),
	R1*(R1 + 2*R2);
my_fib_para3(N) ->
	M = (N-1) div 2,
	F1 = future(fun() -> fib_2(M+1) end),
	F2 = future(fun() -> fib_2(M) end),
	R1 = future_get(F1),
	R2 = future_get(F2),
	R1*R1 + R2*R2.


fib_2_para(0) -> 0;
fib_2_para(1) -> 1;
fib_2_para(N) when N rem 2 =:= 0 ->
	M = N div 2,
	F1 = future(fun() -> fib_2(M) end),
	F2 = future(fun() -> fib_2(M-1) end),
	R1 = future_get(F1),
	R2 = future_get(F2),
	R1*(R1 + 2*R2);
fib_2_para(N) ->
	M = (N-1) div 2,
	F1 = future(fun() -> fib_2(M+1) end),
	F2 = future(fun() -> fib_2(M) end),
	R1 = future_get(F1),
	R2 = future_get(F2),
	R1*R1 + R2*R2.
	
% From Lib to Service
	
calc(0) -> 0;
calc(1) -> 1;
calc(N) -> calc(N-1) + calc(N-2).

