def test():
	exec ""
	locals()["x"] = 10
	print x

test()