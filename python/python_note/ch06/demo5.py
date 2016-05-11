def main():
    import test
    from test import add, _x
    from sys import *            # SyntaxWarning: import * only allowed at module level

    print("hello")

main()