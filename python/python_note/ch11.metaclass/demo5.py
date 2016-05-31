class InjectMeta(type):
    def __new__(cls, *args, **kwargs):
        print(cls)      # class
        print(args[0])  # name
        print(args[1])  # bases
        print(args[2])  # attrs
        return type.__new__(cls, *args)

    def __init__(cls, name, bases, attrs):
        print(cls)
        print(name)
        print(bases)
        print(attrs)
        type.__init__(cls, name, bases, attrs)


class Data(object):
    __metaclass__ = InjectMeta
    x = 1
    def test(self): pass

