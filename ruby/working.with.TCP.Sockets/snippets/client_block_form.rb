require 'socket'

Socket.tcp('baidu.com', 80) do |connection|
  connection.write "GET / HTTP/1.1\r\n"
  connection.close
end

# 如果省略代码块参数,则行为方式同TCPSocket.new()一样
client = Socket.tcp('baidu.com', 80)