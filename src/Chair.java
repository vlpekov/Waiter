
public class Chair {
private int chairNumber;
private boolean isFree;
//private Customer customer;

public Chair() {
	this.isFree = true;
}
public Chair(int chairNumber) {
	this.chairNumber = chairNumber;
	this.isFree = true;
}
public int getChairNumber() {
	return chairNumber;
}
public void setChairNumber(int chairNumber) {
	this.chairNumber = chairNumber;
}
public boolean isFree() {
	return isFree;
}
public void setFree(boolean isFree) {
	this.isFree = isFree;
}

}
