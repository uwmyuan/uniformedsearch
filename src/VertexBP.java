import java.util.Iterator;

/**
 * Created by Yun on 2017/4/5.
 */
public class VertexBP<T> extends Vertex<T> {
    GraphInterface<T> graph;
    double Heuristic = 0;
    T endLabel;

    VertexBP(T vertexLabel, T endLabel, GraphInterface<T> g) {
        super(vertexLabel);
        this.endLabel = endLabel;
        this.graph = g;
    }

    public double getHeuristic() {
        return Heuristic;
    }

    public T getEndLabel() {
        return endLabel;
    }

    /**
     * calculate Hamming Distance between vertexLabel and endLabel
     */
    public void setHeuristic() {
        String str1 = (String) getLabel();
        String str2 = (String) getEndLabel();
        int distance = 0;
        if (str1.length() != str2.length()) {
            System.out.println("vertexLabel.length()!=endLabel.length()");
        } else {
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        Heuristic = distance / 2;
    }

    @Override
    public Iterator getEdgeIterator() {
        if (Heuristic != 0)
            getNewVerticesWithH();
        else
            getNewVertices();
        return super.edgeList.iterator();
    }

    int getE() {
        String s = (String) getLabel();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'E')
                return i;
        }
        return -1;
    }

    void getNewVertices() {
        int i = getE();
        if (i == -1) {
            System.out.println("not found E position");
        }
        for (int k : new int[]{1, -1}) {
            for (int j = 1; j <= 4; j++) {
                StringBuffer tiles = new StringBuffer((String) getLabel());
                if (0 <= i + k * j && i + k * j < tiles.length()) {
                    tiles.setCharAt(i, tiles.charAt(i + k * j));
                    tiles.setCharAt(i + k * j, 'E');
                    //create new vertex
                    VertexBP<T> v = new VertexBP<>((T) tiles.toString(), getEndLabel(), graph);
                    if (v.getLabel().toString().length() == this.getLabel().toString().length()) {
                        graph.addVertex(v);
                        graph.addEdge(this.getLabel(), v.getLabel(), (j <= 1) ? 1 : (j - 1));

                    }
                }
            }
        }
    }

    void getNewVerticesWithH() {
        int i = getE();
        int[] q = {1, -1};
        for (int k : q) {
            for (int j = 1; j <= 4; j++) {
                if (0 <= i + k * j && i + k * j < getLabel().toString().length()) {
                    StringBuffer tiles = new StringBuffer((String) getLabel());
                    tiles.setCharAt(i, tiles.charAt(i + k * j));
                    tiles.setCharAt(i + k * j, 'E');
                    //create new vertex
                    VertexBP<T> v = new VertexBP<>((T) tiles.toString(), getEndLabel(), graph);
                    v.setHeuristic();
                    graph.addVertex(v);
                    graph.addEdge(this.getLabel(), v.getLabel(), ((j <= 1) ? 1 : (j - 1)) + v.getHeuristic() - this.getHeuristic());
                }
            }
        }
    }

    @Override
    public String toString() {
        return (String) getLabel();
    }

    @Override
    public boolean isEquals(Object obj) {
        if (!(obj instanceof VertexBP)) {
            return false;
        }
        VertexBP<T> s = (VertexBP<T>) obj;
        return (s.getLabel() == getLabel());
    }
}
