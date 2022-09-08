package videoStoreMovies;

import videoStore.Movie;

public class ChildrenMovie extends Movie {

	public ChildrenMovie(String title) {
		super(title, 0);

	}

	@Override
	public double processingAmmount(int daysRented) {
		double processingAmount = 0;
		processingAmount = 1.5;
		if (daysRented > 3)
			processingAmount += (daysRented - 3) * 1.5;
		return processingAmount;
	}


}
