package filter;

import image.ImageMatrix;

/**
 * A filter that sharpens the image by subtracting blurred pixels from original
 * pixels to get details. Then adds details to the original image
 *
 */
public class SharpenFilter implements Filter {
	/**
	 * Applies sharpen to the ImageMatrix to the specified degree
	 *
	 * @param image the image matrix to apply the filter to
	 * @param degree      the degree of the filter
	 * @return a new image matrix with the sharpen filter applied
	 * @see ImageMatrix
	 */
	@Override
	public ImageMatrix apply(ImageMatrix image, int degree) {
		if (degree == 0) {
			return image;
		}
		// First, we need to blur the original image
		BlurFilter blurFilter = new BlurFilter();
		ImageMatrix blurredImageMatrix = blurFilter.apply(image, degree);

		// Then, we need to calculate the difference between the original image and the
		// blurred image
		ImageMatrix detailImageMatrix = new ImageMatrix(image.getWidth(), image.getHeight());
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				int originalRed = image.getRed(i, j);
				int originalGreen = image.getGreen(i, j);
				int originalBlue = image.getBlue(i, j);

				int blurredRed = blurredImageMatrix.getRed(i, j);
				int blurredGreen = blurredImageMatrix.getGreen(i, j);
				int blurredBlue = blurredImageMatrix.getBlue(i, j);

				// Subtract the blurred image from the original image to get the details
				int red = Math.max(0, originalRed - blurredRed);
				int green = Math.max(0, originalGreen - blurredGreen);
				int blue = Math.max(0, originalBlue - blurredBlue);

				int detailRGB = ImageMatrix.convertRGB(red, green, blue);
				detailImageMatrix.setRGB(i, j, detailRGB);
			}
		}

		// Add the details to the original image to make it sharp
		ImageMatrix sharpImageMatrix = new ImageMatrix(image.getWidth(), image.getHeight());
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				int originalRed = image.getRed(i, j);
				int originalGreen = image.getGreen(i, j);
				int originalBlue = image.getBlue(i, j);

				int detailRed = detailImageMatrix.getRed(i, j);
				int detailGreen = detailImageMatrix.getGreen(i, j);
				int detailBlue = detailImageMatrix.getBlue(i, j);

				// Add the details to the original image to make it sharp
				int red = Math.max(0, detailRed + originalRed);
				int green = Math.max(0, detailGreen + originalGreen);
				int blue = Math.max(0, detailBlue + originalBlue);

				int sharpRGB = ImageMatrix.convertRGB(red, green, blue);
				sharpImageMatrix.setRGB(i, j, sharpRGB);
			}
		}

		return sharpImageMatrix;
	}
}
