import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;

class Customer {

	private String name;
	private Vector _rentals = new Vector();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental newRental) {
		_rentals.addElement(newRental);

	}

	public String getName() {
		return this.name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;

		Enumeration rentals = _rentals.elements();
		String result = String.format("Rental Record for %s\n", getName());

		while (rentals.hasMoreElements()) {
			double thisAmount = 0;
			Rental each = (Rental) rentals.nextElement();
			// determine amounts for each line
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (each.getDaysRented() > 2)
					thisAmount += (each.getDaysRented() - 2) * 1.5;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += each.getDaysRented() * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += 1.5;
				if (each.getDaysRented() > 3)
					thisAmount += (each.getDaysRented() - 3) * 1.5;
				break;
			}

			// add frequent renter points
			frequentRenterPoints++;

			// add bonus for a two day new release rental
			if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}

		result = addingFooterLines(totalAmount, frequentRenterPoints, result);
		return result;
	}

	private String addingFooterLines(double totalAmount, int frequentRenterPoints, String result) {
		result += String.format(Locale.US, "Amount owed is %.1f\n", totalAmount);
		result += String.format("You earned %d frequent renter points", frequentRenterPoints);
		return result;
	}
}
