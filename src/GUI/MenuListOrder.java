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

public class MenuListOrder extends JPanel {
	int currentRow;
	private final int firstColumn = 0;
	private final int secondColumn = 1;
	private final int thirdColumn = 2;
	private final int fourthColumn = 3;
	// static ArrayList<RestaurantObjects.MenuItem> menuFromJTable = new
	// ArrayList<RestaurantObjects.MenuItem>();

	public MenuListOrder() {

		String[] header = { "Име", "Цена", "Количество", "Категория" };
		// String[][] data = new String[1000][];

		DefaultTableModel tableModel = new DefaultTableModel(header, 0);

		JTable table = new JTable(tableModel);
		// table.getColumn("Име").setMinWidth(400);
		// table.getColumn("Цена").setMinWidth(60);
		// table.getColumn("Цена").setMaxWidth(60);
		// table.getColumn("Количество").setMinWidth(100);
		// table.getColumn("Количество").setMaxWidth(100);
		// table.getColumn("Количество").setResizable(false);
		// table.getColumn("Цена").setResizable(false);
		// table.setPreferredScrollableViewportSize(new Dimension(770, 330));
		// table.setFillsViewportHeight(true);
		//
		// JScrollPane tableScroll = new JScrollPane(table);
		// tableScroll.setVisible(true);
		// add(tableScroll);
		setTableProperties(table);
		new Menu();

		for (int i = 0; i < Menu.getMenuList().size(); i++) {
			String name = Menu.getMenuList().get(i).getName();
			double price = Menu.getMenuList().get(i).getPrice();
			String quantity = Menu.getMenuList().get(i).getQuantity();
			String category = Menu.getMenuList().get(i).getCategory();

			Object[] data = { name, price, quantity, category };

			tableModel.addRow(data);

		}
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultEditor(Object.class, null);
		// printTable(table);
		// printArrayList(menuFromJTable);
	}

	public static void main(String[] args) {

		runMenuTableTest();

	}

	public void runMenuTable() {
		JFrame tableFrame = new JFrame();
		tableFrame.setBounds(200, 200, 800, 400);
		MenuListOrder menuTable = new MenuListOrder();
		tableFrame.setTitle("Меню");
		// tableFrame.setSize(800, 400);
		tableFrame.setVisible(true);
		// tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableFrame.add(menuTable);
	}

	public static void runMenuTableTest() {
		JFrame tableFrame = new JFrame();
		tableFrame.setBounds(200, 200, 800, 400);

		MenuListOrder menuTable = new MenuListOrder();
		tableFrame.setTitle("Меню");
		// tableFrame.setSize(800, 400);
		tableFrame.setVisible(true);
		tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableFrame.add(menuTable);

	}

	private void menuTableMouseEvent(JTable table) {

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				currentRow = table.getSelectedRow();
				if (e.getClickCount() == 2) {
					getDataFromCurrentRow(table, currentRow);
				}
			}
		});
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

		menuTableMouseEvent(table);

	}

	private Object[] getDataFromCurrentRow(JTable table, int currentRow) {
		String name = (String) table.getValueAt(currentRow, firstColumn);
		double price = (double) table.getValueAt(currentRow, secondColumn);
		String quantity = (String) table.getValueAt(currentRow, thirdColumn);
		Object[] data = { name, price, quantity };
		OrderTable.addNewRow(name, price, quantity);
		return data;
	}

}