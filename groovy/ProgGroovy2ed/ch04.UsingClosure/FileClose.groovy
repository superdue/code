writer = new FileWriter('output.txt')
writer.withWriter { writer ->
	writer.write('a')
}