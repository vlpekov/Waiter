package GUI;

import RestaurantObjects.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerOrderList extends JPanel {
	
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
	
		JPopupMenu popupMenuTable = new JPopupMenu();
		JMenuItem mntmRemove = new JMenuItem("Премахни");

		mntmRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				double currentItemPrice = (double) table.getValueAt(selectedRow, 1);
				tableModel.removeRow(selectedRow);
				currentCustomer.orderList.remove(selectedRow);
				currentCustomer.refreshBill();
				currentCustomer.getCustomerTable().refreshTableBill();
			}
		});

		popupMenuTable.add(mntmRemove);
		addPopup(table, popupMenuTable);
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
}