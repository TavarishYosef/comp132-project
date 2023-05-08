package filter;

import image.ImageMatrix;

public interface Filter {
	
	public abstract ImageMatrix apply(ImageMatrix image, int degree);

}
