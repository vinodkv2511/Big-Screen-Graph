import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
/*
    A simple implementation of graph ADT with Adjacency list
    This Graph will be an array of Linked Lists of type int
 */

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


    public void addVertex(String data, Vertex.VertexType type){
        if(this.hasVertex(data)){
//            System.out.println("Vertex Already Exists!");
            return;
        }
        Vertex newVertex = new Vertex(data,type);
        this.adjacencyList.put(data, newVertex);
        this.numberOfVertices++;
    }

    public Vertex getVertex(String data){
        return this.adjacencyList.get(data);
    }

    public void addEdge(String vkey1, String vKey2){
        if(this.hasEdge(vkey1, vKey2)){
//            System.out.println("Edge Already Exists!");
            return;
        }

        Vertex firstVertex = this.adjacencyList.get(vkey1);
        firstVertex.connectedTo.add(vKey2);
        this.numberOfEdges++;
    }


    public boolean hasEdge(String vkey1, String vKey2){
        if(hasVertex(vkey1)){
            Vertex firstVertex = this.adjacencyList.get(vkey1);
            return firstVertex.connectedTo.contains(vKey2);
        }
        else{
            return false;
        }

    }

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

