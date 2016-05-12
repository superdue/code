class User(object):
    def a(self): pass

    @staticmethod
    def b(): pass

    @classmethod
    def c(cls): pass

print(User.a)
print(User.b)
print(User.c)

User.b()
User.c()
