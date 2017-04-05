package RestaurantObjects;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import GUI.*;

public class Menu {
	public static ArrayList<MenuItem> menuList;

	public Menu() {
		this.menuList = new ArrayList<MenuItem>();
//		menuList.add(new MenuItem("Минерална вода", 1.00, "500ml", "безалкохолни напитки"));
//		menuList.add(new MenuItem("Айран", 1.20, "300ml", "безалкохолни напитки"));
//		menuList.add(new MenuItem("Минерална вода", 2.20, "1500ml", "безалкохолни напитки"));
//		menuList.add(new MenuItem("Чай", 1.60, "200ml", "безалкохолни напитки"));
//		menuList.add(new MenuItem("Мед", 0.30, "10g", "други"));
//		menuList.add(new MenuItem("Сос - чили", 0.80, "50ml", "други"));
//		menuList.add(new MenuItem("Сос - майонеза", 0.80, "50ml", "други"));
//		menuList.add(new MenuItem("Сос - барбекю", 0.80, "50ml", "други"));
//		menuList.add(new MenuItem("Сос - чеснов", 0.80, "50ml", "други"));
//		menuList.add(new MenuItem("Ракия - домашна", 1.80, "50ml", "алкохолни напитки"));
//		menuList.add(new MenuItem("Вино - домашно", 3.20, "500ml", "алкохолни напитки"));
//		menuList.add(new MenuItem("Водка - българска", 1.80, "50ml", "алкохолни напитки"));
//		menuList.add(new MenuItem("Водка - внос", 2.50, "50ml", "алкохолни напитки"));
//		menuList.add(new MenuItem("Бира \"Zagorka\"", 1.80, "500ml", "алкохолни напитки"));
//		menuList.add(new MenuItem("Бира \"Kamenitza\"", 1.80, "500ml", "алкохолни напитки"));
//		menuList.add(new MenuItem("Бира безалк. \"Heineken\"", 2.20, "330ml", "алкохолни напитки"));
//		menuList.add(new MenuItem("Шопска салата", 2.90, "300g", "салати"));
//		menuList.add(new MenuItem("Овчарска салата", 3.40, "350g", "салати"));
//		menuList.add(new MenuItem("Зелена салата", 2.40, "200g", "салати"));
//		menuList.add(new MenuItem("Салата \"Снежанка\"", 2.80, "150g", "салати"));
//		menuList.add(new MenuItem("Панирано сирене", 3.30, "250g", "предястия"));
//		menuList.add(new MenuItem("Паниран кашкавал", 4.00, "250g", "предястия"));
//		menuList.add(new MenuItem("Пържени картофи", 2.50, "300g", "предястия"));
//		menuList.add(new MenuItem("Пържени картофи със сирене", 3.00, "350g", "предястия"));
//		menuList.add(new MenuItem("Зеленчукова супа", 3.50, "400g", "супи"));
//		menuList.add(new MenuItem("Рибена супа", 4.40, "400g", "супи"));
//		menuList.add(new MenuItem("Гъбена супа", 3.90, "400g", "супи"));
//		menuList.add(new MenuItem("Таратор", 3.00, "400g", "супи"));
		try {
			loadMenuFromFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<MenuItem> getMenuList() {
		return menuList;
	}

	public static void setMenuList(ArrayList<MenuItem> menuList) {
		Menu.menuList = menuList;
	}
	public static void addToMenuList(MenuItem MenuItem) {
		menuList.add(MenuItem);
	}
	
	public static void removeFromMenuList(MenuItem menuItemToRemove) {
		for (MenuItem item: menuList) {
			if (item == menuItemToRemove) {
				menuList.remove(item);
			}
		}
	}
	private void loadMenuFromFile () throws FileNotFoundException {
		FileInputStream fin= new FileInputStream ("restaurantMenu.sav");
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(fin);
			menuList= (ArrayList<MenuItem>)ois.readObject();
			fin.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
