class A(object):
    def test(self):
        print("a")

class B(A):
    def test(self):
        super(self.__class__, self).test()
        print "b"

class C(B):
    pass

C().test()