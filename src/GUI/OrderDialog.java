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
import RestaurantObjects.Restaurant;
import RestaurantObjects.Table;

import javax.swing.JSeparator;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class OrderDialog {

	JFrame frame;
	private JTable tableOrder;
	private JTable table;
	public static double currentBill;
	private static Customer customerObject;
	private static Table tableObject;
	public JLabel orderObjectInfo;
	public JLabel currentBillLabel;
	private JPanel panel;

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
		System.out.println("Поръчка на клиент номер: " + currentCustomere.getCustomerNumber() + ", седяща на маса "
				+ currentCustomere.getTableNumber());
		currentCustomere.setCustomerTable(Restaurant.getTable(currentCustomere.getTableNumber()));
		tableObject = currentCustomere.getCustomerTable();
	}

	public static void setCurrentTable() {
		int tableNumber = customerObject.getTableNumber();
		Restaurant.getTable(tableNumber);
	}

	public static Table getTableObject() {
		return tableObject;
	}

	public static Customer getCustomerObject() {
		return customerObject;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLebelsText();
			}
		});
		frame.setTitle("Индивидуална поръчка");
		frame.setBounds(100, 100, 481, 401);
		// frame.setDefaultCloseOperation(JFrame.ABORT);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 465, 93);
		frame.getContentPane().add(panel);

		orderObjectInfo = new JLabel();
		orderObjectInfo.setBounds(10, 11, 445, 14);
		orderObjectInfo.setHorizontalAlignment(SwingConstants.CENTER);
		orderObjectInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(orderObjectInfo);

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
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = (String) itemComboBox.getSelectedItem();
				System.out.println(itemName);
				addItemToOrderList(itemName);
			}

			private void addItemToOrderList(String itemName) {
				MenuItem item = Menu.getMenuItem(itemName);
				String name = item.getName();
				double price = item.getPrice();
				String quantity = item.getQuantity();
				OrderTable.addNewRow(name, price, quantity);
			}
		});
		addButton.setBounds(310, 65, 145, 23);
		panel.add(addButton);

		panel.setLayout(null);

		JButton cancelButton = new JButton("Затвори");
		cancelButton.setBounds(350, 235, 105, 23);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.out.println("Поръчването от клиент " + customerObject.getCustomerNumber()
						+ " приключи с текуща сметка от: " + customerObject.getCustomerBill());
				System.out.printf("Обща сметка за маса %d: %.2f\n", tableObject.getTableNumber(),
						tableObject.getBill());
			}
		});

		JButton menuChoice = new JButton("Избери от меню");
		menuChoice.setBounds(310, 36, 145, 23);
		menuChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMenuTable();
			}

			private void showMenuTable() {
				MenuListOrder menuTable = new MenuListOrder();
				menuTable.runMenuTable();
			}
		});
		panel.add(menuChoice);

		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 93, 465, 269);
		frame.getContentPane().add(panelBottom);
		panelBottom.setLayout(null);
		panelBottom.add(cancelButton);

		currentBillLabel = new JLabel();
		currentBillLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		currentBillLabel.setHorizontalAlignment(SwingConstants.LEFT);
		currentBillLabel.setBounds(10, 211, 445, 23);
		panelBottom.add(currentBillLabel);

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
				menuList.saveToOrderList(customerObject);
				menuList.deleteAllRow();
				menuList.deleteAllRow();
				menuList.deleteAllRow();
				// frame.dispose();
			}

		});
		panelBottom.add(orderButton);
		
		JButton orderListButton = new JButton("Списък поръчки");
		orderListButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuListTable list = new MenuListTable();
			}
		});
		orderListButton.setBounds(10, 235, 195, 23);
		panelBottom.add(orderListButton);
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
			}
		}
		return items;

	}

	private void addItemsComboBox(JComboBox<String> categoryComboBox, JComboBox<String> itemComboBox) {
		itemComboBox.removeAll();
		String category = categoryComboBox.getSelectedItem().toString();
		for (MenuItem item : Menu.menuList) {
			if (item.getCategory().equals(category)) {
				itemComboBox.addItem(item.getName());
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

	private void setLebelsText() {
		orderObjectInfo.setText(
				"Поръчка на клиент " + customerObject.getCustomerNumber() + " от маса " + tableObject.getTableNumber());
		currentBillLabel.setText(
				"Текущи сметки - клиент: " + customerObject.getCustomerBill() + ", маса: " + tableObject.getBill());
	}
}