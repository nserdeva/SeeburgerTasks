package com.seeburger.tasks.qa.biginteger;

/**
 * A representation of my implementation of the functionalities of the
 * BigInteger class.
 * <p>
 * Full task: BigInteger simulation. Numbers are assigned as strings. The
 * strings should not be converted to int but should be broken down into
 * separate digits which should be transferred to a certain data structure. The
 * class should be able to perform addition and subtraction operations.
 * <p>
 * Assigned by Todor Manahov
 * <p>
 * 
 * @author Natali Serdeva
 */

public final class BigInteger implements Comparable<BigInteger> {
	private final String absoluteValue;
	private final boolean isNegative;

	/**
	 * Translates the decimal String representation of a BigInteger into a
	 * BigInteger. The String representation consists of an optional minus sign
	 * followed by a sequence of one or more decimal digits. The String may not
	 * contain any extraneous characters (whitespace, for example).
	 *
	 * @param value
	 *            decimal String representation of BigInteger.
	 * @throws BigIntegerDefinitionException
	 *             {@code value} is not a valid representation of a BigInteger.
	 */
	public BigInteger(String value) throws BigIntegerDefinitionException {
		value = this.validateValue(value);
		boolean isNegative = this.defineIfNegative(value);
		value = this.retrieveAbsoluteValue(value);
		this.absoluteValue = value.length() > 1 ? trimZeros(value) : value;
		this.isNegative = !this.absoluteValue.equals("0") ? isNegative : false;
	}

	/**
	 * Checks whether the specified value consists of an optional minus/plus sign
	 * followed by a sequence of one or more decimal digits.
	 * 
	 * @return checked value trimmed from whitespace characters
	 */
	private String validateValue(String value) {
		if (value == null || value.isEmpty()) {
			throw new BigIntegerDefinitionException("BigInteger value cannot be null/empty!");
		}
		value = value.trim();
		if (((value.charAt(0) == '+' || value.charAt(0) == '-') && value.length() > 1)
				|| Character.isDigit(value.charAt(0))) {
			for (int i = 1; i < value.length(); i++) {
				if (!Character.isDigit(value.charAt(i))) {
					throw new BigIntegerDefinitionException(
							"BigInteger value must consist of an optional minus sign followed by a sequence of one or more decimal digits.");
				}
			}
		} else {
			throw new BigIntegerDefinitionException(
					"BigInteger value must consist of an optional minus sign followed by a sequence of one or more decimal digits.");
		}
		return value;
	}

	/**
	 * @return absolute value (regardless of sign)
	 */
	private final String retrieveAbsoluteValue(String value) {
		return !Character.isDigit(value.charAt(0)) ? new StringBuilder(value).deleteCharAt(0).toString() : value;
	}

	/**
	 * Just in case the user specifies something like "0000000058" for BigInteger's
	 * value.
	 * 
	 * @param value
	 *            the value to be trimmed
	 * @return the value without unnecessary zeros in the beginning
	 */
	private final String trimZeros(String value) {
		if (value.charAt(0) != '0') {
			return value;
		}
		int zerosCount = 1;
		for (int i = 1; i < value.length() - 1; i++) {
			if (value.charAt(i) != '0') {
				break;
			}
			zerosCount++;
		}
		return new StringBuilder(value).delete(0, zerosCount).toString();
	}

	/**
	 * @return {@code true} if the given value is negative, {@code false} if the
	 *         given value is positive
	 */
	private final boolean defineIfNegative(String value) {
		return value.charAt(0) == '-' ? true : false;
	}

	/**
	 * Returns a BigInteger whose value is {@code (this + addend)}.
	 *
	 * @param addend
	 *            value to be added to this BigInteger.
	 * @return {@code this + addend}
	 * 
	 * @throws BigIntegerOperationException
	 *             {@code addend} is null.
	 */
	public final BigInteger add(BigInteger addend) throws BigIntegerOperationException {
		if (addend.absoluteValue.equals("0")) {
			return this;
		}
		try {
			if (!this.isNegative && !addend.isNegative) {
				return new BigInteger(this.pureAdd(addend.absoluteValue));
			}
			if (this.isNegative && !addend.isNegative) {
				if (this.compareAbsoluteValueTo(addend) == 1) {
					return new BigInteger("-".concat(this.pureSubtract(addend.absoluteValue)));
				}
				return new BigInteger(addend.pureSubtract(this.absoluteValue));
			}
			if (this.isNegative && addend.isNegative) {
				return new BigInteger("-".concat(this.pureAdd(addend.absoluteValue)));
			}
			if (this.compareAbsoluteValueTo(addend) == -1) {
				return new BigInteger("-".concat(addend.pureSubtract(this.absoluteValue)));
			}
			return new BigInteger(this.pureSubtract(addend.absoluteValue));
		} catch (NullPointerException npe) {
			throw new BigIntegerOperationException("Addend cannot be null!");
		}
	}

	/**
	 * Calculates the sum of this BigInteger's absolute value and a given absolute
	 * value.
	 *
	 * @param addendValue
	 *            absolute value to be added to this BigInteger's absolute value.
	 * @return a string representation of the sum of {@code this}'s absolute value
	 *         and {@code addendValue}
	 */
	private final String pureAdd(String addendValue) {
		StringBuilder sb = new StringBuilder();
		int silentAddend = 0; // if tempSum exceeds 10, the silentAddend will be tempSum / 10 which will be
								// considered in the calculation of the next tempSum
		int iterationsCount = (this.absoluteValue.length() < addendValue.length() ? addendValue.length()
				: this.absoluteValue.length());
		for (int i = 0; i <= iterationsCount; i++) {
			int tempSum = 0; // sum of the separate one-digit numbers from the two addends that are currently
								// being manipulated
			tempSum += this.absoluteValue.length() - 1 - i > -1
					? ((this.absoluteValue.charAt(this.absoluteValue.length() - 1 - i)) - '0')
					: 0;
			tempSum += addendValue.length() - 1 - i > -1 ? (addendValue.charAt(addendValue.length() - 1 - i) - '0') : 0;
			tempSum += silentAddend;
			sb.append(i == iterationsCount ? tempSum : tempSum % 10);
			silentAddend = tempSum / 10;
		}
		return sb.reverse().toString();
	}

	/**
	 * Returns a BigInteger whose value is {@code (this - subtrahend)}.
	 *
	 * @param subtrahend
	 *            value to be subtracted from this BigInteger.
	 * @return {@code this - subtrahend}
	 * 
	 * @throws BigIntegerOperationException
	 *             {@code subtrahend} is null.
	 */
	public final BigInteger subtract(BigInteger subtrahend) throws BigIntegerOperationException {
		if (subtrahend.absoluteValue.equals("0")) {
			return this;
		}
		try {
			if (this.isNegative && !subtrahend.isNegative) {
				return new BigInteger("-".concat(this.pureAdd(subtrahend.absoluteValue)));
			}
			if (!this.isNegative && subtrahend.isNegative) {
				return new BigInteger(this.pureAdd(subtrahend.absoluteValue));
			}
			if (!this.isNegative && !subtrahend.isNegative) {
				if (this.compareTo(subtrahend) > -1) {
					return new BigInteger(this.pureSubtract(subtrahend.absoluteValue));
				}
				return new BigInteger("-".concat(subtrahend.pureSubtract(this.absoluteValue)));
			}
			if (this.compareAbsoluteValueTo(subtrahend) == 1) {
				return new BigInteger("-".concat(this.pureSubtract(subtrahend.absoluteValue)));
			}
			return new BigInteger(subtrahend.pureSubtract(this.absoluteValue));
		} catch (NullPointerException npe) {
			throw new BigIntegerOperationException("Addend cannot be null!");
		}
	}

	/**
	 * Calculates the difference between this BigInteger's absolute value and a
	 * given absolute value. It is ensured that the caller object's absolute value
	 * is always greater than the value which is to be subtracted.
	 *
	 * @param subtrahendValue
	 *            absolute value to be subtracted from this BigInteger's absolute
	 *            value.
	 * @return a string representation of the difference between {@code this}'s
	 *         absolute value and {@code subtrahendValue}
	 */
	private final String pureSubtract(String subtrahendValue) throws BigIntegerOperationException {
		// the caller object is considered as the minuend
		StringBuilder sb = new StringBuilder();
		int silentSubtrahend = 0; // if tempMinuend is less than tempSubtrahend, the silentSubtrahend is increased
									// by 1, hence the next calculated tempDifference will be reduced by 1
		int tempMinuend = 0; // the current one-digit number to subtract from
		int tempSubtrahend = 0; // the current one-digit number to be subtracted

		for (int i = 0; i < this.absoluteValue.length(); i++) {
			int tempDifference = 0; // the difference between the current one-digit numbers that are being
									// manipulated from the two values
			tempMinuend = (this.absoluteValue.charAt(this.absoluteValue.length() - 1 - i) - '0');
			tempSubtrahend = subtrahendValue.length() - 1 - i > -1
					? (subtrahendValue.charAt(subtrahendValue.length() - 1 - i) - '0')
					: 0;
			if (tempMinuend - silentSubtrahend < tempSubtrahend) {
				tempDifference = (10 + tempMinuend - silentSubtrahend) - tempSubtrahend;
				silentSubtrahend = 1;
			} else {
				tempDifference = tempMinuend - silentSubtrahend - tempSubtrahend;
				silentSubtrahend = 0;
			}
			sb.append(tempDifference);
		}
		if (sb.charAt(sb.length() - 1) == '0') {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.reverse().toString();
	}

	/**
	 * Compares this BigInteger with the specified BigInteger.
	 *
	 * @param bi
	 *            BigInteger to which this BigInteger is to be compared.
	 * @return -1, 0 or 1 as this BigInteger is numerically less than, equal to, or
	 *         greater than {@code bi}.
	 */
	@Override
	public final int compareTo(BigInteger bi) {
		if (this.equals(bi)) {
			return 0;
		}
		if (this.isNegative != bi.isNegative) {
			if (this.absoluteValue.equals("0") && bi.absoluteValue.equals("0")) {
				return 0;
			}
			return bi.isNegative ? 1 : -1;
		}
		return !this.isNegative ? this.compareAbsoluteValueTo(bi) : this.compareAbsoluteValueTo(bi) * -1;
	}

	/**
	 * Compares this BigInteger's absolute value with that of the specified
	 * BigInteger.
	 *
	 * @param bi
	 *            BigInteger to whose absolute value this BigInteger's absolute
	 *            value is to be compared.
	 * @return -1, 0 or 1 as this BigInteger's absolute value is numerically less
	 *         than, equal to, or greater than that of {@code bi}.
	 */
	private final int compareAbsoluteValueTo(BigInteger bi) {
		if (this.absoluteValue.length() > bi.absoluteValue.length()) {
			return 1;
		}
		if (this.absoluteValue.length() < bi.absoluteValue.length()) {
			return -1;
		}
		for (int i = 0; i < this.absoluteValue.length(); i++) {
			if (this.absoluteValue.charAt(i) - '0' > bi.absoluteValue.charAt(i) - '0') {
				return 1;
			}
			if (this.absoluteValue.charAt(i) - '0' < bi.absoluteValue.charAt(i) - '0') {
				return -1;
			}
		}
		return 0;
	}

	/**
	 * Returns the decimal String representation of this BigInteger. A minus sign is
	 * prepended if appropriate.
	 * 
	 * @return decimal String representation of this BigInteger.
	 */
	@Override
	public final String toString() {
		return this.isNegative ? new StringBuilder(this.absoluteValue).insert(0, '-').toString() : this.absoluteValue;
	}

}