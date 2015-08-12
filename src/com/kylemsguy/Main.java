package com.kylemsguy;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static String VERSION_STR = "v0.5-beta";

    private static void usage(){
        System.err.println("Usage:\tjava -jar JProfilePicImagifier.jar [IMAGEFILE]");
        System.err.println("\tjava -jar JProfilePicImagifier.jar -i");
        System.err.println("Converts a 32x32 image file to the TCaS format and prints the result to stdout");
        System.err.println("Supported file types:");
        System.err.println("\t- PNG");
        System.err.println("\t- BMP");
        System.err.println("\t- GIF");
        System.err.println("\t- JPG");
        System.err.println("\tTODO: Figure out what file types supported");
        System.err.println("\nNote: the image MUST be 32x32, or things may go wrong");
    }

    /**
     * (╯°□°）╯︵ ┻━┻)
     */
    public static void startGUI(){
        final JFrame frame = new JFrame("TCaS Profile Pic Converter " + VERSION_STR);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("TCaS Profile Pic Converter " + VERSION_STR);

        JPanel inputPanel = new JPanel(new BorderLayout());
        Border inputBorder = BorderFactory.createTitledBorder("Input");
        inputPanel.setBorder(inputBorder);
        inputPanel.setMaximumSize(new java.awt.Dimension(475, 85));
        inputPanel.setMinimumSize(new java.awt.Dimension(322, 50));
        inputPanel.setPreferredSize(new java.awt.Dimension(475, 85));

        final JFilePicker filePicker = new JFilePicker(20);
        inputPanel.add(filePicker, BorderLayout.LINE_START);

        final RGBFrame rgbFrame = new RGBFrame();
        inputPanel.add(rgbFrame);

        JPanel outputPanel = new JPanel(new GridLayout(0, 1));
        Border outputBorder = BorderFactory.createTitledBorder("Output");
        outputPanel.setBorder(outputBorder);

        final JTextArea outputCode = new JTextArea(20, 20);
        outputCode.setLineWrap(true);
        outputCode.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputCode);
        outputPanel.add(scrollPane);
        //outputPanel.add(outputCode);

        final JButton submitButton = new JButton("Convert");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle Browse action
                if (e.getSource() == submitButton) {
                    File file = null;
                    try {
                        file = filePicker.getFile();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error: File not found\n" + ex.toString());
                        return;
                    }
                    try {
                        TCaSImageConverter converter = new TCaSImageConverter(file);
                        String output = converter.convertToTImg(rgbFrame.getRGB());
                        outputCode.setText(output);
                    } catch (IOException | NullPointerException ex) {
                        JOptionPane.showMessageDialog(frame, "Error: Invalid File\n" + ex.toString());
                    } catch(IllegalStateException | NumberFormatException ex){
                        JOptionPane.showMessageDialog(frame, ex.toString());
                    }
                }
            }
        });

        inputPanel.add(submitButton, BorderLayout.SOUTH);

        Container main = frame.getContentPane();
        main.add(titleLabel, BorderLayout.BEFORE_FIRST_LINE);
        main.add(inputPanel, BorderLayout.CENTER);
        main.add(outputPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        System.err.println("TCaS Profile Pic Converter " + VERSION_STR);
        String filename = null;
        String outFile = null;
        if(args.length == 1){
            if(args[0].equals("-i")){
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
            }else {
                filename = args[0];
            }
        }else if(args.length == 0){
            startGUI();
            return;
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
