-module(fbw).
-compile(export_all).

-type pred() :: fun((integer()) -> boolean()).
-type action_result() :: string() | integer().
-type action() :: fun((integer()) -> action_result()).
-type rule() :: {'ATOM', pred(), action()} |
                {'AND',  rule(), rule()}   |
				{'OR',   rule(), rule()}.
-type rule_result() :: {true, string()} | false.

-spec 'ATOM'(pred(), action()) -> rule().
'ATOM'(Pred, Action) -> {'ATOM', Pred, Action}.

-spec 'AND'(rule(), rule()) -> rule().
'AND'(R1, R2) -> {'AND', R1, R2}.

-spec 'AND3'(rule(), rule(), rule()) -> rule().
'AND3'(R1, R2, R3) -> {'AND', R1, 'AND'(R2, R3)}.

-spec 'OR'(rule(), rule()) -> rule().
'OR'(R1, R2) -> {'OR', R1, R2}.

-spec 'OR3'(rule(), rule(), rule()) -> rule().
'OR3'(R1, R2, R3) -> {'OR', R1, 'OR'(R2, R3)}.

-spec 'OR4'(rule(), rule(), rule(), rule()) -> rule().
'OR4'(R1, R2, R3, R4) -> {'OR', R1, 'OR3'(R2, R3, R4)}.

-spec apply_rule(rule(), integer()) -> rule_result().
apply_rule({'ATOM', Pred, Action}, N) ->
	case Pred(N) of
		true -> {true, Action(N)};
		false -> false
	end;
apply_rule({'AND', R1, R2}, N) ->
	case apply_rule(R1, N) of
		{true, Res1} ->
			case apply_rule(R2, N) of
				{true, Res2} ->
					{true, Res1 ++ Res2};
				false ->
					false
			end;
		false ->
			false
	end;
apply_rule({'OR', R1, R2}, N) ->
	case apply_rule(R1, N) of
		{true, Res} ->
			{true, Res};
		false ->
			apply_rule(R2, N)
	end.
	
-spec times(integer()) -> fun((integer()) -> boolean()).
times(N) ->
	fun(M) ->
		M rem N =:= 0
	end.
	
-spec contains(integer()) -> boolean().
contains(N) ->
	fun(M) ->
		P1 = M rem 10,
		P2 = M div 10 rem 10,
		P3 = M div 10 div 10 rem 10,
		P1 =:= N orelse P2 =:= N orelse P3 =:= N
	end.
	
test() ->
	R1_3 = 'ATOM'(times(3), fun(_) -> "Fizz" end),
	R1_5 = 'ATOM'(times(5), fun(_) -> "Buzz" end),
	R1_7 = 'ATOM'(times(7), fun(_) -> "Whizz" end),
	
	R1 = 'OR3'(R1_3, R1_5, R1_7),
	R2 = 'OR4'('AND3'(R1_3, R1_5, R1_7),
	           'AND'(R1_3, R1_5),
			   'AND'(R1_3, R1_7),
			   'AND'(R1_5, R1_7)),
	R3 = 'ATOM'(contains(3), fun(_) -> "Fizz" end),
	Rd = 'ATOM'(fun(_) -> true end, fun(N) -> N end),
	
	Spec = 'OR4'(R3, R2, R1, Rd),
	
	[apply_rule(Spec, I) || I<-lists:seq(1, 20)].
	
eval(Insts, N) ->
	eval(Insts, N, []).
	
eval([], _, [Val]) -> Val;
eval([{op_atom, Pred, Action}|Rest], N, Stack) ->
	case Pred(N) of
		true -> eval(Rest, N, [{true, Action(N)} | Stack]);
		false -> eval(Rest, N, [false|Stack])
	end;
eval([op_and|Rest], N, [V1, V2|Stack]) ->
	case {V1, V2} of
		{{true, S1},{true, S2}} ->
			eval(Rest, N, [{true, S1++S2}|Stack]);
		_ ->
			eval(Rest, N, [false|Stack])
	end;
eval([op_or|Rest], N, [V1, V2|Stack]) ->
	case {V2, V1} of
		{{true, S1}, _} ->
			eval(Rest, N, [{true, S1}|Stack]);
		{_, {true, S2}} ->
			eval(Rest, N, [{true, S2}|Stack]);
		_ ->
			eval(Rest, N, [false|Stack])
	end.
	