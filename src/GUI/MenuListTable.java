package GUI;

import RestaurantObjects.*;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuListTable extends JPanel {

	JTable tableMenu;

	public MenuListTable() {

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

		JScrollPane js = new JScrollPane(table);
		js.setVisible(true);
		add(js);
		
		new Menu();

		for (int i = 0; i < Menu.getMenuList().size(); i++){
			String name = Menu.getMenuList().get(i).getName();
		   double price = Menu.getMenuList().get(i).getPrice();
		   String quantity = Menu.getMenuList().get(i).getQuantity();
		   String category = Menu.getMenuList().get(i).getCategory();


		   Object[] data = {name, price, quantity, category};

		   tableModel.addRow(data);

		}
	}

	public static void main(String[] a) {

		JFrame tableFrame = new JFrame();
		MenuListTable menuTable = new MenuListTable();
		tableFrame.setTitle("Меню");
		tableFrame.setSize(800, 400);
		tableFrame.setVisible(true);
		tableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableFrame.add(menuTable);

	}

}