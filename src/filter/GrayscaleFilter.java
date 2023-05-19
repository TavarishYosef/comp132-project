package filter;

import image.ImageMatrix;

/**
 * A filter that converts an image to grayscale
 */
public class GrayscaleFilter implements Filter {

	/**
	 * Applies grayscale effect to the ImageMatrix
	 * degree 0 means no change, degree 10 means full grayscale
	 * @param image the image matrix to apply the filter to
	 * @param degree degree of the filter
	 * @return a new image matrix with the grayscale filter applied
	 * @see ImageMatrix
	 */
	@Override
	public ImageMatrix apply(ImageMatrix image, int degree) {
		if (degree <= 0)
			return image;
		if (degree > 10)
			degree = 10;
		ImageMatrix result = new ImageMatrix(image.getWidth(), image.getHeight());

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int red = image.getRed(x, y);
				int green = image.getGreen(x, y);
				int blue = image.getBlue(x, y);

				int gray = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
				gray = Math.min(255, Math.max(0, gray));

				int finalRed = Math.abs(gray*degree - red*(10-degree)) / 10;
				int finalGreen = Math.abs(gray*degree - green*(10-degree)) /10;
				int finalBlue = Math.abs(gray*degree - blue*(10-degree)) / 10;
				int grayRGB = ImageMatrix.convertRGB(finalRed, finalGreen, finalBlue);

				result.setRGB(x, y, grayRGB);
			}
		}

		return result;
	}
}
