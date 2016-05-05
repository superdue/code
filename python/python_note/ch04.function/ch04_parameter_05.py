# -*- encoding:utf-8 -*-

def test(a, b, *args, **kwargs):
	print a, b
	print args
	print kwargs

# 可“展开”序列类型和字典，将全部元素当做多个实参使用。如不展开的话，那仅是单个实参对象。
test(*range(1,5), **{"x": "Hello", "y": "World"})