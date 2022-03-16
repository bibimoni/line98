import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.Map.Entry;

public class line98 {

	public static void main(String[] args) {
		// max move value
		int maxBallBroken = 0;
		// input
		Scanner input = new Scanner(System.in);
		System.out.println("enter max time, and the rectangle m*n");
		String layout = input.nextLine();
		String[] layoutArr = layout.trim().split("\\s+");
		int k = Integer.parseInt(layoutArr[0]); // max moves
		int m = Integer.parseInt(layoutArr[1]); // collumns
		int n = Integer.parseInt(layoutArr[2]); // rows

		System.out.println("enter ur board at the end of row hit ,");
		String boardInput = input.nextLine();
		int[][] board = new int[n][m];
		board = convertTo2DArr(boardInput, n, m);
		// array for manage elements
		ArrayList<Integer> elements = new ArrayList<Integer>();
		elements = uniqueElement(board);
		// create hashmap with elements and the number of element
		HashMap<Integer, Integer> elementNumber = new HashMap<Integer, Integer>();
		elementNumber = getNumberOfElement(board, elements);
		maxBallBroken = getMaxBallBroken(k, elementNumber, elements);
		System.out.println(maxBallBroken);

	}

	public static int getMaxBallBroken(int k, HashMap<Integer, Integer> elementNumber, ArrayList<Integer> elements) {
		int a = 0;
		int maxMoveAllowed = elements.size();
		if (k < maxMoveAllowed) {
			for (int m = 0; m < k; m++) {
				int max = 0;
				// find max value of each key and add it to a
				for (int i = 0; i < elements.size(); i++) {
					if (max <= elementNumber.get(elements.get(i))) {
						max = elementNumber.get(elements.get(i));
					}
					// after get the max value, remove the hashmap and the element
					elements.remove(i);
					elementNumber.remove(getKey(elementNumber, max));
				}
				a += max;
			}
		} else {
			// if k = all unique element then return the value of all keys
			for (int i : elementNumber.values()) {
				a += i;
			}
		}

		/*
		 * elementNumber.entrySet().forEach(entry -> {
		 * System.out.println(entry.getKey()+" "+entry.getValue()); });
		 */
		return a;
	}

	public static int getKey(HashMap<Integer, Integer> elementNumber, int value) {
		for (Entry<Integer, Integer> entry : elementNumber.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return 0;
	}

	public static HashMap<Integer, Integer> getNumberOfElement(int[][] board, ArrayList<Integer> elements) {
		HashMap<Integer, Integer> elementNumber = new HashMap<Integer, Integer>();
		for (int k = 0; k < elements.size(); k++) {
			int count = 0;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j] == elements.get(k)) {
						count++;
					}
				}
			}
			elementNumber.put(elements.get(k), count);
		}
		return elementNumber;
	}

	public static ArrayList<Integer> uniqueElement(int[][] board) {
		ArrayList<Integer> elements = new ArrayList<Integer>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (!elements.contains(board[i][j])) {
					elements.add(board[i][j]);
				}
			}
		}
		return elements;
	}

	public static int[][] convertTo2DArr(String boardInput, int rows, int cols) {
		int[][] board = new int[rows][cols];
		// split the string to multiples string as rows
		String[] strRows = boardInput.split(",");
		for (int i = 0; i < strRows.length; i++) {
			// split the rows to collumn
			String[] strCols = strRows[i].split("\\s+");
			for (int j = 0; j < strCols.length; j++) {
				board[i][j] = Integer.parseInt(strCols[j]);
			}
		}
		return board;
	}

}
