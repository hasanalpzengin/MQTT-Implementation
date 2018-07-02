import socket

s = socket.socket()
host = "localhost"
port = 1885
s.bind((host, port))

s.listen(5)

(c, addr) = s.accept()
print('Connection Established from', addr)
c.send(b'\x10\x17\x00\x04MQTT\x04\x02\x00<\x00\x0bpython_test')
c.close()
