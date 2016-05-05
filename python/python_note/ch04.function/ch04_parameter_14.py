# -*- encoding:utf-8 -*-

def test():
	x = [1,2]
	print hex(id(x))

	def a():
		x.append(3)
		print hex(id(x))

	def b():
		print hex(id(x)), x

	return a, b

a, b = test()
a()
b()

# test在创建 a 和 b 时，将它们所引用的外部对象 x 添加到 func_closure 列表中。
# 因为 x 引用计数增加了，所以就算test堆栈帧没有了，x 对象也不会被回收。

print a.func_closure
print b.func_closure