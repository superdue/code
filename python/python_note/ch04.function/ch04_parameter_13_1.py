# -*- encoding:utf-8 -*-

def test():
	locals()["x"] = 10 # 名字作用域是在编译时确定的，编译时并不存在 locals x 这个名字
	print x

test()