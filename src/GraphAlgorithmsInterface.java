public interface GraphAlgorithmsInterface<T> {
    /**
     *
     * @param begin
     * @param end
     * @return 0 if success, 1 if fail
     */
    int Dijkstra(T begin, T end);
}