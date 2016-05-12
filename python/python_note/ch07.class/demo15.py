class User(object): pass

u = User()

setattr(u, "name", "tom")
print(u.__dict__)

setattr(User, "table", "t_user")
print(User.table)

print(hasattr(u, "table"))
print(getattr(u, "table", None))

#delattr(u, "table")
delattr(User, "table")
delattr(u, "name")
print(u.__dict__)