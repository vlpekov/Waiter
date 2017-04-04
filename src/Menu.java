import java.util.ArrayList;

public class Menu {
	public static ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
	
	public static MenuItem getMenuItem(String itemName) {
		MenuItem menuItem = null;
		for (MenuItem item : menuList) {
			if (item.getName().equals(itemName)) {
				menuItem = item;
			}
		}
		return menuItem;
	}
	
	
	
}
