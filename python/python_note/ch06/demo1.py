import sys, types

m = types.ModuleType("sample", "sample module.")
print(m)

print(m.__dict__)
print("sample" in sys.modules)

def test(): print "test..."

m.test = test
m.test()

def test(): print "test:", __name__
test()

m.test = test
m.test()

print(sys.modules)