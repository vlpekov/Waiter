package RestaurantObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import GUI.*;

public class Menu {
	public static ArrayList<MenuItem> menuList;
	public static ArrayList<String> categoryList;

	public Menu() {
		this.menuList = new ArrayList<MenuItem>();
		try {
			loadMenuFromFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setCategoryList();
	}

	private void setCategoryList() {
		this.categoryList = new ArrayList<String>();
		categoryList.add("безалкохолни напитки");
		categoryList.add("алкохолни напитки");
		categoryList.add("супи");
		categoryList.add("предястия");
		categoryList.add("салати");
		categoryList.add("основни ястия");
		categoryList.add("десерти");
		categoryList.add("други");
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
		for (MenuItem item : menuList) {
			if (item == menuItemToRemove) {
				menuList.remove(item);
			}
		}
	}

	private void loadMenuFromFile() throws FileNotFoundException {
		FileInputStream fin = new FileInputStream("restaurantMenu.sav");
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(fin);
			menuList = (ArrayList<MenuItem>) ois.readObject();
			fin.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void restoreMenuFromBackup() throws FileNotFoundException {
		FileInputStream fin = new FileInputStream("restaurantMenu.backup");
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(fin);
			menuList = (ArrayList<MenuItem>) ois.readObject();
			fin.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			FileOutputStream fileOut = new FileOutputStream("restaurantMenu.sav");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(menuList);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}

	}
}
