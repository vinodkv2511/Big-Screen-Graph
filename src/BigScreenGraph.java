import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class BigScreenGraph {

    Graph graph;

    public BigScreenGraph(){
        graph = new Graph();
    }


    public void readActMovFile(String filename) throws IOException {
        try{
            File inputFile = new File(filename);
            FileInputStream fis = new FileInputStream(inputFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            //Reading line by line
            String line;
            while((line = br.readLine()) != null){
                //process the line
//                System.out.println(line);

                String[] splits = line.split("/");

                for(int i = 0; i< splits.length; i ++ ){
                    try {
                        if (i == 0) {
                            this.graph.addVertex(splits[i].trim(), Vertex.VertexType.MOVIE);
                        } else {
                            this.graph.addVertex(splits[i].trim(), Vertex.VertexType.ACTOR);
                            this.graph.addEdge(splits[0].trim(), splits[i].trim());
                            this.graph.addEdge(splits[i].trim(), splits[0].trim());
                        }
                    }
                    catch (IndexOutOfBoundsException ex){
                        System.out.println("Something wrong with input line -> " + line);
                    }
                }
            }
        }
        catch (Exception ex){
            System.out.println("Error reading file\n" + ex.toString());
            throw ex;
        }

    }


    public void displayMoviesOfActor(String actor){
        Vertex thisVertex = this.graph.getVertex(actor);
        if(thisVertex != null){
            if(thisVertex.type != Vertex.VertexType.ACTOR){
                System.out.println("Aborting! Expecting a actor name but found movie.");
                return;
            }
            System.out.println("\n"+actor+ " acted in the following movies");
            for(int i = 0 ; i < thisVertex.connectedTo.size(); i++){
                System.out.println(thisVertex.connectedTo.get(i));
            }
        }
        else{
            System.out.println("Actor not found! Please check spelling and retry.");
        }
    }

    public void displayActorsOfMovie(String movie){
        Vertex thisVertex = this.graph.getVertex(movie);
        if(thisVertex != null){
            if(thisVertex.type != Vertex.VertexType.MOVIE){
                System.out.println("Aborting! Expecting a movie name but found actor.");
                return;
            }
            System.out.println("\nActors in the movie "+movie+" are:");
            for(int i = 0 ; i < thisVertex.connectedTo.size(); i++){
                System.out.println(thisVertex.connectedTo.get(i));
            }
        }
        else{
            System.out.println("Movie not found! Please check spelling and retry.");
        }
    }

    public void displayActMov(Boolean shouldPrintFull){
        LinkedList<String> actorsL = new LinkedList<>();
        LinkedList<String> moviesL = new LinkedList<>();

        for(String key : this.graph.getAdjacencyList().keySet()){
            Vertex currentVertex = this.graph.getVertex(key);
            if(currentVertex.type == Vertex.VertexType.MOVIE){
                moviesL.add("'"+key+"'");
            }
            else{
                actorsL.add("'"+key+"'");
            }
        }

        System.out.println("\nNumber of Movies : " + Integer.toString(moviesL.size()));
        System.out.println("Number of Actors : " + Integer.toString(actorsL.size()));
        if(shouldPrintFull) System.out.println("List of movies \n"+ moviesL);
        if(shouldPrintFull) System.out.println("List of actors \n"+ actorsL);

    }


    public void displayActMov(){
        displayActMov(true);
    }

    public void findMovieRelation(String movA, String movB){
        Vertex movAVertex = this.graph.getVertex(movA);
        Vertex movBVertex = this.graph.getVertex(movB);

        if(movAVertex != null && movBVertex != null){
            if (movAVertex.type != Vertex.VertexType.MOVIE || movBVertex.type != Vertex.VertexType.MOVIE){
                System.out.println("You have to provide two movies. Please retry");
            }
        }
        else{
            System.out.println("Movie name not found! Please check and try again.");
        }

        BFS bfs = new BFS(this.graph, movA);

        if(bfs.distanceTo(movB) == 2 ){
            ArrayStack<String> stack = bfs.pathTo(movB);
            stack.pop();
            System.out.println(movA+" and "+movB+" are related by R with "+stack.pop()+" as a common actor");
        }
        else{
            System.out.println(movA+" and "+movB+" are not related by R");
        }
    }

    public void findMovieTransRelation(String movA, String movB){
        Vertex movAVertex = this.graph.getVertex(movA);
        Vertex movBVertex = this.graph.getVertex(movB);

        if(movAVertex != null && movBVertex != null){
            if (movAVertex.type != Vertex.VertexType.MOVIE || movBVertex.type != Vertex.VertexType.MOVIE){
                System.out.println("You have to provide two movies. Please retry");
            }
        }
        else{
            System.out.println("Movie name not found! Please check and try again.");
        }

        BFS bfs = new BFS(this.graph, movA);

        if(bfs.distanceTo(movB) == 2 ){
            ArrayStack<String> stack = bfs.pathTo(movB);
            stack.pop();
            System.out.println(movA+" and "+movB+" are related by T with "+stack.pop()+" as a common actor");
        }
        else if(bfs.distanceTo(movB) == 4){
            ArrayStack<String> stack = bfs.pathTo(movB);
            stack.pop();
            stack.pop();
            System.out.println(movA+" and "+movB+" are related by T with "+stack.pop()+" as a common movie");
        }
    }

    public static void main(String[] args) {
        BigScreenGraph bsg = new BigScreenGraph();
//        bsg.readActMovFile("/home/vinod/IdeaProjects/BigScreenGraph/test.txt");
//        bsg.readActMovFile("/home/vinod/IdeaProjects/BigScreenGraph/cast.06.txt");
//        bsg.readActMovFile("/home/vinod/IdeaProjects/BigScreenGraph/cast.all.txt");


        Scanner sc = new Scanner(System.in);
        boolean fileReadSuccess = false;
        boolean aborted = false;

        System.out.println("Welcome to Big Screen Graph!\n\nPlease provide a input file name (full path)");

        do{
            String inputFile = sc.nextLine();

            if("abort".equals(inputFile) || "\"abort\"".equals(inputFile)){
                aborted = true;
                System.out.println("Aborting . . ");
                break;
            }

            System.out.println("Reading file . . .\n");
            try{
                bsg.readActMovFile(inputFile);

                fileReadSuccess = true;
            }
            catch (Exception ex){
                System.out.println("\nPlease try again with a valid file name or Enter \"abort\" to quit! \n");
            }


        }while (!fileReadSuccess);


        if(!aborted){
            System.out.println("Big Screen Graph is ready!\n\nPlease choose an option to proceed!");
        }


        while(!aborted){
            System.out.println("\n" +
                    "    Enter 1 to see the number of unique movies and actors\n" +
                    "    Enter 2 to see the number of unique movies and actors along with the full list\n" +
                    "    Enter 3 to see all the movies of an actor\n" +
                    "    Enter 4 to see all the actors of a movie\n" +
                    "    Enter 5 to find if two movies are related by relation R (as defined in problem statement)\n" +
                    "    Enter 6 to find if two movies are related by relation T (as defined in problem statement)\n" +
                    "    Enter 7 to load another input file onto existing graph\n"+
                    "    Enter 8 to load another input file as a new graph\n"+
                    "    \n" +
                    "    Enter 0 to quit.");

            int choice;
            try{
                choice = sc.nextInt();
            }
            catch (Exception ex){
                choice = 10;
            }

            switch(choice){
                case 1:
                    bsg.displayActMov(false);
                    break;
                case 2:
                    bsg.displayActMov();
                    break;
                case 3:
                    System.out.println("\nPlease enter name of the actor (as in the input file)");
                    sc.nextLine();
                    String actor = sc.nextLine();
                    bsg.displayMoviesOfActor(actor);
                    break;
                case 4:
                    System.out.println("\nPlease enter name of the movie (as in the input file)");
                    sc.nextLine();
                    String movie = sc.nextLine();
                    bsg.displayActorsOfMovie(movie);
                    break;
                case 5:
                    System.out.println("\nPlease enter name of the first movie (as in the input file)");
                    sc.nextLine();
                    String firstMovieR = sc.nextLine();
                    System.out.println("\nPlease enter name of the second movie (as in the input file)");
                    String secondMovieR = sc.nextLine();

                    bsg.findMovieRelation(firstMovieR, secondMovieR);

                    break;
                case 6:
                    System.out.println("\nPlease enter name of the first movie (as in the input file)");
                    sc.nextLine();
                    String firstMovieT = sc.nextLine();
                    System.out.println("\nPlease enter name of the second movie (as in the input file)");
                    String secondMovieT = sc.nextLine();

                    bsg.findMovieTransRelation(firstMovieT, secondMovieT);
                    break;
                case 7:
                    System.out.println("Please provide a input file name (full path)");

                    do{
                        sc.nextLine();
                        String inputFile = sc.nextLine();

                        if("abort".equals(inputFile) || "\"abort\"".equals(inputFile)){
                            aborted = true;
                            System.out.println("Aborting . . ");
                            break;
                        }

                        System.out.println("Reading file . . .\n");
                        try{
                            bsg.readActMovFile(inputFile);

                            fileReadSuccess = true;
                        }
                        catch (Exception ex){
                            System.out.println("\nPlease try again with a valid file name or Enter \"abort\" to quit! \n");
                        }


                    }while (!fileReadSuccess);
                    break;
                case 8:
                    System.out.println("Please provide a input file name (full path)");

                    do{
                        sc.nextLine();
                        String inputFile = sc.nextLine();

                        if("abort".equals(inputFile) || "\"abort\"".equals(inputFile)){
                            aborted = true;
                            System.out.println("Aborting . . ");
                            break;
                        }

                        System.out.println("Reading file . . .\n");
                        try{
                            bsg.graph = new Graph();
                            bsg.readActMovFile(inputFile);

                            fileReadSuccess = true;
                        }
                        catch (Exception ex){
                            System.out.println("\nPlease try again with a valid file name or Enter \"abort\" to quit! \n");
                        }


                    }while (!fileReadSuccess);
                    break;
                case 0:
                    System.out.println("Thanks for playing around. Hope you had a great time!");
                    aborted = true;
                    break;
                default:
                    System.out.println("You have entered an invalid option. Please try again.");
            }
        }





    }

}
