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
'ATOM'(Pred, Action) ->
  {'ATOM', Pred, Action}.

-spec 'AND'(rule(), rule()) -> rule().
'AND'(R1, R2) ->
  {'AND', R1, R2}.

-spec 'AND3'(rule(), rule(), rule()) -> rule().
'AND3'(R1, R2, R3) ->
  {'AND', R1, 'AND'(R2, R3)}.

-spec 'OR'(rule(), rule()) -> rule().
'OR'(R1, R2) ->
  {'OR', R1, R2}.

-spec 'OR3'(rule(), rule(), rule()) -> rule().
'OR3'(R1, R2, R3) ->
  {'OR', R1, 'OR'(R2, R3)}.

-spec 'OR4'(rule(), rule(), rule(), rule()) -> rule().
'OR4'(R1, R2, R3, R4) ->
  {'OR', R1, 'OR3'(R2, R3, R4)}.

-spec apply_rule(rule(), integer()) -> rule_result().
apply_rule({'ATOM', Pred, Action}, N) ->
  case Pred(N) of
    true  -> {true, Action(N)};
    false -> false
  end;
apply_rule({'AND', R1, R2}, N) ->
  case apply_rule(R1, N) of
    {true, Res1} ->
      case apply_rule(R2, N) of
        {true, Res2} ->
          {true, Res2 ++ Res1};
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

-spec p_apply_rule(rule(), integer()) -> rule_result().
p_apply_rule({'ATOM', Pred, Action}, N) ->
  case Pred(N) of
    true  -> {true, Action(N)};
    false -> false
  end;
p_apply_rule({'AND', R1, R2}, N) ->
  S = self(),
  spawn(fun() -> S ! {r1, p_apply_rule(R1, N)} end),
  spawn(fun() -> S ! {r2, p_apply_rule(R2, N)} end),
  receive
    {r1, {true, Res1}} ->
      receive
        {r2, {true, Res2}} ->
          {true, Res2 ++ Res1};
        {_, false} ->
          false
      end;
    {_, false} ->
      false
  end;
p_apply_rule({'OR', R1, R2}, N) ->
  case apply_rule(R1, N) of
    {true, Res} ->
      {true, Res};
    false ->
      apply_rule(R2, N)
  end.

-spec p2_apply_rule(rule(), integer(), rule()) -> rule_result().
p2_apply_rule({'ATOM', Pred, Action}, N, Owner) ->
  case Pred(N) of
    true  -> Owner ! {self(), {true, Action(N)}};
    false -> Owner ! {self(), false}
  end;
p2_apply_rule({'AND', R1, R2}, N, Owner) ->
  S = self(),
  spawn(fun() -> p2_apply_rule(R1, N, S) end),
  spawn(fun() -> p2_apply_rule(R2, N, S) end),
  receive
    {_, {true, Res1}} ->
      receive
        {_, {true, Res2}} ->
          Owner ! {S, {true, Res2 ++ Res1}};
        {_, false} ->
          Owner ! {S, false}
      end;
    {_, false} ->
      Owner ! {S, false}
  end;
p2_apply_rule({'OR', R1, R2}, N, Owner) ->
  S = self(),
  P1 = spawn(fun() -> p2_apply_rule(R1, N, S) end),
  P2 = spawn(fun() -> p2_apply_rule(R2, N, S) end),
  receive
    {P1, {true, Res}} ->
      Owner ! {S, {true, Res}};
    {P1, false} ->
      receive
        {P2, Res2} ->
          Owner ! {S, Res2}
      end
  end.

-spec times(integer()) -> boolean().
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

  % [apply_rule(Spec, I) || I <- lists:seq(1, 20)].
  [p_apply_rule(Spec, I) || I <- lists:seq(1, 20)].

test2() ->
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

  S = self(),
  F = fun(I) ->
        spawn(fun() -> p2_apply_rule(Spec, I, S) end),
        receive
          {_, Res} ->
            Res
        end
      end,

  [F(I) || I <- lists:seq(1, 20)].

pmap(Fun, L) ->
  S = self(),
  Pids = [spawn(fun() -> S ! {self(), Fun(I)} end) || I <- L],
  collect_result(Pids, []).

collect_result([], R) -> lists:reverse(R);
collect_result([H|T], R) ->
  receive
    {H, V} ->
      collect_result(T, [V|R])
  end.

test_p() ->
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

  pmap(fun(I) -> p_apply_rule(Spec, I) end, lists:seq(1, 20)).

pe_apply_rule({'ATOM', Pred, Action}, N, Owner) ->
  F = fun() ->
        S = self(),
        Pid = spawn(fun() ->
                        timer:sleep(500),
                        case Pred(N) of
                          true ->
                             Owner ! {S, {true, Action(N)}};
                          false ->
                             Owner ! {S, false}
                        end
                    end),
        receive
          stop ->
            io:format("atom ~p be killed~n", [Pid]),
            exit(Pid, kill)
        end
      end,
  spawn(F);

pe_apply_rule({'AND', R1, R2}, N, Owner) ->
  F = fun() ->
        S = self(),
        Pid1 = pe_apply_rule(R1, N, S),
        Pid2 = pe_apply_rule(R2, N, S),
        receive
          {Pid1, {true, Res1}} ->
            receive
              {Pid2, {true, Res2}} ->
                Owner ! {S, {true, Res1++Res2}};
              {_, false} ->
                Owner ! {S, false}
            end;
          {Pid1, false} ->
            Owner ! {S, false},
            Pid2 ! stop;
          {Pid2, false} ->
            Owner ! {S, false},
            Pid1 ! stop;
          stop ->
            io:format("and ~p be killed~n", [S]),
            Pid1 ! stop,
            Pid2 ! stop
        end
      end,
  spawn(F);

pe_apply_rule({'OR', R1, R2}, N, Owner) ->
  F = fun() ->
        S = self(),
        Pid1 = pe_apply_rule(R1, N, S),
        Pid2 = pe_apply_rule(R2, N, S),
        receive
          {Pid1, {true, Res1}} ->
            Owner ! {S, {true, Res1}},
            Pid2 ! stop;
          {Pid1, false} -> %% It's Pid1, not Pid2 !!!
            receive
              {Pid2, Res2} ->
                Owner ! {S, Res2}
              end;
          stop ->
            io:format("or ~p be killed~n", [S]),
            Pid1 ! stop,
            Pid2 ! stop
        end
      end,
  spawn(F).

test_pe() ->
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

  S = self(),
  F = fun(I) ->
        pe_apply_rule(Spec, I, S),
        receive
          {_, Res} ->
            Res
        end
      end,

  [F(I) || I <- lists:seq(1, 20)].
  

test_pe_pmap() ->
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
  pmap(fun(I) ->
        S = self(),
        pe_apply_rule(Spec, I, S),
        receive
          {_, V} ->
            V
        end
       end, lists:seq(1, 20)).

%% N ways to tackle the problem of memory

%% VM

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
    {{true, S1}, {true, S2}} ->
      eval(Rest, N, [{true, S2++S1}|Stack]); %% notice here.
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

compile(Prog) ->
  compile(Prog, []).

compile({'ATOM', Pred, Action}, Insts) ->
  Insts ++ [{op_atom, Pred, Action}];
compile({'AND', R1, R2}, Insts) ->
  I1 = compile(R1, []),
  I2 = compile(R2, []),
  Insts ++ I1 ++ I2 ++ [op_and];
compile({'OR', R1, R2}, Insts) ->
  I1 = compile(R1, []),
  I2 = compile(R2, []),
  Insts ++ I1 ++ I2 ++ [op_or].

test_eval() ->
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

  [eval(compile(Spec, []), I) || I <- lists:seq(1, 20)].

%%

schedule(Progs) ->
  NewProgs = [{Name, Codes, N, []} || {Name, Codes, N} <- Progs],
  do_schedule(NewProgs).

do_schedule([]) ->
  [];
do_schedule([{ProgName, [], N, Stack}|T]) ->
  io:format("prog[~p] result: ~p -> ~p  ---~n", [ProgName, N, Stack]),
  do_schedule(T);
do_schedule([{ProgName, Codes, N, Stack}|T]) ->
  timer:sleep(500), %% 2000
  io:format("scheduling[~p]:~n codes: ~p~n stack: ~p~n", [ProgName, Codes, Stack]),
  {Rest, N, Stack2} = eval2(Codes, N, Stack),
  do_schedule(T ++ [{ProgName, Rest, N, Stack2}]).

eval2([], N, [Val]) -> {[], N, Val};
eval2([{op_atom, Pred, Action}|Rest], N, Stack) ->
  case Pred(N) of
    true -> {Rest, N, [{true, Action(N)} | Stack]};
    false -> {Rest, N, [false|Stack]}
  end;
eval2([op_and|Rest], N, [V1, V2|Stack]) ->
  case {V1, V2} of
    {{true, S1}, {true, S2}} ->
      {Rest, N, [{true, S2++S1}|Stack]}; %% notice here.
    _ ->
      {Rest, N, [false|Stack]}
  end;
eval2([op_or|Rest], N, [V1, V2|Stack]) ->
  case {V2, V1} of
    {{true, S1}, _} ->
      {Rest, N, [{true, S1}|Stack]};
    {_, {true, S2}} ->
      {Rest, N, [{true, S2}|Stack]};
    _ ->
      {Rest, N, [false|Stack]}
  end.

test_s() ->
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
  schedule([{prog1, compile(Spec, []), 13},
            {prog2, compile(Spec, []), 17}
           ]),
  ok.

%%

eval3(_, [], N, [Val]) ->
  {[], N, Val};
eval3({AtomRid, _, _}, [{op_atom, Pred, Action}|Rest], N, Stack) ->
  Val = resource:execute(AtomRid, Pred, Action, N),
  {Rest, N, [Val | Stack]};
eval3({_, AndRid, _ }, [op_and|Rest], N, [V1, V2|Stack]) ->
  Val = resource:execute(AndRid, V1, V2),
  {Rest, N, [Val | Stack]};
eval3({_, _, OrRid}, [op_or|Rest], N, [V1, V2|Stack]) ->
  Val = resource:execute(OrRid, V2, V1),
  {Rest, N, [Val | Stack]}.

schedule3(Progs) ->
  NewProgs = [{Name, Codes, N, []} || {Name, Codes, N} <- Progs],
  AtomRid = resource:create_atom(),
  AndRid  = resource:create_and(),
  OrRid   = resource:create_or(),
  do_schedule3(NewProgs, {AtomRid, AndRid, OrRid}).

do_schedule3([], _) ->
  [];
do_schedule3([{ProgName, [], N, Stack}|T], Rids) ->
  io:format("prog[~p] result: ~p -> ~p   ---~n", [ProgName, N, Stack]),
  do_schedule3(T, Rids);
do_schedule3([{ProgName, Codes, N, Stack}|T], Rids) ->
  timer:sleep(500), %% 2000
  io:format("scheduling[~p]:~n codes: ~p~n stack: ~p~n", [ProgName, Codes, Stack]),

  {Rest, N, Stack2} = eval3(Rids, Codes, N, Stack),
  do_schedule3(T ++ [{ProgName, Rest, N, Stack2}], Rids).

test_s3() ->
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
  schedule3([{prog1, compile(Spec, []), 13},
             {prog2, compile(Spec, []), 17}
            ]),
  ok.

test_scheduler() ->
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

  R4 = 'OR4'(R3, R2, R1, Rd),

  AtomRid = resource:create_atom(),
  AndRid  = resource:create_and(),
  OrRid   = resource:create_or(),
  Rids = {AtomRid, AndRid, OrRid},

  scheduler:start([{prog1, compile(R3), 15},
                   {prog2, compile(R4), 17}
                  ], 
                  Rids),
  ok.

test_multi_scheduler() ->
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
  
  Sch1 = scheduler:start(111, {1,2,3}),
  scheduler:load(Sch1, [{prog1, compile(Sepc, []), 13, []},
                        {prog2, compile(Spec, []), 21, []}]),

  Sch2 = scheduler:start(112, {4,5,6}),
  scheduler:load(Sch2, [{prog3, compile(Sepc, []), 53, []},
                        {prog4, compile(Spec, []), 81, []}]),