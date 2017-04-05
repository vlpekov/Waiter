package RestaurantObjects;

public class MenuItem {
	private String name;
	private double price;
	private String category;
	private String quantity;
	
	
	public MenuItem(String name, double price, String quantity, String category) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public void printItem() {
		System.out.println(getName() + ", цена: " + getPrice() + ", количество: " + getQuantity() + ", категория: " + getCategory());
		
	}
	
	
}
