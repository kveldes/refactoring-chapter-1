package videoStore;
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

	public double processingAmmount(Rental eachRental) {
		double processingAmount = 0;

		switch (eachRental.getPriceCode()) {
		case Movie.REGULAR: // case 0
			processingAmount = 2;
			if (eachRental.getDaysRented() > 2)
				processingAmount += (eachRental.getDaysRented() - 2) * 1.5;
			break;
		case Movie.NEW_RELEASE: // case 1
			processingAmount = eachRental.getDaysRented() * 3;
			break;
		case Movie.CHILDRENS: // case 2
			processingAmount += 1.5;
			if (eachRental.getDaysRented() > 3)
				processingAmount += (eachRental.getDaysRented() - 3) * 1.5;
			break;
		}
		return processingAmount;
	}
}
