package GUI;

import RestaurantObjects.*;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PaymentTable extends JPanel {
	int currentRow;

	public PaymentTable(int tableNumber) {
		Table tableObject = Restaurant.getTable(tableNumber);
		String[] header = { "Клиент", "Сметка" };
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		JTable table = new JTable(tableModel);
		setTableProperties(table);

		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultEditor(Object.class, null);

		fillTable(tableObject, tableModel);
	}



	private void fillTable(Table tableObject, DefaultTableModel tableModel) {
		double totalBill = 0;
		for (int i = 1; i <= tableObject.getPlacesNumber(); i++) {
			if (tableObject.getCustomer(i).isActive()) {
				Customer currentCustomer = tableObject.getCustomer(i);
				String name = "Клиент " + currentCustomer.getCustomerNumber();
				double price = currentCustomer.getCustomerBill();
				String billString = String.format("%1$,.2f", price);
				billString = billString.replace(',', '.');
				price = Double.parseDouble(billString);
				totalBill += price;
				Object[] data = { name, price };
				tableModel.addRow(data);
			}

		}
		String billString = String.format("%1$,.2f", totalBill);
		billString = billString.replace(',', '.');
		totalBill = Double.parseDouble(billString);
		Object[] data = { "Всичко", totalBill };
		tableModel.addRow(data);
		
	}



	public void runMenuTable(int tableNumber) {
		JFrame tableFrame = new JFrame();
		tableFrame.setBounds(300, 300, 295, 245);
		PaymentTable billList = new PaymentTable(tableNumber);
		tableFrame.setTitle("Текуща сметка на маса" + tableNumber);
		tableFrame.setVisible(true);
		tableFrame.add(billList);
	}

	public void setTableProperties(JTable table) {
		table.getColumn("Клиент").setMinWidth(200);
		table.getColumn("Сметка").setMinWidth(60);
		table.getColumn("Сметка").setMaxWidth(60);
		table.getColumn("Сметка").setResizable(false);
		table.setPreferredScrollableViewportSize(new Dimension(270, 180));
		table.setFillsViewportHeight(true);

		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setVisible(true);
		add(tableScroll);
	}

}