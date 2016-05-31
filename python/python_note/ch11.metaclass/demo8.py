class SealedClassMeta(type):
    _types = set()

    def __init__(cls, name, bases, attrs):
        if cls._types & set(bases):
            raise SyntaxError("Cannot inherit from a sealed class!")
        cls._types.add(cls)

class A(object):
    __metaclass__ = SealedClassMeta


class B(A): pass