package tictactoe;

import java.util.Scanner;
import java.lang.Exception;

public class Fight {

	public static void main(String... args) {

		Scanner scanner = new Scanner(System.in);
		Fight fm = new Fight();

		char[][] charArray = new char[3][3];
		String[] cords = new String[2];

		for (int i = charArray.length - 1, k = 0; i >= 0; i--) {
			for (int j = 0; j < 3; j++) {
				charArray[j][i] = ' ';
				k++;
			}
		}
		
		System.out.println("---------");
		for (int i = charArray.length - 1; i >= 0; i--) {
			System.out.print("|" + " ");
			for (int j = 0; j < 3; j++) {
				System.out.print(charArray[j][i] + " ");
			}
			System.out.println("|");
		}
		System.out.print("---------");

		System.out.println();

		System.out.print("Enter the coordinates: ");
		cords[0] = scanner.next();
		cords[1] = scanner.next();	
	
		fm.cordsDetails(charArray, cords, 0);
	}
	
	public static String gameResult (char[][] charArray){	
		int[] sumOne = new int[3];
		int[] sumTwo = new int[3];
		int sumThree = 0;
		int sumFour = 0;
		int countX = 0;
		int countO = 0;
		int countA = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				sumOne[i] += charArray[i][j];
				sumTwo[i] += charArray[j][i];
				if (i == j) {
					sumThree += charArray[i][j];
				} else {
					sumFour = charArray[0][2] + charArray[1][1] + charArray[2][0];
				}
				if (charArray[i][j] == 'X') {
					countX++;
				} else if (charArray[i][j] == 'O') {
						countO++;
				}	
			}
		}

		for (int i = 0; i < 3; i++) {
			if (sumOne[0] == 237 && sumOne[1] == 264 || sumOne[0] == 264 && sumOne[1] == 237 ||
				sumOne[1] == 237 && sumOne[2] == 264 || sumOne[1] == 264 && sumOne[2] == 237 ||
				sumOne[0] == 237 && sumOne[2] == 264 || sumOne[1] == 264 && sumOne[2] == 237 ||
				sumTwo[0] == 237 && sumTwo[1] == 264 || sumTwo[0] == 264 && sumTwo[1] == 237 ||
				sumTwo[1] == 237 && sumTwo[2] == 264 || sumTwo[1] == 264 && sumTwo[2] == 237 ||
				sumTwo[0] == 237 && sumTwo[2] == 264 || sumTwo[1] == 264 && sumTwo[2] == 237) {
					

					return "Impossible";	
			
			} else if (Math.abs(countX - countO) > 1) {

					return "Impossible";				
			}	
		}

		for (int i = 0; i < 3; i++) {
			if (sumOne[i] == 264 || sumTwo[i] == 264 || sumThree == 264 || sumFour == 264) {

				return "X wins";
				
			} else if (sumOne[i] == 237 || sumTwo[i] == 237 || sumThree == 237 || sumFour == 237) {

				return "O wins";
			} 
		}		

		if (countX + countO == 9 && countX - countO == 1 || countO - countX == 1) { 

			return "Draw";
		
		} else {

			return "Game not finished";
		}
	}

	public void cordsDetails (char[][] array, String[] cordsInfo, int off) {
		Scanner scanner = new Scanner(System.in);
		Fight non = new Fight();
		int count = 0;
		int[] finalCords = new int[2];
		boolean check = false;
		boolean flag = false;
		String result = null;

		while (count < 9) {
		do {
			if (cordsInfo[0].startsWith("one") || cordsInfo[0].startsWith("two") || cordsInfo[0].startsWith("three") || 
				cordsInfo[1].startsWith("one") || cordsInfo[1].startsWith("two") || cordsInfo[1].startsWith("three")) {
				System.out.println("You should enter numbers!");

				Fight.enterCords(cordsInfo);
				check = Fight.isNumber(cordsInfo[0], cordsInfo[1]);

			} else {

				check = true;
			}

		} while (check == false);
		 
		finalCords[0] = Integer.parseInt(cordsInfo[0]);
		finalCords[1] = Integer.parseInt(cordsInfo[1]);

		flag = true;

		if (finalCords[0] > 3 || finalCords[1] > 3) {	

			System.out.println("Coordinates should be from 1 to 3!");

			Fight.enterCords(cordsInfo);
			new Fight().cordsDetails(array, cordsInfo, off);

		} else if (array[finalCords[0] - 1][finalCords[1] - 1] == ' ') {

			if (off == 0) {

				array[finalCords[0] - 1][finalCords[1] - 1] = 'X';

				System.out.println("---------");
				for (int i = array.length - 1; i >= 0; i--) {
					System.out.print("|" + " ");
					
					for (int j = 0; j < 3; j++) {
						System.out.print(array[j][i] + " ");
					}
					System.out.println("|");
				}
				System.out.println("---------");

				result = Fight.gameResult(array);
			
				switch (result) {

					default: 
						
						Fight.enterCords(cordsInfo);
						off = 1;
						count++;
						flag = true;

					break;

					case "X wins":
						
						System.out.println ("X wins");
						count = 9;
						flag = false;
						System.exit(0);
						
					return;
			
					case "O wins":

						System.out.println ("O wins");
						count = 9;
						flag = false;
						System.exit(0);
					
					return;
			
					case "Impossible":

						System.out.println ("Impossible");
						count = 9;
						flag = false;
						System.exit(0);
					
					return;
					
					case "Draw":

						System.out.println ("Draw");
						count = 9;
						flag = false;
						System.exit(0);
					
					return;
				}
			
			} else if (off == 1) {
				array[finalCords[0] - 1][finalCords[1] - 1] = 'O';

				System.out.println("---------");
				for (int i = array.length - 1; i >= 0; i--) {
					System.out.print("|" + " ");
					
					for (int j = 0; j < 3; j++) {
					System.out.print(array[j][i] + " ");
					}
					System.out.println("|");
				}
				System.out.println("---------");

				result = Fight.gameResult(array);
			
				switch (result) {

					default: 
						
						Fight.enterCords(cordsInfo);
						off = 0;
						count++;
						flag = true;

					break;

					case "X wins":
						
						System.out.println ("X wins");
						count = 9;
						flag = false;
						System.exit(0);
						
					return;
			
					case "O wins":

						System.out.println ("O wins");
						count = 9;
						flag = false;
						System.exit(0);
					
					return;
			
					case "Impossible":

						System.out.println ("Impossible");
						count = 9;
						flag = false;
						System.exit(0);
					
					return;
					
					case "Draw":

						System.out.println ("Draw");
						count = 9;
						flag = false;
						System.exit(0);
					
					return;
				}
			}

		} else if (flag == true && array[finalCords[0] - 1][finalCords[1] - 1] == 'X' || array[finalCords[0] - 1][finalCords[1] - 1] == 'O') {
			
			System.out.println("This cell is occupied! Choose another one!");
				if (array[finalCords[0] - 1][finalCords[1] - 1] == 'X') {

					//off = 1; 
					Fight.enterCords(cordsInfo);
					new Fight().cordsDetails(array, cordsInfo, off);
				
				} else if (array[finalCords[0] - 1][finalCords[1] - 1] == 'O') {

					//off = 0;
					Fight.enterCords(cordsInfo);
					new Fight().cordsDetails(array, cordsInfo, off);
				}
			}
		}
	} 

	public static String[] enterCords(String[] cordsInfo) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the coordinates: ");
		cordsInfo[0] = scanner.next();
		cordsInfo[1] = scanner.next();

		return cordsInfo;
	}

	public static boolean isNumber(String aString, String bString) {
			Scanner scanner = new Scanner(System.in);

		if (aString.startsWith("one") || bString.startsWith("one")) {

			return false;
		
		}

		return true; 
	}
}