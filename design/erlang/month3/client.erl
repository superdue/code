-module(client).
-compile(export_all).

calc(N) ->
	case rpc:call('zhangsan@Stephan-MacBook-Pro', fib_service_2, calc, [N]) of
		{ok, Rec} -> Rec;
		{error, timeout} -> 
			case rpc:call('lisi@Stephan-MacBook-Pro', fib_service_2, calc, [N]) of
				{ok, Rec} -> Rec;
				{error, timeout} ->
					rpc:call('zhangsan@Stephan-MacBook-Pro', fib_service_2, calc, [N]);
				{badrpc, nodedown} ->
					rpc:call('zhangsan@Stephan-MacBook-Pro', fib_service_2, calc, [N])
			end;
		{badrpc, nodedown} ->
			case rpc:call('lisi@Stephan-MacBook-Pro', fib_service_2, calc, [N]) of
				{ok, Rec} -> Rec;
				{error, timeout} ->
					rpc:call('zhangsan@Stephan-MacBook-Pro', fib_service_2, calc, [N]);
				{badrpc, nodedown} ->
					rpc:call('zhangsan@Stephan-MacBook-Pro', fib_service_2, calc, [N])
			end
	end.

%[begin 21 = client:calc(8), timer:sleep(100) end || _ <- lists:seq(1,100)].