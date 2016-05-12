class User(object):
    table = "t_user"

    def __init__(self, name, age):
        self.name = name
        self.age = age

u1 = User("user1", 20)
print(u1.__dict__)

u2 = User("user2", 30)
print(u2.__dict__)

for k, v in User.__dict__.items():
    print "{0:12} = {1}".format(k, v)

u1.x = 100
print(u1.__dict__)
print(u2.__dict__)

print(User.table)
print(u1.table)
print(u2.table)

u1.table = "xxx"
print(u1.table)
print(u2.table)
