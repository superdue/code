class User(object):
    def __init__(self, uid):
        self._uid = uid

    uid = property(lambda o: o._uid)

    name = property(lambda o: o._name, lambda o, v: setattr(o, "_name", v))

u = User(1)
print(u.uid)
#u.uid = 100

u.name = "Tom"
print(u.name)

print(u._name)



u = User(1)
u.name = "Tom"
print(u.__dict__)

u.__dict__["uid"] = 1000000
u.__dict__["name"] = "xxxxxxxx"
print(u.__dict__)
print(u.uid)
print(u.name)