-module(resource).
-compile(export_all).

execute(RID, Pred, Action, N) ->
  RID ! {self(), {execute, Pred, Action, N}},
  receive
    {ok, R} -> R
  end.

execute(RID, V1, V2) ->
  RID ! {self(), {execute, V1, V2}},
  receive
    {ok, R} -> R
  end.

%% APIs

create_atom() ->
  spawn(fun() -> atom_loop() end).

create_and() ->
  spawn(fun() -> and_loop() end).

create_or() ->
  spawn(fun() -> or_loop() end).

or_loop() ->
  receive
    {From, {execute, V1, V2}} ->
      case V1 of
        {true, S1} ->
          From ! {ok, {true, S1}};
        false ->
          From ! {ok, V2}
      end,
      or_loop()
  end.

atom_loop() ->
  receive
    {From, {execute, Pred, Action, N}} ->
      case Pred(N) of
        true ->
          From ! {ok, {true, Action(N)}};
        false ->
          From ! {ok, false}
      end,
      atom_loop()
  end.

and_loop() ->
  receive
    {From, {execute, V1, V2}} ->
      case V1 of
        {true, S1} ->
          case V2 of
            {true, S2} ->
              From ! {ok, {true, S1 ++ S2}};
            false ->
              From ! {ok, false}
          end;
        false ->
          From ! {ok, false}
      end,
      and_loop()
  end.