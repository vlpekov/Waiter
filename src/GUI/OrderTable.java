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
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SizeAction;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class OrderTable extends JPanel {
	int currentRow;
	private final int firstColumn = 0;
	private final int secondColumn = 1;
	private final int thirdColumn = 2;
	private final int fourthColumn = 3;


	public OrderTable() {

		String[] header = { "Име", "Цена", "Количество"};

		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		setSize(400, 200);
		JTable table = new JTable(tableModel);
		table.setFillsViewportHeight(true);

		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setVisible(true);
		add(tableScroll);
		setTableProperties(table);
		new Menu();

//		for (int i = 0; i < Menu.getMenuList().size(); i++) {
//			String name = Menu.getMenuList().get(i).getName();
//			double price = Menu.getMenuList().get(i).getPrice();
//			String quantity = Menu.getMenuList().get(i).getQuantity();
//
//			Object[] data = { name, price, quantity };
//
//			tableModel.addRow(data);
//
//		}
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultEditor(Object.class, null);
	}

	public static void main(String[] args) {

		runMenuTableTest();

	}

	public void runMenuTable() {
	}

	public static void runMenuTableTest() {
	}

	public void setTableProperties(JTable table) {
		table.getColumn("Име").setMinWidth(200);
		table.getColumn("Име").setMaxWidth(300);
		table.getColumn("Цена").setMinWidth(40);
		table.getColumn("Цена").setMaxWidth(60);
		table.getColumn("Количество").setMinWidth(60);
		table.getColumn("Количество").setMaxWidth(100);
		table.getColumn("Количество").setResizable(false);
		table.getColumn("Цена").setResizable(false);

		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setVisible(true);
		add(tableScroll);
	}

}