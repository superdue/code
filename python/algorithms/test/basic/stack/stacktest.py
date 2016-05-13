from pythonds.basic.stack import Stack

s = Stack()

assert(s.isEmpty())
s.push(4)
s.push('dog')
assert(s.peek() is 'dog')
s.push(True)
assert(s.size() is 3)
assert(s.isEmpty() is False)
s.push(8.4)
assert(s.pop() is 8.4)
assert(s.pop() is True)
assert(s.size() is 2)
