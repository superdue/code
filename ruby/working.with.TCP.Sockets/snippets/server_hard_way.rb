require 'socket'

server = Socket.new(:INET, :STREAM)
addr = Socket.pack_sockaddr_in(481, '0.0.0.0')
server.bind(addr)
server.listen(5)
