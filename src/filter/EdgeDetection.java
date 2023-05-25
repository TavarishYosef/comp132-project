package filter;

import image.ImageMatrix;

/**
 * Applies the Sobel operator the detect borders on the image
 * 
 * @author Yusuf
 *
 */
public class EdgeDetection implements Filter {

	private static final int BLUR_DEGREE = 2;
	private static final int[][] SOBEL_X = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
	private static final int[][] SOBEL_Y = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };

	/**
	 * Applies EdgeDetection to the image to the specified degree. Takes an average
	 * between the original image and edge detected image
	 * 
	 * @param image  the image matrix to apply the filter to
	 * @param degree the degree of the filter
	 * @return a new image matrix with the edge detection filter applied
	 * @see ImageMatrix
	 */
	public ImageMatrix apply(ImageMatrix image, int degree) {
		if (degree <= 0)
			return image;
		if (degree > 10)
			degree = 10;
		int width = image.getWidth();
		int height = image.getHeight();
		ImageMatrix result = new ImageMatrix(width, height);
		ImageMatrix grayscale = image.applyFilter(new GrayscaleFilter(), 10);
		grayscale = grayscale.applyFilter(new BlurFilter(), BLUR_DEGREE);

		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				int xSum = 0;
				int ySum = 0;
				// Get the sum by applying the kernel
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						int gray = grayscale.getRed(x + i - 1, y + j - 1);
						xSum += SOBEL_X[i][j] * gray;
						ySum += SOBEL_Y[i][j] * gray;
					}
				}
				// Calculate edge detected pixels
				int edge = (int) Math.sqrt(xSum * xSum + ySum * ySum);
				edge = Math.min(255, Math.max(0, edge));
				int red = image.getRed(x, y);
				int green = image.getGreen(x, y);
				int blue = image.getBlue(x, y);
				// Get an average
				int finalRed = (edge * degree + red * (10 - degree)) / 10;
				int finalGreen = (edge * degree + green * (10 - degree)) / 10;
				int finalBlue = (edge * degree + blue * (10 - degree)) / 10;
				result.setRGB(x, y, ImageMatrix.convertRGB(finalRed, finalGreen, finalBlue));
			}
		}

		return result;
	}
}
