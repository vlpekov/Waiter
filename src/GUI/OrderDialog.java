package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Point;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import RestaurantObjects.Customer;
import RestaurantObjects.Menu;
import RestaurantObjects.MenuItem;
import RestaurantObjects.Table;

import javax.swing.JSeparator;
import javax.swing.JTextPane;
import java.awt.FlowLayout;

public class OrderDialog {

	JFrame frame;
	private JTable tableOrder;
	private JTable table;
	public static double currentBill;
	private static Customer customerObject;
	private static Table tableObject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDialog window = new OrderDialog();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrderDialog() {
		initialize();
	}

	public static void run() {

	}

	public static void setCurrentCustomer(Customer currentCustomer) {
		customerObject = currentCustomer;
		setCurrentTable(customerObject);
	}

	public static void setCurrentTable(Customer currentCustomere) {
		tableObject = currentCustomere.getCustomerTable();
	}

	public static Customer getCustomerObject() {
		return customerObject;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Индивидуална поръчка");
		frame.setBounds(100, 100, 481, 401);
		// frame.setDefaultCloseOperation(JFrame.ABORT);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 465, 93);
		frame.getContentPane().add(panel);

		String currentCustomer = "";
		JLabel lblNewLabel = new JLabel(currentCustomer);
		lblNewLabel.setBounds(40, 11, 348, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel);

		JComboBox<String> itemComboBox = new JComboBox<String>();
		itemComboBox.setBounds(10, 66, 290, 20);
		itemComboBox.setToolTipText("Изберете категория");

		int categoryNumber = getCategoriesNumber();
		ComboBoxModel[] models = new ComboBoxModel[categoryNumber];
		for (int i = 0; i < categoryNumber; i++) {
			models[i] = new DefaultComboBoxModel(getStringArray(getCategory(i)).toArray());
		}

		panel.add(itemComboBox);
		new Menu();
		JComboBox<String> categoryComboBox = new JComboBox(Menu.categoryList.toArray());
		categoryComboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				int i = categoryComboBox.getSelectedIndex();
				itemComboBox.setModel(models[i]);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				int i = categoryComboBox.getSelectedIndex();
				itemComboBox.setModel(models[i]);
			}
		});
		categoryComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int i = categoryComboBox.getSelectedIndex();
				itemComboBox.setModel(models[i]);
			}
		});
		categoryComboBox.setBounds(10, 37, 290, 20);
		categoryComboBox.setToolTipText("Изберете категория");
		panel.add(categoryComboBox);

		itemComboBox.setModel(models[4]);
		JButton addButton = new JButton("Добави");
		addButton.setBounds(310, 65, 145, 23);
		panel.add(addButton);

		panel.setLayout(null);

		JButton cancelButton = new JButton("Отмени");
		cancelButton.setBounds(350, 235, 105, 23);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		JButton button_2 = new JButton("Избери от меню");
		button_2.setBounds(310, 36, 145, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMenuTable();
			}

			private void showMenuTable() {
				MenuListOrder menuTable = new MenuListOrder();
				menuTable.runMenuTable();
			}
		});
		panel.add(button_2);

		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 93, 465, 269);
		frame.getContentPane().add(panelBottom);
		panelBottom.setLayout(null);
		panelBottom.add(cancelButton);

		OrderTable menuList = new OrderTable();
		menuList.setBounds(0, 0, 465, 224);
		panelBottom.add(menuList);
		FlowLayout flowLayout = (FlowLayout) menuList.getLayout();
		flowLayout.setAlignOnBaseline(true);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(menuList, popupMenu);

		JMenuItem menuItem = new JMenuItem("Премахни");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				menuList.deleteRow(selectedRow);
			}
		});
		popupMenu.add(menuItem);

		JButton orderButton = new JButton("Поръчай");
		orderButton.setBounds(235, 235, 105, 23);
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuList.sumItemPrices();
				// frame.dispose();
			}

		});
		panelBottom.add(orderButton);
	}

	// private void addItemsToCategoryComboBox(JComboBox categoryComboBox,
	// JComboBox itemComboBox) {
	// String category = categoryComboBox.getSelectedItem().toString();
	// for (String item : Menu.categoryList) {
	// if (item.equals(category))
	// itemComboBox.addItem(item);
	// }
	// @Override
	// public void itemStateChanged(ItemEvent e) {
	// if(e.getStateChange() == ItemEvent.SELECTED) {
	// String newCategory = categoryComboBox.getSelectedItem().toString();
	// for (String item : Menu.categoryList) {
	// if (item.equals(newCategory)) {
	// itemComboBox.addItem(item);
	// }
	// }
	// }
	// });
	//
	// }

	private String getCategory(int i) {
		new Menu();
		return Menu.categoryList.get(i);
	}

	private int getCategoriesNumber() {
		new Menu();
		int categoryNumber = 0;
		for (String category : Menu.categoryList) {
			categoryNumber++;
		}
		return categoryNumber;
	}

	private ArrayList<String> getStringArray(String category) {
		ArrayList<String> items = new ArrayList<String>();
		for (MenuItem item : Menu.menuList) {
			if (item.getCategory().equals(category)) {
				items.add(item.getName());
				System.out.println(item.getName());
			}

		}
		return items;

	}

	private void addItemsComboBox(JComboBox<String> categoryComboBox, JComboBox<String> itemComboBox) {
		itemComboBox.removeAll();
		String category = categoryComboBox.getSelectedItem().toString();
		System.out.println(category);

		for (MenuItem item : Menu.menuList) {
			if (item.getCategory().equals(category)) {
				itemComboBox.addItem(item.getName());
				System.out.println(item.getName());
			}
		}
	}

	private void addItemsToCategoryComboBox(JComboBox<String> itemsComboBox) {
		new Menu();
		for (String category : Menu.categoryList) {
			itemsComboBox.addItem(category);
		}

	}

	// private void setTableProperties(JTable tableOrder) {
	// tableOrder.setBounds(38, 120, 397, 191);
	// tableOrder.getColumn("Име").setMinWidth(400);
	// tableOrder.getColumn("Цена").setMinWidth(60);
	// tableOrder.getColumn("Цена").setMaxWidth(60);
	// tableOrder.getColumn("Количество").setMinWidth(100);
	// tableOrder.getColumn("Количество").setMaxWidth(100);
	// tableOrder.getColumn("Количество").setResizable(false);
	// tableOrder.getColumn("Цена").setResizable(false);
	// tableOrder.setPreferredScrollableViewportSize(new Dimension(770, 330));
	// tableOrder.setFillsViewportHeight(true);
	// JScrollPane tableScroll = new JScrollPane(tableOrder);
	// tableScroll.setVisible(true);
	// // table.add(tableScroll);
	// tableOrder.getTableHeader().setReorderingAllowed(false);
	//
	// }

	private void tableModel(JTable table, DefaultTableModel tableModel) {
		new Menu();
		for (int i = 0; i < Menu.getMenuList().size(); i++) {
			String name = Menu.getMenuList().get(i).getName();
			double price = Menu.getMenuList().get(i).getPrice();
			String quantity = Menu.getMenuList().get(i).getQuantity();
			String category = Menu.getMenuList().get(i).getCategory();
			Object[] data = { name, price, quantity, category };
			tableModel.addRow(data);
		}
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
		});
	}
}