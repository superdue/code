-module(scheduler).
-compile(export_all).

start(Progs, Rids) ->
  NewProgs = [{Name, Codes, N, []} || {Name, Codes, N} <- Progs],
  fbw:do_schedule3(NewProgs, Rids).