import java.util.Iterator;

/**
 * Created by njauy on 3/29/2017.
 */
enum Position {
    RIGHT, LEFT
}

public class VertexMC<T> extends Vertex<T> {

    int m;
    int LeftMissionaries;
    int LeftCannibals;
    int RightMissionaries;
    int RightCannibals;
    GraphInterface<T> graph;
    Position BoatPosition;

    public VertexMC(T vertexLabel, GraphInterface<T> g) {

        super(vertexLabel);
        setNumbers();
        this.graph = g;
    }

    public void setBoatPosition(Position BoatPositionPosition) {
        BoatPosition = BoatPositionPosition;
    }


    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    @Override
    public Iterator getEdgeIterator() {
        getNewVertices();
        return super.edgeList.iterator();
    }

    void setNumbers() {
        String Numbers = (String) super.getLabel();
        String[] NumberGroup = (Numbers).split(",");
        LeftMissionaries = Integer.parseInt(NumberGroup[0]);
        LeftCannibals = Integer.parseInt(NumberGroup[1]);
        RightMissionaries = Integer.parseInt(NumberGroup[2]);
        RightCannibals = Integer.parseInt(NumberGroup[3]);
        if (Integer.parseInt(NumberGroup[4]) == 1)
            setBoatPosition(Position.LEFT);
        else
            setBoatPosition(Position.RIGHT);
    }

    public boolean isValid(int LM, int LC, int RM, int RC) {
        if (LM >= 0 && LC >= 0 && RM >= 0 && RC >= 0
                && (LM == 0 || LM >= LC)
                && (RM == 0 || RM >= RC)) {
            return true;
        }
        return false;
    }

    void getNewVertices() {
        setNumbers();
        if (BoatPosition == Position.LEFT) {
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= m; j++) {
                    if (1 <= i + j && i + j <= m) {
                        Integer LM = LeftMissionaries - i;
                        Integer LC = LeftCannibals - j;
                        Integer RM = RightMissionaries + i;
                        Integer RC = RightCannibals + j;
                        if (isValid(LM, LC, RM, RC)) {
                            VertexMC<T> v = new VertexMC<>((T) (LM.toString() + "," + LC.toString() + "," + RM.toString() + "," + RC.toString() + "," + "0"), graph);
                            v.setM(m);
                            graph.addVertex(v);
                            graph.addEdge(this.getLabel(), v.getLabel(), 1);

                        }
                    }

                }
            }
        } else {
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= m; j++) {
                    if (1 <= i + j && i + j <= m) {
                        Integer LM = LeftMissionaries + i;
                        Integer LC = LeftCannibals + j;
                        Integer RM = RightMissionaries - i;
                        Integer RC = RightCannibals - j;
                        if (isValid(LM, LC, RM, RC)) {
                            VertexMC<T> v = new VertexMC<>((T) (LM.toString() + "," + LC.toString() + "," + RM.toString() + "," + RC.toString() + "," + "1"), graph);
                            v.setM(m);
                            graph.addVertex(v);
                            graph.addEdge(this.getLabel(), v.getLabel(), 1);

                        }
                    }
                }
            }

        }

    }

    @Override
    public String toString() {
        if (BoatPosition == Position.LEFT) {
            return "(Left: Missionaries = " + LeftMissionaries + ", Cannibals = " + LeftCannibals + ", Right: Missionaries = "
                    + RightMissionaries + ", Cannibals = " + RightCannibals + ",Boat Position: Left)";
        } else {
            return "(Left: Missionaries = " + LeftMissionaries + ", Cannibals = " + LeftCannibals + ", Right: Missionaries = "
                    + RightMissionaries + ", Cannibals = " + RightCannibals + ",Boat Position: Right)";
        }
    }

    @Override
    public boolean isEquals(Object obj) {
        if (!(obj instanceof VertexMC)) {
            return false;
        }
        VertexMC<T> s = (VertexMC<T>) obj;
        return (s.getLabel()==getLabel());
    }
}
