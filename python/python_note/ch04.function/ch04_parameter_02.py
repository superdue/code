def test(x, ints=[]):
	ints.append(x)
	return ints

print test(1)
print test(2)

print test(1, [])
print test(3)
