package filter;

import image.ImageMatrix;

public class GrayscaleFilter implements Filter {

	@Override
	public ImageMatrix apply(ImageMatrix image, int degree) {
		ImageMatrix result = new ImageMatrix(image.getWidth(), image.getHeight());

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int red = image.getRed(x, y);
				int green = image.getGreen(x, y);
				int blue = image.getBlue(x, y);

				int gray = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
				gray = Math.min(255, Math.max(0, gray + degree));

				int grayRGB = ImageMatrix.convertRGB(gray, gray, gray);

				result.setRGB(x, y, grayRGB);
			}
		}

		return result;
	}
}
