public class Test {
    public static void main(String[] args) {
        //create the object of graph
        GraphInterface graph = new DirectedGraph();
        /*//case 1: [GT] fig.7.3
        System.out.println("graph of fig. 7.3 [GT]");
        //Adding vertices...
        graph.addVertex("SFO");
        graph.addVertex("LAX");
        graph.addVertex("DFW");
        graph.addVertex("MIA");
        graph.addVertex("BWI");
        graph.addVertex("BOS");
        graph.addVertex("PVD");
        graph.addVertex("JFK");
        graph.addVertex("ORD");
        System.out.println("Number of vertices = " + graph.getNumberOfVertices());
        //Adding edges
        graph.addEdge("SFO", "LAX", 337);
        graph.addEdge("LAX", "SFO", 337);
        graph.addEdge("SFO", "DFW", 1464);
        graph.addEdge("DFW", "SFO", 1464);
        graph.addEdge("SFO", "BOS", 2704);
        graph.addEdge("BOS", "SFO", 2704);
        graph.addEdge("SFO", "ORD", 1846);
        graph.addEdge("ORD", "SFO", 1846);
        graph.addEdge("LAX", "DFW", 1235);
        graph.addEdge("DFW", "LAX", 1235);
        graph.addEdge("LAX", "MIA", 2342);
        graph.addEdge("MIA", "LAX", 2342);
        graph.addEdge("DFW", "MIA", 1121);
        graph.addEdge("MIA", "DFW", 1121);
        graph.addEdge("DFW", "ORD", 802);
        graph.addEdge("ORD", "DFW", 802);
        graph.addEdge("DFW", "JFK", 1391);
        graph.addEdge("JFK", "DFW", 1391);
        graph.addEdge("ORD", "BOS", 867);
        graph.addEdge("BOS", "ORD", 867);
        graph.addEdge("ORD", "PVD", 849);
        graph.addEdge("PVD", "ORD", 849);
        graph.addEdge("ORD", "JFK", 740);
        graph.addEdge("JFK", "ORD", 740);
        graph.addEdge("ORD", "BWI", 621);
        graph.addEdge("BWI", "ORD", 621);
        graph.addEdge("MIA", "BWI", 946);
        graph.addEdge("BWI", "MIA", 946);
        graph.addEdge("BOS", "JFK", 187);
        graph.addEdge("JFK", "BOS", 187);
        graph.addEdge("PVD", "JFK", 144);
        graph.addEdge("JFK", "PVD", 144);
        graph.addEdge("MIA", "JFK", 1090);
        graph.addEdge("JFK", "MIA", 1090);
        graph.addEdge("MIA", "BOS", 1258);
        graph.addEdge("BOS", "MIA", 1258);
        graph.addEdge("JFK", "BWI", 184);
        graph.addEdge("BWI", "JFK", 184);
        //output number of vertices
        System.out.println("Number of edges = " + graph.getNumberOfEdges());
        //run Dijkstra's algorithm
        graph.Dijkstra("BWI", "SFO LAX");
        //trash graph
        graph.clear();
        */
        //case 2: The Missionaries and Cannibals problem
        //n=3, m=2
        //Adding vertices...
        String start = "3,3,0,0,1";
        VertexMC vertexStart = new VertexMC(start, graph);
        vertexStart.setM(2);
        graph.addVertex(vertexStart);
        String end = "0,0,3,3,0";
        VertexMC vertexEnd = new VertexMC(end, graph);
        graph.addVertex(vertexEnd);
        graph.Dijkstra(start, end);
        graph.clear();

        //n=4, m=3
        //Adding vertices...
        start = "4,4,0,0,1";
        vertexStart = new VertexMC(start, graph);
        vertexStart.setM(3);
        graph.addVertex(vertexStart);
        end = "0,0,4,4,0";
        vertexEnd = new VertexMC(end, graph);
        graph.addVertex(vertexEnd);
        graph.Dijkstra(start, end);
        graph.clear();

    }

}
