import java.util.Random;

public class random {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println(randInt(1, 10));
System.out.println(randInt(1, 10));
System.out.println(randInt(1, 10));
System.out.println(randInt(1, 10));
System.out.println(randInt(1, 10));
System.out.println(randInt(1, 10));
System.out.println(randInt(1, 10));
System.out.println(randInt(1, 10));
System.out.println(randInt(1, 10));

	}
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
