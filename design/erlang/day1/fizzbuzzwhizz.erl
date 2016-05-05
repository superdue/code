-module(fizzbuzzwhizz).
-compile(export_all).

times(N) ->
  fun(M) ->
    M rem N =:= 0
  end.

contains(N) ->
  fun(M) ->
    P1 = M rem 10,
    P2 = M div 10 rem 10,
    P3 = M div 10 div 10 rem 10,
    P1 =:= N orelse P2 =:= N orelse P3 =:= N
  end.

rule(Pred, Trans) ->
  fun(N) ->
    case Pred(N) of
      true  -> {true, Trans(N)};
      false -> false
    end
  end.

'AND'(R5) ->
  'AND'(R5, fun(A1, A2) -> A1++A2 end, "").

'AND'([], _Op, Acc) -> fun(_N) -> {true, Acc} end;
'AND'([R|Rest], Op, Acc) ->
  fun(N) ->
    case R(N) of
      {true,A} -> ('AND'(Rest, Op, Op(Acc, A)))(N);
      false    -> false
    end
  end.

'OR'(R5) ->
  'OR'(R5, fun(A1, A2) -> A1++A2 end, "").

'OR'([], _Op, Acc) -> fun(_N) -> false end;
'OR'([R|Rest], Op, Acc) ->
  fun(N) ->
    case R(N) of
      {true,A} -> {true, Op(Acc, A)};
      false    -> ('OR'(Rest, Op, Acc))(N)
    end
  end.


test1() ->
  R1_3 = rule(times(3), fun(_) -> "Fizz" end),
  R1_5 = rule(times(5), fun(_) -> "Buzz" end),
  R1_7 = rule(times(7), fun(_) -> "Whizz" end),
  
  R1 = 'OR'([R1_3, R1_5, R1_7]),
  
  R2 = 'OR'(['AND'([R1_3, R1_5, R1_7]),
             'AND'([R1_3, R1_5]),
             'AND'([R1_3, R1_7]),
             'AND'([R1_5, R1_7])]),
  
  R3 = rule(contains(3), fun(_) -> "Fizz" end),
  
  Rd = rule(fun(_) -> true end, fun(N) -> integer_to_list(N) end),
  
  Spec = 'OR'([R3, R2, R1, Rd]),
  
  [Spec(I) || I<-lists:seq(1,20)].
