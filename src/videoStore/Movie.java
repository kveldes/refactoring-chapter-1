package videoStore;

public abstract class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
	private String title;
	private int priceCode;

    public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
    }

    public int getPriceCode() {
		return this.priceCode;
    }

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
    }

    public String getTitle() {
		return this.title;
    }

	public double calculateAmount() {
		return 0;
	}

	public double processingAmmount(int daysRented) {
		return 0;
	}
}
