-module(record).
-compile(export_all).

-record(customer,{id,name}).

test() ->
	C1 = #customer{id=1},
	C2 = #customer{},
	C3 = #customer{id=2,name="N"},
	{C1,C2,C3}.
	
test2() ->
	2 = #customer.id,
	3 = #customer.name.
	
test3() ->
	C1 = #customer{id=1},
	undefined = C1#customer.name,
	1 = C1#customer.id,
	pass.
	
test4() ->
	C1 = #customer{id=1,name="N"},
	C2 = C1#customer{id=3},
	1 = C1#customer.id,
	3 = C2#customer.id,
	"N" = C1#customer.name,
	"N" = C2#customer.name,
	pass.
	
get_name(#customer{name=N}) ->
	N.
	
test5() ->
	"foo" = get_name(#customer{id=1,name="foo"}),
	C = #customer{id=1,name="FOO"},
	#customer{name=N} = C,
	"FOO" = N,
	pass.