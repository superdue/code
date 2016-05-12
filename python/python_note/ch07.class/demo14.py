class A(object):
    def __init__(self):
        print("A")
        super(A, self).__init__()


class B(object):
    def __init__(self):
        print("B")
        super(B, self).__init__()


class C(A, B):
    def __init__(self):
        A.__init__(self)
        B.__init__(self)

o = C()
print(C.__mro__)