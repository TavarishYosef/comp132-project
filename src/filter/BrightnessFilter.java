package filter;

import image.ImageMatrix;
/**
 * A filter that increases the brightness of an image
 * @author Yusuf
 * 
 */
public class BrightnessFilter implements Filter {

	private final static int BRIGHTNESS_STEP_COUNT = 10;
    /**
     * Increases the brightness of an image by adding a specified amount to the red, green, and blue
     * values of each pixel.
     *
     * @param image the image matrix to apply the filter to
     * @param degree degree of brightness increase
     * @return the modified image matrix
     */
    public ImageMatrix apply(ImageMatrix image, int degree) {
		if (degree <= 0)
			return image;
		if (degree > 10)
			degree = 10;
        ImageMatrix result = new ImageMatrix(image.getWidth(), image.getHeight());

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
            	// Increase RGB values
                int red = Math.min(255, Math.max(0, image.getRed(x, y) + degree * BRIGHTNESS_STEP_COUNT));
                int green = Math.min(255, Math.max(0, image.getGreen(x, y) + degree * BRIGHTNESS_STEP_COUNT));
                int blue = Math.min(255, Math.max(0, image.getBlue(x, y) + degree * BRIGHTNESS_STEP_COUNT));
                int newRGB = ImageMatrix.convertRGB(red, green, blue);
                result.setRGB(x, y, newRGB);
            }
        }

        return result;
    }
}
