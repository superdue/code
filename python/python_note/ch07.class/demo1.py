class User: pass

print(type(User))
print(issubclass(User, object))

__metaclass__ = type

class Manager: pass
print(type(Manager))
print(issubclass(Manager, object))
