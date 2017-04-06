import java.util.Iterator;

public interface VertexInterface<T> {

    /**
     * get the vertex
     *
     * @return vertex object
     */
    T getLabel();

    /**
     * connect this edge with endVertex with a weighted edge
     *
     * @param endVertex  the end vertex of
     * @param edgeWeight weight of the edge
     * @return if success, return true, else false
     */
    boolean connect(VertexInterface<T> endVertex, double edgeWeight);

    /**
     * connect this edge with endVertex with an unweighted edge
     *
     * @param endVertex ending vertex
     * @return if success, return true, else false
     */
    boolean connect(VertexInterface<T> endVertex);

    /**
     * Task: creating iterator for getting all vertices starting from this vertex
     *
     * @return the iterator
     */
    Iterator<VertexInterface<T>> getNeighborIterator();

    /**
     * creating iterator for getting weights to all neighbor
     *
     * @return the iterator
     */
    Iterator<Vertex.Edge> getEdgeIterator();

    /**
     * check if this vertex is isolated
     *
     * @return return true if yes, else no
     */
    boolean hasNeighbor();

    /**
     * getter of the predecessor
     *
     * @return the predecessor,if there exist no predecessor, return null
     */
    VertexInterface<T> getPredecessor();

    /**
     * setter of the predecessor
     *
     * @param predecessor
     */
    void setPredecessor(VertexInterface<T> predecessor);

    /**
     * check if these exists a predecessor
     *
     * @return return true if yes, else no
     */
    boolean hasPredecessor();

    /**
     * getter of the cost to this vertex
     *
     * @return
     */
    double getCost();

    /**
     * setter of the cost to this vertex
     *
     * @param newCost
     */
    void setCost(double newCost);

    /**
     * check if other is equal to this vertex
     * @param other another object
     */
    boolean isEquals(Object other);
}
