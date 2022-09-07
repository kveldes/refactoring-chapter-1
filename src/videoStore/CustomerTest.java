package videoStore;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CustomerTest {

    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Stelios");
    }

    @Test
    public void
    when_renting_one_new_movie_for_3_days_then_statement_has_9_price_and_2_credits() throws Exception {
        customer.addRental(getInterstellarRentalFor(3));

        assertThat(customer.statement(), is("Rental Record for " +
                "Stelios\n\tInterstellar\t9.0\nAmount owed is 9.0\nYou earned" +
                " 2 frequent renter points"));
    }

    @Test
    public void
    when_renting_two_new_movie_for_3_and_4_days_respectively_then_statement_has_21_price_and_4_credits() throws Exception {
        customer.addRental(getInterstellarRentalFor(3));
        Rental judgeRental = new Rental(new Movie("The Judge", Movie
                .NEW_RELEASE), 4);
        customer.addRental(judgeRental);

        assertThat(customer.statement(), is("Rental Record for " +
                "Stelios\n\tInterstellar\t9.0\n\tThe Judge\t12.0\nAmount owed" +
                " is 21.0\nYou earned 4 frequent renter points"));
    }

    @Test
    public void
    when_renting_one_child_movie_for_2_days_then_statement_has_1_5_price_and_1_credit() throws Exception {
        customer.addRental(getLionKingRental(2));

        assertThat(customer.statement(), is("Rental Record for " +
                "Stelios\n\tLion King\t1.5\nAmount owed is 1.5\nYou earned 1 " +
                "frequent renter points"));
    }

    @Test
    public void
    when_renting_one_regular_movie_for_5_days_then_statement_has_1_5_price_and_1_credit() throws Exception {
        customer.addRental(getGodFatherRental(5));

        assertThat(customer.statement(), is("Rental Record for Stelios\n\tThe" +
                " Godfather\t6.5\nAmount owed is 6.5\nYou earned 1 frequent " +
                "renter points"));
    }

    @Test
    public void
    when_renting_one_child_movie_for_5_days_then_statement_has_4_5_price_and_1_credit() throws Exception {
        customer.addRental(getLionKingRental(5));

        assertThat(customer.statement(), is("Rental Record for " +
                "Stelios\n\tLion King\t4.5\nAmount owed is 4.5\nYou earned 1 " +
                "frequent renter points"));
    }
    
    @Test
    public void rent_no_movies() {
    	assertThat(customer.statement(), is("Rental Record for Stelios\nAmount owed is 0.0\nYou earned 0 frequent renter points"));
    } 
    
    @Test
    public void when_renting_regular_movie_for_one_day_then_statement_has_2_price_and_1_credit() {
    	customer.addRental(getGodFatherRental(1));
    	
    	assertThat(customer.statement(), is("Rental Record for Stelios\n\tThe Godfather\t2.0\nAmount owed is 2.0\nYou earned 1 frequent renter points"));
    }
    
    @Test
    public void when_renting_a_new_release_for_1_day_then_statement_has_3_price_and_1_credit() {
    	customer.addRental(getInterstellarRentalFor(1));
    	
    	assertThat(customer.statement(), is("Rental Record for Stelios\n\tInterstellar\t3.0\nAmount owed is 3.0\nYou earned 1 frequent renter points"));
    }

    @Test
    public void when_renting_all_types_of_movies() {
        customer.addRental(getInterstellarRentalFor(3));
        customer.addRental(getLionKingRental(5));
        customer.addRental(getGodFatherRental(5));

        assertThat(customer.statement(), is("Rental Record for " +
                "Stelios\n\tInterstellar\t9.0\n\tLion King\t4.5\n\tThe " +
                "Godfather\t6.5\nAmount owed is 20.0\nYou earned 4 frequent " +
        		"renter points"));

    }

    private Rental getInterstellarRentalFor(int daysRented) {
        return new Rental(new Movie("Interstellar", Movie.NEW_RELEASE), daysRented);
    }

    private Rental getLionKingRental(int daysRented) {
        return new Rental(new Movie("Lion King", Movie.CHILDRENS), daysRented);
    }

    private Rental getGodFatherRental(int daysRented) {
        return new Rental(new Movie("The Godfather", Movie.REGULAR), daysRented);
    }

}