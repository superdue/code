class User(object):
    @property
    def name(self):
        return self.__name

    @name.setter
    def name(self, value):
        self.__name = value

    @name.deleter
    def name(self):
        del self.__name


u = User()
u.name = "Tom"

print(u.__dict__)
print(u.name)
del u.name
print(u.__dict__)

for k, v in User.__dict__.items():
    print("{0:12} = {1}".format(k, v))