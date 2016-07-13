%% APIs
-spec begin_trans(UserId::integer(), N::integer()) -> ok | {error, Reason::term()}.
-spec op(UserId::integer(), Op::atom()) -> ok | {error, Reason::term()}.
-spec end_trans(UserId::integer()) -> {ok, integer()}.

