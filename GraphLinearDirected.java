import fr_departments.FR_Department;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import static fr_departments.FR_AllDepartments.getDepartment;

public class GraphLinearDirected {
    private byte[][] adjacencyMatrix;
    private int[][] adjacencyList;

    private final int nbSommet;

    public GraphLinearDirected(int n) {
        this.nbSommet = n;
        this.adjacencyMatrix = new byte[this.nbSommet][this.nbSommet];
        this.adjacencyList = new int[this.nbSommet][this.nbSommet];
    }

    public int order() {
        return this.nbSommet;
    }

    public boolean isVertex(int vertex) {
        return vertex > 0 && vertex < this.nbSommet;
    }

    public int[] getVertexSet() {
        int[] tmp = new int[this.nbSommet];
        for (int i = 0; i < this.nbSommet; i++) {
            tmp[i] = i + 1;
        }
        return tmp;
    }

    public void setAdjacencyMatrix(byte[][] matrix) {
        this.adjacencyMatrix = matrix;
    }

    public byte[][] getAdjacencyMatrix() {
        return this.adjacencyMatrix;
    }

    public boolean isEdge(int source, int target) throws NotInGraphException {
        if (!(isVertex(source) && isVertex(target))) throw new NotInGraphException();
        return (this.adjacencyMatrix[source - 1][target - 1] == 1);
    }

    public void setAdjacencyList(int[][] lists) {
        this.adjacencyList = lists;
    }

    public int[][] getAdjacencyLists() {
        return this.adjacencyList;
    }

    public int[] getAdjacencyList(int vertex) {
        return this.adjacencyList[vertex];
    }

    public int getOutDegree(int vertex) throws NotInGraphException {
        if (!isVertex(vertex)) throw new NotInGraphException();
        return this.adjacencyList[vertex].length;
    }

    public int getInDegree(int vertex) throws NotInGraphException {
        if (!isVertex(vertex)) throw new NotInGraphException();
        return this.computeInDegree(vertex);
    }

    private int computeInDegree(int vertex) {
        int n = 0;
        for (int i = 0; i < this.order(); i++) {
            if (i == vertex) continue;
            if (this.adjacencyMatrix[i][vertex] == 1) n++;
        }
        return n;
    }

    public int getDegree(int vertex) throws NotInGraphException {
        if (!isVertex(vertex)) throw new NotInGraphException();
        return this.getOutDegree(vertex) + this.getInDegree(vertex);
    }

    public void computeAdjacencyMatrix() {
        byte[][] tab = new byte[this.adjacencyList.length][this.adjacencyList.length];
        for (byte[] bytes : tab) {
            Arrays.fill(bytes, (byte) 0);
        }

        for (int i = 0; i < this.adjacencyList.length; i++) {
            for (int j = 0; j < this.adjacencyList[i].length; j++) {
                tab[i][this.adjacencyList[i][j] - 1] = 1;
            }
        }
        this.adjacencyMatrix = tab;
    }

    public void computeAdjacencyLists() {
        for (int x = 0; x < this.adjacencyMatrix.length; x++) {
            int[] listeAdjacente;
            int compteur = 0;
            for (int i = 0; i < this.adjacencyMatrix.length; i++) {
                if (this.adjacencyMatrix[x][i] == 1) {
                    compteur++;
                }
            }

            listeAdjacente = new int[compteur];
            compteur = 0;
            for (int i = 0; i < this.adjacencyMatrix.length; i++) {
                if (this.adjacencyMatrix[x][i] == 1) {
                    listeAdjacente[compteur] = i + 1;
                    compteur++;
                }
            }
            this.adjacencyList[x] = listeAdjacente;
        }
    }

    public static GraphLinearDirected inputByAdjacencyMatrix(Scanner input){
        int order = input.nextInt();
        GraphLinearDirected g = new GraphLinearDirected(order);
        byte[][] matrix = new byte[order][order];
        for (int i = 0; i < order; i++){
            for (int j = 0; j < order; j++){
                matrix[i][j] = input.nextByte();
            }
        }
        g.setAdjacencyMatrix(matrix);
        g.computeAdjacencyLists();
        return g;
    }

    public static GraphLinearDirected inputByAdjacencyLists(Scanner input){
        int order = input.nextInt();
        GraphLinearDirected g = new GraphLinearDirected(order);

        int[][] tableau = new int[order][];
        for(int i = 0; i < g.order(); i++){
            int[] tmp = new int[order];
            int compteur = 0;
            while(true){
                int val = input.nextInt();
                if(val == 0){
                    break;
                }
                tmp[compteur] = val;
                compteur++;
            }
            g.adjacencyList[i] = new int[compteur];
//            System.arraycopy(tmp, 0, tableau[i], 0, tableau[i].length);
            for(int j = 0; j < g.adjacencyList[i].length; j++){
                g.adjacencyList[i][j] = tmp[j];
            }
        }
//        g.setAdjacencyList(tableau);
        g.computeAdjacencyMatrix();
        return g;
    }

    public void outputAdjacencyMatrix(PrintStream output){
        output.println(this.adjacencyMatrix.length);
        for (int i = 0; i < this.adjacencyMatrix.length; i++) {
            for (int j = 0; j < this.adjacencyMatrix[0].length; j++) {
                output.print(this.adjacencyMatrix[i][j] + (j == this.adjacencyMatrix[0].length - 1 ? "" : " "));
            }
            output.println();
        }
    }

    public void outputAdjacencyLists(PrintStream output){
        output.println(this.adjacencyList.length);
        for (int i = 0; i < this.adjacencyMatrix.length; i++) {
            output.print(i + " ");
            for (int j = 0; j < this.adjacencyMatrix[0].length; j++) {
                if(this.adjacencyMatrix[i][j] == 0) continue;
                output.print(j+ (j == this.adjacencyMatrix[0].length - 1 ? "" : " "));
            }
            output.println(0);
        }
    }

    public static void main(String[] args) {
        Scanner s = null;
        try {
            s = new Scanner(new File("dep.command"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(true){
            int e = s.nextInt();
            if(e == 0) break;
            FR_Department dep = getDepartment(e);
            System.out.println("[ " + dep.getCode() + " " + dep.getName() + " ]");
            System.out.println("\t");
        }

//        GraphLinearDirected g = inputByAdjacencyLists(s);
//        try{
//            PrintStream w = new PrintStream(new File("res.amatrix"));
//            g.outputAdjacencyMatrix(w);
//        }catch(FileNotFoundException e){
//            System.out.println("not found");
//        }
    }
}
