import java.util.ArrayList;
import java.util.Arrays;

public class Table {
	private int tableNumber;
	private int placesNumber;
	private boolean isAvailable;
	private boolean isReserved;
	// public static ArrayList<Table> tableList = new ArrayList<Table>();
	private ArrayList<Chair> chairs = new ArrayList<Chair>();

	public Table(int tableNumber, int placesNumber) {
		this.tableNumber = tableNumber;
		this.placesNumber = placesNumber;
		this.isAvailable = true;
		this.isReserved = false;
		setChairs();
	}

	public Table() {
		Table table0 = new Table(0, 0);
		Table table1 = new Table(1, 6);
		Table table2 = new Table(2, 4);
		Table table3 = new Table(3, 4);
		Table table4 = new Table(4, 6);
		Table table5 = new Table(5, 10);
		Table table6 = new Table(6, 10);
		Table table7 = new Table(7, 10);
		Table table8 = new Table(8, 3);
		Table table9 = new Table(9, 3);
		//
		// tableList.add(table1);
		// tableList.add(table2);
		// tableList.add(table3);
		// tableList.add(table4);
		// tableList.add(table5);
		// tableList.add(table6);
		// tableList.add(table7);
		// tableList.add(table8);
		// tableList.add(table9);

		Restaurant.tableList.add(table1);
		Restaurant.tableList.add(table2);
		Restaurant.tableList.add(table3);
		Restaurant.tableList.add(table4);
		Restaurant.tableList.add(table5);
		Restaurant.tableList.add(table6);
		Restaurant.tableList.add(table7);
		Restaurant.tableList.add(table8);
		Restaurant.tableList.add(table9);

	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getPlacesNumber() {
		return placesNumber;
	}

	public void setPlacesNumber(int placesNumber) {
		this.placesNumber = placesNumber;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public boolean isReserved() {
		return isReserved;
	}

	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}

	// public void printTableList() {
	// System.out.println("Table list:");
	// for (Table table : tableList) {
	// System.out.printf("Table number: %d, Number of chairs: %d, Available:
	// %b\n", table.getTableNumber(),
	// table.getPlacesNumber(), table.isAvailable());
	// }
	// }

	public void occupyTable(int tableNumber) {
		for (Table table : Restaurant.tableList) {
			if (table.getTableNumber() == tableNumber) {
				table.setAvailable(false);
				table.setReserved(false);
			}
		}
	}

	public void occupyTable() {
		this.setAvailable(false);
		this.setReserved(false);
	}

	public void release(int tableNumber) {
		for (Table table : Restaurant.tableList) {
			if (table.getTableNumber() == tableNumber) {
				table.setAvailable(true);
				table.setReserved(false);
			}
		}
	}

	public void release() {
		this.setAvailable(true);
		this.setReserved(false);

	}

	public void bookTable(int tableNumber) {
		for (Table table : Restaurant.tableList) {
			if (table.getTableNumber() == tableNumber) {
				table.setAvailable(false);
				table.setReserved(true);
			}
		}
	}

	public void bookTable() {
		this.setAvailable(false);
		this.setReserved(true);
	}

	public void printTableinfo() {
		System.out.printf("Table number: %d, Number of chairs: %d, Available: %b\n", this.getTableNumber(),
				this.getPlacesNumber(), this.isAvailable());
	}

	public void setChairs() {
		for (int i = 0; i < getPlacesNumber(); i++) {
			chairs.add(new Chair(i + 1));
		}
	}

	public void printChairList() {
		System.out.println("Chairs:");
		for (Chair chair : this.chairs) {
			System.out.printf("Chair number: %d, Available: %b\n", chair.getChairNumber(), chair.isFree());
		}
	}

	public Chair getChair(int chairNumber) {
		Chair getChair = null;
		for (Chair chair : chairs) {
			if (chair.getChairNumber() == chairNumber) {
				getChair = chair;
			}
		}
		return getChair;
	}

	public int countFreeChairs() {
		int countFreeChairs = 0;
		for (Chair chair : chairs) {
			if (chair.isFree()) {
				countFreeChairs++;
			}
		}
		return countFreeChairs;
	}

	public void swapTables(Table baseTable, Table tableSuccessor) {
		int customersNumber = baseTable.getPlacesNumber() - baseTable.countFreeChairs();
		if (customersNumber <= tableSuccessor.getPlacesNumber() && tableSuccessor.isAvailable()) {
			tableSuccessor = baseTable;
			baseTable.release();
		}
	}

	public void releaseChairs() {
		for (Chair chair : chairs) {
			chair.setFree(true);
		}
	}
	
	public String getTabelInfo() {
		String tableInfo = "";
		if (isReserved) {
			tableInfo = "Reserved";
		} else if (!isAvailable) {
			tableInfo = "Оccupied.";
		} else {
			tableInfo = "Аvailable";
		}
		return tableInfo;
	}
	

}
