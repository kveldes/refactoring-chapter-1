package videoStore;
import java.math.BigDecimal;
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
		BigDecimal totalAmount = new BigDecimal("0");
		int frequentRenterPoints = 0;

		String resultStatement = addingHeaderLines();

		for (Rental eachRental : rentals) {
			BigDecimal processingAmount = new BigDecimal(processingAmmountForEachRental(eachRental));
			eachRental.setRentalPoints(frequentRenterPoints, eachRental);
			frequentRenterPoints = eachRental.getRentalPoints();
			resultStatement = showingFiguresForThisRental(resultStatement, processingAmount, eachRental);
			totalAmount = totalAmount.add(processingAmount);
		}

		return resultStatement = addingFooterLines(totalAmount, frequentRenterPoints, resultStatement);
	}

	private String addingHeaderLines() {
		return String.format("Rental Record for %s\n", getName());
	}

	private double processingAmmountForEachRental(Rental eachRental) {
		return eachRental.processingAmmount();
	}

	private String showingFiguresForThisRental(String resultStatement, BigDecimal processingAmount, Rental eachRental) {
		return resultStatement += String.format(Locale.US, "\t%s\t%.1f\n", eachRental.getMovie().getTitle(),
				processingAmount);
	}

	private String addingFooterLines(BigDecimal totalAmount, int frequentRenterPoints, String resultStatement) {
		resultStatement += String.format(Locale.US, "Amount owed is %.1f\n", totalAmount);
		resultStatement += String.format("You earned %d frequent renter points", frequentRenterPoints);
		return resultStatement;
	}



}
