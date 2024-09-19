# Note: läs gärna artikeln om A-Star i kompendiet först :)
import random

class AsNode:
    def __init__(self, x, y, blocked):
        self.x = x
        self.y = y
        self.g = 1000
        self.h = 0
        self.f = self.g + self.h
        self.parent = None
        self.children = []  # Initialize a new list for each instance
        self.blocked = blocked
        self.path = False

    def __eq__(self, o):
        if isinstance(o, AsNode):
            return self.x == o.x and self.y == o.y
        return False

as_nodes = []

def print_nodes(nodes: list[AsNode]):
    for n in nodes:
        print(f"{n.x}, {n.y}")


# creates random 10x10 map with walls
for i in range(0,10):
    for j in range(0,10):
        r = random.randint(0,9)
        if r > 5:
            block = True
        else:
            block = False
        n = AsNode(i,j, block)
        #n.g = 1000
        #n.h = 0
        #n.f = n.g + n.h
        as_nodes.append(n)

def find_node(f_x, f_y, nodes) -> AsNode:
    for n in nodes:
        if n == AsNode(f_x, f_y, True):
            return n
    return None

def manhattan_dist(s_x, s_y, g_x, g_y):
    return abs(s_x - g_x) + abs(s_y - g_y)

def best_node(nodes: list[AsNode]):
    min_node: AsNode = nodes[0]
    for n in nodes:
        if n.f < min_node.f:
            min_node = n
    return min_node

def set_children(nodes: list[AsNode]):
    for n in nodes:
        for i in range(-1,2):
            for j in range(-1,2):
                c = find_node(n.x + i, n.y + j, nodes)
                if c is None or c == n or c.blocked:
                    continue
                n.children.append(c)

def print_map(nodes: list[AsNode]):
    for i in range(0,10):
        for j in range(0,10):
            n = find_node(i,j,nodes)
            if n.blocked:
                print("[x]", end="")
            else:
                print("[ ]", end="")
        print("")

def print_path(nodes: list[AsNode]):
    for i in range(0,10):
        for j in range(0,10):
            n = find_node(i,j,nodes)
            if n.blocked:
                print("[x]", end="")
            elif n.path:
                print("[o]", end="")
            else:
                print("[ ]", end="")
        print("")

print_map(as_nodes)

# collecting start and goal coords
start_x = int(input("Start X: "))
start_y = int(input("Start Y: "))
goal_x = int(input("Goal X: "))
goal_y = int(input("Goal Y: "))

goal: AsNode = find_node(goal_x, goal_y, as_nodes)

# setting node neighbours
set_children(as_nodes)

# initializing start node
p: AsNode = find_node(start_x, start_y, as_nodes)
p.g = 0
p.h = manhattan_dist(p.x, p.y, goal_x, goal_y)
p.f = p.g + p.h

open_list = []
closed = []

open_list.append(p)

print_map(as_nodes)

# START OF A-STAR ALGORITHM
while open_list:
    b = best_node(open_list)
    print(f"looking at: {b.x}, {b.y}")
    open_list.remove(b)
    closed.append(b)
    if b == goal:
        print("found")
        print(f"parent: {goal.parent.x}, {goal.parent.y}")
        break

    for c in b.children:
        new_f = b.g + 1 + manhattan_dist(c.x, c.y, goal_x, goal_y)

        if c in open_list or c in closed:
            if new_f < c.f:
                print("lower")
                c.g = b.g + 1
                c.h = manhattan_dist(c.x, c.y, goal_x, goal_y)
                c.f = c.g + c.h
                c.parent = b
        else:
            c.g = b.g + 1
            c.h = manhattan_dist(c.x, c.y, goal_x, goal_y)
            c.f = c.g + c.h
            open_list.append(c)
            c.parent = b
# END OF A-STAR ALGORITHM

# Printing out the node path
path_first = goal
while True:
    if path_first is None:
        break
    print(f"{path_first.x}, {path_first.y}")
    path_first.path = True
    path_first = path_first.parent

print("MAP:\n")
print_map(as_nodes)
print()
for i in range(0,30):
    print("=", end="")
print()
print("PATH:\n")
print_path(as_nodes)
