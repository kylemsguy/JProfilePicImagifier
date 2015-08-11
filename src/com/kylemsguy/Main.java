package com.kylemsguy;

import java.util.Scanner;

public class Main {

    private static void usage(){
        System.out.println("Usage: java -jar JProfilePicImagifier.jar <imagename>.bmp");
        System.out.println("Converts a 32x32 bitmap to the TCaS format and prints the result to stdout");
        System.out.println("\tNote: the image MUST be 32x32, or things may go wrong");
        System.out.println("\t(TODO: throw exception if image size not as expected");
    }

    public static void main(String[] args) {
        if(args.length != 2){
            usage();
        }
	    Scanner s = new Scanner(System.in);
    }
}
