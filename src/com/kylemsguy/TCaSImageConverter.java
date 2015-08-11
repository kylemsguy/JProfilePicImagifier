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
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < argbAry.length - 1; i++){
            //int alpha = argbAry[i] >> 24 & 255;
            int red = argbAry[i] >> 16 & 255;
            int green = argbAry[i] >> 8 & 255;
            int blue = argbAry[i] & 255;

            sb.append(red / 4);
            sb.append("|");
            sb.append(green / 4);
            sb.append("|");
            sb.append(blue / 4);
            sb.append(",");

        }

        int lastPixel = argbAry[argbAry.length - 1];

        //int alpha = lastPixel >> 24 & 255;
        int red = lastPixel >> 16 & 255;
        int green = lastPixel >> 8 & 255;
        int blue = lastPixel & 255;

        sb.append(red / 4);
        sb.append("|");
        sb.append(green / 4);
        sb.append("|");
        sb.append(blue / 4);

        return sb.toString();
    }

    public String toString() {
        // TODO implement
        return super.toString();
    }

}
