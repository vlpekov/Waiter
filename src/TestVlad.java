
public class TestVlad {

	public static void main(String[] args) {
		new Table();
		Restaurant.printTableList();
		printHorizontalLine();
		Restaurant.getTable(2).printTableinfo();
		Restaurant.getTable(2).bookTable();
		printHorizontalLine();
		Restaurant.getTable(2).printTableinfo();
		printHorizontalLine();
		Restaurant.getTable(5).occupyTable();
		Restaurant.getTable(2).release();
		Restaurant.printTableList();
		printHorizontalLine();
		Restaurant.getTable(2).printChairList();
		printHorizontalLine();
		Restaurant.getTable(7).printChairList();
		printHorizontalLine();
		Restaurant.getTable(9).printChairList();
		System.out.println(Restaurant.getTable(9).countFreeChairs());
	}
	public static void printHorizontalLine() {
		System.out.println("-----------------------");
	}
}
