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

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int numOfEqns, opt, coef;
        String fileName = "";

        System.out.println("Please select an option: ");
        System.out.println(" 1. Manually Enter Coefficients for Equations");
        System.out.println(" 2. Import Data fom Text File");

        opt = input.nextInt();

        System.out.println("Please enter the number of linear equations ( between 1 and 10 )");
        numOfEqns = input.nextInt();

        // checks to ensure that number of linear eqns is between 1 and 10
        while (numOfEqns < 1 || numOfEqns > 10){
            System.out.println("ERROR!");
            System.out.println("Please enter the number of linear equations ( between 1 and 10 )");
            numOfEqns = input.nextInt();
        }

        // array to store the coefficients
        int[][] coeffArr = new int[numOfEqns][numOfEqns];
        int[] bVal = new int[numOfEqns];
//        int bVal = 0;


        switch (opt){

            //    Give them the choice to enter the coefficients from the command line
            //         - (by asking for each row that includes the b value)
            case 1:

                System.out.println("Please enter the appropriate coefficient: ");

                // i is row
                // j is col
                for(int i = 1; i < numOfEqns + 1; i++){

                    for(int j = 1; j < numOfEqns + 1; j ++) {
                        System.out.println("ROW " + i);
                        System.out.println("COL " + j);
                        System.out.println("Enter coefficient for X" + j);
                        coeffArr[i - 1][j - 1] = input.nextInt();
                    }

                    System.out.println("Please enter b-value for the equation " + i + ": ");
                        bVal[i - 1] = input.nextInt();


                }


                break;
            case 2:
                break;
            default:
                break;

        }

        System.out.println("TEST PRINT:");

        for(int i = 0; i < coeffArr.length; i++){

            for (int j = 0; j < coeffArr.length; j++) {
                System.out.println(coeffArr[i][j]);
            }

            System.out.println("b value: " + bVal[i]);

        }



//    Ask the user for the number of linear equations to solve (let’s say n <=10)
//    Ask the user to first enter the number of equations


//    or have them enter a file name which has the augmented coefficient matrix (including the b values)
//    in a simple text file format as seen below for an



        // New code that was discussed with alondra




        // STEP 1:
        // Find largest numbers in every row.
        // Find S-vector

        for(int i = 0; i < numOfEqns; i++){

            // maxTemp will hold the temp max value, starting with first number in each row automatically set as the highest
            int maxTemp[i] = cm[i][0];

            // search through maxtrix
            for(int j = 1; j < numOfEqns; j++){

                // compare numbers within the rows to see which one is the largest
                if (cm[i][j] > maxTemp
                maxtemp = cm[i][j];
            }

            // Store all max temps that were found for each row into S array
            s[i] = maxTemp;
        }


        // STEP 2: Find SR

        int pivot_index = 0;
        int pivot_value = -1;

        for(int i = 0; i < sr.length; i++){

            s[i] = (cm[i][j])/s[i];

            //store the largest ration to help find the pivot row/number
            if(s[i] > pivot_value){
                pivot_value = s[i];
                pivot_index = i;
            }
        }






    }


    //STEP 3: populate the multiplier
    public static void matrix_update(int sizeOfMultiplier, int pivotValue){

        int[] multiplier = new int[sizeOfMultiplier];

        for(int i; ){

            cm[i][0] = multiplier[i] * pivotValue;

        }



    }


}