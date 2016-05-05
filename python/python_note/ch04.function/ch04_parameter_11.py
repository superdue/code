x = 100
print hex(id(x))

def test():
	global x, y
	x = 1000
	y = "Hello, World!"
	print hex(id(x))

test()
print globals()["x"]
print globals()["y"]
print x, y
