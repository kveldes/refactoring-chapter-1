package videoStoreMovies;

import videoStore.Movie;

public class NewReleaseMovie extends Movie {

	public NewReleaseMovie(String title) {
		super(title, 0);

	}

	@Override
	public double processingAmmount(int daysRented) {
		return daysRented * 3;
	}


}
