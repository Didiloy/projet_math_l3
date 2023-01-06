package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class GraphLinearDirectedWeight extends GraphLinear {
    protected int[][] weightMatrix;
    public int[][] pathsMatrix;

    public GraphLinearDirectedWeight(int n) {
        super(n);
        this.weightMatrix = new int[this.nbSommet][this.nbSommet];
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


    public void computeWeightMatrix() {
        int[][] tab = new int[this.nbSommet][this.nbSommet];
        for (int[] in : tab) {
            Arrays.fill(in, Integer.MAX_VALUE);
        }

        try {
            Scanner s = new Scanner(new File("res/fr-dept-distances.data"));
            while (s.hasNextInt()) {
                int x = s.nextInt();
                int y = s.nextInt();
                int dist = s.nextInt();
                tab[x - 1][y - 1] = dist;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.weightMatrix = tab;
    }

    public static GraphLinearDirectedWeight inputByAdjacencyMatrixWeight(Scanner input) {
        int order = input.nextInt();
        GraphLinearDirectedWeight g = new GraphLinearDirectedWeight(order);
        byte[][] matrix = new byte[order][order];
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                matrix[i][j] = input.nextByte();
            }
        }
        g.setAdjacencyMatrix(matrix);
        g.computeAdjacencyLists();
        g.computeWeightMatrix();
        return g;
    }

    public static GraphLinearDirectedWeight inputByAdjacencyListsWeight(Scanner input) {
        int order = input.nextInt();
        GraphLinearDirectedWeight g = new GraphLinearDirectedWeight(order);

        for (int i = 0; i < g.order(); i++) {
            int[] tmp = new int[order];
            int compteur = 0;
            while (true) {
                int val = input.nextInt();
                if (val == 0) {
                    break;
                }
                tmp[compteur] = val;
                compteur++;
            }
            g.adjacencyList[i] = new int[compteur];
            for (int j = 0; j < g.adjacencyList[i].length; j++) {
                g.adjacencyList[i][j] = tmp[j];
            }
        }
        g.computeAdjacencyMatrix();
        g.computeWeightMatrix();
        return g;
    }
}
