require 'socket'

socket = Socket.new(:INET, :STREAM)

remote_addr = Socket.pack_sockaddr_in(80, 'baidu.com')

socket.connect(remote_addr)