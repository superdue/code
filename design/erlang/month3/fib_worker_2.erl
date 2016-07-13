-module(fib_worker_2).
-compile(export_all).

%% APIs
start(Ref, From, {calc, N}) ->
	From ! {Ref, {ok, fib:calc(N)}}.