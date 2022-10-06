import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // ASSUMING ZERO BASED INDEXING THEREFORE IN ARRAYS
    // 1 to n ==> 0 to n-1

    public static void main(String[] args) throws IOException {

//        File file = new File("/Users/lindathao/IdeaProjects/cs3010_proj1_v2/src/test.txt");
//
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//
//        int n;
//        String st ;
//        ArrayList<String> arrayList = new ArrayList<>();
//        while ((st = bufferedReader.readLine()) != null) {
//            arrayList.add(st);
//        }
//
//        Scanner scanner = new Scanner(System.in);
//        n = Integer.parseInt(arrayList.get(0));


        Scanner input = new Scanner(System.in);

        int numOfEqns, opt, coef;

        System.out.println("Please select an option: ");
        System.out.println(" 1. Manually Enter Coefficients for Equations");
        System.out.println(" 2. Import Data fom Text File");

        opt = input.nextInt();

        System.out.println("Please enter the number of linear equations ( between 1 and 10 )");
        numOfEqns = input.nextInt();

        // checks to ensure that number of linear eqns is between 1 and 10
        while (numOfEqns < 1 || numOfEqns > 10) {
            System.out.println("ERROR!");
            System.out.println("Please enter the number of linear equations ( between 1 and 10 )");
            numOfEqns = input.nextInt();
        }

        // array to store the coefficients
        double[][] coeffArr = new double[numOfEqns][numOfEqns];
        double[] bVal = new double[numOfEqns];
        int[] sVector = new int[numOfEqns];
//        int bVal = 0;

        switch (opt) {

            //    Give them the choice to enter the coefficients from the command line
            //         - (by asking for each row that includes the b value)
            case 1:

                System.out.println("Please enter the appropriate coefficient: ");

                // i is row
                // j is col
                for (int i = 0; i < numOfEqns; i++) {

                    for (int j = 0; j < numOfEqns; j++) {
                        System.out.println("ROW " + i);
                        System.out.println("COL " + j);
                        System.out.println("Enter coefficient for X" + j);
                        coeffArr[i][j] = input.nextInt();
                    }

                    System.out.println("Please enter b-value for the equation " + i + ": ");
                    bVal[i] = input.nextInt();

                }


//                double[][] coeff = new double[n][n];
//                int k = 1;
//                for (int i = 0; i < n; i++) {
//                    String[] temp = arrayList.get(k++).split(" ");
//                    for (int j = 0; j < n; j++) {
//                        coeff[i][j] = Double.parseDouble(temp[j]);
//                    }
//                }
//
//                double[] vector = new double[n];
//                String[] temp = arrayList.get(k).split(" ");
//                for (int i = 0; i < n; i++) {
//                    vector[i] = Double.parseDouble(temp[i]);
//                }

                Main main = new Main();
                main.SPPGaussian(coeffArr, bVal);


                break;
            case 2:
                
                break;
            default:
                break;
        }

    }

    private void SPPGaussian(double[][] coeff, double[] vector) {

        int n = coeff.length;
        double[] sol = new double[n];
        int[] ind = new int[n];
        for (int i = 0; i < n; i++) {
            ind[i] = i;
        }
        SPPForwardElimination(coeff, vector, ind);
        SPPBackSubst(coeff, vector, sol, ind);

        System.out.println(Arrays.toString(sol));
    }

    private void SPPForwardElimination(double[][] coeff, double[] vector, int[] ind) {
        int n = coeff.length;
        double[] scaling = new double[n];

        for (int i = 0; i < n; i++) {
            double smax = 0;
            for (int j = 0; j < n; j++) {
                smax = Math.max(smax, coeff[i][j]);
            }
            scaling[i] = smax;
        }
        for (int k = 0; k < n; k++) {
            double rmax = 0;
            int maxInd = k;

            for (int i = k; i < n; i++) {
                double r = coeff[ind[i]][k] / scaling[ind[i]];
                if (r > rmax) {
                    rmax = r;
                    maxInd = i;
                }
            }
            swap(ind, maxInd, k);

            for (int i = k + 1; i < n; i++) {
                double mult = coeff[ind[i]][k] / coeff[ind[k]][k];
                for (int j = k + 1; j < n; j++) {
                    coeff[ind[i]][j] = coeff[ind[i]][j] - mult * coeff[ind[k]][j];
                }
                vector[ind[i]] = vector[ind[i]] - mult * vector[ind[k]];
            }
        }
    }

    private void SPPBackSubst(double[][] coeff, double[] vector, double[] sol, int[] ind) {
        int n = coeff.length;
        for (int i = n - 1; i >= 0; i--) {
            double sum = vector[ind[i]];
            for (int j = i + 1; j < n; j++) {
                sum = sum - coeff[ind[i]][j] * sol[j];
            }
            sol[i] = sum / coeff[ind[i]][i];
        }
    }

    private void swap(int[] ind, int i, int j) {
        int temp = ind[i];
        ind[i] = ind[j];
        ind[j] = temp;
    }
}
