import java.util.Scanner;

public class MatrixOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of matrices: ");
        int nMatrices = scanner.nextInt();
        int[][][] matrices = new int[nMatrices][][];

        for (int i = 0; i < nMatrices; i++) {
            System.out.println("Matrix " + i + ":");
            System.out.print("Enter the number of rows: ");
            int rows = scanner.nextInt();
            System.out.print("Enter the number of columns: ");
            int cols = scanner.nextInt();
            matrices[i] = createMatrix(rows, cols, scanner);
        }

        System.out.print("Enter the operation you want to perform (addition, subtraction, multiplication): ");
        String operation = scanner.next().toLowerCase();

        switch (operation) {
            case "addition":
                performAddition(matrices);
                break;
            case "subtraction":
                performSubtraction(matrices);
                break;
            case "multiplication":
                performMultiplication(matrices);
                break;
            default:
                System.out.println("Invalid operation!");
        }
    }

    public static int[][] createMatrix(int rows, int cols, Scanner scanner) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter element (" + i + ", " + j + "): ");
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public static void performAddition(int[][][] matrices) {
        int[][] result = matrices[0];

        for (int i = 1; i < matrices.length; i++) {
            if (matrices[i].length != result.length || matrices[i][0].length != result[0].length) {
                System.out.println("Error");
                return;
            }
            for (int row = 0; row < result.length; row++) {
                for (int col = 0; col < result[0].length; col++) {
                    result[row][col] += matrices[i][row][col];
                }
            }
        }

        System.out.println("Result of addition:");
        printMatrix(result);
    }

    public static void performSubtraction(int[][][] matrices) {
        int[][] result = matrices[0];

        for (int i = 1; i < matrices.length; i++) {
            if (matrices[i].length != result.length || matrices[i][0].length != result[0].length) {
                System.out.println("Error");
                return;
            }
            for (int row = 0; row < result.length; row++) {
                for (int col = 0; col < result[0].length; col++) {
                    result[row][col] -= matrices[i][row][col];
                }
            }
        }

        System.out.println("Result of subtraction:");
        printMatrix(result);
    }

    public static void performMultiplication(int[][][] matrices) {
        if (matrices.length < 2) {
            System.out.println("Error");
            return;
        }

        int[][] result = matrices[0];

        for (int i = 1; i < matrices.length; i++) {
            if (result[0].length != matrices[i].length || result.length == 0 || matrices[i].length == 0) {
                System.out.println("Error");
                return;
            }
            result = multiplyMatrices(result, matrices[i]);
        }

        if (result.length == 0) {
            System.out.println("Error");
            return;
        }

        System.out.println("Result of multiplication:");
        printMatrix(result);
    }

    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        if (cols1 != rows2 || rows1 == 0 || cols1 == 0 || cols2 == 0) {
            return new int[0][0];
        }

        int[][] result = new int[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
