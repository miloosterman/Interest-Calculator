
/*************************************************************************
* Name: Interest Calculator
* File: MOsterman_HW3.java
* Author: Milo Osterman
* Date: 2/12/2021
*
* HW 3 Methods and Method Overloading
* You will write the Java code for a program that uses a method to calculate the monthly payment for a loan.
* The formula for calculating a payment is shown further down. This application
* will produce a list of possible loan payments based on a series of annual interest
* rates starting with a user entered starting annual interest rate which is
* incremented in a loop until it reaches a user entered ending annual interest rate.
* The rate will be incremented by an increment rate which the user also enters.
*
* Sample payment schedule with a starting rate of 4.00, ending rate of 6.00
* annual increment of 0.25, first term of 15, last term of 30, term increment 5
* and loan amount of 100000
*
*   Interest  15 Years  20 Years  25 Years  30 Years
*			Rate
*   4.0000 		739.69   605.98    527.84    477.42
*   4.2500      752.28   619.23    541.74    491.42
*   4.5000      764.99   632.65    555.83    506.69
*   4.7500      777.83   646.22    570.12    521.65
*   5.0000      790.79   659.96    584.59    536.82
*   5.2500      803.88   673.84    599.25    552.20
*   5.5000      817.08   687.89    614.09    567.79
*   5.7500      830.41   702.08    629.11    583.57
*   6.0000      843.86   716.43    644.30    599.55
*
*************************************************************************/

package mOsterman_HW3;

import java.util.Scanner;

public class MOsterman_HW3 {

	static Scanner sc = new Scanner(System.in);

	// Main acquires input and invokes the payment calculation
	public static void main(String[] args) {
		// Declare and initialize variables for calculations
		double startingInterestRate = getInterestRate("Enter the starting annual interest rate as a percent (n.nnn): ",
				0, 100);
		double endingInterestRate = getInterestRate("Enter the ending annual interest rate as a percent (n.nnn): ", 0,
				100);
		while (!validateLastGreaterThanFirst(endingInterestRate, startingInterestRate)) { //Validate that ending interest rate is greater
			endingInterestRate = getInterestRate(
					"Ending interest rate cannot be less than starting interest rate. Enter new ending rate: ", 0, 100);
		}
		double interestRateIncrement = getInterestRate(
				"Enter the annual interest rate increment as a percent (n.nnn): ", 0, 100);
		int firstTerm = Input.getInteger("Enter the first term in years for calculating payments: ", Integer.MIN_VALUE,
				Integer.MAX_VALUE);
		int lastTerm = Input.getInteger("Enter the last term in years for calculating payments: ", Integer.MIN_VALUE,
				Integer.MAX_VALUE);
		while (!validateLastGreaterThanFirst(lastTerm, firstTerm)) { //Validate last term is greater than first term
			lastTerm = Input.getInteger(
					"Last term cannot be less than first term. Enter new last term: ", Integer.MIN_VALUE,
					Integer.MAX_VALUE);
		}
		int termIncrement = Input.getInteger("Enter the term increment in years: ", Integer.MIN_VALUE,
				Integer.MAX_VALUE);
		double loanAmount = Input.getDouble("Enter the loan amount: ", 0, Integer.MAX_VALUE);
		// Call main payment calculator function
		paymentCalc(startingInterestRate, endingInterestRate, interestRateIncrement, firstTerm, lastTerm, termIncrement,
				loanAmount);

	}

	// Calculator function
	private static void paymentCalc(double startInterest, double endInterest, double incAmt, int firstYearRepay,
			int lastYearRepay, int yearsInc, double loanAmt) {
		// Print header with formatting
		System.out.println("Payment Schedule");
		System.out.println("");
		System.out.println("Interest");
		System.out.print("  rate    ");
		for (int j = firstYearRepay; j <= lastYearRepay; j += yearsInc) {
			System.out.printf("%11s ", j + " years");
		}
		System.out.print("\n\n");
		// Display interest rate with increments
		for (double i = startInterest; i <= (endInterest + incAmt); i += incAmt) {
			System.out.print(String.format("%.4f", i * 100) + "  ");
			// Display possible loan payments with nested loop
			for (int j = firstYearRepay; j <= lastYearRepay; j += yearsInc) {
				double mir = i / 12;
				double mtp = j * 12;
				double numerator = mir * (Math.pow((1 + mir), mtp));
				double denominator = (Math.pow((1 + mir), mtp) - 1);
				double annuityFactor = numerator / denominator;
				double payment = loanAmt * annuityFactor;
				String paymentStr = String.format("%.2f", payment);
				System.out.printf("%12s", paymentStr);

			}
			System.out.println();
		}
	}

	// Get interest rate and validate that is between 0 and 100
	public static double getInterestRate(String msg, double min, double max) {
		double interestRate = Input.getDouble(msg, min, max);

		double interestRateAsDecimal = interestRate / 100;
		
		return interestRateAsDecimal;
	}
	
	//Validation method to check that greater number is greater than smaller number
	public static boolean validateLastGreaterThanFirst(double greaterNum, double smallerNum) {
		if (greaterNum > smallerNum) {
			return true;
		}

		return false;
	}
}
