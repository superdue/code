def test(a, *args, **kwargs):
	s = "Hello, World!"
	print locals()

test(1, "a", "b", x = 10, y = "hi")