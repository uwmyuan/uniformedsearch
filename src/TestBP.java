import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yun on 2017/4/5.
 */
public class TestBP {
    int m, n;

    TestBP(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public void testBPmTimesWithoutH(GraphInterface graph) {
        for (int i = 0; i < m; i++) {
            String[] s = createNewCaseBP(n);
            testBPwithoutH(s[0], s[1], graph);
        }
    }

    public void testBPmTimesWithH(GraphInterface graph) {
        for (int i = 0; i < m; i++) {
            String[] s = createNewCaseBP(n);
            testBPwithH(s[0], s[1], graph);
        }
    }

    public void testBPwithoutH(String start, String end, GraphInterface graph) {
        System.out.println("without heuristic:");
        VertexBP vertexStart = new VertexBP(start, end, graph);
        graph.addVertex(vertexStart);
        VertexBP vertexEnd = new VertexBP(end, end, graph);
        graph.addVertex(vertexEnd);
        graph.Astar(start, end);
        graph.clear();
        System.out.println();
    }

    public void testBPwithH(String start, String end, GraphInterface graph) {
        System.out.println("with heuristic");
        VertexBP vertexStart = new VertexBP(start, end, graph);
        vertexStart.getDistance();
        vertexStart.setCost(vertexStart.getHeuristic());
        graph.addVertex(vertexStart);
        VertexBP vertexEnd = new VertexBP(end, end, graph);
        graph.addVertex(vertexEnd);
        graph.Astar(start, end);
        graph.clear();
        System.out.println();
    }

    public String[] createNewCaseBP(int n) {
        StringBuffer end = new StringBuffer();
        for (int i = 0; i < n; i++) {
            end.append('A');
        }
        for (int i = 0; i < n; i++) {
            end.append('B');
        }
        end.append('E');
        List<Character> characterList = end.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        Collections.shuffle(characterList);

        String[] se = {characterList.toString().replaceAll("[\\[\\]]", "").replaceAll(", ",""), end.toString()};
        return se;
    }
}
