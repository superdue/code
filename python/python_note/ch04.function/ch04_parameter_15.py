def test(x):
	def a():
		print x

	print hex(id(a))
	return a

a1 = test(100)
a2 = test("hi")
a1()
a2()

print a1.func_closure
print a2.func_closure

print a1.func_code is a2.func_code	# 被内部函数 a1 引用的名字

print test.func_code.co_cellvars
print a1.func_code.co_freevars			# a1 应用外部函数 test 中的名字
print a2.func_code.co_freevars