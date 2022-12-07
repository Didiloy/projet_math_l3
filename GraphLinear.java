import java.io.PrintStream;
import java.util.Scanner;

public abstract class GraphLinear {
    protected byte[][] adjacencyMatrix;
    protected int[][] adjacencyList;

    protected final int nbSommet;
    public GraphLinear(int n) {
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

}
