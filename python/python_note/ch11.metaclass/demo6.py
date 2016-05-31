def inject_meta(name, bases, attrs):
    t = type.__new__()
    t.s = "Hello, World!"
    return t


class Data(object):
    __metaclass__ = inject_meta


print(Data.__metaclass__)
print(Data.s)