#def test(a, b=0, c): pass

def test(a, b=0, *args, **kwargs):
	print a, b
	print args
	print kwargs

test(1, 2, "a", "b", "c", x = 100, y = 200)