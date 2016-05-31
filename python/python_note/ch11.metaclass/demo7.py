class StaticClassMeta(type):
    def __new__(cls, *args, **kwargs):
        t = type.__new__(cls, *args)

        def ctor(cls, *args, **kwargs):
            raise RuntimeError("Cannot create a instance of the static class!")

        t.__new__ = staticmethod(ctor)

        return t


class Data(object):
    __metaclass__ = StaticClassMeta

Data()