# -*- encoding:utf-8 -*-

def test():
	for i in range(3):
		def a():
			print i
		yield a

a, b, c = test()

a(), b(), c()

# test 只是返回函数对象，并没有执行。其次，test 完成 for 循环时，i 已经等于 2，所以执行a, b, c 时，它们所持有的 i 自然也就等于2。
