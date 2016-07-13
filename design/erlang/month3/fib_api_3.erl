-module(fib_api_3).
-compile(export_all).

calc(N) ->
	pg2:start(),
	Node = pg2:get_closest_pid(fib_group),
	%calc(N, Node).
	
calc(N, Node) ->
	case rpc:call(Node, fib_service_3, calc, [N]) of
		{badrpc, _} ->
			Node = pg2:get_closest_pid(fib_group),
			calc(N, Node);
		Result ->
			Result
	end.