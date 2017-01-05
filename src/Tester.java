import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester {

	private static int twoThreeWin = 0;
	private static int threeTwoWin = 0;
	private static int twoThreeThreeWin = 0;

	private static int twoThreeLose = 0;
	private static int threeTwoLose = 0;
	private static int twoThreeThreeLose = 0;

	public static void main(String[] args) throws IOException {

		/*
		 * int numOfShoe = 1;
		 * 
		 * for (int i = 1; i <= numOfShoe; i++) {
		 * 
		 * LinkedList<Card> shoe = new Shoe().initShuffleShoe();
		 * 
		 * PlayShoe game = new PlayShoe(shoe);
		 * 
		 * TestTwoThreePattern(game); TestThreeTwoPattern(game);
		 * TestTwoThreeThreePattern(game);
		 * 
		 * }
		 * 
		 * System.out.println("Total Number of Shoes: " + numOfShoe);
		 * System.out.println("Total twoThreeWin: " + twoThreeWin);
		 * System.out.println("Total twoThreeLose: " + twoThreeLose);
		 * System.out.println("Total threeTwoWin: " + threeTwoWin);
		 * System.out.println("Total threeTwoLose: " + threeTwoLose);
		 * System.out.println("Total twoThreeThreeWin: " + twoThreeThreeWin);
		 * System.out.println("Total twoThreeThreeLose: " + twoThreeThreeLose);
		 * 
		 */

		LinkedList<Card> shoe = new Shoe().initShuffleShoe();

		PlayShoe game1 = new PlayShoe(shoe);

		System.out.println("Number of Hands: " + game1.getNumHands());
		System.out.println("Number of Bankers: " + game1.getNumBanker());
		System.out.println("Number of Players: " + game1.getNumPlayer());
		System.out.println("Number of Naturals: " + game1.getNumNaturals());
		System.out.println("Number of Dragaons: " + game1.getNumDragon());
		System.out.println("Number of Pandas: " + game1.getNumPanda());
		System.out.println("Number of Ox's: " + game1.getNumOx());
		System.out.println("Number of Kills: " + game1.getNumKills());
		System.out.println("Number of Tie's: " + game1.getNumTie());
		System.out.println("Size of Results: " + game1.getResults().size());

		System.out.println("------TwoThreePattern------------------");
		TestTwoThreePattern(game1);
		System.out.println("------ThreeTwoPattern------------------");
		TestThreeTwoPattern(game1);
		System.out.println("------TwoThreeThreePattern------------------");
		TestTwoThreeThreePattern(game1);

	}

	static void TestTwoThreePattern(PlayShoe game) {

		List<Character> line1 = new ArrayList<Character>();
		List<Character> line2 = new ArrayList<Character>();
		List<Character> line3 = new ArrayList<Character>();
		List<Character> results = new ArrayList<Character>();

		List<String> units1 = new ArrayList<String>();
		List<String> units2 = new ArrayList<String>();
		boolean lose = false;

		results = game.getResults();
		int var = 1;

		for (Character w : results) {

			switch (var) {
			case 1:
				line1.add(w);
				var++;
				break;
			case 2:
				line2.add(w);
				var++;
				break;
			case 3:
				line1.add(w);
				var++;
				break;
			case 4:
				line2.add(w);
				var++;
				break;
			case 5:
				line3.add(w);
				var = 1;
				break;

			}
		}

		// Print Line1
		StringBuilder result1 = new StringBuilder(line1.size());
		for (Character c : line1) {
			result1.append(c);
		}

		String output1 = result1.toString();
		// System.out.println("Size of Line1: " + line1.size());
		// System.out.println(output1);

		// Print Line2
		StringBuilder result2 = new StringBuilder(line2.size());
		for (Character c : line2) {
			result2.append(c);
		}

		// System.out.println("Size of Line2: " + line2.size());
		String output2 = result2.toString();
		// System.out.println(output2);

		units1 = UnitTester(output1);

		for (String unit : units1) {
			if (unit.length() >= 7) {
				twoThreeLose++;
				lose = true;
				break;
			}
		}

		units2 = UnitTester(output2);

		if (!lose) {
			for (String unit : units2) {
				if (unit.length() >= 7) {
					twoThreeLose++;
					lose = true;
					break;
				}
			}
		}

		if (!lose) {
			twoThreeWin++;
		}

	}

	static void TestThreeTwoPattern(PlayShoe game) {

		List<Character> line1 = new ArrayList<Character>();
		List<Character> line2 = new ArrayList<Character>();
		List<Character> line3 = new ArrayList<Character>();
		List<Character> results = new ArrayList<Character>();

		List<String> units1 = new ArrayList<String>();
		List<String> units2 = new ArrayList<String>();
		boolean lose = false;

		results = game.getResults();
		int var = 1;

		for (Character w : results) {

			switch (var) {
			case 1:
				line1.add(w);
				var++;
				break;
			case 2:
				line2.add(w);
				var++;
				break;
			case 3:
				line3.add(w);
				var++;
				break;
			case 4:
				line1.add(w);
				var++;
				break;
			case 5:
				line2.add(w);
				var = 1;
				break;

			}
		}

		// Print Line1
		StringBuilder result1 = new StringBuilder(line1.size());
		for (Character c : line1) {
			result1.append(c);
		}
		String output1 = result1.toString();
		// System.out.println("Size of Line1: " + line1.size());
		// System.out.println(output1);

		// Print Line2
		StringBuilder result2 = new StringBuilder(line2.size());
		for (Character c : line2) {
			result2.append(c);
		}
		// System.out.println("Size of Line2: " + line2.size());
		String output2 = result2.toString();
		// System.out.println(output2);

		units1 = UnitTester(output1);

		for (String unit : units1) {
			if (unit.length() >= 7) {
				threeTwoLose++;
				lose = true;
				break;
			}
		}

		units2 = UnitTester(output2);

		if (!lose) {
			for (String unit : units2) {
				if (unit.length() >= 7) {
					threeTwoLose++;
					lose = true;
					break;
				}
			}
		}

		if (!lose) {
			threeTwoWin++;
		}
	}

	static void TestTwoThreeThreePattern(PlayShoe game) {

		List<Character> line1 = new ArrayList<Character>();
		List<Character> line2 = new ArrayList<Character>();
		List<Character> line3 = new ArrayList<Character>();
		List<Character> results = new ArrayList<Character>();
		List<String> units1 = new ArrayList<String>();
		List<String> units2 = new ArrayList<String>();
		boolean lose = false;

		results = game.getResults();
		int var = 1;

		for (Character w : results) {

			switch (var) {
			case 1:
				line1.add(w);
				var++;
				break;
			case 2:
				line2.add(w);
				var++;
				break;
			case 3:
				line1.add(w);
				var++;
				break;
			case 4:
				line2.add(w);
				var++;
				break;
			case 5:
				line3.add(w);
				var++;
				break;
			case 6:
				line1.add(w);
				var++;
				break;
			case 7:
				line2.add(w);
				var++;
				break;
			case 8:
				line3.add(w);
				var = 1;
				break;

			}
		}

		// Print Line1
		StringBuilder result1 = new StringBuilder(line1.size());
		for (Character c : line1) {
			result1.append(c);
		}
		String output1 = result1.toString();
		// System.out.println("Size of Line1: " + line1.size());
		// System.out.println(output1);

		// Print Line2
		StringBuilder result2 = new StringBuilder(line2.size());
		for (Character c : line2) {
			result2.append(c);
		}
		// System.out.println("Size of Line2: " + line2.size());
		String output2 = result2.toString();
		// System.out.println(output2);

		units1 = UnitTester(output1);

		for (String unit : units1) {
			// System.out.println("unit Length: " + unit.length());
			if (unit.length() >= 7) {
				twoThreeThreeLose++;
				lose = true;
				break;
			}
		}

		units2 = UnitTester(output2);

		if (!lose) {
			for (String unit : units2) {
				// System.out.println("unit Length: " + unit.length());
				if (unit.length() >= 7) {
					twoThreeThreeLose++;
					lose = true;
					break;
				}
			}
		}

		if (!lose) {
			twoThreeThreeWin++;
			// System.out.println(twoThreeThreeWin);
		}

	}

	static List<String> UnitTester(String output) {

		String pattern = "(.)\\1+";
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(output);

		while (m.find()) {
			tokens.add(m.group());

		}

		return tokens;

	}

	static void TestMod() {

		System.out.println("23 mod: " + (23 % 10));
		System.out.println("27 mod: " + (27 % 10));
		System.out.println("15 mod: " + (15 % 10));
		System.out.println("26 mod: " + (26 % 10));
		System.out.println("3 mod: " + (3 % 10));
		System.out.println("9 mod: " + (9 % 10));
		System.out.println("11 mod: " + (11 % 10));
	}

}
