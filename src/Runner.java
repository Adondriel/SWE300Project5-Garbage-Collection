import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Benjamin Uleau, Adam Pine, Joshua McMillen
 */
public class Runner {

	static List<WeakReference<String>> intList = new ArrayList<WeakReference<String>>();
	static WeakReference<List<WeakReference<String>>> weakList = new WeakReference<List<WeakReference<String>>>(
			intList);

	/**
	 * Loads the values into the list
	 */
	public static void loadList() {

		File file = new File("variables.txt");

		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				WeakReference<String> input = new WeakReference<String>(sc.nextLine());
				weakList.get().add(input);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return whether or not a null was found
	 */
	public static boolean nullFound() {
		int i = 0;
		boolean found = false;

		while (found == false && i < weakList.get().size()) {
			if (weakList.get().get(i).get() == null) {
				found = true;
			}
			i++;
		}

		return found;
	}

	/**
	 * Clear the list to prepare for reloading
	 */
	public static void clear() {
		int size = weakList.get().size();
		for (int i = 0; i < size; i++) {
			weakList.get().remove(0);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		loadList();
		boolean found = nullFound();

		// Before garbage collection there should be no nulls
		if (!found) {
			System.out.println("Good");
		}

		System.gc();
		found = nullFound();

		// After garbage collection, there should be nulls
		if (found) {
			System.out.println("Garbage collection detected");
		}

		clear();
		loadList();
		found = nullFound();

		// After list is reloaded with values, it shouldn't have nay nulls
		if (!found) {
			System.out.println("Garbage collection reverted; list re-initialized");
		}

	}
}