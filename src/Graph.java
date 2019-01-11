import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * A simple implementation of graph ADT with Adjacency list concept
 * the adjecency list is implemented using a HashMap with String key and Vertex type as value
 *
 * @author Vinod Krishna Vellampalli
 * @date 08/01/2019
 * 
 */

public class Graph {

    private int numberOfVertices;
    private int numberOfEdges;

    public HashMap<String, Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    private HashMap<String, Vertex> adjacencyList;

    public Graph() {
        try{
            this.adjacencyList = new HashMap<>();
            this.numberOfVertices = 0;
            this.numberOfEdges = 0;
        }
        catch (Exception e){
            System.out.println("Error while creating adjacency list\nTry with a smaller size");
        }
    }


    /**
     * Adds a new vertex to the graph
     * @param data String value of the actual data of vertex
     * @param type One of the possible vertex types as defined in vertextype enum, in this case either MOVIE or ACTOR
     */
    public void addVertex(String data, Vertex.VertexType type){
        if(this.hasVertex(data)){
//            System.out.println("Vertex Already Exists!");
            return;
        }
        Vertex newVertex = new Vertex(data,type);
        this.adjacencyList.put(data, newVertex);
        this.numberOfVertices++;
    }

    /**
     * This function takes the String value which a key of vertex 
     * and return the Vertex object if it exists in the graph
     * @param data Case sensitive string with vertex data
     * @return Vertex Vertex if found else null
     * @see Vertex class for representing a vertex in graph
     */
    public Vertex getVertex(String data){
        return this.adjacencyList.get(data);
    }

    /**
     * This function takes two String keys and adds an edge in the graph if it doesn't exist
     * @param vkey1 first vertex data
     * @param vkey2 second vertex data
     */
    public void addEdge(String vkey1, String vKey2){
        if(this.hasEdge(vkey1, vKey2)){
//            System.out.println("Edge Already Exists!");
            return;
        }

        Vertex firstVertex = this.adjacencyList.get(vkey1);
        firstVertex.connectedTo.add(vKey2);
        this.numberOfEdges++;
    }

    /**
     * This function takes two String keys and verifies if an edge exists between given vertices
     * and return true if exists else false
     * @param vkey1 first vertex data
     * @param vkey2 second vertex data
     * @return boolean 
     */
    public boolean hasEdge(String vkey1, String vKey2){
        if(hasVertex(vkey1)){
            Vertex firstVertex = this.adjacencyList.get(vkey1);
            return firstVertex.connectedTo.contains(vKey2);
        }
        else{
            return false;
        }

    }

    /**
     * This function takes a String key and verifies if a vertex exists with given key
     * and return true if exists else false
     * @param data first vertex data
     * @return boolean 
     */
    public boolean hasVertex(String data){
        return adjacencyList.containsKey(data);
    }

    public void printGraph(){
        System.out.println("The graph looks like below");
        for(String key : adjacencyList.keySet()){
            Vertex currentVertex = adjacencyList.get(key);
            System.out.print(currentVertex.data);
            for(int i=0; i<currentVertex.connectedTo.size(); i++){
                System.out.print(" -> "+currentVertex.connectedTo.get(i));
            }
            System.out.print("\n");
        }
    }

    public int V(){
        return numberOfVertices;
    }

    public int E(){
        return numberOfEdges/2;
    }


}

