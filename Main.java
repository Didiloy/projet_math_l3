import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s;
        try {
            s = new Scanner(new File("graph-001.alists"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        GraphLinearDirectedWeight g = GraphLinearDirectedWeight.inputByAdjacencyListsWeight(s);
        try {
            PrintStream w = new PrintStream(new File("res.amatrix"));
//            g.outputAdjacencyMatrix(w);
            g.outputWeightMatrix(w);
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        }

        GraphLinear.floydWarshallAlgorithm(g);
    }
}
