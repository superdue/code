add = lambda x, y = 0: x + y
print add(1, 2)
print add(3)
print range(10)
print 0 and None or 0
print 1 and None or 1
print 0 and None or 2
print 1 and None or 3
print 0 and None or 4
print 1 and None or 5
print map(lambda x: x % 2 or x, range(10))