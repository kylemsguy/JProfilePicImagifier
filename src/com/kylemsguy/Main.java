package com.kylemsguy;

import java.io.IOException;

public class Main {

    private static void usage(){
        System.err.println("Usage: java -jar JProfilePicImagifier.jar <imagename>");
        System.err.println("Converts a 32x32 image file to the TCaS format and prints the result to stdout");
        System.err.println("Supported file types:");
        System.err.println("\t- PNG");
        System.err.println("\t- BMP");
        System.err.println("\tTODO: Figure out what file types supported");
        System.err.println("\nNote: the image MUST be 32x32, or things may go wrong");
    }

    public static void main(String[] args) {
        if(args.length != 1){
            usage();
            System.exit(1);
        }
	    String filename = args[0];
        System.err.println("Processing file...");
        TCaSImageConverter ic = null;
        try {
            ic = new TCaSImageConverter(filename);
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        String data = ic.convertToTImg();
        System.err.println("Done!\n");
        System.err.println("Please refer to the README for what to do with the following data:");
        System.err.println();
        System.out.println(data);
    }
}
