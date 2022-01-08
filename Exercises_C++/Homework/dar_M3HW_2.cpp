/*
This program calculates the prime factorizations of a positive number greater than 1.

PSEUDOCODE:
	- Declare a function called Factor()
	- get user input - number to factorize
	- check the validity of the inputs
	- call the function Factor()
	- display the output
	- ask user if they would like to run again

	Last update on 09/16/2021 by Dario
*/

#include<iostream>
using namespace std;

// prototype funtion
int Factor(int x);

int main() {
	cout << "Welcome to Prime Factorization Calculator" << endl;
	char again = 'y';
	do {
		// get user input - number to factorize
		// check the validity of the inputs
		int num = 0;
		while (num <= 1) {
			cout << "Enter a positive integer (>1): ";
			cin >> num;
		}
		// call the function Factor()
		// display output
		cout << "The Prime Factorization of " << num << " is: ";
		int factorized = Factor(num);
		
		while (true) {
			cout << factorized << " ";
			if ((num / factorized) == 1) {
				break;
			}
			num = num / factorized;
			factorized = Factor(num);
		}
		// ask user if they would like to run again
		cout << "\n\nWould you like to enter another integer (y/n)? ";
		cin >> again;
	} while (again == 'y');
	
	cout << "Thank you for using this program. Goodbye!";

	return 0;
}

// funtion definition
int Factor(int x) {
	for (int i = 2; i <= x; i++) {
		if ((x % i) == 0) {
			
			return i;
		}
	}
	return -1;
}