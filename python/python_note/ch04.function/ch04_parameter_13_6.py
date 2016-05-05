# -*- encoding:utf-8 -*-

# 编译器作用域不受执行器条件影响

def test():
	if False:
		global x	# 尽管此语句永不执行，但编译器依然会将x当作globals名字
	x = 10
	print globals()["x"] is x

test()
print x