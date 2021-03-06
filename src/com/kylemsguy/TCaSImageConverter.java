package com.kylemsguy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Based on Hydrogen777's Profile Pic Imagifier
 * http://www.twocansandstring.com/forum/announcements/9066/
 *
 * Supported file types:
 *  - PNG
 *  - BMP
 *  - TODO: figure them all out
 */
public class TCaSImageConverter {
    private static final int[] BACKGROUND_COLOUR = {255, 255, 255}; // This is the proper spelling. Trust me. I'm Canadian.

    private int[] argbAry;

    private void commonInit(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        if(width != 32 || height != 32){
            throw new IOException("Unsupported image size (" + width + "x" + height + ")");
        }

        argbAry = image.getRGB(0, 0, width, height, null, 0, width);
    }

    public TCaSImageConverter(String path) throws IOException {
        File f = new File(path);
        commonInit(f);
    }

    public TCaSImageConverter(File f) throws IOException {
        commonInit(f);
    }

    public String convertToTImg(){
        return convertToTImg(BACKGROUND_COLOUR);
    }

    public String convertToTImg(int[] bgcolour){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < argbAry.length - 1; i++){
            int[] rgb = ARGB2RGB(argbAry[i], bgcolour);

            sb.append(rgb[0] / 4);
            sb.append("|");
            sb.append(rgb[1] / 4);
            sb.append("|");
            sb.append(rgb[2] / 4);
            sb.append(",");

        }

        int[] lastPixel = ARGB2RGB(argbAry[argbAry.length - 1], bgcolour);

        sb.append(lastPixel[0] / 4);
        sb.append("|");
        sb.append(lastPixel[1] / 4);
        sb.append("|");
        sb.append(lastPixel[2] / 4);;

        return sb.toString();
    }

    /**
     * Applies a background colour to an int packed ARGB value
     * @param argb Integer packed ARGB value
     * @param background RGB array for the background colour (length 3)
     * @return An array of RGB values (length 3)
     */
    public static int[] ARGB2RGB(int argb, int[] background){
        int alpha = argb >> 24 & 255;

        // bitwise AND with 255 because we only want the lower 8 bits.
        int red = argb >> 16 & 255;
        int green = argb >> 8 & 255;
        int blue = argb & 255;

        int newRed = pixelARGB2RGB(alpha, red, background[0]);
        int newGreen = pixelARGB2RGB(alpha, green, background[1]);
        int newBlue = pixelARGB2RGB(alpha, blue, background[2]);
        return new int[]{newRed, newGreen, newBlue};
    }

    private static int pixelARGB2RGB(int alpha, int original, int background){
        int opacity = alpha / 255;
        return opacity * original + (1-opacity) * background;
    }

    public String toString() {
        // TODO implement
        return super.toString();
    }

}
