# -*- encoding:utf-8 -*-

def test():
	if False:
		x = 10	# x是locals名字，后面出错也就正常了。
	print x

test()