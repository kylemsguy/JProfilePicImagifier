package com.kylemsguy;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by kyle on 11/08/15.
 * ¯\_(ツ)_/¯
 */
public class RGBFrame extends JPanel {

    private JTextField fieldRed;
    private JTextField fieldGreen;
    private JTextField fieldBlue;

    public RGBFrame() {
        super(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        Border border = BorderFactory.createTitledBorder("Background Colour");
        panel.setBorder(border);

        JPanel frameRed = new JPanel(new BorderLayout());
        JPanel frameGreen = new JPanel(new BorderLayout());
        JPanel frameBlue = new JPanel(new BorderLayout());

        JLabel labelRed = new JLabel("R:");
        JLabel labelGreen = new JLabel("G:");
        JLabel labelBlue = new JLabel("B:");

        labelRed.setHorizontalAlignment(JTextField.CENTER);
        labelGreen.setHorizontalAlignment(JTextField.CENTER);
        labelBlue.setHorizontalAlignment(JTextField.CENTER);

        fieldRed = new JTextField("255");
        fieldGreen = new JTextField("255");
        fieldBlue = new JTextField("255");

        frameRed.add(labelRed, BorderLayout.LINE_START);
        frameRed.add(fieldRed);

        frameGreen.add(labelGreen, BorderLayout.LINE_START);
        frameGreen.add(fieldGreen);

        frameBlue.add(labelBlue, BorderLayout.LINE_START);
        frameBlue.add(fieldBlue);

        panel.add(frameRed, BorderLayout.LINE_START);
        panel.add(frameGreen);
        panel.add(frameBlue, BorderLayout.LINE_END);

        // add items to layout
        add(panel);
    }

    public int[] getRGB() throws IllegalStateException {
        int red = Integer.parseInt(fieldRed.getText());
        int green = Integer.parseInt(fieldGreen.getText());
        int blue = Integer.parseInt(fieldBlue.getText());

        if(red > 255 || red < 0)
            throw new IllegalStateException("Red is out of bounds (0 <= R <= 255");
        else if(green > 255 || green < 0)
            throw new IllegalStateException("Green is out of bounds (0 <= G <= 255");
        else if(blue > 255 || green < 0)
            throw new IllegalStateException("Blue is out of bounds (0 <= B <= 255");
        else{
            return new int[]{red, green, blue};
        }
    }
}
