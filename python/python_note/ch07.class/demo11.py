class User(object):
    table = "t_user"

    def __init__(self, name, age):
        self._name = name
        self._age = age

    def test(self):
        print(self._name, self._age)

class Manager(User):
    table = "t_manager"

    def __init__(self, name, age, title):
        User.__init__(self, name, age)
        self._title = title

    def kill(self):
        print "213..."

m = Manager("Tom", 40, "CXO")
print(m.__dict__)

for k, v in Manager.__dict__.items():
    print("{0:5} = {1}".format(k, v))

print(Manager.__base__)
print(User.__subclasses__())

print(issubclass(Manager, User))
print(issubclass(Manager, object))
print(isinstance(m, Manager))
print(isinstance(m, object))