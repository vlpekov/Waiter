package RestaurantObjects;

public class MenuItem {
	private String name;
	private double price;
	private String category;
	private String weight;
	
	
	public MenuItem(String name, double price, String weight, String category) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.weight = weight;
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
	
	
}
