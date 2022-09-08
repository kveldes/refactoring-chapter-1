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


	public double processingAmmount() {
		double processingAmount = 0;

		switch (getPriceCode()) {
		case Movie.REGULAR:
			processingAmount = 2;
			if (daysRented > 2)
				processingAmount += (daysRented - 2) * 1.5;
			break;
		case Movie.NEW_RELEASE:
			processingAmount = daysRented * 3;
			break;
		case Movie.CHILDRENS:
			processingAmount += 1.5;
			if (daysRented > 3)
				processingAmount += (daysRented - 3) * 1.5;
			break;
		}
		return processingAmount;
	}

}
