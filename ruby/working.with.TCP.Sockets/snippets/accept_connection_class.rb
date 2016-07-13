require 'socket'

server = Socket.new(:INET, :STREAM)
addr = Socket.pack_sockaddr_in(4481, '0.0.0.0')
server.bind(addr)
server.listen(128)

connection, addrinfo = server.accept

print 'Connection class: '
p connection.class

print 'Server fileno: '
p server.fileno

print 'Connection fileno: '
p connection.fileno

print 'local address: '
p connection.local_address

print 'Remote address: '
p connection.remote_address
p connection.hash

print 'addrinfo: '
p addrinfo
p addrinfo.hash

__END__

Connection class: Socket
Server fileno: 7
Connection fileno: 8
local address: #<Addrinfo: 127.0.0.1:4481 TCP>
Remote address: #<Addrinfo: 127.0.0.1:58389 TCP>
addrinfo: #<Addrinfo: 127.0.0.1:58389 TCP>