package lapr.project.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Ana Rita on 12/12/2017
 */
public class InitializeUIElements {

    void initializeLabels(JLabel label, Font font, String text, int hAlignment, Color color){
        label.setFont(font);
        label.setText(text);
        label.setForeground(color);
        label.setHorizontalAlignment(hAlignment);
    }

    void initializeJButton(JButton button, Font font, String text, Color color, Border border){
        button.setForeground(color);
        button.setFont(font);
        button.setText(text);
        button.setBorder(border);
    }

    <E> void initializeJList(JList<E> list, ListSelectionModel selectionModel, Color background, Color foreground, Border border, Font font, String text){
        list.setBackground(background);
        list.setForeground(foreground);
        list.setBorder(border);
        list.setFont(font);
        list.setSelectionModel(selectionModel);
    }
}
