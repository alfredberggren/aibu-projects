from graphics import *

class CaCell:
    def __init__(self, cell_type):
        self.cell_type = cell_type

win = GraphWin(width=500, height=500)
win.setCoords(0,0,500,500)

cells = []
for i in range(0,50):
    for j in range(0,50):
        cells.append(Rectangle(Point(i*10,j*10), Point(10+i*10,10+j*10)))

for c in cells:
    c.draw(win)

win.getMouse()
