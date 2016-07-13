require 'socket'

socket = Socket.new(:INET, :STREAM)

addr = Socket.pack_sockaddr_in(4481, '0.0.0.0')

socket.bind(addr)

__END__

这是一个低层次实现,演示了如何将TCP套接字绑定到本地端口上,实际上,它和用于实现同样功能的C代码几乎一模一样
