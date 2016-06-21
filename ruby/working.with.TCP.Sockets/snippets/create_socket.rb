require 'socket'

# INET是internet的缩写,特别用于指代IPv4版本的套接字
# STREAM表示将使用数据流进行通信,该功能由TCP提供
socket = Socket.new(Socket::AF_INET, Socket::SOCK_STREAM)