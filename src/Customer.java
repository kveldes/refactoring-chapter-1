import java.util.ArrayList;
import java.util.Locale;

class Customer {

	private String customerName;
	ArrayList<Rental> rentals = new ArrayList<Rental>();


	public Customer(String customerName) {
		this.customerName = customerName;
	}

	public void addRental(Rental newRental) {
		rentals.add(newRental);

	}

	public String getName() {
		return this.customerName;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;

		String resultStatement = String.format("Rental Record for %s\n", getName());


		for (Rental eachRental : rentals) {
			double processingAmount = 0;
			// determine amounts for each line
			switch (eachRental.getMovie().getPriceCode()) {
			case Movie.REGULAR: // case 0
				processingAmount += 2;
				if (eachRental.getDaysRented() > 2)
					processingAmount += (eachRental.getDaysRented() - 2) * 1.5;
				break;
			case Movie.NEW_RELEASE: // case 1
				processingAmount += eachRental.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS: // case 2
				processingAmount += 1.5;
				if (eachRental.getDaysRented() > 3)
					processingAmount += (eachRental.getDaysRented() - 3) * 1.5;
				break;
			}

			eachRental.setRentalPoints(frequentRenterPoints, eachRental);
			frequentRenterPoints = eachRental.getRentalPoints();
			resultStatement = showingFiguresForThisRental(resultStatement, processingAmount, eachRental);
			totalAmount += processingAmount;
		}

		return resultStatement = addingFooterLines(totalAmount, frequentRenterPoints, resultStatement);
	}

	private String showingFiguresForThisRental(String resultStatement, double processingAmount, Rental eachRental) {
		return resultStatement += String.format(Locale.US, "\t%s\t%.1f\n", eachRental.getMovie().getTitle(),
				processingAmount);
	}

	private String addingFooterLines(double totalAmount, int frequentRenterPoints, String resultStatement) {
		resultStatement += String.format(Locale.US, "Amount owed is %.1f\n", totalAmount);
		resultStatement += String.format("You earned %d frequent renter points", frequentRenterPoints);
		return resultStatement;
	}



}
