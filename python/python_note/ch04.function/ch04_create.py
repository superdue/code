def test(name):
	if name == "a":
		def a(): pass
		return a
	else:
		def b(): pass
		return b

print test("a").__name__