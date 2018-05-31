/**
 * A class which demonstrates recursive display of the following number
 * sequence: "10 100 1000 10000 10000 1000 100 10".
 * <p>
 * Assigned by Todor Manahov
 * <p>
 *
 * @author Natali Serdeva
 */

package com.seeburger.tasks.qa;

public final class RecursiveNumberSequenceDisplayer {

	/**
	 * Displays the following number sequence: "10 100 1000 10000 10000 1000 100 10"
	 */
	public final void displaySequence() {
		this.displaySequenceRecursively(10, true);
	}

	/**
	 * The algorithm displays the sequence starting from 10 to 10_000 in ascending
	 * order. When 10_000 is reached, the direction reverts. The bottom of the
	 * recursion is reached when the current sequence member is 10 and the order is
	 * descending.
	 *
	 * @param n
	 *            the varying sequence member
	 * @param isInAscendingOrder
	 *            {@code true} if the currently modified sequence member should be
	 *            increased, {@code false} if should be reduced
	 */
	private final void displaySequenceRecursively(int n, boolean isInAscendingOrder) {
		System.out.print(n + " ");
		if (n == 10_000) {
			isInAscendingOrder = false;
			System.out.print(n + " ");
		}
		if (n == 10 && !isInAscendingOrder) {
			return;
		}
		n = isInAscendingOrder ? n * 10 : n / 10;

		displaySequenceRecursively(n, isInAscendingOrder);
	}

}