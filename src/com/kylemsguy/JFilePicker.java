package com.kylemsguy;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by kyle on 11/08/15.
 */
public class JFilePicker extends JPanel implements ActionListener {
    private static final String newline = "\n";
    private JButton browseButton;
    private JTextField pathField;
    private JFileChooser fc;

    public JFilePicker(int textFieldWidth){
        super(new BorderLayout());
        //super(new SpringLayout());
        //SpringLayout layout = (SpringLayout) this.getLayout();

        // create the path field first
        pathField = new JTextField(textFieldWidth);

        // create the file chooser
        fc = new JFileChooser();

        // create the browse button
        browseButton = new JButton("Browse");
        browseButton.addActionListener(this);

        // add items to layout
        add(pathField);
        add(browseButton, BorderLayout.LINE_END);

/*
        // set springs for pathField
        layout.putConstraint(SpringLayout.WEST, pathField,
                5,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, pathField,
                5,
                SpringLayout.NORTH, this);

        // set springs for browseButton
        layout.putConstraint(SpringLayout.WEST, browseButton,
                5,
                SpringLayout.EAST, pathField);
        layout.putConstraint(SpringLayout.NORTH, browseButton,
                -3,
                SpringLayout.NORTH, pathField);

        // set springs for rest of window
        layout.putConstraint(SpringLayout.EAST, this,
                5,
                SpringLayout.EAST, browseButton);
        layout.putConstraint(SpringLayout.SOUTH, this,
                5,
                SpringLayout.SOUTH, browseButton);
*/
    }

    public void actionPerformed(ActionEvent e){
        // handle Browse action
        if(e.getSource() == browseButton){
            int returnVal = fc.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File selectedFile = fc.getSelectedFile();
                try {
                    pathField.setText(selectedFile.getCanonicalPath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.err.println("Opening: " + selectedFile.getName() + "." + newline);
            } else {
                System.err.println("Open command cancelled by user." + newline);
            }
        }
    }

    public File getFile() throws IOException{
        File file = new File(pathField.getText());
        // checks if the path is valid
        file.getCanonicalPath();
        return file;
    }
}
