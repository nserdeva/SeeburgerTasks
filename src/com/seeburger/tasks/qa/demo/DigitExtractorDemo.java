package com.seeburger.tasks.qa.demo;

import java.util.Scanner;

import com.seeburger.tasks.qa.digitextractor.DigitExtractor;

final class DigitExtractorDemo {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a number:");
		new DigitExtractor(in.nextLong()).displayDigits();
		in.close();
	}

}
