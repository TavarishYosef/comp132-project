package filter;

import image.ImageMatrix;

public class EdgeDetection implements Filter {

	private static final int BLUR_DEGREE = 3;
	private static final int[][] SOBEL_X = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
	private static final int[][] SOBEL_Y = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };

	public ImageMatrix apply(ImageMatrix image, int degree) {
		if (degree == 0) {
			return image;
		}
		int width = image.getWidth();
		int height = image.getHeight();
		ImageMatrix result = new ImageMatrix(width, height);
		ImageMatrix grayscale = image.applyFilter(new GrayscaleFilter(), 10);
		grayscale = grayscale.applyFilter(new BlurFilter(), BLUR_DEGREE);

		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				int xSum = 0;
				int ySum = 0;

				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						int gray = grayscale.getRed(x + i - 1, y + j - 1);
						xSum += SOBEL_X[i][j] * gray;
						ySum += SOBEL_Y[i][j] * gray;
					}
				}

				int edge = (int) Math.sqrt(xSum * xSum + ySum * ySum);
				edge = Math.min(255, Math.max(0, edge));
				int red = image.getRed(x, y);
				int green = image.getGreen(x, y);
				int blue = image.getBlue(x, y);
				
				int finalRed = (edge*degree + red*(10 - degree))/10;
				int finalGreen = (edge*degree + green*(10 - degree))/10;
				int finalBlue = (edge*degree + blue*(10 - degree))/10;
				result.setRGB(x, y, ImageMatrix.convertRGB(finalRed, finalGreen, finalBlue));
			}
		}

		return result;
	}
}
