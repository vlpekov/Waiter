package GUI;

import RestaurantObjects.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
	private JTable table;
	private static DefaultTableModel tableModel;

	public OrderTable() {

		String[] header = { "Име", "Цена", "Количество"};

		tableModel = new DefaultTableModel(header, 0);
		setSize(400, 200);
		this.table = new JTable(tableModel);
		table.setFillsViewportHeight(true);

//		JScrollPane tableScroll = new JScrollPane(table);
//		tableScroll.setVisible(true);
//		add(tableScroll);
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
		
		JPopupMenu popupMenuTable = new JPopupMenu();
		JMenuItem mntmRemove = new JMenuItem("Премахни");
		
		mntmRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				tableModel.removeRow(selectedRow);
			}
		});
		
		popupMenuTable.add(mntmRemove);
		addPopup(table, popupMenuTable);
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
	}
	
	public static void main(String[] args) {

		runMenuTableTest();

	}

	public static void addNewRow (String name, double price, String quantity) {
		Object[] data = { name, price, quantity };
		tableModel.addRow(data);
	}
	
	public void deleteRow (int row) {
		tableModel.removeRow(row);
	}
	
	public void deleteAllRow () {
		for (int row = 0; row<tableModel.getRowCount(); row++) {
		tableModel.removeRow(row);
		}
	}
	
	public double sumItemPrices() {
		double sum = 0;
		    for (int i = 0; i < table.getRowCount(); i++){
		        double itemPrice = (double) table.getValueAt(i, 1);
		        sum += itemPrice;
		    }
		    System.out.println("Направена е поръчка на стойност: " + sum);
		    setSumCustomer(sum);
		    setSumTable(sum);
		    
		    return sum;
	}
	
	private void setSumTable(double sum) {
		OrderDialog.getTableObject().addToBill(sum);
	}

	private void setSumCustomer(double sum) {
		OrderDialog.getCustomerObject().addToBill(sum);;
		
	}


	public static void runMenuTableTest() {
	}

	private void setTableProperties(JTable table) {
		table.getColumn("Име").setMinWidth(200);
		table.getColumn("Име").setMaxWidth(300);
		table.getColumn("Цена").setMinWidth(40);
		table.getColumn("Цена").setMaxWidth(60);
		table.getColumn("Количество").setMinWidth(60);
		table.getColumn("Количество").setMaxWidth(100);
		table.getColumn("Количество").setResizable(false);
		table.getColumn("Цена").setResizable(false);
		table.setPreferredScrollableViewportSize(new Dimension(450,180));
        table.setFillsViewportHeight(true);
		JScrollPane tableScroll = new JScrollPane(table);
		tableScroll.setVisible(true);
		add(tableScroll);
	}

}