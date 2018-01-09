# uniformedsearch
Dijkstra shortest path algorithm

The expanded set E is implemented with a list of vertices “expandedVertices”. The frontier set F is implemented with a priority queue object “vertexQueue”. The priority queue is provided by standard Java library, which promises efficient operations. 
The Dijkstra’s algorithm is implemented as method Dijkstra(T,T) in the class “DirectedGraph”. The parameters of method Dijkstra(T,T) are defined with type “template” to ensure the generality of the method. The first parameter of method Dijkstra(T,T) is for the start vertex, and the second parameter for the goal vertices. For easy modification to solve problems such as route planning, 8-puzzie, 15-puzzle, River-Crossing, new class of vertices can be coded extending the class “Vertex”, and work with the method Dijkstra(T,T).

Details of the state and edge cost representations for the two sample problems.
For Case 1, the state is represented by the set of expanded vertices and the set of the frontier vertices, and the edge cost is represented by the distance between each pair of airports.
For Case 2, the state is represented by five numbers: the numbers of missionaries and the cannibals on left side, the numbers of missionaries and the cannibals on right side, and the last number is coded as 1 for the boat on the left side, 0 for the boat on the right side. For the case with n=3, the initial state is 3,3,0,0,1 where all missionaries and cannibals and the boat are on the left side and the goal state is 0,0,3,3,0 where all missionaries and cannibals and the boat are on the right side. The edge cost is represented by a constant “one” for one movement.

Test results and the argument of correctness 
Test results: 
	Output for case 1:

graph of fig. 7.3 [GT]
Number of vertices = 9
Number of edges = 38
The shortest path is [BWI, ORD, SFO]
The length of the shortest path is 2467.0
The number of visited vertices is 9
The running time is 37ms
Thus, the result is: the shortest path BWI->ORD->SFO, and the total cost is 2470.
	Output for case 2:

//n=3, m=2
The shortest path is [3,3,0,0,1, 2,2,1,1,0, 3,2,0,1,1, 3,0,0,3,0, 3,1,0,2,1, 1,1,2,2,0, 2,2,1,1,1, 0,2,3,1,0, 0,3,3,0,1, 0,1,3,2,0, 0,2,3,1,1, 0,0,3,3,0]
The length of the shortest path is 11.0
The number of visited vertices is 15
The running time is 40ms

//n=4, m=3
The shortest path is [4,4,0,0,1, 4,1,0,3,0, 4,2,0,2,1, 2,2,2,2,0, 3,3,1,1,1, 0,3,4,1,0, 0,4,4,0,1, 0,1,4,3,0, 1,1,3,3,1, 0,0,4,4,0]
The length of the shortest path is 9.0
The number of visited vertices is 21
The running time is 28ms
Thus, in the case with n=3, m=2, the movements are: two cannibals from left to right, empty boat from right to left, two missionaries from left to right, empty boat from right to left, one cannibal and one missionary from left to right. And the total number of movements is 5. 
In the case with n=4, m=3, the movements are: three cannibals from left to right, empty boat from right to left, three cannibals from left to right, empty boat from right to left, one cannibal and one missionary from left to right. And the total number of movements is 5.

Argument of correctness:
We can argue that the implement is correct with two ideas. 
First, in the class we have the proof of Dijkstra’s shortest path algorithm, then we can argue the implementation is correct if we code the algorithm correctly. We can see the code does implement all the steps of Dijkstra’s shortest path algorithm from the appendix.
Secondly, from the aspect of experimental tests, we can find: (a) the code produces correct answer for each example in black box testing; (b) in the glass box testing, every local variable has the expected value as in the correct implementation. Thus, I confirm the code is correct.



A* shortest path algorithm

Heuristic: The heuristic is one half of the hamming distance between the evaluated configuration and the goal combination, which means one half of the number of misplaced tiles. 
Example: When n=2, the goal configuration is AABBE. Suppose the evaluated configuration is ABEAB, one half of the number of misplaced tiles is (0+1+1+1+1)/2=2.
Prove the one half of the hamming distance is an admissible heuristic. 
Proof: 
	The proposed heuristic is the half hamming distance (HHD) between the state n and the goal t, namely h(n)=HHD(n,t). 
	Obviously, each pair of misplaced tiles must be moved at least once because if a pair of misplaced tiles could be moved once to reach the goal configuration, the HHD=1/2, the cost of the move would be 1,2 or 3. Even if the HHD=1, more than one move should be taken because the pair of displaced tiles may be far away from each other, in other words, more than three (not including three) tiles between them. Thus, HHD(n,n’)≤c(n,n'), where c(n,n’) is the cost of moves from the state n to the state n’.
	Because the hamming distance (HD) has triangle inequality HD(n,t)≤HD(n,n^' )+HD(n^',t) in the triangle formed by state n,n’ and the goal state t, Divide each side of the inequation by 2, we have HHD(n,t)≤HHD(n,n^' )+HHD(n^',t). Thus, h(n)≤HHD(n,n^' )+HHD(n^',t)≤c(n,n^' )+h(n^'). Therefore, HHD never overestimates the cost of moves.
Thus, one half of hamming distance is an admissible heuristic.∎
