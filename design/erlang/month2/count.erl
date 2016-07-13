-module(count).
-compile(export_all).

%% set S1 is a subset of set S2
sub([], _S2) -> true;
sub([E|T], S2) ->
  lists:member(E, S2) andalso sub(T, S2).

%
% a------b
%

test() ->
  P1 = $a,
  P2 = $b,
  Line = [$a, $b],
  true = sub([P1, P2], Line),
  ok.

%
%          d
%         /
%        /
% a-----c------b
%

test2() ->
  P1 = $a,
  P2 = $c,
  P3 = $b,
  P4 = $d,
  Line1 = [$a, $c, $b],
  Line2 = [$c, $d],
  true = sub([P1, P2], Line1),
  true = sub([P2, P3], Line1),
  false = sub([P2, P4], Line1),
  true = sub([P2, P4], Line2),
  ok.

test3() ->
  P1 = $a,
  P2 = $c,
  P3 = $b,
  P4 = $d,
  Line1 = [$a, $c, $b],
  Line2 = [$c, $d],
  true  = sub([P1, P2, P3], Line1),
  false = sub([P1, P2, P3], Line2),
  false = sub([P1, P2, P4], Line1),
  false = sub([P1, P2, P4], Line2),
  ok.

%
%       c
%      /  \
%     /    \ 
%    a------b
%     

belong(_S1, []) -> false;
belong(S1, [E|T]) ->
  sub(S1, E) orelse belong(S1, T).

on_a_line(P1, P2, P3) ->
  belong([P1, P2, P3], lines()).

connected(P1, P2) ->
  belong([P1, P2], lines()).

%lines() ->
%  Line1 = [$a, $b],
%  Line2 = [$a, $c],
%  Line3 = [$b, $c],
%  [Line1, Line2, Line3].

triangle([P1, P2, P3]) ->
  connected(P1, P2) andalso
  connected(P1, P3) andalso
  connected(P2, P3) andalso
  (not on_a_line(P1, P2, P3)).

test4() ->
  P1 = $a,
  P2 = $b,
  P3 = $c,
  true = triangle([P1, P2, P3]).

%
%       c
%      / |\
%     /  | \
%    /   |  \
%   a----d---b
%      
%

%lines() ->
%  Line1 = [$a, $d, $b],
%  Line2 = [$a, $c],
%  Line3 = [$b, $c],
%  Line4 = [$c, $d],
%  [Line1, Line2, Line3, Line4].

%line2() ->
%  ["adb", "ac", "bc", "cd"]

lines() ->
  ["abc", "adef", "aghi", "ajk", "bdgj", "cehj", "cfik"].

test5() ->
  P1 = $a,
  P2 = $b,
  P3 = $c,
  P4 = $d,
  true  = triangle([P1, P2, P3]),
  false = triangle([P1, P2, P4]),
  true  = triangle([P1, P3, P4]),
  true  = triangle([P2, P3, P4]),
  ok.

%% combinations of a set.
comb(L, 1) ->
  [[I] || I <- L];
comb(L, N) when length(L) =:= N ->
  [L];
comb([H|T], N) ->
  C1 = comb(T, N-1),
  C2 = comb(T, N),
  C2 ++ [[H|I] || I <- C1].

%points() ->
%  P1 = $a,
%  P2 = $b,
%  P3 = $c,
%  P4 = $d,
%  [P1, P2, P3, P4].

%points2() ->
%  "abcd"

points() ->
  "abcdefghijk".

triple_points() ->
  comb(points(), 3).

count() ->
  Triples = triple_points(),
  count(Triples, {0,[]}).

count([], {N,L}) -> {N,L};
count([H|T], {N,L}) ->
  case triangle(H) of
    true ->
      count(T, {N+1, [H | L]});
    false ->
      count(T, {N,L})
  end.

test6() ->
  %AllTriplePoints = triple_points(),
  %count(AllTriplePoints).
  count().

%
%
% a
% |
% |
% |
% b  d  g   j
% |
% |       h
% |     e
% |
% |
% c       c     i       k
