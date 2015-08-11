package com.kylemsguy;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static String VERSION_STR = "v0.3-alpha";

    private static void usage(){
        System.err.println("Usage:\tjava -jar JProfilePicImagifier.jar [IMAGEFILE]");
        System.err.println("Converts a 32x32 image file to the TCaS format and prints the result to stdout");
        System.err.println("Supported file types:");
        System.err.println("\t- PNG");
        System.err.println("\t- BMP");
        System.err.println("\tTODO: Figure out what file types supported");
        System.err.println("\nNote: the image MUST be 32x32, or things may go wrong");
    }

    public static void main(String[] args) {
        System.err.println("TCaS Profile Pic Converter " + VERSION_STR);
        String filename = null;
        String outFile = null;
        if(args.length == 1){
            filename = args[0];
        }
        else if(args.length == 0){
            Scanner sc = new Scanner(System.in);
            System.err.println("Please enter the full path to the file.");
            System.err.println("Alternatively, you may drag the file onto this window.");
            while(filename == null || filename.isEmpty()) {
                System.err.print("Input File> ");
                filename = sc.nextLine();
            }
            System.err.println("Please enter a filename for the output.");
            System.err.println("If you don't wish to save to a file, just press enter");
            System.err.print("Output File> ");
            String input = sc.nextLine();
            if(!input.isEmpty()){
                outFile = input;
            }
        }else{
            usage();
            System.exit(1);
        }
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
        System.err.println("Please refer to the README for what to do with the data.");
        System.err.println();
        if(outFile == null)
            System.out.println(data);
        else{
            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
                writer.write(data);
                writer.close();
            } catch(FileNotFoundException e){
                System.err.println("File not found. Printing data to console.");
                System.out.println(data);
            } catch(IOException e){
                System.err.println("An error occurred when writing to the file. Printing data to console.");
                System.out.println(data);
            }
        }
    }
}
