
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {

		// make word1 and word2 lower case
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if (word2.length() == 0)
			return word1.length();
		else if (word1.length() == 0)
			return word2.length();
		else if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		} else {
			return 1 + minvalueforlevenshtien(
					levenshtein(tail(word1), word2), // deletion
					levenshtein(word1, tail(word2)), // insertion
					levenshtein(tail(word1), tail(word2))); // substitution

		}

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String failsafe = word;
		for (int i = 0; i < dictionary.length; i++) {
			if (word == dictionary[i])
				return word;
			if (levenshtein(word, dictionary[i]) <= threshold) {
				failsafe = dictionary[i];
			}
		}
		return failsafe;
	}

	// min value of 2 numbers
	public static int minvalue(int alpha1, int alpha2) {
		return Math.min(alpha1, alpha2);
	}

	// minimum value of three numbers
	public static int minvalueforlevenshtien(int num1, int num2, int num3) {
		return Math.min(Math.min(num1, num2), num3);
	}

}
