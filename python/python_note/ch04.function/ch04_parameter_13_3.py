def test():
	exec "x = 10"
	locals()["x"] = 20
	print x

test()