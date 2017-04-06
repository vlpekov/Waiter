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
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Point;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import RestaurantObjects.Menu;

import javax.swing.JSeparator;
import javax.swing.JTextPane;
import java.awt.FlowLayout;

public class OrderDialog {

	JFrame frame;
	private JTable tableOrder;
	private JTable table;

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
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 401);
//		frame.setDefaultCloseOperation(JFrame.ABORT);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 465, 93);
		frame.getContentPane().add(panel);

		JButton orderButton = new JButton("Поръчай");
		orderButton.setBounds(235, 235, 105, 23);
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.setLayout(null);
		

		JLabel lblNewLabel = new JLabel("Поръчка на клиент 1 от маса 2");
		lblNewLabel.setBounds(40, 11, 348, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel);

		JComboBox categoryComboBox = new JComboBox();
		categoryComboBox.setBounds(10, 36, 290, 20);
		categoryComboBox.setToolTipText("Изберете категория");
		panel.add(categoryComboBox);

		JButton ordetButton = new JButton("Добави");
		ordetButton.setBounds(310, 65, 145, 23);
		panel.add(ordetButton);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 66, 290, 20);
		comboBox.setToolTipText("Изберете категория");
		panel.add(comboBox);

		JButton cancelButton = new JButton("Отмени");
		cancelButton.setBounds(350, 235, 105, 23);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		

		JButton button_2 = new JButton("Избери от меню");
		button_2.setBounds(310, 36, 145, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(button_2);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBounds(0, 93, 465, 269);
		frame.getContentPane().add(panelBottom);
		panelBottom.setLayout(null);
		panelBottom.add(cancelButton);
		panelBottom.add(orderButton);
		OrderTable menuList = new OrderTable();
		menuList.setBounds(0, 0, 465, 224);
		panelBottom.add(menuList);
		FlowLayout flowLayout = (FlowLayout) menuList.getLayout();
		flowLayout.setAlignOnBaseline(true);
//		menuList.setMaximumSize(new Dimension(500, 300));

//		table = new JTable();
//		DefaultTableModel tableModel = new DefaultTableModel(new String[] { "New column", "New column", "New column" }, 0);
//		table.setModel(tableModel);
//		table.setBounds(10, 102, 445, 191);
//		panel.add(new JScrollPane(table));
//		fillTable(table, tableModel);
//		panel.add(table);

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}

	private void setTableProperties(JTable tableOrder) {
		tableOrder.setBounds(38, 120, 397, 191);
		tableOrder.getColumn("Име").setMinWidth(400);
		tableOrder.getColumn("Цена").setMinWidth(60);
		tableOrder.getColumn("Цена").setMaxWidth(60);
		tableOrder.getColumn("Количество").setMinWidth(100);
		tableOrder.getColumn("Количество").setMaxWidth(100);
		tableOrder.getColumn("Количество").setResizable(false);
		tableOrder.getColumn("Цена").setResizable(false);
		tableOrder.setPreferredScrollableViewportSize(new Dimension(770, 330));
		tableOrder.setFillsViewportHeight(true);
		JScrollPane tableScroll = new JScrollPane(tableOrder);
		tableScroll.setVisible(true);
		// table.add(tableScroll);
		tableOrder.getTableHeader().setReorderingAllowed(false);

	}

	private void fillTable(JTable table, DefaultTableModel tableModel) {
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

	private void setPopupMenu(JTable tableOrder, DefaultTableModel tableModel) {
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tableOrder, popupMenu);

		JMenuItem menuRemove = new JMenuItem("Премахни");
		menuRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = tableOrder.getSelectedRow();
				tableModel.removeRow(selectedRow);
			}
		});
		popupMenu.add(menuRemove);

		tableOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent event) {
				// selects the row at which point the mouse is clicked
				Point point = event.getPoint();
				int currentRow = tableOrder.rowAtPoint(point);
				tableOrder.setRowSelectionInterval(currentRow, currentRow);
			}
		});
	}
}