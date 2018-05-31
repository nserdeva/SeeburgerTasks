/**
 * The DigitExtractor class's purpose is to extract and display a given number's digits in a specified format.
 * <p>
 * Full task description:
 * Breaking a number into digits. For example: 9821 -> 9 8 2 1. String transformation and division by characters should not be used.
 * Assigned by Todor Manahov
 * <p>
 * @author Natali Serdeva
 */

package com.seeburger.tasks.qa.digitextractor;

public final class DigitExtractor {

	private long number;

	/**
	 * Used to extract and display the separate digits of a specified number. The
	 * number must be within the range [-999_999_999_999_999_999,
	 * 999_999_999_999_999_999].
	 *
	 * @param number
	 *            the number whose digits will be extracted
	 * @throws DigitExtractorException
	 *             {@code number} is not a number within the specified range.
	 */
	public DigitExtractor(long number) throws DigitExtractorException {
		if (!isValidNumber(number)) {
			throw new DigitExtractorException(
					"The number must be within the range [-999_999_999_999_999_999, 999_999_999_999_999_999]!");
		}
		this.number = number;
	}

	private final boolean isValidNumber(long number) {
		return number >= -999_999_999_999_999_999l && number <= 999_999_999_999_999_999l;
	}

	/**
	 * Displays the specified number's digits in the following format: (for example)
	 * {@code 9821 -> 9 8 2 1 }
	 */
	public final void displayDigits() {
		System.out.println(this.number + " -> " + this.extractDigits());
	}

	/**
	 * Extracts the separate digits from the specified number.
	 *
	 * @param n
	 *            a number whose digits are to be extracted
	 * @return a String consisting of the given number's digits, separated by space
	 */
	private final String extractDigits() {
		if (this.number > -10 && this.number < 10) { // if the number consists of only one digit, it will be directly
														// returned
			return Long.toString(this.number);
		}
		if (this.number < 0) { // if the number is negative, it is multiplied by -1 for the algorithm to work
			this.number *= -1;
		}
		StringBuilder sb = new StringBuilder();
		long moduleDivider = 10;
		long divider = 1;

		while (divider <= this.number && divider <= 1000000000000000000l) {
			sb.append((this.number % moduleDivider) / divider).append(" ");
			moduleDivider *= 10;
			divider *= 10;
		}
		return sb.reverse().toString();
	}

}