def test():
	x = 10

	locals()["x"] = 100 # 解释器会将locals名字复制到FAST区域来优化访问速度
	                    # 因此直接修改locals名字空间并不会影响该区域，解决方法还是用exec
	print x

	exec "x = 100"
	print x

test()