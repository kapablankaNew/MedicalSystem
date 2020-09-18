package org.kapablankaNew.simpleNeuralNetwork.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxSkinsListener implements ActionListener {
    private final JFrame frame;

    public ComboBoxSkinsListener(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComboBox){
            JComboBox<?> source;
            source = (JComboBox<?>)e.getSource();
            String className = "com.jtattoo.plaf." +
                    source.getSelectedItem().toString().toLowerCase() +"." +
                    source.getSelectedItem() + "LookAndFeel";
            try {
                UIManager.setLookAndFeel(className);
                SwingUtilities.updateComponentTreeUI(frame);
            } catch (ClassNotFoundException |
                    IllegalAccessException |
                    UnsupportedLookAndFeelException |
                    InstantiationException exception) {
                exception.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Attention! Error!");
        }
    }
}
