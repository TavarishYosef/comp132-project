package filter;

import image.ImageMatrix;

/**
 * A filter that adjusts the contrast of an image by modifying each pixel's RGB
 * values according to a specified formula.
 */
public class ContrastFilter implements Filter {
	/**
	 * Applies the contrast filter to the given ImageMatrix by modifying each
	 * pixel's RGB values according to the formula: color = factor * (color - 128) +
	 * 128 where factor = (259 * (C + 255) / (255 * (259 - C) where C is the
	 * contrast multiplier
	 * 
	 * @param image  the image matrix to apply the filter to
	 * @param degree degree of the filter between -10 and 10
	 * @return the modified image matrix
	 */
	@Override
	public ImageMatrix apply(ImageMatrix image, int degree) {
		if (degree <= 0)
			return image;
		if (degree > 10)
			degree = 10;

		ImageMatrix result = new ImageMatrix(image.getWidth(), image.getHeight());

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				double factor = (259 * (25 * degree + 255)) / (255 * (259 - 25 * degree));
				int red = image.getRed(x, y);
				int green = image.getGreen(x, y);
				int blue = image.getBlue(x, y);

				int newRed = Math.min(255, Math.max(0, (int) factor * (red - 128) + 128));
				int newGreen = Math.min(255, Math.max(0, (int) factor * (green - 128) + 128));
				int newBlue = Math.min(255, Math.max(0, (int) factor * (blue - 128) + 128));

				int newPixel = ImageMatrix.convertRGB(newRed, newGreen, newBlue);
				result.setRGB(x, y, newPixel);
			}

		}
		return result;
	}

}
