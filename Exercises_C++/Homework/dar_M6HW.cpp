/*
Check if the sentence input by the user is palindrome.

PSEUDOCODE:
	- get user input 
	- store it in an array of characters
	- count how many characters I have 
	- check if the sentence is palindrome 
		- convert the sentence in lower case
		- iterate and compare on the first and last charcters of the array 
	- ask the user if he wants to enter another sentence

Last update on 10/13/2021 by Dario
*/

#include<iostream>
#include<cctype>
using namespace std;

// functions prototype
bool isPalindrome(char array[], int n);



int main() {
	char again = 'y';

	// inizialize a null char array
	char array[80] = { '\0' };

	while (again == 'y') {
		//get user input
		// store it in an array of characters
		cout << "Enter your phrase (just letters and blanks, please): " << endl;
		cin.getline(array, 80);
		//get the size of array
		int n = strlen(array);
		//convert to lower
		for (int i = 0; i < n; i++) {
			array[i] = tolower(array[i]);
		}
		//remove the blank space
		int i = 0, j = 0;

		while (array[i]) {
			if (array[i] != ' ') {
				array[j++] = array[i];
			}
			i++;
		}
		array[j] = '\0';

		//get the new size of array without blank space
		n = strlen(array);

		// check if the sentence is palindrome
		if (isPalindrome(array, n) == true) {
			cout << "Yes, the phrase is a palindrome!";
		}
		else {
			cout << "No, the phrase is not a palindrome.";
		}
		//ask the user if he wants to enter another sentence
		cout << "\nWould you like to run the program again (y/n)? ";
		cin >> again;
		cout << endl;
		cin.get(); // clean the buffer
	}
	cout << "Thank you for using the program! Goodbye!";
	return 0;
}

bool isPalindrome(char array[],int n) {
	int j = 80;
	int flag = 0;
	for (int i = 0; i < n; i++) {
		if (array[i] != array[n - i - 1]) {
			flag = 1;
		}
	}
	if (flag == 1)
		return false;
	else
		return true;

	
}


//for (int i = 0; i < n; i++) {
	//cout << array[i];
//}