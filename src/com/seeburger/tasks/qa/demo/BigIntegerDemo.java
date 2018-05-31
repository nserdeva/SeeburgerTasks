package com.seeburger.tasks.qa.demo;

import com.seeburger.tasks.qa.biginteger.BigInteger;

class BigIntegerDemo {

	public static void main(String[] args) {
		BigInteger first = new BigInteger(
				"961738593473729212100057487547845783458753874387348756565478348743893489435893489349843862666111145968247475485343484374748568846845656846854859345893544446647844417178967444433335324545892020786767847876464645");
		BigInteger second = new BigInteger(
				"357742366437848292725770010701077034577040858046796760458954556757835695490023995784944956775896496594684856854964956945964854654546465464511796785697757467455654645768965443331111111111223424385752147752124234");

		// so... we know for sure it works for large numbers...
		System.out.println(first + " + " + second + " = " + first.add(second).toString());
		System.out.println(first + " - " + second + " = " + first.subtract(second).toString());

		/*
		 * but now let's examine some test cases which show much more clearly that the
		 * BigInteger methods work correctly:
		 */

		first = new BigInteger("-000000000000000000000000005801");
		second = new BigInteger("-930   ");
		System.out.println(first + " + " + second + " = " + first.add(second).toString());
		System.out.println(first + " - " + second + " = " + first.subtract(second).toString());
		System.out.println(second + " - " + first + " = " + second.subtract(first).toString());
		first = new BigInteger(" 000000000000000000000000005801  ");
		System.out.println(first + " + " + second + " = " + first.add(second).toString());
		System.out.println(first + " - " + second + " = " + first.subtract(second).toString());


		first = new BigInteger("  -000000000  ");
		second = new BigInteger("  +118   ");
		System.out.println(first + " + " + second + " = " + first.add(second).toString());
		System.out.println(first + " - " + second + " = " + first.subtract(second).toString());
		System.out.println(second + " + " + first + " = " + second.add(first).toString());
		System.out.println(second + " - " + first + " = " + second.subtract(first).toString());

		second = new BigInteger("  0000");
		System.out.println(first + " + " + second + " = " + first.add(second).toString());
		System.out.println(first + " - " + second + " = " + first.subtract(second).toString());
	}

}