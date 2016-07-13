require './sudoku.rb'

puts Sudoku.solve(Sudoku::Puzzle.new(ARGF.readlines))
#puts puzzle
#puts puzzle.to_s

# http://www.sudoku9x9.com/
# L2: #570852121
