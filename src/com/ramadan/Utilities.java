package com.ramadan;

import java.util.Random;

public class Utilities {

	/**
	 * Select a random integer in a range.
	 * @param min Minimum int the object can have (inclusive)
	 * @param max Maximum int the object can have (exclusive)
	 * @return an int between min (inclusive) and max (exclusive)
	 */
	public static int getRandomInt(int min, int max) {
		Random random = new Random();
		
		return random.nextInt(max - min) + min;
	}
	
}
