package videoStoreMovies;

import videoStore.Movie;

public class NewReleaseMovie extends Movie {

	public NewReleaseMovie(String title, int priceCode) {
		super(title, priceCode);

	}

	public double calculateAmount() {
		return 0;
	}

}
