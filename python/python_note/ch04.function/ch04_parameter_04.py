def test(*args, **kwargs):
	print args
	print kwargs

test(1, "a", x = "x", y = "y")
test(1)
test(x = "x")