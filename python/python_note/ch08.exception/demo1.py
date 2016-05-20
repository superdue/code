class MyContext(object):
    def __init__(self, *args):
        self._data = args

    def __enter__(self):
        print "__enter__"
        return self._data

    def __exit__(self, exc_type, exc_value, traceback):
        if exc_type:
            print("Exception:", exc_value)
        print "__exit__"
        return True

with MyContext(1, 2, 3) as data:
    print data

with MyContext(1, 2, 3):
    raise Exception("data error!")

