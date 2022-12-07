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
      //TODO
        return null;
    }

    public static GraphLinearDirected inputByAdjacencyListsWeight(Scanner input){
        //TODO
        return null;
    }
}
