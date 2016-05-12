class User(object):
    __table = "t_user"

    def __init__(self, name, age):
        self.__name = name
        self.__age = age

    def __str__(self):
        return "{0}: {1}, {2}".format(
            self.__table,
            self.__name,
            self.__age
        )

u = User("tom", 20)
print(u.__dict__)