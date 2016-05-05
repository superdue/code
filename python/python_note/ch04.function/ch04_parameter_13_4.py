x = "abc"

def test():
	print x
	exec "x = 10" # 如果函数中包含exec语句，编译器生成的名字指令会依照LEGB规则搜索
	print x

test()

