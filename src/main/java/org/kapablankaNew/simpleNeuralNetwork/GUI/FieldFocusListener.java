package org.kapablankaNew.simpleNeuralNetwork.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FieldFocusListener implements FocusListener {
    private JFrame frame;

    public FieldFocusListener(JFrame frame){
        this.frame = frame;
    }
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent() instanceof JTextField){
            JTextField source = (JTextField)e.getComponent();
            if (source.getText().equals(source.getName())){
                source.setText("");
                source.setFont(new Font("Verdana", Font.PLAIN, 12));
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Attention! Error!");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getComponent() instanceof JTextField){
            JTextField source = (JTextField)e.getComponent();
            if (source.getText().equals("")) {
                source.setFont(new Font("Verdana", Font.ITALIC, 10));
                if(source.getName() != null){
                    source.setText(source.getName());
                } else {
                    JOptionPane.showMessageDialog(frame, "Attention! Error!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Attention! Error!");
        }
    }
}
