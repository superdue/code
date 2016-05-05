x = 10
print hex(id(x))

def test():
	x = "hi"
	print hex(id(x)), x

test()

print x