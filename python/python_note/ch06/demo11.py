from os.path import abspath, join

__path__ = ["/Users/stephansun/Desktop/"]

subdirs = lambda *dirs: [abspath(join(__path__[0], sub)) for sub in dirs]

__path__ = subdirs("a", "b")

print(__path__)

