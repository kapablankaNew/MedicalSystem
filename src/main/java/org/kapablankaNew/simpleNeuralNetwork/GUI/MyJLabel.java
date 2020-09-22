package org.kapablankaNew.simpleNeuralNetwork.GUI;

import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel {

    public MyJLabel(int width, int height, String text){
        super(text);
        this.setSize(100, 20);
        this.setFont(new Font("Verdana", Font.BOLD, 12));
    }
}
