def test():
	"""
	func help
	"""
	pass

print(test.__doc__)

class User(object):
	"""User Model"""

	def __init__(self):
		"""user.__init__"""
		pass

print(User.__doc__)
print(User.__init__.__doc__)