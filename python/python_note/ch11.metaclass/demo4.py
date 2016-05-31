class InjectMeta(type):
    def __new__(cls, *args, **kwargs):
        t = type.__new__(cls, *args)

        def print_id(self): print(hex(id(self)))
        t.print_id = print_id                     # 为类型对象添加实例方法
        t.s = "Hello, World!"                     # 添加静态字段

        return t


class Data(object):
    __metaclass__ = InjectMeta


print(Data.__metaclass__)
print(Data.__class__)
print(dir(Data))
print(Data.s)
Data().print_id()
