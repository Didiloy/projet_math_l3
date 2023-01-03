import fr_departments.FR_AllDepartments;

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
            GraphLinear.printMatrix(w, g.weightMatrix);
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        }

        GraphLinear.floydWarshallAlgorithm(g);

        //Lire les entiers au clavier pour afficher les plus courts chemin
        try {
            s = new Scanner(new File("res/paths.command"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int dep = -1;
        int arr = -1;
        do {
            if(!s.hasNextInt()) break;
            dep = s.nextInt();
            if(!s.hasNextInt()) break;
            arr = s.nextInt();
            System.out.print("Plus court chemin de " + dep + " vers " + arr + ": (");
            GraphLinear.convertStrToArrayList(GraphLinear.printPath(g.pathsMatrix, dep, arr)).forEach(e -> System.out.print(FR_AllDepartments.getDepartment(e).getName() + " "));
            System.out.println(")");
        } while (dep != 0 && arr != 0);

        s.close();
    }
}
