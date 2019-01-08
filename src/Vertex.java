import java.util.LinkedList;

public class Vertex{

    enum VertexType{
        MOVIE, ACTOR;
    }

    String data;
    VertexType type;
    LinkedList<String> connectedTo;

    Vertex(String vData, VertexType vType){
        this.data = vData;
        this.type = vType;
        this.connectedTo = new LinkedList<>();
    }
}