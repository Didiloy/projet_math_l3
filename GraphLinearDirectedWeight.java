import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class GraphLinearDirectedWeight extends GraphLinear{
    protected int[][] weightMatrix;

    public GraphLinearDirectedWeight(int n) {
        super(n);
        this.weightMatrix = new int[this.nbSommet][this.nbSommet];
    }

    public void outputWeightMatrix(PrintStream output){
        //TODO
    }

    public void computeWeightMatrix() {
        //TODO
    }

    public static GraphLinearDirected inputByAdjacencyMatrixWeight(Scanner input){
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
        g.computeWeightMatrix();
        return g;
    }

    public static GraphLinearDirected inputByAdjacencyListsWeight(Scanner input){
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
        g.computeWeightMatrix();
        return g;
    }
}
