# -*- encoding=utf-8 -*-

# 修改外部嵌套函数名字空间

from ctypes import pythonapi, py_object
from sys import _getframe

def nonlocal(**kwargs):
	f = _getframe(2)	# 通过 _getframe() 来获取外部函数堆栈帧名字空间，存在一些限制。
	                  # 因为拿到是调用者，而不一定是函数创建者。
	ns = f.f_locals
	ns.update(kwargs)
	pythonapi.PyFrame_LocalsToFast(py_object(f), 0)

def enclose():
	x = 10

	def test():
		nonlocal(x = 1000)

	test()
	print x

enclose()