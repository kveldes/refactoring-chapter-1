class Rental {

	private Movie movie;
	private int daysRented;
	private int frequentRenterPoints;

    public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
    }

    public int getDaysRented() {
		return this.daysRented;
    }

    public Movie getMovie() {
		return this.movie;
    }

	public void setRentalPoints(int frequentRenterPoints, Rental eachRental) {
		frequentRenterPoints++;
		if (eachRental.getMovie().getPriceCode() == Movie.NEW_RELEASE && eachRental.getDaysRented() > 1) {
			frequentRenterPoints++;
		}
		this.frequentRenterPoints = frequentRenterPoints;
	}

	public int getRentalPoints() {
		return this.frequentRenterPoints;
	}

	public int getPriceCode() {
		return getMovie().getPriceCode();
	}
}
