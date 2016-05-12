class User(object):
    def get_name(self):
        return self.__name

    def set_name(self, value):
        self.__name = value

    def del_name(self):
        del self.__name

    name = property(get_name, set_name, del_name, "help...")

for k, v in User.__dict__.items():
    print "{0:12} = {1}".format(k, v)

u = User()
u.name = "Tom"
print(u.__dict__)
print(u.name)

del u.name
print(u.__dict__)
