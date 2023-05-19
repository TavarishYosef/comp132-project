package filter;

import image.ImageMatrix;
/**
 * A collective interface for applying image filters
 * @author Yusuf
 *
 */
public interface Filter {
	/**
	 * Applies the filter to the image
	 * @param image
	 * @param degree an integer between 0-10
	 * @return the filtered image matrix
	 */
	public abstract ImageMatrix apply(ImageMatrix image, int degree);

}
