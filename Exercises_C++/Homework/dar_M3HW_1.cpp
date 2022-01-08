/*
2D printer based on the user input. The input can just be (#,* or $). For each input, a drawing will be printed

PSEUDOCODE:
	- Declare three function called print1(), print2(), print3()
	- get user input - what to display
	- check the validity of the inputs
	- call one of the print function according to input 
	- display the output
	- ask user if they would like to run again

	Last update on 09/16/2021 by Dario

*/


#include<iostream>
using namespace std;

// functions prototype
void print1();
void print2();
void print3();

int main() {
	cout << "Welcome to 2D Image Printer";
	char again = 'y';
	// get user input - what to display
	// check the validity of the inputs
	char choice;
	do {
		
		cout << "Choose a character (#, *, $): ";
		cin >> choice;
		while (choice != '#' && choice != '*' && choice != '$') {
			cout << "Character MUST be #, *, $!" << endl;
			cout << "Choose again: ";
			cin >> choice;
		}

		// call one of the print function according to input
		// display the output
		switch (choice) {
		case '#':
			print1();
			break;
		case '*':
			print2();
			break;
		case '$':
			print3();
			break;
		default:
			cout << "cannot be here";
		}

		// ask user if they would like to run again
		cout << "Would you like to print another 2D image (y/n)? ";
		cin >> again;
	} while (again == 'y');
	cout << "Thank you for using this program. Goodbye! ";
	return 0;
}

void print1() {
	int x = 0;
	//for the first 7 increasing #
	while (x < 7) {
		for (int i = 0; i <= x; i++) {
			cout << "#";
		}
		cout << endl;
		++x;
	}
	// for the last 7 decreasing #

	while (x > 0) {
		for (int i = 0; i < x; i++) {
			cout << "#";
		}
		cout << endl;
		--x;
	}
}

void print2() {
	for (int row = 1; row <= 14; row++) {
		for (int col = 1; col <= 15; col++) {
			if ((row >= col) || (col > (15 - row))) {
				cout << "*";
			}
			else {
				cout << " ";
			}
		}
		cout << endl;
	}
}

void print3() {
	//first row
	for (int col = 1; col <= 15; col++) {
		cout << "$";
	}
	cout << endl;

	for (int row = 2; row <= 14; row++) {

		if (row % 2 == 0) {
			cout << "$$";

			for (int col = 1; col <= 11; col++) {
				if (col % 2 == 0) {
					cout << "$";
				}
				else {
					cout << " ";
				}
			}
			cout << "$$" << endl;
		}
		else {
			for (int col = 1; col <= 15; col++) {
				if (col % 2 == 0) {
					cout << " ";
				}
				else {
					cout << "$";
				}
			}
			cout << endl;
		}
	}
		
	
	//last row
	for (int col = 1; col <= 15; col++) {
		cout << "$";
	}
	cout << endl;
}