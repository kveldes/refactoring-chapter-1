import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;

class Customer {

	private String customerName;
	private Vector _rentals = new Vector();


	public Customer(String customerName) {
		this.customerName = customerName;
	}

	public void addRental(Rental newRental) {
		_rentals.addElement(newRental);

	}

	public String getName() {
		return this.customerName;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;

		Enumeration rentals = _rentals.elements();
		String resultStatement = String.format("Rental Record for %s\n", getName());

		while (rentals.hasMoreElements()) {
			double processingAmount = 0;
			Rental eachRental = (Rental) rentals.nextElement();
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

			frequentRenterPoints = addingRentingPoints(frequentRenterPoints, eachRental);
			resultStatement = showingFiguresForThisRental(resultStatement, processingAmount, eachRental);
			totalAmount += processingAmount;
		}

		return resultStatement = addingFooterLines(totalAmount, frequentRenterPoints, resultStatement);
	}

	private int addingRentingPoints(int frequentRenterPoints, Rental eachRental) {
		// Common case
		frequentRenterPoints++;
		// Bonus case if it exist
		if (eachRental.getMovie().getPriceCode() == Movie.NEW_RELEASE && eachRental.getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
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
