require 'socket'

servers = Socket.tcp_server_sockets(4481)
p servers # The sockets contains IPv6 and IPv4 sockets.
servers.each {|s| p s.local_address }