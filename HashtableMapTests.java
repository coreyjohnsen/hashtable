// --== CS400 Project One File Header ==--
// Name: Corey Johnsen
// CSL Username: johnsen
// Email: cjjohnsen@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: N/A

import java.util.NoSuchElementException;

public class HashtableMapTests {

	public static void main(String[] args) {
		System.out.println(test1());
		System.out.println(test2());
		System.out.println(test3());
		System.out.println(test4());
		System.out.println(test5());
	}

	// tests putting, resizing, and constructor
	public static boolean test1() {
		try {
			// make new table
			HashtableMap<Integer, Integer> table = new HashtableMap<Integer, Integer>(10);
			// try putting within range
			if (!table.put(5, 1))
				return false;
			// try putting out of range
			if (!table.put(5000, 2))
				return false;
			// try putting with duplicate key
			if (table.put(5, 3))
				return false;
			// try putting with duplicate value
			if (!table.put(9, 3))
				return false;
			// try putting in same position but different key
			if (!table.put(15, 4))
				return false;
			// add 3 more keys to overcome load factor, check capacity
			table.put(7, 5);
			table.put(4, 6);
			table.put(3, 7);
			table.put(19, 8);
			if (table.capacity() != 20 || table.size() != 8)
				return false;
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	// tests removing and clearing
	public static boolean test2() {
		// make new table and put in some test values
		HashtableMap<Integer, Integer> table = new HashtableMap<Integer, Integer>(5);
		table.put(1, 10);
		table.put(2, 11);
		table.put(3, 12);
		table.put(4, 13);
		try {
			// test removing first value
			if (table.remove(1) != 10)
				return false;
			// test removing second value
			if (table.remove(3) != 12)
				return false;
			// test removing value not in table
			if (table.remove(42) != null)
				return false;
			// test clearing
			table.clear();
			if(table.size() != 0 || table.containsKey(2) || table.containsKey(4))
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// tests size value
	public static boolean test3() {
		HashtableMap<Integer, Integer> table = new HashtableMap<Integer, Integer>(2);
		try {
			// test empty
			if(table.size() != 0)
				return false;
			// test adding
			table.put(1, 42);
			if(table.size() != 1)
				return false;
			// test adding with resize
			table.put(2, 50);
			if(table.size() != 2)
				return false;
			// test removing
			table.remove(1);
			if(table.size() != 1)
				return false;
			// test clearing
			table.clear();
			if(table.size() != 0)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// tests constructors
	public static boolean test4() {
		try {
			// test default constructor
			HashtableMap<Integer, Integer> table = new HashtableMap<Integer, Integer>();
			if(table.size() != 0 || table.capacity() != 20)
				return false;
			// test constructor with arguments
			table = new HashtableMap<Integer, Integer>(10);
			if(table.size() != 0 || table.capacity() != 10)
				return false;
			// test constructor with invalid capacity
			table = new HashtableMap<Integer, Integer>(-5);
			if(table.size() != 0 || table.capacity() != 20)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// tests get and contains
	public static boolean test5() {
		HashtableMap<Integer, Integer> table = new HashtableMap<Integer, Integer>();
		table.put(0, 1);
		table.put(1, 2);
		table.put(2, 4);
		table.put(4, 8);
		table.put(8, 16);
		table.put(16, 32);
		// test valid get
		try {
			if(table.get(4) != 8)
				return false;
			if(table.get(16) != 32)
				return false;
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		// test invalid get
		try {
			table.get(32);
			return false;
		} catch (NoSuchElementException e) {
			// expected exception thrown, do nothing
		} catch (Exception e) {
			return false;
		}
		// test contains
		try {
			if(table.containsKey(2) != true)
				return false;
			if(table.containsKey(1) != true)
				return false;
			if(table.containsKey(8) != true)
				return false;
			if(table.containsKey(5) != false)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
