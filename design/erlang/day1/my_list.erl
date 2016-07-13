-module(my_list).
-compile(export_all).

head([H|_]) -> H.

sum([]) -> 0;
sum([H|T]) -> H + sum(T).

len([]) -> 0;
len([_|T]) -> 1 + len(T).

double(L) -> map(fun(x) -> x*2 end, L).

add_1(L) -> map(fun(x) -> x+1 end, L).

map(_Op, []) -> [];
map(Op, [H|T]) -> [Op(H)|map(Op, T)].





times(N) -> fun(M) -> M rem N =:= 0 end.

contains(N, M) when M =:= 0 -> false;
contains(N, M) -> (N =:= M rem 10) or contains(N, M div 10).
