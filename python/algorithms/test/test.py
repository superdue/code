class Foo:
    def __init__(self):
        self.pages = random.randrange(1, 21)

import random

class Bar:
    def __init__(self):
        self.pages = random.randrange(1, 21)

a = Foo()
b = Bar()
print(hex(id(b)))
print(b.pages)
print(hex(id(a)))
print(a.pages)