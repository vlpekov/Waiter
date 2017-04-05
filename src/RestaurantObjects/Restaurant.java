import java.util.ArrayList;

public class Restaurant {
	public static ArrayList<Table> tableList = new ArrayList<Table>();

	public static void printTableList() {
		System.out.println("Table list:");
		for (Table table : tableList) {
			System.out.printf("Table number: %d, Number of chairs: %d, Available: %b\n", table.getTableNumber(),
					table.getPlacesNumber(), table.isAvailable());
		}
	}

	public static Table getTable(int tableNumber) {
		Table getTable = null;
		for (Table table : tableList) {
			if (table.getTableNumber() == tableNumber) {
				getTable = table;
			}
		}
		return getTable;
	}

}
