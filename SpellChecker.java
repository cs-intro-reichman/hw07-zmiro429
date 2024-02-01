
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
		String newstr = "";
		if (str.length() == 1) {
			return newstr;
		}
		for (int i = 1; i < str.length(); i++) {
			newstr += str.charAt(i);
		}
		return newstr;
	}

	public static int levenshtein(String word1, String word2) {
		int sum = 0, biggest = 0;
		// make word1 and word2 lower case
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word2.length() == 0)
			return word1.length();
		else if (word1.length() == 0)
			return word2.length();
		else if (word1.charAt(0) == word2.charAt(0)) {
			if (word1.length() > word2.length())
				biggest = word1.length();
			else
				biggest = word2.length();
			for (int i = 0; i < biggest; i++) {
				if (word1.charAt(minvalue(i, word1.length() - 1)) != word2.charAt(minvalue(i, word2.length() - 1))) {
					sum++;
				}
			}
			return sum;
		} else {
			return 1 + minvalueforlevenshtien(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2)),
					levenshtein(tail(word1), tail(word2)));
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
		// Your code goes here
		for (int i = 0; i < dictionary.length; i++) {
			if (levenshtein(word, dictionary[i]) <= threshold) {
				return dictionary[i];
			}
		}
		return word;
	}

	// min value of 2 numbers
	public static int minvalue(int alpha1, int alpha2) {
		if (alpha1 > alpha2)
			return alpha2;
		return alpha1;
	}

	// minimum value of three numbers
	public static int minvalueforlevenshtien(int num1, int num2, int num3) {
		int biggest;
		if (num1 > num2) {
			biggest = num1;
		} else if (num1 > num3) {
			biggest = num1;
		} else if (num2 > num3) {
			biggest = num2;
		} else if (num2 > num1) {
			biggest = num2;
		} else {
			biggest = num3;
		}
		return biggest;
	}

}
