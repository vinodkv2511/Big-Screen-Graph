import java.util.HashMap;

public class BFS {
    HashMap<String, String> prev = new HashMap<>();
    HashMap<String, Integer> dist = new HashMap<>();

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

    public boolean hasPathTo(String key){
        return dist.containsKey(key);
    }

    public int distanceTo(String v) {
        if (!hasPathTo(v)) return Integer.MAX_VALUE;
        return dist.get(v);
    }


    // return the shortest path from v to s as an Iterable
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
