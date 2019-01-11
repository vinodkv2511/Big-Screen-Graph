import java.util.HashMap;

/**
 * Class to perform a breadth first search and also provides
 * some convinience methods to find path and distance 
 * @author Vinod Krishna Vellampalli
 */
public class BFS {
    //prev hashmap will help find the path
    HashMap<String, String> prev = new HashMap<>();
    //dist will help find the distance
    HashMap<String, Integer> dist = new HashMap<>();

    /**
     * Constructor takes a graph and a string s which will be the start vertex from which BFS has to be performed
     * @param graph The graph on which BFS has to be performed
     * @param s The key in the graph on which BFS has to be performed
     */
    public BFS(Graph graph, String s){

        Queue<String> queue = new Queue<>();
        queue.enqueue(s);
        this.dist.put(s,0);

        while( !queue.isEmpty() ){
            String v = queue.dequeue().data;
            for(String w : graph.getVertex(v).connectedTo){
                if (!dist.containsKey(w)) {
                    queue.enqueue(w);
                    dist.put(w, 1 + dist.get(v));
                    prev.put(w, v);
                }
            }
        }
    }

    /**
     * Function to check if there is a path to given key or vertex
     * @param key String key of the vertex to which the path has to verified
     * @return boolean value, true if a path exists else false
     */
    public boolean hasPathTo(String key){
        return dist.containsKey(key);
    }

    /**
     * Function to get the distance to a given vertex
     * @param v String key of the vertex to which distance has to found
     * @return Integere distance to the given Vertex if path exists else infinity(MAX integer value is returned)
     */
    public int distanceTo(String v) {
        if (!hasPathTo(v)) return Integer.MAX_VALUE;
        return dist.get(v);
    }
     
    /**
     * This function return the shortest path from v to s as an ArrayStack object
     * @param v
     * @return
     */
    public ArrayStack pathTo(String v) {
        if(this.hasPathTo(v)){
            ArrayStack path = new ArrayStack(this.distanceTo(v)+1);

            while (v != null) {
                path.push(v);
                v = prev.get(v);
            }
            return path;
        }
        else{
            return new ArrayStack(0);
        }
    }



}
