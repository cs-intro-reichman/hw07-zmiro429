
public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String[] dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		// dictionary = in.readAllStrings();
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String[] dictionary) {
		// Your code here
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i]))
				return true;
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) {
			return;
		}

		int N = hashtag.length();
		hashtag = hashtag.toLowerCase();
		int newword = 0;
		for (int i = 1; i <= N; i++)
			if (existInDictionary(hashtag.substring(newword, i), dictionary)) {
				System.out.println(hashtag.substring(newword, i));
				newword = i;
			}
	}

}
