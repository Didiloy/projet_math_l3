package src;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class GraphLinear {
    protected byte[][] adjacencyMatrix;
    protected int[][] adjacencyList;

    protected int nbSommet;

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

    public static void printMatrix(PrintStream output, int[][] matrix) {
        output.println(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                output.print(matrix[i][j] + (j == matrix[0].length - 1 ? "" : " "));
            }
            output.println();
        }
    }

    public static void printMatrix(PrintStream output, byte[][] matrix) {
        output.println(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                output.print(matrix[i][j] + (j == matrix[0].length - 1 ? "" : " "));
            }
            output.println();
        }
    }

    public void outputAdjacencyLists(PrintStream output) {
        output.println(this.adjacencyList.length);
        for (int i = 0; i < this.adjacencyMatrix.length; i++) {
            output.print(i + " ");
            for (int j = 0; j < this.adjacencyMatrix[0].length; j++) {
                if (this.adjacencyMatrix[i][j] == 0) continue;
                output.print(j + (j == this.adjacencyMatrix[0].length - 1 ? "" : " "));
            }
            output.println(0);
        }
    }

    /**
     * Fonction récursive qui permet d'afficher les chemins les plus courts d'un
     * point à un autre.
     * @param chemins
     * @param dep
     * @param arr
     */
    public static String printPath(int[][] chemins, int dep, int arr){
        String s = "";

        while(chemins[arr][dep] != (arr + 1)){
            s = s + " " + (dep + 1);
            dep = chemins[arr][dep] - 1;
        }
        s = s + " " + (dep + 1);

        return s + " " + (arr + 1);
    }

    public static ArrayList<Integer> convertStrToArrayList(String s){
        Scanner sc = new Scanner(s);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        while (sc.hasNextInt()){
            arrayList.add(sc.nextInt());
        }
        sc.close();
        return arrayList;
    }

    /**
     * Fonction qui permet d'afficher tous les chemins les plus courts entre chaque
     * couple de points.
     * @param chemins la matrice des chemins
     */
    public static void printAnswer(PrintStream s, int[][] chemins){
        int n = chemins.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j && chemins[i][j] != -1){
                    s.println("Plus court chemin de " + (i + 1) + " vers " + ( j + 1) + ": (" + printPath(chemins, i, j) + ")");
                }
            }
        }
    }

    public static void floydWarshallAlgorithm(GraphLinearDirectedWeight g) {
        int inf = Integer.MAX_VALUE;
        int[][] weightMatrixCopy = g.weightMatrix.clone();
//        //TODO test
//        g.nbSommet = 5; //TODO supprimer ça
//        int[][] weightMatrixCopy = new int[][]{
//                {0, 3, 8, inf, -4},
//                {inf, 0, inf, 1, 7},
//                {inf, 4, 0, inf, inf},
//                {2, inf, -5, 0, inf},
//                { inf, inf, inf, 6, 0}
//        };

        int[][] chemins = new int[g.nbSommet][g.nbSommet];
        for (int[] a : chemins) {
            Arrays.fill(a, -1);
        }

        for (int dep = 0; dep < g.nbSommet; dep++) {
            for (int arr = 0; arr < g.nbSommet; arr++) {
                if (dep != arr && weightMatrixCopy[dep][arr] != inf) {
                    chemins[dep][arr] = dep + 1; //ajouter +1 parce que les cases des tableaux commencent a 0
                }
            }
        }

        for (int inter = 0; inter < g.nbSommet; inter++) {
            for (int dep = 0; dep < g.nbSommet; dep++) {
                for (int arr = 0; arr < g.nbSommet; arr++) {
//                    if(dep == arr) continue;
                    if(weightMatrixCopy[dep][inter] == inf || weightMatrixCopy[inter][arr] == inf){
                        continue;
                    }
                    if ((weightMatrixCopy[dep][inter] + weightMatrixCopy[inter][arr]) < weightMatrixCopy[dep][arr]) {
                        weightMatrixCopy[dep][arr] = weightMatrixCopy[dep][inter] + weightMatrixCopy[inter][arr];
                        chemins[dep][arr] = chemins[inter][arr];
                    }
                }
                if (weightMatrixCopy[dep][dep] < 0) {
                    System.out.println("cycle de poids négatif");
                    return;
                }
            }
        }
        g.pathsMatrix = chemins;

        //=============
        // output les matrices dans des fichiers
        //=============
        try {
            PrintStream w = new PrintStream("fw_out/graph-011.paths");
            GraphLinear.printMatrix(w,chemins);
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        }

        try {
            PrintStream w = new PrintStream("fw_out/graph-001.costs");
            GraphLinear.printMatrix(w,weightMatrixCopy);
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        }

        try {
            PrintStream w = new PrintStream("fw_out/all_paths.txt");
            printAnswer(w, chemins);
        } catch (FileNotFoundException e) {
            System.out.println("not found");
        }

    }

}
