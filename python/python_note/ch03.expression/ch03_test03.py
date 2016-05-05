# -*- encoding: UTF-8 -*-
d = {}; d["a"] = 1; print(d.items())
for k, v in d.items():
	print k, v # 注意这里是Python 2的都有写法，如果写成print(k, v)，打印出来的结果会不一样的，带有括号。
	print(k, v)
