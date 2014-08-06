package com.photobank.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ChangeImage {
	public static void toBlackAndWhiteImg(String path) throws IOException {
//"D:/Users/plan_zala.jpg"
		//path = "D:/Users/" + path;
	    File file = new File("D:/Users/" + path);
	    BufferedImage orginalImage = ImageIO.read(file);

	    BufferedImage blackAndWhiteImg = new BufferedImage(
	        orginalImage.getWidth(), orginalImage.getHeight(),
	        BufferedImage.TYPE_BYTE_BINARY);
	    
	    Graphics2D graphics = blackAndWhiteImg.createGraphics();
	    graphics.drawImage(orginalImage, 0, 0, null);

	    ImageIO.write(blackAndWhiteImg, "png", new File("D:/Users/" + path)); 
	    /*BufferedImage sepia=toSepia(orginalImage, 100) ;*/
	   
	  
	}
	public static void toSepia(String path, int sepiaIntensity) throws IOException {
        path = "D:/Users/" + path;
		  File file = new File(path);
		    BufferedImage img = ImageIO.read(file);
		
	    BufferedImage sepia = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    // Play around with this.  20 works well and was recommended
	    //   by another developer. 0 produces black/white image
	    int sepiaDepth = 20;

	    int w = img.getWidth();
	    int h = img.getHeight();

	    WritableRaster raster = sepia.getRaster();

	    // We need 3 integers (for R,G,B color values) per pixel.
	    int[] pixels = new int[w * h * 3];
	    img.getRaster().getPixels(0, 0, w, h, pixels);

	    for (int x = 0; x < img.getWidth(); x++) {
	        for (int y = 0; y < img.getHeight(); y++) {

	            int rgb = img.getRGB(x, y);
	            Color color = new Color(rgb, true);
	            int r = color.getRed();
	            int g = color.getGreen();
	            int b = color.getBlue();
	            int gry = (r + g + b) / 3;

	            r = g = b = gry;
	            r = r + (sepiaDepth * 2);
	            g = g + sepiaDepth;

	            if (r > 255) {
	                r = 255;
	            }
	            if (g > 255) {
	                g = 255;
	            }
	            if (b > 255) {
	                b = 255;
	            }

	            // Darken blue color to increase sepia effect
	            b -= sepiaIntensity;

	            // normalize if out of bounds
	            if (b < 0) {
	                b = 0;
	            }
	            if (b > 255) {
	                b = 255;
	            }

	            color = new Color(r, g, b, color.getAlpha());
	            sepia.setRGB(x, y, color.getRGB());

	        }
	    }
	    ImageIO.write(sepia, "png", new File("D:/Users/" + path));
	    //return sepia;
	}
}
