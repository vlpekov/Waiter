import javax.swing.JLabel;

public class Chair {
	private int chairNumber;
	private boolean isFree;
	private int tableNumber;
	private JLabel chairLabel;

	// private Customer customer;

	public Chair() {
		this.isFree = true;
	}

	public Chair(int chairNumber, int tableNumber) {
		this.chairNumber = chairNumber;
		this.isFree = true;
		this.tableNumber = tableNumber;
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
		return Restaurant.getTable(tableNumber);
	}
	
	public int getTableNumber() {
		return tableNumber;
	}
		
	public JLabel getChairLabel() {
		return chairLabel;
	}

	public void setChairLabel(JLabel chairLabel) {
		this.chairLabel = chairLabel;
	}

	public void chairInfo () {
		System.out.printf("Chair number: %d, Table: %d Available: %b\n", getChairNumber(), getTableNumber(), isFree());
	}
}
