import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s;
        try {
            s = new Scanner(new File("res/graph-001.alists"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        GraphLinearDirectedWeight g = GraphLinearDirectedWeight.inputByAdjacencyListsWeight(s);
        try {
            PrintStream w = new PrintStream("fw_out/res.amatrix");
//            g.outputAdjacencyMatrix(w);
            GraphLinear.printMatrix(w, g.weightMatrix);
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        }

        GraphLinear.floydWarshallAlgorithm(g);
    }
}
