package GUI;

import RestaurantObjects.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuListTableEditable extends JPanel {

	JTable tableMenu;

	public MenuListTableEditable() {

		String[] header = { "Име", "Цена", "Количество", "Категория" };
//		String[][] data = new String[1000][];

		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		
		JTable table = new JTable(tableModel);
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
		
		new Menu();

		for (int i = 0; i < Menu.getMenuList().size(); i++){
			String name = Menu.getMenuList().get(i).getName();
		   double price = Menu.getMenuList().get(i).getPrice();
		   String quantity = Menu.getMenuList().get(i).getQuantity();
		   String category = Menu.getMenuList().get(i).getCategory();


		   Object[] data = {name, price, quantity, category};

		   tableModel.addRow(data);

		}
		table.getTableHeader().setReorderingAllowed(false);
//		table.setDefaultEditor(Object.class, null);
		
// pop-up menu
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			
			}
		});
		popupMenu.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		popupMenu.add(mntmPaste);
		
		JMenuItem mntmClear = new JMenuItem("Clear");
		popupMenu.add(mntmClear);

		JMenuItem menuInsert = new JMenuItem("Insert New Row");
		menuInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				tableModel.insertRow(selectedRow, new String[0]);
				tableModel.addRow(new String[0]);
				table.getSelectedRow();
			}
		});
		popupMenu.add(menuInsert);
		
		JMenuItem menuAdd = new JMenuItem("Add New Row");
		menuAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableModel.addRow(new String[0]);
			}
		});
		popupMenu.add(menuAdd);
		
		JMenuItem menuRemove = new JMenuItem("Remove Current Row");
		menuRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				tableModel.removeRow(selectedRow);
			}
		});
		popupMenu.add(menuRemove);


		
		
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		 	    public void mousePressed(MouseEvent event) {
		        // selects the row at which point the mouse is clicked
		        Point point = event.getPoint();
		        int currentRow = table.rowAtPoint(point);
		        table.setRowSelectionInterval(currentRow, currentRow);
		    }
		});
	}

	public static void main(String[] a) {

runMenuTableTest();

	}
	public void runMenuTable (){
		JFrame tableFrame = new JFrame();
		tableFrame.setBounds(200, 200, 800, 400);
		MenuListTableEditable menuTable = new MenuListTableEditable();
		tableFrame.setTitle("Меню");
//		tableFrame.setSize(800, 400);
		tableFrame.setVisible(true);
//		tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableFrame.add(menuTable);
	}
	
	public static void runMenuTableTest (){
		JFrame tableFrame = new JFrame();
		tableFrame.setBounds(200, 200, 800, 400);
		
		MenuListTableEditable menuTable = new MenuListTableEditable();
		tableFrame.setTitle("Меню");
//		tableFrame.setSize(800, 400);
		tableFrame.setVisible(true);
		tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableFrame.add(menuTable);
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