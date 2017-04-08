package RestaurantObjects;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;

import GUI.Interface;

public class Table {
	private int tableNumber;
	private int placesNumber;
	private boolean isAvailable;
	private boolean isReserved;
	private double bill;

	private ArrayList<Chair> chairs = new ArrayList<Chair>();
	private ArrayList<Customer> customers = new ArrayList<Customer>();

	public Table(int tableNumber, int placesNumber) {
		this.tableNumber = tableNumber;
		this.placesNumber = placesNumber;
		this.isAvailable = true;
		this.isReserved = false;
		setChairs();
		setCustomers();
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

	public ArrayList<Customer> getCustomersArrayList() {
		return customers;
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

	public double getBill() {
		return bill;
	}

	public void addToBill(double bill) {
		this.bill += bill;
	}

	public void resetBill() {
		this.bill = 0;
	}

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
		Interface.setPopupMenuPayBill(true);
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
		isBillPaid();
		this.setAvailable(true);
		this.setReserved(false);

	}

	private void isBillPaid() {
		// TODO Auto-generated method stub

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
		System.out.printf("Маса номер: %d, Брой столове: %d, Статус: %b\n", this.getTableNumber(),
				this.getPlacesNumber(), this.isAvailable());
	}

	public void setChairs() {
		for (int i = 0; i < getPlacesNumber(); i++) {
			chairs.add(new Chair(i + 1, getTableNumber()));
		}
	}

	public void printChairList() {
		System.out.println("Chairs:");
		for (Chair chair : this.chairs) {
			chair.chairInfo();
		}
	}

	public void printCustomerList() {
		System.out.println("Customers:");
		for (Customer customer : customers) {
			customer.customerInfo();
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

	public void setCustomers() {
		for (int i = 0; i < getPlacesNumber(); i++) {
			customers.add(new Customer(i + 1, getTableNumber()));
		}
	}

	public Customer getCustomer(int customerNumber) {
		Customer getCustomer = null;
		for (Customer customer : customers) {
			if (customer.getCustomerNumber() == customerNumber) {
				getCustomer = customer;
			}
		}
		return getCustomer;
	}

	public Customer getCustomer(JLabel customerLabel) {
		Customer getCustomer = null;
		for (Customer customer : customers) {
			if (customer.getCustomerLabel().equals(customerLabel)) {
				getCustomer = customer;
			}
		}
		return getCustomer;
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
			tableInfo = "Резервирана";
		} else if (!isAvailable) {
			tableInfo = "Заета";
		} else {
			tableInfo = "Свободна";
		}
		return tableInfo;
	}

	public double getTableBill() {
		double bill = 0;
		for (Customer customer : customers) {
			bill += customer.getCustomerBill();
			System.out
					.println("Сметката на клиент" + customer.getCustomerNumber() + " е " + customer.getCustomerBill());
		}
		System.out.println("Сметката на маса " + tableNumber + " е: " + bill);
		System.out.println("Сметката на маса " + tableNumber + " е: " + getBill());
		return bill;
	}

	public void refreshTableBill() {
		bill = getTableBill();
	}

	public void billInfo() {
		System.out.println("Сметка: " + getBill());
	}

	public void clearBill() {
		resetBill();
		;
		for (Customer customer : customers) {
			customer.resetBill();
		}
	}
}
