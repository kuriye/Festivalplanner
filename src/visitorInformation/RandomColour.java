package visitorInformation;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;


public class RandomColour {

    public RandomColour() {
    }

    public BufferedImage colorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        int r = (int)(Math.random() * 255);
        int g = (int)(Math.random() * 255);
        int b = (int)(Math.random() * 255);
        for (int xx = 3; xx < width -3; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                pixels[0] = r;
                pixels[1] = g;
                pixels[2] = b;
                raster.setPixel(xx, yy, pixels);
            }
        }
        return image;
    }
}
