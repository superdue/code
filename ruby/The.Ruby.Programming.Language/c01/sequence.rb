class Sequence
  include Enumerable

  def initialize(from, to, buy)
    @from, @to, @by = from, to, buy
  end

  def each
    x = @from
    while x <= @to
      yield x
      x += @by
    end
  end

  def length
    return 0 if @from > @to
    Integer((@to - @from)/@by) + 1
  end

  alias size length

  def [](index)
    return nil if index < 0
    v = @from + index*@by
    if v <= @to
      v
    else
      nil
    end
  end

  def *(factor)
    Sequence.new(@from*factor, @to*factor, @by*factor)
  end

  def +(offset)
    Sequence.new(@from+offset, @to+offset, @by)
  end
end


module Sequences
  def self.fromtoby(from, to, by)
    x = from
    while x <= to
      yield x
      x += by
    end
  end
end


class Range
  def by(step)
    x = self.begin
    if exclude_end?
      while x < self.end
        yield x
        x += step
      end
    else
      while x <= self.end
        yield x
        x += step
      end
    end
  end
end
