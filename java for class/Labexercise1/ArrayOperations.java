public class ArrayOperations {
    public static void main(String[] args) {
        int N = 5;
        double[] a = {2, 3, 5, 6, 7};
        double[] b = {4, 5, 7, 8, 9};

        System.out.println("Array a values:");
        for (int i = 0; i < N; i++) {
            System.out.println(a[i]);
        }

        double max = a[0];
        for (int i = 1; i < N; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        System.out.println("Maximum value in array a: " + max);

        double[][] c = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                c[i][j] = a[i] + b[j];
            }
        }

        System.out.println("Resulting matrix c (after addition):");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
    }
}