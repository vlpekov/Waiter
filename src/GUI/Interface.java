package GUI;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.Color;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.SwingConstants;

import RestaurantObjects.Restaurant;
import RestaurantObjects.Table;

import java.awt.Label;
import java.awt.Cursor;

public class Interface {

	private static JFrame frame;
	private JFrame frameMenu;
	private Table currentTable;
	private JLabel currentReservedSign;
	private JLabel currentCustomer;
	private static ArrayList<JLabel> reservedSigns = new ArrayList<JLabel>();
	private static ArrayList<JLabel> tableInfoLabels = new ArrayList<JLabel>();
	private String topChair = "n";
	private String bottomChair = "s";
	private String leftChair = "w";
	private String rightChair = "e";
	private int maxCustomerImageNumber = 10;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		new Table();
	}

	public static void printHorizontalLine() {
		System.out.println("-----------------------");
	}

	public Table getCurrentTable() {
		return currentTable;
	}

	public void setCurrentTable(int tableNumber) {
		this.currentTable = Restaurant.getTable(tableNumber);
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		frameMenu = new JFrame();
		frameMenu.setBounds(300, 300, 650, 300);
		frameMenu.setVisible(false);

		// customers lists
		Map<Integer, JLabel> customersTable1 = new HashMap<Integer, JLabel>(15);
		Map<Integer, JLabel> customersTable2 = new HashMap<Integer, JLabel>(15);
		Map<Integer, JLabel> customersTable3 = new HashMap<Integer, JLabel>(15);
		Map<Integer, JLabel> customersTable4 = new HashMap<Integer, JLabel>(15);
		Map<Integer, JLabel> customersTable5 = new HashMap<Integer, JLabel>(15);
		Map<Integer, JLabel> customersTable6 = new HashMap<Integer, JLabel>(15);
		Map<Integer, JLabel> customersTable7 = new HashMap<Integer, JLabel>(15);
		Map<Integer, JLabel> customersTable8 = new HashMap<Integer, JLabel>(15);
		Map<Integer, JLabel> customersTable9 = new HashMap<Integer, JLabel>(15);
		Map<Table, Map<Integer, JLabel>> allTablesCustomers = new HashMap<Table, Map<Integer, JLabel>>(15);
		
		// menu bar
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmEditMenu = new JMenuItem("Edit Menu");
		mnEdit.add(mntmEditMenu);

		JMenuItem mntmEditTables = new JMenuItem("Edit Tables");
		mnEdit.add(mntmEditTables);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmShowMenu = new JMenuItem("Menu");
		mntmShowMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				showMenuTable();
			}
		});
		mnView.add(mntmShowMenu);
		
		// Customer pop-up menu
		JPopupMenu popupMenuCustomer = new JPopupMenu();
		JMenuItem mntmRemove = new JMenuItem("Remove");
		popupMenuCustomer.add(mntmRemove);
		mntmRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				currentCustomer.setVisible(false);
//				getTableInfoLabel(currentTable).setText(currentTable.getTabelInfo());
				removeCurrentCustomer(currentCustomer, currentTable, allTablesCustomers);
			}
		});
		JMenuItem mntmChangeIcon = new JMenuItem("Change Icon");
		popupMenuCustomer.add(mntmChangeIcon);
		mntmChangeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				customerChangeIcon();
			}
		});

		

		frame.getContentPane().setLayout(null);

		
		
		JLabel customerTable1Ch1 = new JLabel("");
		customerTable1Ch1.setBounds(75, 162, 61, 61);
		customerTable1Ch1.setIcon(new ImageIcon(Interface.class.getResource("/images/clients4.png")));
		customerSet(customerTable1Ch1, popupMenuCustomer, bottomChair, customersTable1, 1, 1);
		frame.getContentPane().add(customerTable1Ch1);

		JLabel customerTable1Ch2 = new JLabel("");
		customerTable1Ch2.setBounds(140, 162, 56, 61);
		customerTable1Ch2.setIcon(new ImageIcon(Interface.class.getResource("/images/clients6.png")));
		customerSet(customerTable1Ch2, popupMenuCustomer, bottomChair, customersTable1, 2, 1);
		frame.getContentPane().add(customerTable1Ch2);
		
		JLabel customerTable1Ch3 = new JLabel("");
		customerTable1Ch3.setBounds(193, 105, 56, 61);
		customerTable1Ch3.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente4.png")));
		customerSet(customerTable1Ch3, popupMenuCustomer, rightChair, customersTable1, 3, 1);
		frame.getContentPane().add(customerTable1Ch3);
		
		JLabel customerTable1Ch4 = new JLabel("");
		customerTable1Ch4.setBounds(140, 35, 56, 61);
		customerTable1Ch4.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn6.png")));
		customerSet(customerTable1Ch4, popupMenuCustomer, topChair, customersTable1, 4, 1);
		frame.getContentPane().add(customerTable1Ch4);
		
		JLabel customerTable1Ch5 = new JLabel("");
		customerTable1Ch5.setBounds(75, 35, 56, 61);
		customerTable1Ch5.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn10.png")));
		customerSet(customerTable1Ch5, popupMenuCustomer, topChair, customersTable1, 5, 1);
		frame.getContentPane().add(customerTable1Ch5);
		
		JLabel customerTable1Ch6 = new JLabel("");
		customerTable1Ch6.setBounds(20, 105, 61, 56);
		customerTable1Ch6.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw7.png")));
		customerSet(customerTable1Ch6, popupMenuCustomer, leftChair, customersTable1, 6, 1);
		frame.getContentPane().add(customerTable1Ch6);
		
		JLabel customerTable2Ch1 = new JLabel("");
		customerTable2Ch1.setBounds(356, 166, 61, 61);
		customerTable2Ch1.setIcon(new ImageIcon(Interface.class.getResource("/images/clients1.png")));
		customerSet(customerTable2Ch1, popupMenuCustomer, bottomChair, customersTable2, 1, 2);
		frame.getContentPane().add(customerTable2Ch1);
		
		JLabel customerTable2Ch2 = new JLabel("");
		customerTable2Ch2.setBounds(413, 105, 61, 61);
		customerTable2Ch2.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente4.png")));
		customerSet(customerTable2Ch2, popupMenuCustomer, rightChair, customersTable2, 2, 2);
		frame.getContentPane().add(customerTable2Ch2);
		
		JLabel customerTable2Ch3 = new JLabel("");
		customerTable2Ch3.setBounds(352, 44, 61, 61);
		customerTable2Ch3.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn8.png")));
		customerSet(customerTable2Ch3, popupMenuCustomer, topChair, customersTable2, 3, 2);
		frame.getContentPane().add(customerTable2Ch3);
		
		JLabel customerTable2Ch4 = new JLabel("");
		customerTable2Ch4.setBounds(283, 105, 61, 61);
		customerTable2Ch4.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw7.png")));
		customerSet(customerTable2Ch4, popupMenuCustomer, leftChair, customersTable2, 4, 2);
		frame.getContentPane().add(customerTable2Ch4);
		
		JLabel customerTable3Ch1 = new JLabel("");
		customerTable3Ch1.setBounds(580, 170, 61, 61);
		customerTable3Ch1.setIcon(new ImageIcon(Interface.class.getResource("/images/clients10.png")));
		customerSet(customerTable3Ch1, popupMenuCustomer, bottomChair, customersTable3, 1, 3);
		frame.getContentPane().add(customerTable3Ch1);
		
		JLabel customerTable3Ch2 = new JLabel("");
		customerTable3Ch2.setBounds(652, 105, 61, 61);
		customerTable3Ch2.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente4.png")));
		customerSet(customerTable3Ch2, popupMenuCustomer, rightChair, customersTable3, 2, 3);
		frame.getContentPane().add(customerTable3Ch2);
		
		JLabel customerTable3Ch3 = new JLabel("");
		customerTable3Ch3.setBounds(573, 41, 61, 61);
		customerTable3Ch3.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn10.png")));
		customerSet(customerTable3Ch3, popupMenuCustomer, topChair, customersTable3, 3, 3);
		frame.getContentPane().add(customerTable3Ch3);
		
		JLabel customerTable3Ch4 = new JLabel("");
		customerTable3Ch4.setBounds(502, 105, 61, 61);
		customerTable3Ch4.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw6.png")));
		customerSet(customerTable3Ch4, popupMenuCustomer, leftChair, customersTable3, 4, 3);
		frame.getContentPane().add(customerTable3Ch4);
		
		JLabel customerTable4Ch1 = new JLabel("");
		customerTable4Ch1.setBounds(795, 165, 61, 61);
		customerTable4Ch1.setIcon(new ImageIcon(Interface.class.getResource("/images/clients4.png")));
		customerSet(customerTable4Ch1, popupMenuCustomer, bottomChair, customersTable4, 1, 4);
		frame.getContentPane().add(customerTable4Ch1);
		
		JLabel customerTable4Ch2 = new JLabel("");
		customerTable4Ch2.setBounds(861, 162, 61, 61);
		customerTable4Ch2.setIcon(new ImageIcon(Interface.class.getResource("/images/clients10.png")));
		customerSet(customerTable4Ch2, popupMenuCustomer, bottomChair, customersTable4, 2, 4);
		frame.getContentPane().add(customerTable4Ch2);
		
		JLabel customerTable4Ch3 = new JLabel("");
		customerTable4Ch3.setBounds(913, 105, 61, 61);
		customerTable4Ch3.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente3.png")));
		customerSet(customerTable4Ch3, popupMenuCustomer, rightChair, customersTable4, 3, 4);
		frame.getContentPane().add(customerTable4Ch3);
		
		JLabel customerTable4Ch4 = new JLabel("");
		customerTable4Ch4.setBounds(865, 35, 61, 61);
		customerTable4Ch4.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn2.png")));
		customerSet(customerTable4Ch4, popupMenuCustomer, topChair, customersTable4, 4, 4);
		frame.getContentPane().add(customerTable4Ch4);
		
		JLabel customerTable4Ch5 = new JLabel("");
		customerTable4Ch5.setBounds(805, 35, 61, 61);
		customerTable4Ch5.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn6.png")));
		customerSet(customerTable4Ch5, popupMenuCustomer, topChair, customersTable4, 5, 4);
		frame.getContentPane().add(customerTable4Ch5);
		
		JLabel customerTable4Ch6 = new JLabel("");
		customerTable4Ch6.setBounds(753, 105, 61, 61);
		customerTable4Ch6.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw3.png")));
		customerSet(customerTable4Ch6, popupMenuCustomer, leftChair, customersTable4, 6, 4);
		frame.getContentPane().add(customerTable4Ch6);
		
		JLabel customerTable5Ch1 = new JLabel("");
		customerTable5Ch1.setIcon(new ImageIcon(Interface.class.getResource("/images/clients4.png")));
		customerTable5Ch1.setBounds(89, 533, 61, 61);
		customerSet(customerTable5Ch1, popupMenuCustomer, bottomChair, customersTable5, 1, 5);
		frame.getContentPane().add(customerTable5Ch1);
		
		JLabel customerTable5Ch2 = new JLabel("");
		customerTable5Ch2.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente7.png")));
		customerTable5Ch2.setBounds(160, 509, 61, 61);
		customerSet(customerTable5Ch2, popupMenuCustomer, rightChair, customersTable5, 2, 5);
		frame.getContentPane().add(customerTable5Ch2);
		
		JLabel customerTable5Ch3 = new JLabel("");
		customerTable5Ch3.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente6.png")));
		customerTable5Ch3.setBounds(159, 453, 61, 61);
		customerSet(customerTable5Ch3, popupMenuCustomer, rightChair, customersTable5, 3, 5);
		frame.getContentPane().add(customerTable5Ch3);
		
		JLabel customerTable5Ch4 = new JLabel("");
		customerTable5Ch4.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente3.png")));
		customerTable5Ch4.setBounds(163, 400, 61, 61);
		customerSet(customerTable5Ch4, popupMenuCustomer, rightChair, customersTable5, 4, 5);
		frame.getContentPane().add(customerTable5Ch4);
		
		JLabel customerTable5Ch5 = new JLabel("");
		customerTable5Ch5.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente9.png")));
		customerTable5Ch5.setBounds(164, 346, 61, 61);
		customerSet(customerTable5Ch5, popupMenuCustomer, rightChair, customersTable5, 5, 5);
		frame.getContentPane().add(customerTable5Ch5);
		
		JLabel customerTable5Ch6 = new JLabel("");
		customerTable5Ch6.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn9.png")));
		customerTable5Ch6.setBounds(95, 312, 61, 61);
		customerSet(customerTable5Ch6, popupMenuCustomer, topChair, customersTable5, 6, 5);
		frame.getContentPane().add(customerTable5Ch6);

		
		JLabel customerTable5Ch7 = new JLabel("");
		customerTable5Ch7.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw4.png")));
		customerTable5Ch7.setBounds(21, 347, 61, 61);
		customerSet(customerTable5Ch7, popupMenuCustomer, leftChair, customersTable5, 7, 5);
		frame.getContentPane().add(customerTable5Ch7);
		
		JLabel customerTable5Ch8 = new JLabel("");
		customerTable5Ch8.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw3.png")));
		customerTable5Ch8.setBounds(22, 398, 61, 61);
		customerSet(customerTable5Ch8, popupMenuCustomer, leftChair, customersTable5, 8, 5);
		frame.getContentPane().add(customerTable5Ch8);
		
		JLabel customerTable5Ch9 = new JLabel("");
		customerTable5Ch9.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw7.png")));
		customerTable5Ch9.setBounds(19, 452, 61, 61);
		customerSet(customerTable5Ch9, popupMenuCustomer, leftChair, customersTable5, 9, 5);
		frame.getContentPane().add(customerTable5Ch9);
		
		JLabel customerTable5Ch10 = new JLabel("");
		customerTable5Ch10.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw6.png")));
		customerTable5Ch10.setBounds(23, 501, 61, 61);
		customerSet(customerTable5Ch10, popupMenuCustomer, leftChair, customersTable5, 10, 5);
		frame.getContentPane().add(customerTable5Ch10);
		
		JLabel customerTable6Ch1 = new JLabel("");
		customerTable6Ch1.setIcon(new ImageIcon(Interface.class.getResource("/images/clients5.png")));
		customerTable6Ch1.setBounds(354, 537, 61, 61);
		customerSet(customerTable6Ch1, popupMenuCustomer, bottomChair, customersTable6, 1, 6);
		frame.getContentPane().add(customerTable6Ch1);
		
		JLabel customerTable6Ch2 = new JLabel("");
		customerTable6Ch2.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente6.png")));
		customerTable6Ch2.setBounds(426, 509, 61, 61);
		customerSet(customerTable6Ch2, popupMenuCustomer, rightChair, customersTable6, 2, 6);
		frame.getContentPane().add(customerTable6Ch2);
		
		JLabel customerTable6Ch3 = new JLabel("");
		customerTable6Ch3.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente7.png")));
		customerTable6Ch3.setBounds(428, 453, 61, 61);
		customerSet(customerTable6Ch3, popupMenuCustomer, rightChair, customersTable6, 3, 6);
		frame.getContentPane().add(customerTable6Ch3);
		
		JLabel customerTable6Ch4 = new JLabel("");
		customerTable6Ch4.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente10.png")));
		customerTable6Ch4.setBounds(427, 398, 61, 61);
		customerSet(customerTable6Ch4, popupMenuCustomer, rightChair, customersTable6, 4, 6);
		frame.getContentPane().add(customerTable6Ch4);
		
		JLabel customerTable6Ch5 = new JLabel("");
		customerTable6Ch5.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente1.png")));
		customerTable6Ch5.setBounds(426, 349, 61, 61);
		customerSet(customerTable6Ch5, popupMenuCustomer, rightChair, customersTable6, 5, 6);
		frame.getContentPane().add(customerTable6Ch5);
		
		JLabel customerTable6Ch6 = new JLabel("");
		customerTable6Ch6.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn3.png")));
		customerTable6Ch6.setBounds(357, 309, 61, 61);
		customerSet(customerTable6Ch6, popupMenuCustomer, topChair, customersTable6, 6, 6);
		frame.getContentPane().add(customerTable6Ch6);
		
		JLabel customerTable6Ch7 = new JLabel("");
		customerTable6Ch7.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw6.png")));
		customerTable6Ch7.setBounds(280, 346, 61, 61);
		customerSet(customerTable6Ch7, popupMenuCustomer, leftChair, customersTable6, 7, 6);
		frame.getContentPane().add(customerTable6Ch7);
		
		JLabel customerTable6Ch8 = new JLabel("");
		customerTable6Ch8.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw3.png")));
		customerTable6Ch8.setBounds(282, 402, 61, 61);
		customerSet(customerTable6Ch8, popupMenuCustomer, leftChair, customersTable6, 8, 6);
		frame.getContentPane().add(customerTable6Ch8);
		
		JLabel customerTable6Ch9 = new JLabel("");
		customerTable6Ch9.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw5.png")));
		customerTable6Ch9.setBounds(280, 453, 61, 61);
		customerSet(customerTable6Ch9, popupMenuCustomer, leftChair, customersTable6, 9, 6);
		frame.getContentPane().add(customerTable6Ch9);
		
		JLabel customerTable6Ch10 = new JLabel("");
		customerTable6Ch10.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw6.png")));
		customerTable6Ch10.setBounds(282, 507, 61, 61);
		customerSet(customerTable6Ch10, popupMenuCustomer, leftChair, customersTable6, 10, 6);
		frame.getContentPane().add(customerTable6Ch10);
		
		JLabel customerTable7Ch1 = new JLabel("");
		customerTable7Ch1.setIcon(new ImageIcon(Interface.class.getResource("/images/clients6.png")));
		customerTable7Ch1.setBounds(609, 535, 61, 61);
		customerSet(customerTable7Ch1, popupMenuCustomer, bottomChair, customersTable7, 1, 7);
		frame.getContentPane().add(customerTable7Ch1);
		
		JLabel customerTable7Ch2 = new JLabel("");
		customerTable7Ch2.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente6.png")));
		customerTable7Ch2.setBounds(678, 503, 61, 61);
		customerSet(customerTable7Ch2, popupMenuCustomer, rightChair, customersTable7, 2, 7);
		frame.getContentPane().add(customerTable7Ch2);
		
		JLabel customerTable7Ch3 = new JLabel("");
		customerTable7Ch3.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente4.png")));
		customerTable7Ch3.setBounds(680, 452, 61, 61);
		customerSet(customerTable7Ch3, popupMenuCustomer, rightChair, customersTable7, 3, 7);
		frame.getContentPane().add(customerTable7Ch3);
		
		JLabel customerTable7Ch4 = new JLabel("");
		customerTable7Ch4.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente10.png")));
		customerTable7Ch4.setBounds(680, 397, 61, 61);
		customerSet(customerTable7Ch4, popupMenuCustomer, rightChair, customersTable7, 4, 7);
		frame.getContentPane().add(customerTable7Ch4);
		
		JLabel customerTable7Ch5 = new JLabel("");
		customerTable7Ch5.setIcon(new ImageIcon(Interface.class.getResource("/images/cliente7.png")));
		customerTable7Ch5.setBounds(681, 346, 61, 61);
		customerSet(customerTable7Ch5, popupMenuCustomer, rightChair, customersTable7, 5, 7);
		frame.getContentPane().add(customerTable7Ch5);
		
		JLabel customerTable7Ch6 = new JLabel("");
		customerTable7Ch6.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn1.png")));
		customerTable7Ch6.setBounds(611, 307, 61, 61);
		customerSet(customerTable7Ch6, popupMenuCustomer, topChair, customersTable7, 6, 7);
		frame.getContentPane().add(customerTable7Ch6);
		
		JLabel customerTable7Ch7 = new JLabel("");
		customerTable7Ch7.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw2.png")));
		customerTable7Ch7.setBounds(540, 345, 61, 61);
		customerSet(customerTable7Ch7, popupMenuCustomer, leftChair, customersTable7, 7, 7);
		frame.getContentPane().add(customerTable7Ch7);
		
		JLabel customerTable7Ch8 = new JLabel("");
		customerTable7Ch8.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw8.png")));
		customerTable7Ch8.setBounds(541, 400, 61, 61);
		customerSet(customerTable7Ch8, popupMenuCustomer, leftChair, customersTable7, 8, 7);
		frame.getContentPane().add(customerTable7Ch8);
		
		JLabel customerTable7Ch9 = new JLabel("");
		customerTable7Ch9.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw5.png")));
		customerTable7Ch9.setBounds(539, 450, 61, 61);
		customerSet(customerTable7Ch9, popupMenuCustomer, leftChair, customersTable7, 9, 7);
		frame.getContentPane().add(customerTable7Ch9);
		
		JLabel customerTable7Ch10 = new JLabel("");
		customerTable7Ch10.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw9.png")));
		customerTable7Ch10.setBounds(538, 508, 61, 61);
		customerSet(customerTable7Ch10, popupMenuCustomer, leftChair, customersTable7, 10, 7);
		frame.getContentPane().add(customerTable7Ch10);
		
		JLabel customerTable8Ch1 = new JLabel("");
		customerTable8Ch1.setIcon(new ImageIcon(Interface.class.getResource("/images/clients8.png")));
		customerTable8Ch1.setBounds(898, 368, 61, 61);
		customerSet(customerTable8Ch1, popupMenuCustomer, bottomChair, customersTable8, 1, 8);
		frame.getContentPane().add(customerTable8Ch1);
		
		JLabel customerTable8Ch2 = new JLabel("");
		customerTable8Ch2.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn6.png")));
		customerTable8Ch2.setBounds(908, 251, 61, 61);
		customerSet(customerTable8Ch2, popupMenuCustomer, topChair, customersTable8, 2, 8);
		frame.getContentPane().add(customerTable8Ch2);
		
		JLabel customerTable8Ch3 = new JLabel("");
		customerTable8Ch3.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw4.png")));
		customerTable8Ch3.setBounds(827, 311, 61, 61);
		customerSet(customerTable8Ch3, popupMenuCustomer, leftChair, customersTable8, 3, 8);
		frame.getContentPane().add(customerTable8Ch3);
		
		JLabel customerTable9Ch1 = new JLabel("");
		customerTable9Ch1.setIcon(new ImageIcon(Interface.class.getResource("/images/clients1.png")));
		customerTable9Ch1.setBounds(901, 554, 61, 61);
		customerSet(customerTable9Ch1, popupMenuCustomer, bottomChair, customersTable9, 1, 9);
		frame.getContentPane().add(customerTable9Ch1);
		
		JLabel customerTable9Ch2 = new JLabel("");
		customerTable9Ch2.setIcon(new ImageIcon(Interface.class.getResource("/images/clientn10.png")));
		customerTable9Ch2.setBounds(906, 441, 61, 61);
		customerSet(customerTable9Ch2, popupMenuCustomer, topChair, customersTable9, 2, 9);
		frame.getContentPane().add(customerTable9Ch2);
		
		JLabel customerTable9Ch3 = new JLabel("");
		customerTable9Ch3.setIcon(new ImageIcon(Interface.class.getResource("/images/clientw3.png")));
		customerTable9Ch3.setBounds(826, 495, 61, 61);
		customerSet(customerTable9Ch3, popupMenuCustomer, leftChair, customersTable9, 3, 9);
		frame.getContentPane().add(customerTable9Ch3);


		// table info labees
		// JLabel table1Info = new
		// JLabel(Restaurant.getTable(2).getTabelInfo());
		// table1Info.setBounds(207, 175, 275, 14);
		// frame.getContentPane().add(table1Info);
		//
		// JLabel table5Info = new
		// JLabel(Restaurant.getTable(5).getTabelInfo());
		// table5Info.setForeground(Color.ORANGE);
		// table5Info.setBounds(77, 474, 275, 14);
		// frame.getContentPane().add(table5Info);
		setTableInfoLables();

		// Set "Reserved" signs
		// reservedTest = new JLabel("");
		// reservedTest.addMouseListener(new MouseAdapter() {
		// @Override
		// public void mouseClicked(MouseEvent e) {
		// if (e.getClickCount() >= 2) {
		// System.out.println("double clicked");
		// Restaurant.getTable(2).release();
		// reservedTest.setVisible(false);
		// getTableInfoLable(currentTable).setText(currentTable.getTabelInfo());
		// System.out.println("Clear table");
		// }
		// }
		// });
		// reservedTest.setHorizontalAlignment(SwingConstants.CENTER);
		// reservedTest.setIcon(new
		// ImageIcon(Interface.class.getResource("/images/reserved.png")));
		// reservedTest.setBounds(314, 115, 94, 38);
		// frame.getContentPane().add(reservedTest);
		// reservedTest.setVisible(false);

		// setReservedSignTable5(currentTable,
		// getTableInfoLable(Restaurant.getTable(5)));

		setReservedSigns();

		// tables menu
		JPopupMenu popupMenuTable = new JPopupMenu();
		JMenuItem mntmClearTable = new JMenuItem("Clear table");
		popupMenuTable.add(mntmClearTable);
		mntmClearTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				removeCustomers(currentTable, allTablesCustomers);
				currentTable.release();
				currentReservedSign.setVisible(false);
				getTableInfoLabel(currentTable).setText(currentTable.getTabelInfo());
				
				System.out.println("Clear table");
			}
		});

		JMenuItem mntmReservedTable = new JMenuItem("Reserved table");
		popupMenuTable.add(mntmReservedTable);
		mntmReservedTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				currentTable.bookTable();
				currentReservedSign.setVisible(true);
				getTableInfoLabel(currentTable).setText(currentTable.getTabelInfo());
				System.out.println("Reserved table");
			}
		});
		

		
		// set tables
		// table 1
		JLabel table1 = new JLabel("");
		table1.setBounds(72, 82, 127, 95);
		tableSet(table1, 1, popupMenuTable, allTablesCustomers, customersTable1);
		table1.setIcon(new ImageIcon(Interface.class.getResource("/images/table8a.png")));
		frame.getContentPane().add(table1);
		
		// table 2
		JLabel table2 = new JLabel("");
		table2.setBounds(335, 91, 91, 91);
		tableSet(table2, 2, popupMenuTable, allTablesCustomers, customersTable2);
		table2.setIcon(new ImageIcon(Interface.class.getResource("/images/table4a.png")));
		frame.getContentPane().add(table2);

		// table 3
		JLabel table3 = new JLabel("");
		table3.setBounds(558, 91, 91, 91);
		tableSet(table3, 3, popupMenuTable, allTablesCustomers, customersTable3);
		table3.setIcon(new ImageIcon(Interface.class.getResource("/images/table4b.png")));
		frame.getContentPane().add(table3);

		// table 4
		JLabel table4 = new JLabel("");
		table4.setBounds(795, 82, 127, 95);
		tableSet(table4, 4, popupMenuTable, allTablesCustomers, customersTable4);
		table4.setIcon(new ImageIcon(Interface.class.getResource("/images/table8b.png")));
		frame.getContentPane().add(table4);

		// table 5
		JLabel table5 = new JLabel("");
		table5.setBounds(72, 357, 101, 194);
		tableSet(table5, 5, popupMenuTable, allTablesCustomers, customersTable5);
		table5.setIcon(new ImageIcon(Interface.class.getResource("/images/table10a.png")));
		frame.getContentPane().add(table5);
		addPopup(table5, popupMenuTable);

		// table 6
		JLabel table6 = new JLabel("");
		table6.setBounds(336, 357, 101, 194);
		tableSet(table6, 6, popupMenuTable, allTablesCustomers, customersTable6);
		table6.setIcon(new ImageIcon(Interface.class.getResource("/images/table10b.png")));
		frame.getContentPane().add(table6);
		
		// table 7
		JLabel table7 = new JLabel("");
		table7.setBounds(589, 357, 101, 194);
		tableSet(table7, 7, popupMenuTable, allTablesCustomers, customersTable7);
		table7.setIcon(new ImageIcon(Interface.class.getResource("/images/table10c.png")));
		frame.getContentPane().add(table7);

		// table 8
		JLabel table8 = new JLabel("");
		table8.setBounds(880, 303, 94, 71);
		tableSet(table8, 8, popupMenuTable, allTablesCustomers, customersTable8);
		table8.setIcon(new ImageIcon(Interface.class.getResource("/images/table3b.png")));
		frame.getContentPane().add(table8);

		// table 9
		JLabel table9 = new JLabel("");
		table9.setBounds(880, 491, 94, 71);
		tableSet(table9, 9, popupMenuTable, allTablesCustomers, customersTable9);
		table9.setIcon(new ImageIcon(Interface.class.getResource("/images/table3b.png")));
		frame.getContentPane().add(table9);
		
		// set chairs
		// chairs table 1
		JLabel chair1T1 = new JLabel("");
		chair1T1.setBounds(82, 185, 41, 42);
		chair1T1.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		chairSet(chair1T1, customerTable1Ch1, 1, 1);
		frame.getContentPane().add(chair1T1);
		

		JLabel chair2T1 = new JLabel("");
		chair2T1.setBounds(142, 185, 41, 42);
		chairSet(chair2T1, customerTable1Ch2, 1, 2);
		chair2T1.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		frame.getContentPane().add(chair2T1);

		JLabel chair3T1 = new JLabel("");
		chair3T1.setBounds(209, 113, 42, 41);
		chairSet(chair3T1, customerTable1Ch3, 1, 3);
		chair3T1.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		frame.getContentPane().add(chair3T1);

		JLabel chair4T1 = new JLabel("");
		chair4T1.setBounds(142, 35, 41, 42);
		chairSet(chair4T1, customerTable1Ch4, 1, 4);
		chair4T1.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		frame.getContentPane().add(chair4T1);
		
		JLabel chair5T1 = new JLabel("");
		chair5T1.setBounds(82, 35, 41, 42);
		chairSet(chair5T1, customerTable1Ch5, 1, 5);
		chair5T1.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		frame.getContentPane().add(chair5T1);
		
		JLabel chair6T1 = new JLabel("");
		chair6T1.setBounds(20, 113, 42, 41);
		chairSet(chair6T1, customerTable1Ch6, 1, 6);
		chair6T1.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		frame.getContentPane().add(chair6T1);
		
		//chairs table 2
		JLabel chair1T2 = new JLabel("");
		chair1T2.setBounds(360, 187, 41, 42);
		chairSet(chair1T2, customerTable2Ch1, 2, 1);
		chair1T2.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		frame.getContentPane().add(chair1T2);

		JLabel chair2T2 = new JLabel("");
		chair2T2.setBounds(436, 115, 42, 41);
		chairSet(chair2T2, customerTable2Ch2, 2, 2);
		chair2T2.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		frame.getContentPane().add(chair2T2);

		JLabel chair3T2 = new JLabel("");
		chair3T2.setBounds(360, 45, 41, 42);
		chairSet(chair1T2, customerTable2Ch1, 2, 3);
		chair3T2.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		frame.getContentPane().add(chair3T2);
		
		JLabel chair4T2 = new JLabel("");
		chair4T2.setBounds(283, 113, 42, 41);
		chairSet(chair4T2, customerTable2Ch4, 2, 4);
		chair4T2.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		frame.getContentPane().add(chair4T2);
	
		//chairs table 3
		JLabel chair1T3 = new JLabel("");
		chair1T3.setBounds(583, 187, 41, 42);
		chairSet(chair1T3, customerTable3Ch1, 3, 1);
		chair1T3.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		frame.getContentPane().add(chair1T3);

		JLabel chair2T3 = new JLabel("");
		chair2T3.setBounds(659, 115, 42, 41);
		chairSet(chair2T3, customerTable3Ch2, 3, 2);
		chair2T3.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		frame.getContentPane().add(chair2T3);
		
		JLabel chair3T3 = new JLabel("");
		chair3T3.setBounds(583, 45, 41, 42);
		chairSet(chair3T3, customerTable3Ch3, 3, 3);
		chair3T3.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		frame.getContentPane().add(chair3T3);
		
		JLabel chair4T3 = new JLabel("");
		chair4T3.setBounds(506, 115, 42, 41);
		chairSet(chair4T3, customerTable3Ch4, 3, 4);
		chair4T3.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		frame.getContentPane().add(chair4T3);
		

		//chairs table 4
		JLabel chair1T4 = new JLabel("");
		chair1T4.setBounds(805, 185, 41, 42);
		chairSet(chair1T4, customerTable4Ch1, 4, 1);
		chair1T4.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		frame.getContentPane().add(chair1T4);

		JLabel chair2T4 = new JLabel("");
		chair2T4.setBounds(870, 185, 41, 42);
		chairSet(chair2T4, customerTable4Ch2, 4, 2);
		chair2T4.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		frame.getContentPane().add(chair2T4);
		
		JLabel chair3T4 = new JLabel("");
		chair3T4.setBounds(932, 113, 42, 41);
		chairSet(chair3T4, customerTable4Ch3, 4, 3);
		chair3T4.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		frame.getContentPane().add(chair3T4);
		
		JLabel chair4T4 = new JLabel("");
		chair4T4.setBounds(870, 35, 41, 42);
		chairSet(chair4T4, customerTable4Ch4, 4, 4);
		chair4T4.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		frame.getContentPane().add(chair4T4);
		
		JLabel chair5T4 = new JLabel("");
		chair5T4.setBounds(810, 35, 41, 42);
		chairSet(chair5T4, customerTable4Ch5, 4, 5);
		chair5T4.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		frame.getContentPane().add(chair5T4);

		JLabel chair6T4 = new JLabel("");
		chair6T4.setBounds(743, 115, 42, 41);
		chairSet(chair6T4, customerTable4Ch6, 4, 6);
		chair6T4.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		frame.getContentPane().add(chair6T4);

		//table 5
		JLabel chair1T5 = new JLabel("");
		chair1T5.setBounds(101, 558, 41, 42);
		chairSet(chair1T5, customerTable5Ch1, 5, 1);
		chair1T5.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		frame.getContentPane().add(chair1T5);

		JLabel chair2T5 = new JLabel("");
		chair2T5.setBounds(182, 513, 42, 41);
		chairSet(chair2T5, customerTable5Ch2, 5, 2);
		chair2T5.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		frame.getContentPane().add(chair2T5);

		JLabel chair3T5 = new JLabel("");
		chair3T5.setBounds(182, 461, 42, 41);
		chairSet(chair3T5, customerTable5Ch3, 5, 3);
		chair3T5.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		frame.getContentPane().add(chair3T5);

		JLabel chair4T5 = new JLabel("");
		chair4T5.setBounds(182, 409, 42, 41);
		chairSet(chair4T5, customerTable5Ch4, 5, 4);
		chair4T5.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		frame.getContentPane().add(chair4T5);

		JLabel chair5T5 = new JLabel("");
		chair5T5.setBounds(182, 357, 42, 41);
		chairSet(chair5T5, customerTable5Ch5, 5, 5);
		chair5T5.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		frame.getContentPane().add(chair5T5);
		
		JLabel chair6T5 = new JLabel("");
		chair6T5.setBounds(101, 309, 41, 42);
		chairSet(chair6T5, customerTable5Ch6, 5, 6);
		chair6T5.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		frame.getContentPane().add(chair6T5);

		JLabel chair7T5 = new JLabel("");
		chair7T5.setBounds(20, 357, 42, 41);
		chairSet(chair7T5, customerTable5Ch7, 5, 7);
		chair7T5.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		frame.getContentPane().add(chair7T5);

		JLabel chair8T5 = new JLabel("");
		chair8T5.setBounds(20, 409, 42, 41);
		chairSet(chair8T5, customerTable5Ch8, 5, 8);
		chair8T5.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		frame.getContentPane().add(chair8T5);
		
		JLabel chair9T5 = new JLabel("");
		chair9T5.setBounds(20, 461, 42, 41);
		chairSet(chair9T5, customerTable5Ch9, 5, 9);
		chair9T5.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		frame.getContentPane().add(chair9T5);
		
		JLabel chair10T5 = new JLabel("");
		chair10T5.setBounds(20, 513, 42, 41);
		chairSet(chair10T5, customerTable5Ch10, 5, 10);
		chair10T5.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		frame.getContentPane().add(chair10T5);

	//chairs table 6	
		JLabel chair1T6 = new JLabel("");
		chair1T6.setBounds(364, 558, 41, 42);
		chair1T6.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		chairSet(chair1T6, customerTable6Ch1, 6, 1);
		frame.getContentPane().add(chair1T6);

		JLabel chair2T6 = new JLabel("");
		chair2T6.setBounds(445, 513, 42, 41);
		chair2T6.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		chairSet(chair2T6, customerTable6Ch2, 6, 2);
		frame.getContentPane().add(chair2T6);

		JLabel chair3T6 = new JLabel("");
		chair3T6.setBounds(445, 461, 42, 41);
		chair3T6.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		chairSet(chair3T6, customerTable6Ch3, 6, 3);
		frame.getContentPane().add(chair3T6);

		JLabel chair4T6 = new JLabel("");
		chair4T6.setBounds(445, 409, 42, 41);
		chair4T6.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		chairSet(chair4T6, customerTable6Ch4, 6, 4);
		frame.getContentPane().add(chair4T6);

		JLabel chair5T6 = new JLabel("");
		chair5T6.setBounds(445, 357, 42, 41);
		chair5T6.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		chairSet(chair5T6, customerTable6Ch5, 6, 5);
		frame.getContentPane().add(chair5T6);

		JLabel chair6T6 = new JLabel("");
		chair6T6.setBounds(364, 309, 41, 42);
		chair6T6.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		chairSet(chair6T6, customerTable6Ch6, 6, 6);
		frame.getContentPane().add(chair6T6);

		JLabel chair7T6 = new JLabel("");
		chair7T6.setBounds(283, 357, 42, 41);
		chair7T6.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		chairSet(chair7T6, customerTable6Ch7, 6, 7);
		frame.getContentPane().add(chair7T6);

		JLabel chair8T6 = new JLabel("");
		chair8T6.setBounds(283, 409, 42, 41);
		chair8T6.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		chairSet(chair8T6, customerTable6Ch8, 6, 8);
		frame.getContentPane().add(chair8T6);

		JLabel chair9T6 = new JLabel("");
		chair9T6.setBounds(283, 461, 42, 41);
		chair9T6.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		chairSet(chair9T6, customerTable6Ch9, 6, 9);
		frame.getContentPane().add(chair9T6);

		JLabel chair10T6 = new JLabel("");
		chair10T6.setBounds(283, 513, 42, 41);
		chair10T6.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		chairSet(chair10T6, customerTable6Ch10, 6, 10);
		frame.getContentPane().add(chair10T6);

		// Chairs table 7
		JLabel chair1T7 = new JLabel("");
		chair1T7.setBounds(619, 558, 41, 42);
		chair1T7.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		chairSet(chair1T7, customerTable7Ch1, 7, 1);
		frame.getContentPane().add(chair1T7);

		JLabel chair2T7 = new JLabel("");
		chair2T7.setBounds(700, 513, 42, 41);
		chair2T7.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		chairSet(chair2T7, customerTable7Ch2, 7, 2);
		frame.getContentPane().add(chair2T7);

		JLabel chair3T7 = new JLabel("");
		chair3T7.setBounds(700, 461, 42, 41);
		chair3T7.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		chairSet(chair3T7, customerTable7Ch3, 7, 3);
		frame.getContentPane().add(chair3T7);

		JLabel chair4T7 = new JLabel("");
		chair4T7.setBounds(700, 409, 42, 41);
		chair4T7.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		chairSet(chair4T7, customerTable7Ch4, 7, 4);
		frame.getContentPane().add(chair4T7);

		JLabel chair5T7 = new JLabel("");
		chair5T7.setBounds(700, 357, 42, 41);
		chair5T7.setIcon(new ImageIcon(Interface.class.getResource("/images/chaire.png")));
		chairSet(chair5T7, customerTable7Ch5, 7, 5);
		frame.getContentPane().add(chair5T7);

		JLabel chair6T7 = new JLabel("");
		chair6T7.setBounds(619, 309, 41, 42);
		chair6T7.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		chairSet(chair6T7, customerTable7Ch6, 7, 6);
		frame.getContentPane().add(chair6T7);

		JLabel chair7T7 = new JLabel("");
		chair7T7.setBounds(538, 357, 42, 41);
		chair7T7.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		chairSet(chair7T7, customerTable7Ch7, 7, 7);
		frame.getContentPane().add(chair7T7);

		JLabel chair8T7 = new JLabel("");
		chair8T7.setBounds(538, 409, 42, 41);
		chair8T7.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		chairSet(chair8T7, customerTable7Ch8, 7, 8);
		frame.getContentPane().add(chair8T7);

		JLabel chair9T7 = new JLabel("");
		chair9T7.setBounds(538, 461, 42, 41);
		chair9T7.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		chairSet(chair9T7, customerTable7Ch9, 7, 9);
		frame.getContentPane().add(chair9T7);

		JLabel chair10T7 = new JLabel("");
		chair10T7.setBounds(538, 513, 42, 41);
		chair10T7.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		chairSet(chair10T7, customerTable7Ch10, 7, 10);
		frame.getContentPane().add(chair10T7);

		//chairs table 8
		JLabel chair1T8 = new JLabel("");
		chair1T8.setBounds(912, 385, 41, 42);
		chair1T8.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		chairSet(chair1T8, customerTable8Ch1, 8, 1);
		frame.getContentPane().add(chair1T8);

		JLabel chair2T8 = new JLabel("");
		chair2T8.setBounds(912, 250, 41, 42);
		chairSet(chair2T8, customerTable8Ch2, 8, 2);
		chair2T8.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		frame.getContentPane().add(chair2T8);
		
		JLabel chair3T8 = new JLabel("");
		chair3T8.setBounds(828, 320, 42, 41);
		chairSet(chair3T8, customerTable8Ch3, 8, 3);
		chair3T8.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		frame.getContentPane().add(chair3T8);

		// chairs table 9
		JLabel chair1T9 = new JLabel("");
		chair1T9.setBounds(912, 573, 41, 42);
		chairSet(chair1T9, customerTable9Ch1, 9, 1);
		chair1T9.setIcon(new ImageIcon(Interface.class.getResource("/images/chairs.png")));
		frame.getContentPane().add(chair1T9);
		
		JLabel chair2T9 = new JLabel("");
		chair2T9.setBounds(912, 438, 41, 42);
		chairSet(chair2T9, customerTable9Ch2, 9, 2);
		chair2T9.setIcon(new ImageIcon(Interface.class.getResource("/images/chairn.png")));
		frame.getContentPane().add(chair2T9);

		JLabel chair3T9 = new JLabel("");
		chair3T9.setBounds(828, 506, 42, 41);
		chairSet(chair3T9, customerTable9Ch3, 9, 3);
		chair3T9.setIcon(new ImageIcon(Interface.class.getResource("/images/chairw.png")));
		frame.getContentPane().add(chair3T9);

		
		
		Label menuText = new Label("MENU");
		menuText.setBounds(100, 200, 114, 57);
		frameMenu.getContentPane().add(menuText);

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

	private int randomNumber(int min, int max) {
		Random randomNumber = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = randomNumber.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	// private void setReservedSignTable5(Table currentTable, JLabel table5Info)
	// {
	// JLabel reservedTable5 = new JLabel("");
	// reservedTable5.addMouseListener(new MouseAdapter() {
	// @Override
	// public void mouseClicked(MouseEvent e) {
	// if (e.getClickCount() >= 2) {
	// System.out.println("double clicked");
	// currentTable.release();
	// reservedTable5.setVisible(false);
	// table5Info.setText(currentTable.getTabelInfo());
	// System.out.println("Clear table");
	// }
	// }
	//
	// @Override
	// public void mouseEntered(MouseEvent e) {
	// setCurrentTable(5);
	// }
	// });
	// reservedTable5.setHorizontalAlignment(SwingConstants.CENTER);
	// reservedTable5.setIcon(new
	// ImageIcon(Interface.class.getResource("/images/reserved.png")));
	// reservedTable5.setBounds(77, 405, 94, 38);
	// frame.getContentPane().add(reservedTable5);
	// reservedTable5.setVisible(false);
	// System.out.println(reservedTable5);
	// }

	private void setReservedSigns() {
		JLabel reservedTable1 = new JLabel("");
		reservedTable1.setBounds(105, 115, 58, 29);
		reservedSignSettings(reservedTable1, getTableInfoLable(1), 1);
		JLabel reservedTable2 = new JLabel("");
		reservedSignSettings(reservedTable2, getTableInfoLable(2), 2);
		reservedTable2.setBounds(351, 115, 58, 29);
		JLabel reservedTable3 = new JLabel("");
		reservedSignSettings(reservedTable3, getTableInfoLable(3), 3);
		reservedTable3.setBounds(575, 115, 58, 29);
		JLabel reservedTable4 = new JLabel("");
		reservedSignSettings(reservedTable4, getTableInfoLable(4), 4);
		reservedTable4.setBounds(830, 120, 58, 29);
		JLabel reservedTable5 = new JLabel("");
		reservedSignSettings(reservedTable5, getTableInfoLable(5), 5);
		reservedTable5.setBounds(95, 440, 58, 29);
		JLabel reservedTable6 = new JLabel("");
		reservedSignSettings(reservedTable6, getTableInfoLable(6), 6);
		reservedTable6.setBounds(355, 440, 58, 29);
		JLabel reservedTable7 = new JLabel("");
		reservedSignSettings(reservedTable7, getTableInfoLable(7), 7);
		reservedTable7.setBounds(610, 440, 58, 29);
		JLabel reservedTable8 = new JLabel("");
		reservedSignSettings(reservedTable8, getTableInfoLable(8), 8);
		reservedTable8.setBounds(895, 325, 58, 29);
		JLabel reservedTable9 = new JLabel("");
		reservedSignSettings(reservedTable9, getTableInfoLable(9), 9);
		reservedTable9.setBounds(895, 510, 58, 29);
		reservedSigns.add(reservedTable1);
		reservedSigns.add(reservedTable2);
		reservedSigns.add(reservedTable3);
		reservedSigns.add(reservedTable4);
		reservedSigns.add(reservedTable5);
		reservedSigns.add(reservedTable6);
		reservedSigns.add(reservedTable7);
		reservedSigns.add(reservedTable8);
		reservedSigns.add(reservedTable9);
	}

	public void reservedSignSettings(JLabel reservedSign, JLabel tabelInfoLabel, int tableNumber) {
		
	
		reservedSignMouseEvent(reservedSign, tabelInfoLabel, tableNumber);
		reservedSign.setHorizontalAlignment(SwingConstants.CENTER);
		reservedSign.setIcon(new ImageIcon(Interface.class.getResource("/images/reserved.png")));
		frame.getContentPane().add(reservedSign);
		reservedSign.setVisible(false);
	}

	public void reservedSignMouseEvent(JLabel reservedSign, JLabel tabelInfoLabel, int tableNumber) {
		reservedSign.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCurrentTable(tableNumber);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					currentTable.release();
					reservedSign.setVisible(false);
					tabelInfoLabel.setText(currentTable.getTabelInfo());
				}
			}
		});
	}

	public static JLabel getReservedSign(Table currentTable) {
		return reservedSigns.get(currentTable.getTableNumber() - 1);
	}

	public static void setTableInfoLables() {
		JLabel table1Info = new JLabel(Restaurant.getTable(1).getTabelInfo());
		JLabel table2Info = new JLabel(Restaurant.getTable(2).getTabelInfo());
		JLabel table3Info = new JLabel(Restaurant.getTable(3).getTabelInfo());
		JLabel table4Info = new JLabel(Restaurant.getTable(4).getTabelInfo());
		JLabel table5Info = new JLabel(Restaurant.getTable(5).getTabelInfo());
		JLabel table6Info = new JLabel(Restaurant.getTable(6).getTabelInfo());
		JLabel table7Info = new JLabel(Restaurant.getTable(7).getTabelInfo());
		JLabel table8Info = new JLabel(Restaurant.getTable(8).getTabelInfo());
		JLabel table9Info = new JLabel(Restaurant.getTable(9).getTabelInfo());
		table1Info.setBounds(80, 150, 275, 14);
		table2Info.setBounds(350, 150, 275, 14);
		table3Info.setBounds(570, 150, 275, 14);
		table4Info.setBounds(810, 150, 275, 14);
		table5Info.setBounds(80, 525, 275, 14);
		table6Info.setBounds(345, 525, 275, 14);
		table7Info.setBounds(600, 525, 275, 14);
		table8Info.setBounds(900, 355, 275, 14);
		table9Info.setBounds(900, 540, 275, 14);
		tableInfoLabels.add(table1Info);
		tableInfoLabels.add(table2Info);
		tableInfoLabels.add(table3Info);
		tableInfoLabels.add(table4Info);
		tableInfoLabels.add(table5Info);
		tableInfoLabels.add(table6Info);
		tableInfoLabels.add(table7Info);
		tableInfoLabels.add(table8Info);
		tableInfoLabels.add(table9Info);
		for (JLabel label : tableInfoLabels) {
			label.setForeground(Color.ORANGE);
			frame.getContentPane().add(label);
		}
		// frame.getContentPane().add(table1Info);
		// frame.getContentPane().add(table2Info);
		// frame.getContentPane().add(table3Info);
		// frame.getContentPane().add(table4Info);
		// frame.getContentPane().add(table5Info);
		// frame.getContentPane().add(table6Info);
		// frame.getContentPane().add(table7Info);
		// frame.getContentPane().add(table8Info);
		// frame.getContentPane().add(table9Info);
	}

	public static JLabel getTableInfoLabel(Table currentTable) {
		return tableInfoLabels.get(currentTable.getTableNumber() - 1);
	}

	public static JLabel getTableInfoLable(int tableNumber) {
		return tableInfoLabels.get(tableNumber - 1);
	}

	public void doubleMouseClickOnChair(JLabel customer, MouseEvent e, int tableNumber) {
		selectTable(e, tableNumber);
		if (e.getClickCount() >= 2) {
			customer.setVisible(true);
			currentTable.occupyTable();
			getReservedSign(currentTable).setVisible(false);
			getTableInfoLabel(currentTable).setText(currentTable.getTabelInfo());
			currentTable.getCustomer(customer).activateCustomer();
		}
	}

	public void doubleMouseClickOnCustomer(MouseEvent e) {
		if (e.getClickCount() >= 2) {
			System.out.println("double clicked");
			frameMenu.setVisible(true);
		}
	}



	public void chairSet(JLabel chair, JLabel customer, int tableNumber, String cahirPosition) {
		chair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doubleMouseClickOnChair(customer, e, tableNumber);
			}
		});
		chair.setIcon(new ImageIcon(Interface.class.getResource("/images/chair" + cahirPosition + ".png")));
	}

	public void chairSet(JLabel chair, JLabel customer, int tableNumber, int chairNumber) {
		chair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doubleMouseClickOnChair(customer, e, tableNumber);
			}
		});
		Restaurant.getTable(tableNumber).getChair(chairNumber).setChairLabel(chair);
	}

	public void chairMouseEvent(JLabel chair, JLabel customer, int tableNumber) {
		chair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doubleMouseClickOnChair(customer, e, tableNumber);
							}
		});
	}

	public void tableSet(JLabel table, int tableNumber, JPopupMenu popupMenuTable, Map<Table, Map<Integer, JLabel>> allTablesCustomers, Map<Integer, JLabel> customersTable) {
		tableMouseEvent(table, tableNumber);
		Table tableObject = Restaurant.getTable(tableNumber);
		table.setToolTipText("Table " + tableNumber);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setVerticalTextPosition(SwingConstants.CENTER);
		table.setHorizontalTextPosition(SwingConstants.CENTER);
		table.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(table);
		addPopup(table, popupMenuTable);
		allTablesCustomers.put(tableObject, customersTable);
	}
	public void selectTable(MouseEvent e, int tableNumber) {
		setCurrentTable(tableNumber);
		currentReservedSign = getReservedSign(getCurrentTable());
		System.out.println("Current table " + tableNumber);
	}

	public void tableMouseEvent(JLabel table, int tableNumber) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectTable(e, tableNumber);
			}
		});
	}
	
	public void customerSet (JLabel customer, JPopupMenu popupMenuCustomer, String chairPosition, Map<Integer, JLabel> customersTable, int customerNumber, int tableNumber) {
		customer.setVisible(false);
		customer.setIcon(new ImageIcon(Interface.class.getResource("/images/client" + chairPosition + randomNumber(1, 10) + ".png")));
		customer.repaint();
		addPopup(customer, popupMenuCustomer);
		customer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doubleMouseClickOnCustomer(e);
				}
			@Override
			public void mouseEntered(MouseEvent e) {
				currentCustomer = customer;
			}
		});
		customersTable.put(customerNumber, customer);
		Restaurant.getTable(tableNumber).getCustomer(customerNumber).setCustomerLabel(customer);
		
		
	}
	
	public void customerChangeIcon() {
	currentCustomer.repaint();
	currentCustomer.setIcon(new ImageIcon(Interface.class.getResource("/images/client" + getCustomerOrientation(currentCustomer) + getAnotherImageNumber(currentCustomer) + ".png")));
	}
	
	public void removeCustomers(Table currentTable, Map<Table, Map<Integer, JLabel>> allTablesCustomers) {
		Map<Integer, JLabel> curentTableCustomers = allTablesCustomers.get(currentTable);
		for (JLabel customer : curentTableCustomers.values()) {
			customer.setVisible(false);
			currentTable.getCustomer(customer).deactivateCustomer();
		}
	}
	
	public void removeCurrentCustomer(JLabel currentCustomer, Table currentTable, Map<Table, Map<Integer, JLabel>> allTablesCustomers) {
		Map<Integer, JLabel> curentTableCustomers = allTablesCustomers.get(currentTable);
		currentCustomer.setVisible(false);
		currentTable.getCustomer(currentCustomer).deactivateCustomer();
		boolean isTableEmpty = true;
		for (JLabel customer : curentTableCustomers.values()) {
			if (currentTable.getCustomer(customer).isActive()) {
				isTableEmpty = false;
				break;
			}
		}
		if (isTableEmpty) {
			currentTable.release();
			getTableInfoLabel(currentTable).setText(currentTable.getTabelInfo());
		}
	}
	
	public String getCustomerOrientation (JLabel currentCustomer) {
		String customerInfo = currentCustomer.toString();
		int charPosition = customerInfo.indexOf("images/client");
		String orientation = "" + customerInfo.charAt(charPosition+13);
		return ""+orientation;
	}
	
	public int getImageNumber (JLabel currentCustomer) {
		String customerInfo = currentCustomer.toString();
		int numberStartPosition = customerInfo.indexOf("images/client")+14;
		int numberEndPosition = customerInfo.indexOf(".png");
		String imageNumber = customerInfo.substring(numberStartPosition, numberEndPosition);
		System.out.println("image number " + imageNumber);
				return Integer.parseInt(imageNumber);
	}
	
	public int getAnotherImageNumber (JLabel currentCustomer) {
		int currentImageNumber = getImageNumber(currentCustomer);
		int newImageNumber; 
		do {
			newImageNumber = randomNumber(1, maxCustomerImageNumber);
		}
		while (currentImageNumber==randomNumber(1, 10));
		System.out.println("newImageNumber " + newImageNumber);
		return newImageNumber;
	}
	
	public void showMenuTable (){
		MenuListTable menuTable = new MenuListTable();
		menuTable.runMenuTable();
	}
}
