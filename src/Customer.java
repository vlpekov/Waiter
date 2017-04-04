import javax.swing.JLabel;

public class Customer {
	private int customerNumber;
	private boolean isActive;
	private double customerBill;
	private JLabel customerLabel;
	private Table onTable;
	private int tableNumber;

	public Customer(int customerName, int tableNumber) {
		this.customerNumber = customerName;
		this.tableNumber = tableNumber;
		this.onTable = Restaurant.getTable(tableNumber);
		this.isActive = false;
	}

	public int getCustomerName() {
		return customerNumber;
	}

	public void setCustomerName(int customerName) {
		this.customerNumber = customerName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getCustomerBill() {
		return customerBill;
	}

	public void setCustomerBill(double customerBill) {
		this.customerBill = customerBill;
	}

	public JLabel getCustomerLabel() {
		return customerLabel;
	}

	public void setCustomerLabel(JLabel customerLabel) {
		this.customerLabel = customerLabel;
	}

	public Table getCustomerTable() {
		return onTable;
	}

	public void setCustomerTable(Table onTable) {
		this.onTable = onTable;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

}
