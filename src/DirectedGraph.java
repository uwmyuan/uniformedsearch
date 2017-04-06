/**
 * Created by Yun Yuan on 3/15/2017.
 */

import java.util.*;

public class DirectedGraph<T> implements GraphInterface<T>, java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Map<T, VertexInterface<T>> vertices;//collection for all vertices

    private int edgeCount;

    public DirectedGraph() {
        vertices = new LinkedHashMap<>();
    }

    @Override
    public boolean Contains(VertexInterface<T> vertex) {
        return vertices.containsKey(vertex.getLabel());
    }

    @Override
    public void addVertex(T vertexLabel) {
        //if the vertexLabel is the same, the new vertex will replace the old one based on LinkedHashMap.put().
        //Adjacent edges are stored by a new LinkedList
        vertices.put(vertexLabel, new Vertex(vertexLabel) {
        });
    }

    @Override
    public void addVertex(VertexInterface<T> vertex) {
        if (!vertices.containsKey(vertex.getLabel()))
            vertices.put(vertex.getLabel(), vertex);

    }

    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        if (beginVertex != null && endVertex != null)
            result = beginVertex.connect(endVertex, edgeWeight);
        if (result)
            edgeCount++;
        return result;//if the edge exists, return false
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        if (beginVertex == null || endVertex == null || beginVertex.hasNeighbor() == false)
            return found;
        Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();
        while (!found && neighbors.hasNext()) {
            VertexInterface<T> neighbor = neighbors.next();
            if (endVertex.equals(neighbor))
                found = true;
        }//end while
        return found;
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public int getNumberOfVertices() {
        return vertices.size();
    }

    @Override
    public int getNumberOfEdges() {
        return edgeCount;
    }

    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }

    @Override
    public int Dijkstra(T begin, T end) {
        long preTime = System.currentTimeMillis();

        //initialize f-value for each vertex
        resetVertices();

        //the status of the algorithm
        boolean done = false;

        //customize the comparator for priority queue
        Comparator<VertexInterface<T>> Ordering = (o1, o2) -> (int) Math.round(o1.getCost() - o2.getCost());

        //creating a new priority queue set F
        Queue<VertexInterface<T>> vertexQueue = new PriorityQueue<>(11, Ordering);

        VertexInterface<T> beginVertex;

        //create a variable to store the reached goal
        VertexInterface<T> endVertex = null;

        //input the begin vertex and the goal set
        List<VertexInterface<T>> endVertices = new ArrayList<>();
        try {
            beginVertex = vertices.get(begin);

            //set the f-value of the origin to zero and add the origin into PQ
            beginVertex.setCost(beginVertex.getHeuristic());
            vertexQueue.add(beginVertex);
            String[] labelGroup = ((String) end).split(" ");
            for (String str : labelGroup)
                endVertices.add(vertices.get(str));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //create the set E
        List<VertexInterface<T>> expandedVertices = new ArrayList<>();

        //starting searching iterations
        while (!done && !vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.poll();
            //check if any goal reached
            for (VertexInterface<T> v : endVertices) {
                if (v.isEquals(frontVertex)) {
                    done = true;
                    endVertex = frontVertex;
                }
            }
            expandedVertices.add(frontVertex);
            Iterator<Vertex.Edge> edges = frontVertex.getEdgeIterator();
            while (!done && edges.hasNext()) {
                Vertex.Edge nextEdge = edges.next();
                //case ui not in E, not in F
                if (!expandedVertices.contains(nextEdge) && !vertexQueue.contains(nextEdge))
                    if (nextEdge.getEndVertex().getCost() > frontVertex.getCost() + nextEdge.getWeight()) {
                        nextEdge.getEndVertex().setPredecessor(frontVertex);
                        nextEdge.getEndVertex().setCost(frontVertex.getCost() + nextEdge.getWeight());
                        vertexQueue.add(nextEdge.getEndVertex());
                    }
                //case ui in F
                if (vertexQueue.contains(nextEdge)) {
                    if (nextEdge.getEndVertex().getCost() > frontVertex.getCost() + nextEdge.getWeight()) {
                        nextEdge.getEndVertex().setPredecessor(frontVertex);
                        nextEdge.getEndVertex().setCost(frontVertex.getCost() + nextEdge.getWeight());
                    }
                }
                //case ui in E
                //**never used
                if (expandedVertices.contains(nextEdge)) {
                    if (nextEdge.getEndVertex().getCost() > frontVertex.getCost() + nextEdge.getWeight()) {
                        nextEdge.getEndVertex().setPredecessor(frontVertex);
                        expandedVertices.remove(expandedVertices.indexOf(nextEdge.getEndVertex()));
                        vertexQueue.add(nextEdge.getEndVertex());
                    }
                }

            }
        }
        if (!done) {
            return 1;//return 1 for fail
        } else {
            try {
                //initial the length of path
                double pathLength;
                List<T> path = new ArrayList<>();
                //record the cost of the shortest path
                pathLength = endVertex.getCost();
                //record the shortest path
                path.add(endVertex.getLabel());
                VertexInterface<T> vertex = endVertex;
                while (vertex.hasPredecessor()) {
                    vertex = vertex.getPredecessor();
                    path.add(vertex.getLabel());
                }
                //reverse the result sp
                List<T> sp = path.subList(0, path.size());
                Collections.reverse(sp);
                System.out.println("The shortest path is " + sp.toString());
                System.out.println("The length of the shortest path is " + pathLength);
                System.out.println("The number of visited vertices is " + expandedVertices.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("The running time is " + (System.currentTimeMillis() - preTime) + "ms");
            return 0;//return 0 for success
        }
    }

    @Override
    public int Astar(T begin, T end) {
        return Dijkstra(begin, end);
    }

    /**
     * initialize the vertices
     */
    protected void resetVertices() {
        Iterator<VertexInterface<T>> vertexIterator = vertices.values().iterator();
        while (vertexIterator.hasNext()) {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.setCost(Double.MAX_VALUE);
            nextVertex.setPredecessor(null);
        }
    }

}
