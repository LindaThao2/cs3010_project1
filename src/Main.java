//Write a program that asks the user for the number of linear equations to solve (let’s say n <=10)
// using the Gaussian elimination with Scaled Partial Pivoting method. Ask the user to first enter
// the number of equations and then give them the choice to enter the coefficients from the command line
// (by asking for each row that includes the b value) or have them enter a file name which has the
// augmented coefficient matrix (including the b values) in a simple text file format as seen below for an
// example of 3 equations :
//
//        E.g. the contents of a file for 3 linear equations 2x+3y = 8, -x+2y-z=0, 3x+2z=9 will be
//        2 3 0 8
//        -1 2 -1 0
//        3 0 2 9
//
//        Your program should output the scaled ratios at each step, and mention the pivot row selected based on
//        the scaled ratio. Show the intermediate matrix at each step of the Gaussian Elimination process.
//        Finally, the final output of your program should be the solution in the following format :
//        x=1
//        y=2
//        z=3

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        int numOfEqns, opt;

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

        switch (opt) {

            //    Give them the choice to enter the coefficients from the command line
            //    - (by asking for each row that includes the b value)
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

                SPPGaussian(coeffArr, bVal);

                break;
            case 2:

                File file = new File("/Users/lindathao/IdeaProjects/cs3010_proj1_v2/src/test.txt");

                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                String st ;

                // rows
                int i = 0;
                while ((st = bufferedReader.readLine()) != null) {

                    String[] temp = st.split(" ");

                    for (int j = 0; j < temp.length; j++) {

                        if(j == (temp.length - 1) ) {

                            bVal[i] = Double.parseDouble(temp[j]);

                        }else{
                            coeffArr[i][j] = Double.parseDouble(temp[j]);
                        }
                    }
                    i++;
                }

                // i is row
                // j is col
//              Print Matrix
                for (int k = 0; k < numOfEqns; k++) {
                    for (int j = 0; j < numOfEqns; j++) {
                        System.out.print(coeffArr[k][j] + " ");
                    }
                }

                SPPGaussian(coeffArr, bVal);

                break;
            default:
                break;
        }
    }

    public static void SPPGaussian(double[][] coeff, double[] vector) {

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

    public static void SPPForwardElimination(double[][] coeff, double[] vector, int[] ind) {
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

    public static void SPPBackSubst(double[][] coeff, double[] vector, double[] sol, int[] ind) {
        int n = coeff.length;
        for (int i = n - 1; i >= 0; i--) {
            double sum = vector[ind[i]];
            for (int j = i + 1; j < n; j++) {
                sum = sum - coeff[ind[i]][j] * sol[j];
            }
            sol[i] = sum / coeff[ind[i]][i];
        }
    }

    public static void swap(int[] ind, int i, int j) {
        int temp = ind[i];
        ind[i] = ind[j];
        ind[j] = temp;
    }
}
