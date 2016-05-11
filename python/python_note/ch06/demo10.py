import imp

x = imp.load_source("add", "./add.py")
y = imp.load_source("add", "./add.py")

print(hex(id(x)))
print(hex(id(y)))
