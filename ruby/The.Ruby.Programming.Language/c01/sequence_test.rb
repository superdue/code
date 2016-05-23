require './sequence.rb'

s = Sequence.new(1, 10, 2)
s.each {|x| print x}
print s[s.size-1]
t = (s+1)*2
t.each {|x| print x}

Sequences.fromtoby(1, 10, 2) {|x| print x}

(0..10).by(2) {|x| print x}
(0...10).by(2) {|x| print x}
