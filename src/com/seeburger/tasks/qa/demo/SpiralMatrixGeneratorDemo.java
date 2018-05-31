package com.seeburger.tasks.qa.demo;

import java.util.Scanner;

import com.seeburger.tasks.qa.spiralmatrix.SpiralMatrixGenerator;
import com.seeburger.tasks.qa.spiralmatrix.SpiralMatrixGeneratorException;

final class SpiralMatrixGeneratorDemo {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args) {
		executeDemo();
		SCANNER.close();
	}

	private static void executeDemo() {
		System.out.println("Enter matrix size:");
		int inputNum = SCANNER.nextInt();
		System.out.println("How should the matrix be filled?\nEnter '1' for clockwise or '2' for anti-clockwise:");
		int choice = SCANNER.nextInt();
		try {
			switch (choice) {
			case 1:
				new SpiralMatrixGenerator(inputNum, true).generateSpiralMatrix();
				break;
			case 2:
				new SpiralMatrixGenerator(inputNum, false).generateSpiralMatrix();
				break;
			default:
				System.out.println(
						"You have entered an invalid direction for the order of the filling of the matrix!\nPlease, try again!\n");
				executeDemo();
				break;
			}
		} catch (SpiralMatrixGeneratorException smge) {
			System.out.println(smge.getMessage());
			System.out.println("Please, try again!\n");
			executeDemo();
		}
	}

}
