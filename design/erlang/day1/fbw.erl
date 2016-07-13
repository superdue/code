-module(fzw).
-compile(export_all).

-type pred() :: fun((integer()) -> boolean()).

-type action_result() :: string() | integer().

-type action() :: fun((integer()) -> action_result().

-type rule() :: {'ATOM', pred(), action()} |
                {'AND',  rule(), rule()}   |
                {'OR',   rule(), rule()}.

-type rule_result() :: {true, string()} | false.

-spec 'ATOM'(pred(), action()) -> rule().
'ATOM'(Pred, Action) ->
