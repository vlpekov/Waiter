package GUI;

import RestaurantObjects.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class CustomerOrderList extends JPanel {
	int currentRow;
	private final int firstColumn = 0;
	private final int secondColumn = 1;
	private final int thirdColumn = 2;
	private final int fourthColumn = 3;
	
	// static ArrayList<RestaurantObjects.MenuItem> menuFromJTable = new
	// ArrayList<RestaurantObjects.MenuItem>();

	public CustomerOrderList(Customer currentCustomer) {

		String[] header = { "Име", "Цена", "Количество", "Категория" };

		DefaultTableModel tableModel = new DefaultTableModel(header, 0);

		JTable table = new JTable(tableModel);

		setTableProperties(table);
		new Menu();

		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultEditor(Object.class, null);
		// printTable(table);
		// printArrayList(menuFromJTable);

		
		for (int i = 0; i < currentCustomer.orderList.size(); i++) {
			String name = currentCustomer.orderList.get(i).getName();
			double price = currentCustomer.orderList.get(i).getPrice();
			String quantity = currentCustomer.orderList.get(i).getQuantity();
			String category = currentCustomer.orderList.get(i).getCategory();
			System.out.println("" + name + price + quantity + category);
			Object[] data = { name, price, quantity, category };

			tableModel.addRow(data);

		}
	
	}


	public void runMenuTable(Customer currentCustomer) {
		JFrame tableFrame = new JFrame();
		tableFrame.setBounds(200, 200, 800, 400);
		CustomerOrderList orderList = new CustomerOrderList(currentCustomer);
		tableFrame.setTitle("Меню");
		tableFrame.setVisible(true);
		tableFrame.add(orderList);
	}


	public void setTableProperties(JTable table) {
		table.getColumn("Име").setMinWidth(400);
		table.getColumn("Цена").setMinWidth(60);
		table.getColumn("Цена").setMaxWidth(60);
		table.getColumn("Количество").setMinWidth(100);
		table.getColumn("Количество").setMaxWidth(100);
		table.getColumn("Количество").setResizable(false);
		table.getColumn("Цена").setResizable(false);
		table.setPreferredScrollableViewportSize(new Dimension(770, 330));
		table.setFillsViewportHeight(true);

		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setVisible(true);
		add(tableScroll);
	}

}