class User(object):
    def print_id(self):
        print hex(id(self))

u = User()
print(u.print_id)
u.print_id()
print(User.print_id)
User.print_id(u)