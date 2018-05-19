package com.revature.eval.java.core;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String[] words = phrase.split("[\\p{Punct}\\s]+"); 
		StringBuilder word = new StringBuilder("");
		for (int i = 0; i < words.length; i++) {
			word.append(words[i].charAt(0)); 
				
		}
		return word.toString().toUpperCase(); 
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if((sideOne == sideTwo) && (sideTwo == sideThree)) {
				return true; 
			} else return false; 
		}

		public boolean isIsosceles() {
			if((sideOne == sideTwo) || (sideTwo == sideThree) || (sideOne == sideThree)) {
				return true; 
			} else return false;
		}

		public boolean isScalene() {
			if((sideOne != sideTwo) && (sideTwo != sideThree) && (sideOne != sideThree)) {
				return true; 
			} else 	return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		int score = 0;
		string = string.toLowerCase();
		for (int i = 0; i < string.length(); ++i)  {
			char c = string.charAt(i);
			if ((c == 'q') || (c == 'z')) { 
				score += 10; 
			} else if (c == 'x') {
				score += 8; 
			} else if (c == 'k') {
				score += 5; 
			} else if ((c == 'b') || (c == 'c') || (c == 'm') || (c == 'p')) {
				score += 3; 
			} else if ((c == 'f') || (c == 'h') || (c == 'v') || (c == 'w') || (c == 'y')) {
				score += 4; 
			} else if ((c == 'd') || (c == 'g')) {
				score +=2; 
			} else {
				score +=1; 
			}
		}
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	
	public String cleanPhoneNumber(String string) {
		ArrayList<Character> num = new ArrayList<>();
		int i = 0;
		char c = string.charAt(i); 
		while ((Character.isDigit(c) == false) || (c == '1')) {
			++i; 
			c = string.charAt(i);	
		} 
		for(int j = i; j < string.length(); ++j) {
			c = string.charAt(j);
			if (Character.isDigit(c)) {
				num.add(c); 		
			} else if (Character.isLetter(c)) {
				throw new IllegalArgumentException(); 
			}
		}
		StringBuilder fiNum = new StringBuilder(""); 
		if(num.size() == 10) {
			for(int k = 0; k < 10; ++k) {
				fiNum.append(num.get(k)); 
			}
		} else throw new IllegalArgumentException(); 
		
		return fiNum.toString(); 
	}
	

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	
	public Map<String, Integer> wordCount(String string) {
		Map<String, Integer> countMap = new HashMap<String, Integer>(); 
		string.toLowerCase(); 
		String[] set = string.split("[\\p{Punct}\\s]+");
		
		for(int i = 0; i < set.length; i++) {
			if(countMap.containsKey(set[i])) {
				int v = countMap.get(set[i]);
				countMap.replace(set[i], ++v);
			} else {
				countMap.put(set[i], 1); 
			}
		} 
		
		return countMap;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int half = (sortedList.size() / 2); 
			System.out.println(half);
			if(t.getClass() == int.class) {
				
//				while (sortedList.get(half) != t) {
//					if(sortedList.get(half) < t) { //Even with the guarantee of Integers or ints, I can't find a workable way to compare them.
//						half = (int) (half / 2);
//					} else if (sortedList.get(half) > t) {
//						half = (int) (half * 1.5); 
//					}
//				}
			}
			return 0;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		StringBuilder phrase = new StringBuilder();
		string.toLowerCase(); 
		String[] set = string.split(" ");
		boolean go;
		for(int i = 0; i < set.length; i++) {
			StringBuilder word = new StringBuilder(set[i]);
			
			go = true; 
			while(go) {
				char c = word.charAt(0); 
				if ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u')) {
					word.append("ay"); 
					go = false; 
				} else if (c == 'q') {
					word.append("quay");
					word.deleteCharAt(1);
					word.deleteCharAt(0);
					go = false; 
				} else {
					word.append(c);
					word.deleteCharAt(0);
				}
			}
			phrase.append(word.toString());
			if(i == (set.length - 1))  continue;
			else phrase.append(" "); 
			 
		}
			
		return phrase.toString();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		ArrayList<Integer> digits = new ArrayList<Integer>(2); 
		int i = input; 
		int quant = 0;
		while (i > 0) {
			 digits.add(i % 10);
			 i = i / 10;
			 ++quant; 
		}
		int sum = 0; 
		for(int k = 0; k < digits.size(); k++) {
			sum += Math.pow(digits.get(k), quant); 
		}
		if (sum == input) {
			return true; 
		} else {
			return false;
		}
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public boolean isPrime(Long val) {
		boolean prime = true; 
		Integer max = (int) Math.sqrt(val.doubleValue()); 
		for(int i = 2; i <= max; i++) {
			if(val % i == 0) {
				prime = false;
				break; 
			} 
		}
		return prime;
	} 
	
	public ArrayList<Long> generatePrimes(Long n){
		//returns a list of all primes that have the potential to be factors of n  
		ArrayList<Long> primes = new ArrayList<>();
		primes.add((long) 2);
		primes.add((long) 3);
		Integer max = (int) Math.sqrt(n.doubleValue()); 
		for (int i = 5; i < max; ++i) {
			if(isPrime(i)) {
				primes.add((long) i); 
			}
		}
		return primes; 
	}
	
	public List<Long> calculatePrimeFactorsOf(long l) {
		if (l < 0) l = l * (-1); 
		ArrayList<Long> factors = new ArrayList<>(); 
		long hold = l; 
		
		while(isPrime(hold) == false) {
			ArrayList<Long> possfactors = generatePrimes(hold); 
			for(int i = 0; i < possfactors.size(); i++) {
				if(hold % possfactors.get(i) == 0) {
					factors.add(possfactors.get(i)); 
					hold = hold / possfactors.get(i); 
				}
			}
		}
		if(hold > 1) factors.add(hold);  
		factors.sort(null);
		return factors;
	}
	

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			StringBuilder sb = new StringBuilder(); 
			int d;
			for (int i = 0; i < string.length(); ++i)  {
				char c = string.charAt(i);
				if(Character.isLetter(c)) {
					d = c + key;
					if(Character.isUpperCase(c)) {
						d = c + key;
						if(d > 90) {
							d -= 26;   
						}
					} else {
						d = c + key;
						if(d > 122) {
						d -= 26;  
						}
					}
					sb.append((char) d); 
				} else {
					sb.append(c); 
				}
			}	
			return sb.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public boolean isPrime(Integer val) {
		boolean prime = true; 
		Integer max = (int) Math.sqrt(val.doubleValue()); 
		for(int i = 2; i <= max; i++) {
			if(val % i == 0) {
				prime = false;
				break; 
			} 
		}
		return prime;
	} 
	
	public int calculateNthPrime(int i) {
		int j = 5; 
		int k = 3; 
		int p = 0; 
		if(i > 0) {
			switch (i) {
			case 1:
				return 2;
			case 2:
				return 3;
			default:
				while (k <= i) {
					if (isPrime(j) == true) {
						p = j;
						++k;
					}
					++j;
				}	
			}
		} else throw new IllegalArgumentException(); 
		return p;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			string = string.toLowerCase();
			StringBuilder sb = new StringBuilder(); 
			int five = 0; 
			
			for (int i = 0; i < string.length(); ++i)  {
				char c = string.charAt(i);
				if(Character.isLetter(c)) {
					double d = 109.5 - c; 
					d = Math.abs(d);
					d = 2 * d; 
					int newChar = 0;
					if (c > 109) {
						newChar = c - (int) d; 
					} else newChar = c + (int) d; 
					
				    sb.append((char) newChar);
				    five++; 	
				} else if(Character.isDigit(c)) {
					sb.append(c); 
					five++; 
				} else continue; 
				
				if((five == 5) && (string.length() - i > 2)) {
					sb.append(' '); 
					five = 0; 
				}
			}
			return sb.toString();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			StringBuilder sb = new StringBuilder(); 
			
			for (int i = 0; i < string.length(); ++i)  {
				char c = string.charAt(i);
				if(Character.isLetter(c)) {
					double d = 109.5 - c; 
					d = Math.abs(d);
					d = 2 * d; 
					int newChar = 0;
					if (c > 109) {
						newChar = c - (int) d; 
					} else newChar = c + (int) d; 
					
				    sb.append((char) newChar);	
				} else if(Character.isDigit(c)) {
					sb.append(c); 
				} else continue; 
			}
			return sb.toString();
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		ArrayList<Integer> digits = new ArrayList<>();  
		//boolean valid;
		for(int i = 0; i < string.length(); i++) {
			if(Character.isDigit(string.charAt(i))) {
				digits.add(Character.getNumericValue(string.charAt(i)));
			} else if (string.charAt(i) == 'X') {
				digits.add(10); 
			} else if (string.charAt(i) == '-') continue; 
			else {
				return false; 
				//break; 
			}
		}
		int counter = 10;
		int sum = 0; 
		for(int j = 0; j < digits.size(); j++) {
			sum += (digits.get(j) * counter);
			counter -= 1; 
		} 
		if(sum % 11 == 0) return true;
		else return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: pi-alpha-nu gamma-rho-alpha-mu-alpha, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols. //How Ironic that the question prompt does! 
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		string = string.toLowerCase(); 
		int[] letters = new int[26];
		int j = 97;
		for (int i = 0; i < letters.length; i++) {
			letters[i] = j;
			++j; 
		}
		for (int m = 0; m < 26; ++m) {
			if (string.indexOf(letters[m]) > -1) {
				continue; 
			} else {
				return false;
			}
		}	
		return true;
	}
		
		
	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 10^9 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		Temporal myT ;
		if (given.isSupported(ChronoUnit.SECONDS)){
			myT = given.plus(1000000000, ChronoUnit.SECONDS);
		} else {
			myT = LocalDateTime.of(given.get(ChronoField.YEAR), given.get(ChronoField.MONTH_OF_YEAR), given.get(ChronoField.DAY_OF_MONTH), 0, 0, 0);
			myT = myT.plus(1000000000, ChronoUnit.SECONDS); 
		} 
		return myT;
	} 

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		ArrayList<Integer> count = new ArrayList<Integer>();
		for(int j = 1; j < i; j++) { 
			for(int k = 0; k < set.length; k++) {
				if((j % set[k]) == 0) {
					if (count.contains(j)) {
						continue; 
					} else {
						count.add(j); 
					}
				}
			}
			
		}
		int sum = 0;
		for(int n = 0; n < count.size(); n++) {
			sum += count.get(n); 
		}
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		ArrayList<Character> num = new ArrayList<>();
		boolean valid = false; 
		boolean invalidChar = false;
		int sum = 0;
		for(int j = 0; j < string.length(); ++j) {
			char c = string.charAt(j); 
			if (c == ' ') continue; 
			else if (Character.isDigit(c)) {
				num.add(c); 
			} else {
				invalidChar = true; 
			}
		} 
		int size = num.size(); 
		int[] editedNum = new int[size]; 
		
		if(invalidChar == false) {
			for (int m = 0; m < size; m++){
				Integer entry = Character.getNumericValue((char) num.get(m)); 
				editedNum[m] = entry; 
			} 
			for (int i = 1; i < size;  i += 2) {
				editedNum[i] = (editedNum[i] * 2) % 9; 
			}	
			for (int k = 0; k < size; k++) {
				sum += editedNum[k]; 
			}
			if (sum % 10 == 0) {
				valid = true; 
			}						
			 
		}
		return valid;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * Add two numbers together.
	 * What is 5 plus 13?
	 * 18
	 * Now, perform the other three operations.
	 * What is 7 minus 5?
	 * 2
	 * What is 6 multiplied by 4?
	 * 24
	 * What is 25 divided by 5?
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		
		String[] words = string.split("[ ?]");
		int[] nums = new int[2];
		boolean full = false; 
		int opIndex = 0; 
		int answer = 0; 
		for(int i = 0; i < words.length; ++i) {
			if ((Character.isDigit(words[i].charAt(0))) || (words[i].charAt(0) == '-')) { 
				if (full == false) {
					nums[0] =  Integer.parseInt(words[i]);
					opIndex = i + 1; 
					full = true;
				} else {
					nums[1] = Integer.parseInt(words[i]);
				}
			}
		}
		
		String key = words[opIndex]; 
		switch (key) {
		case "plus":
			answer = nums[0] + nums[1]; 
			break;
		case "divided":
			if (nums[1] == 0) {
				System.out.println("Cannot divide by 0. Try again");
				break; 
			} else {
				answer = (int) nums[0] / nums[1]; 
				break;
			}
		case "minus":
			answer = nums[0] - nums[1]; 
			break;
		case "multiplied":
			answer = nums[0] * nums[1]; 
			break;
		
		default:
			System.out.println("Sorry, phrase did not meet specifications. Try Again"); 
			break;
		}
		
		return answer;
	}

}
