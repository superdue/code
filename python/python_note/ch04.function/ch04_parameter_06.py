def test(a, b):
	print a
	print b

d = dict(a = 1, b = 2)
test(*d)

test(**d)

try:
	d = dict(a = 1, b = 2, c = 3)
	test(*d)
except TypeError, e:
	print e

try:
	d = dict(a = 1, b = 2, c = 3)
	test(**d)
except TypeError, e:
	print e