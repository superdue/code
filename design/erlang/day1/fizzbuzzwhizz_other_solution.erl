-module(fizzbuzzwhizz_other_solution).
-compile(export_all).

%% rule (integer() -> boolean()), (integer() -> string()) -> (integer() -> {true, string()}|false)

rule(Pred, Action) ->
  fun(I) ->
    case Pred(I) of
      true ->
        {true, Action(I)};
      false ->
        false
      end
    end.

times(I) ->
  fun(X) ->
    case X rem I of
      0 -> true;
      _ -> false
    end
  end.

'OR'(L) ->
  fun(I) ->
    lists:foldl(
      fun(R, Acc) ->
        case Acc of
          false -> R(I);
          _     -> Acc
        end
      end, false, L)
  end.

'AND'(L) ->
  fun(I) ->
    lists:foldl(
      fun(_, false)  -> 
            false;
         (R, {_, S}) -> 
            case R(I) of 
              false   -> false;
              {_, NS} -> {true, lists:concat([S,NS])}
            end
      end, {true, ""}, L)
  end.

contains(S) -> fun(I) -> contains1(S, I) end.

contains1(_, 0) ->
  false;
contains1(S, I) ->
  case I rem 10 of
    S -> true;
    _ -> contains1(S, I div 10)
  end.

spec() ->
  R1_3 = rule(times(3), fun(_) -> "Fizz" end),
  R1_5 = rule(times(5), fun(_) -> "Buzz" end),
  R1_7 = rule(times(7), fun(_) -> "Whizz" end),

  R1 = 'OR'([R1_3, R1_5, R1_7]),
  R2 = 'OR'(['AND'([R1_3, R1_5, R1_7]),
             'AND'([R1_3, R1_5]),
             'AND'([R1_3, R1_7]),
             'AND'([R1_5, R1_7])]),
  R3 = rule(contains(3), fun(_) -> "Fizz" end),
  Rd = rule(fun(_) -> true end, fun(I) -> I end),
  Spec = 'OR'([R3, R2, R1, Rd]),

  [Spec(I) || I <- lists:seq(1,20)].


