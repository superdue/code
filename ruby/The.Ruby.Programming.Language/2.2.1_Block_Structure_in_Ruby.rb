# coding: ASCII

#
# Stats
#
module Stats
  class Dataset
    def initialize filename
      IO.foreach filename do |line|
        if line[0,1] == '#'
          next
        else
          puts line
        end
      end
    end
  end
end

Stats::Dataset.new '2.2.1_Block_Structure_in_Ruby.rb'

p __ENCODING__
p Encoding.default_external
p Encoding.locale_charmap

BEGIN {
  puts "begin"
}

END {
  puts "end"
}

at_exit do
  puts "at_exit"
end
