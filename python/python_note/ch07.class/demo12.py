class User(object):
    def abc(self):
        print "User.abc"

class Manager(User):
    @staticmethod
    def abc():
        print "Manager.static.abc"

    def test(self):
        self.abc()
        User.abc(self)

Manager().test()