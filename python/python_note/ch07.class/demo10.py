class User(object):
    def __new__(cls, *args, **kwargs):
        print("__new__", cls, args, kwargs)
        return object.__new__(cls)

    def __init__(self, name, age):
        print("__init__", name, age)

    def __del__(self):
        print("__del123__")


u = User("Tom", 23)
#del u
