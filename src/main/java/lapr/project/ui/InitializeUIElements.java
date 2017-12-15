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

    void initializeJButton(JButton button, Font font, String text, Color color, BorderFactory border){
        button.setBackground(color);
        button.setFont(font);
        button.setText(text);
        button.setBorder((Border) border);
    }

    void initializeJList(JList list, ListSelectionModel selectionModel, Color background, Color foreground, BorderFactory border, Font font, String text){
        list.setBackground(background);
        list.setForeground(foreground);
        list.setBorder((Border)border);
        list.setFont(font);
        list.setSelectionModel(selectionModel);
    }
}
