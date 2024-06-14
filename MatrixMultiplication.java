import java.util.Scanner;

public class MatrixMultiplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Matrix Multiplication Program");

        // Input Matrix A
        int[][] A = inputMatrix(scanner, "A");

        // Input Matrix B
        int[][] B = inputMatrix(scanner, "B");

        // Get dimensions
        int n = A.length;
        int m = A[0].length;
        int m2 = B.length;
        int p = B[0].length;

        // Validate if multiplication is possible
        if (m != m2) {
            System.out.println("Error: Number of columns in Matrix A must be equal to number of rows in Matrix B.");
            return;
        }

        // Perform matrix multiplication
        int[][] C = multiplyMatrices(A, B, n, m, p);

        // Display matrices
        printMatrix(A, "A");
        printMatrix(B, "B");
        printMatrix(C, "C");

        scanner.close();
    }

    private static int[][] inputMatrix(Scanner scanner, String matrixName) {
        int rows, cols;

        while (true) {
            try {
                System.out.print("Enter dimensions of Matrix " + matrixName + " (rows, columns): ");
                String[] dimensions = scanner.nextLine().trim().split(",");
                rows = Integer.parseInt(dimensions[0].trim());
                cols = Integer.parseInt(dimensions[1].trim());

                if (rows <= 0 || cols <= 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter valid positive integers for dimensions (rows, columns).");
            }
        }

        int[][] matrix = new int[rows][cols];
        System.out.println("Enter the elements of Matrix " + matrixName + " (" + rows + "x" + cols + "):");

        for (int i = 0; i < rows; i++) {
            while (true) {
                try {
                    System.out.print("Row " + (i + 1) + ": ");
                    String[] rowElements = scanner.nextLine().trim().split("\\s+");
                    if (rowElements.length != cols) {
                        throw new Exception();
                    }
                    for (int j = 0; j < cols; j++) {
                        matrix[i][j] = Integer.parseInt(rowElements[j].trim());
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter exactly " + cols + " integers separated by spaces.");
                }
            }
        }
        return matrix;
    }

    private static int[][] multiplyMatrices(int[][] A, int[][] B, int n, int m, int p) {
        int[][] C = new int[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < m; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private static void printMatrix(int[][] matrix, String name) {
        System.out.println("\nMatrix " + name + ":");
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
