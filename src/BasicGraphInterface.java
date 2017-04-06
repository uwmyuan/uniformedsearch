/**
 * Created by Yun Yuan on 3/15/2017.
 */
public interface BasicGraphInterface<T> {
    /**
     * @param vertexLabel
     * @task: add a vertex to graph
     */
    void addVertex(T vertexLabel);

    void addVertex(VertexInterface<T> vertex);

    /**
     * @param begin beginning vertex
     * @param end   ending vertex
     * @return if success, return true,else false
     * @task add weighted edge between two vertices to graph if there exists no edge between them
     */
    boolean addEdge(T begin, T end, double edgeWeight);

    /**
     * @param begin beginning vertex
     * @param end   ending vertex
     * @return if there exists, return true, else false
     * @task check if there exists an edge between two vertices
     */
    boolean hasEdge(T begin, T end);

    /**
     * @return if empty, return true, else false
     * @task check the graph is empty
     */
    boolean isEmpty();

    /**
     * @return the number of vertices
     * @task count the number of vertices
     */
    int getNumberOfVertices();

    /**
     * @return the number of edges
     * @task count the number of edges
     */
    int getNumberOfEdges();

    /**
     * @task remove all the vertices and edges
     */
    void clear();

    boolean Contains(VertexInterface<T> vertex);
}
