
public class Chair {
	private int chairNumber;
	private boolean isFree;
	private Table table;

	// private Customer customer;

	public Chair() {
		this.isFree = true;
	}

	public Chair(int chairNumber, int tableNumber) {
		this.chairNumber = chairNumber;
		this.isFree = true;
		this.table = Restaurant.getTable(tableNumber);
	}

	public int getChairNumber() {
		return chairNumber;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public Table getTable() {
		return table;
	}

}
