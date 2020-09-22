package org.kapablankaNew.simpleNeuralNetwork.GUI;

import javax.swing.*;
import java.awt.*;

public class MyJTextField extends JTextField {
    private JFrame mainFrame;

    public MyJTextField(int width, int height, String textDefault, JFrame mainFrame){
        super();
        this.mainFrame = mainFrame;
        this.setText(textDefault);
        this.setName(textDefault);
        this.setSize(width, height);
        this.setFont(new Font("Verdana", Font.ITALIC, 10));
        this.addFocusListener(new FieldFocusListener(mainFrame));
    }
}
