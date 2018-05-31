
/**
 * The SpiralMatrixGenerator class's purpose is to generate a spiral (square)
 * matrix of a given size.
 * <p>
 * The matrix should be able to be filled in both clockwise/anti-clockwise
 * direction in the following format:
 * <p>
 * (clockwise example) <br>
 * 1 16 15 14 13 <br>
 * 2 17 24 23 12 <br>
 * 3 18 25 22 11 <br>
 * 4 19 20 21 10 <br>
 * 5 6 7 8 9
 * <p>
 * Assigned by Todor Manahov
 * <p>
 *
 * @author Natali Serdeva
 */

package com.seeburger.tasks.qa.spiralmatrix;

public final class SpiralMatrixGenerator {
	private final int matrixSize;
	private final boolean isFilledClockwise;
	private final int[][] matrix;

	/**
	 * Instantiates a square matrix with a specified size that will be filled in
	 * clockwise/anti-clockwise order with increasing integers starting from '1'.
	 *
	 * @param matrixSize
	 *            the size of the square matrix that will be generated
	 * @param isFilledClockwise
	 *            {@code true} - the matrix will be filled in clockwise order,
	 *            {@code false} - the matrix will be filled in anti-clockwise order
	 * @throws SpiralMatrixGeneratorException
	 *             {@code matrixSize} is not a valid matrix size.
	 */
	public SpiralMatrixGenerator(int matrixSize, boolean isFilledClockwise) throws SpiralMatrixGeneratorException {
		if (!isValidMatrixSize(matrixSize)) {
			throw new SpiralMatrixGeneratorException("Matrix size must be greater than zero!");
		}
		this.matrixSize = matrixSize;
		this.matrix = new int[this.matrixSize][this.matrixSize];
		this.isFilledClockwise = isFilledClockwise;
	}

	private final boolean isValidMatrixSize(int n) {
		return n > 0;
	}

	/**
	 * Generates and prints a square matrix of the specified size, filled in the
	 * specified order with increasing integers from {@code 1} to
	 * {@code matrixSize*matrixSize}
	 */
	public final void generateSpiralMatrix() {
		this.fillMatrix(0, this.matrixSize - 1, 1);
		this.printMatrix(matrix);
	}

	private final void fillMatrix(int start, int end, int counter) {
		if (start > end) {
			return;
		}
		for (int i = start; i <= end; i++) {
			if (this.isFilledClockwise) {
				matrix[i][start] = counter++;
			} else {
				matrix[start][i] = counter++;
			}
		}
		for (int i = start + 1; i <= end; i++) {
			if (this.isFilledClockwise) {
				matrix[end][i] = counter++;
			} else {
				matrix[i][end] = counter++;
			}
		}
		for (int i = end - 1; i >= start; i--) {
			if (this.isFilledClockwise) {
				matrix[i][end] = counter++;
			} else {
				matrix[end][i] = counter++;

			}
		}
		for (int i = end - 1; i > start; i--) {
			if (this.isFilledClockwise) {
				matrix[start][i] = counter++;
			} else {
				matrix[i][start] = counter++;
			}
		}
		fillMatrix(start + 1, end - 1, counter);
	}

	private final void printMatrix(int[][] m) {
		for (int row = 0; row < m.length; row++) {
			for (int col = 0; col < m[row].length; col++) {
				System.out.print(m[row][col] + " ");
			}
			System.out.println();
		}
	}

}