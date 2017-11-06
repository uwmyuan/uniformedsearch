import java.util.*;

class Vertex<T> implements VertexInterface<T>, java.io.Serializable {

    Set<Edge> edgeList;//list of edges
    private T label;//T: String,Integer
    private VertexInterface<T> previousVertex;
    private double cost;//path cost to this vertex

    /**
     * constructor
     *
     * @param vertexLabel
     */
    public Vertex(T vertexLabel) {
        label = vertexLabel;
        edgeList = new HashSet<>();
        previousVertex = null;
        cost = Double.MAX_VALUE;
    }
    public double getHeuristic() {
        return 0;
    }
    @Override
    public T getLabel() {
        return label;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;
        if (!this.isEquals(endVertex)) {
            Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;
            while (!duplicateEdge && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    duplicateEdge = true;
                    break;
                }
            }//end while
            if (!duplicateEdge) {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            }//end if
        }//end if
        return result;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    }

    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new NeighborIterator();
    }

    @Override
    public Iterator getEdgeIterator() {
        return edgeList.iterator();
    }

    @Override
    public boolean hasNeighbor() {
        return !(edgeList.isEmpty());
    }


    @Override
    public VertexInterface<T> getPredecessor() {
        return this.previousVertex;
    }

    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;
    }

    @Override
    public boolean hasPredecessor() {
        return this.previousVertex != null;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public void setCost(double newCost) {
        cost = newCost;
    }

    /**
     * compare two objects
     *
     * @param other
     * @return true if the same, else false
     */
    @Override
    public boolean isEquals(Object other) {
        boolean result;
        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else {
            Vertex<T> otherVertex = (Vertex<T>) other;
            result = label.equals(otherVertex.label);
        }
        return result;
    }

    /**
     * class of edge
     */
    protected class Edge implements java.io.Serializable {
        private VertexInterface<T> vertex;
        private double weight;


        protected Edge(VertexInterface<T> endVertex, double edgeWeight) {
            vertex = endVertex;
            weight = edgeWeight;
        }

        protected VertexInterface<T> getEndVertex() {
            return vertex;
        }

        protected double getWeight() {
            return weight;
        }
    }

    /**
     * class of NeighborIterator
     */
    private class NeighborIterator implements Iterator<VertexInterface<T>> {

        Iterator<Edge> edgesIterator;

        private NeighborIterator() {
            edgesIterator = edgeList.iterator();
        }

        @Override
        public boolean hasNext() {
            return edgesIterator.hasNext();
        }

        @Override
        public VertexInterface<T> next() {
            VertexInterface<T> nextNeighbor;
            if (edgesIterator.hasNext()) {
                Edge edgeToNextNeighbor = edgesIterator.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            } else
                throw new NoSuchElementException();
            return nextNeighbor;
        }
    }
}