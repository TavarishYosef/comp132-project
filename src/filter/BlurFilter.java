package filter;

import image.ImageMatrix;

/**
 * A filter that blurs the image by replacing each pixel with the average value
 * of its neighboring pixels. The degree of blurring is determined by the number
 * of neighboring pixels used to calculate the average.
 */
public class BlurFilter implements Filter {
	/**
	 * Applies blur to the ImageMatrix to the specified degree
	 * 
	 * @param image the image matrix to apply the filter to
	 * @param degree      the degree of the filter
	 * @return a new image matrix with the blur filter applied
	 * @see ImageMatrix
	 */
	public ImageMatrix apply(ImageMatrix image, int degree) {

		if (degree <= 0)
			return image;
		if (degree > 10)
			degree = 10;
		ImageMatrix result = new ImageMatrix(image.getWidth(), image.getHeight());

		int area = 0;

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int redTotal = 0;
				int greenTotal = 0;
				int blueTotal = 0;

				for (int i = -degree; i < degree + 1; i++) {
					for (int j = -degree; j < degree + 1; j++) {
						if (i == 0 && j == 0) {
							break;
						}
						int pixelX = x + i;
						int pixelY = y + j;

						if (pixelX >= 0 && pixelY >= 0 && pixelX < image.getWidth() && pixelY < image.getHeight()) {
							redTotal += image.getRed(pixelX, pixelY);
							greenTotal += image.getGreen(pixelX, pixelY);
							blueTotal += image.getBlue(pixelX, pixelY);
							area++;
						}
					}
				}

				int redAverage = redTotal / area;
				int greenAverage = greenTotal / area;
				int blueAverage = blueTotal / area;
				area = 0;
				int newColor = ImageMatrix.convertRGB(redAverage, greenAverage, blueAverage);
				result.setRGB(x, y, newColor);
			}
		}

		return result;
	}
}
