-module(op_service_final).
-compile(export_all).

oop(State) ->
	receive
		{op_result, UserId, Result} ->
			NewState = handle_op_result(UserId, Result, State),
			loop(NewState);
		{'DOWN', _MonitorRef, process, P, _Reason} ->
			NewState = handle_monitor_down(P, State),
			loop(NewState);
		{Ref, From, Message} ->
			NewState = handle_message(Message, {Ref, From}, State),
			loop(NewState)
	end.
	
handle_message({begin_trans, UserId, N}, {Ref, From}, #state{users=Users}=State) ->
	case lists:keyfind(UserId, #user_info.uid, Users) of
		false ->
			NewUsers = [#user_info{nm=N,uid=UserId}|Users],
			From ! {Ref, ok},
			State#state{users=NewUsers};
		#user_info{} ->
			From ! {Ref, {error, duplicated_trans}},
			State
	end;
