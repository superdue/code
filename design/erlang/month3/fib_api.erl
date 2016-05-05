-module(fib_api).
-compile(export_all).

calc(N) ->
	AvailableNodes = [Node1, Node2],
	calc(N, AvailableNodes).
	
calc(_, []) ->
	{error, no_available_node};
calc(N, [Node|T]) ->
	case rpc:call(Node, fib_service_2, calc, [N]) of
		{badrpc, _} ->
			calc(N,T);
		Result ->
			Result
	end.