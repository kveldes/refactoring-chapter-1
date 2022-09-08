package videoStoreMovies;

import videoStore.Movie;

public class RegularMovie extends Movie {

	public RegularMovie(String title) {
		super(title, 0);

	}

	@Override
	public double processingAmmount(int daysRented) {
		double processingAmount = 2;
		if (daysRented > 2)
			processingAmount += (daysRented - 2) * 1.5;
		return processingAmount;
	}

}
