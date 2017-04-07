package RestaurantObjects;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Customer {
	private int customerNumber;
	private boolean isActive;
	private double customerBill;
	private JLabel customerLabel;
	private Table onTable;
	private int tableNumber;
	public ArrayList<MenuItem> orderList;

	public Customer(int customerNumber, int tableNumber) {
		this.customerNumber = customerNumber;
		this.tableNumber = tableNumber;
		this.onTable = Restaurant.getTable(tableNumber);
		this.isActive = false;
		this.orderList = new ArrayList<MenuItem>();
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void activateCustomer() {
		System.out.println("Клиент " + this.customerNumber + " е активен.");
		setActive(true);
	}

	public void deactivateCustomer() {
		System.out.println("Клиент " + this.customerNumber + " не е акривен.");
		setActive(false);
		addToBill(0);
	}

	public double getCustomerBill() {
		return customerBill;
	}

	public void addToBill(double customerBill) {
		this.customerBill += customerBill;
		this.customerInfo();
		System.out.println("Сметка: " + this.customerBill);
	}

	public void resetBill() {
		this.customerBill = 0;
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

	public void customerInfo() {
		System.out.printf("Текущ клиент: %d, Маса: %d Актириван: %b Сметка: %.2f\n", getCustomerNumber(),
				getTableNumber(), isActive(), getBill());
	}

	private double getBill() {
		return customerBill;
	}
	
	public void addToOrderList (MenuItem item) {
		orderList.add(item);
	}
}
