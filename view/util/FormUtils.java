package view.util;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

public class FormUtils
{
    public static void clearTextFields(JFrame frame,JPanel panel)
    {
        Container container = frame.getContentPane();
        Component components [] = panel.getComponents();
        
        for (Component c : components)
        {
            if (c instanceof JTextField)
            {
                ((JTextField) c).setText("");
            }
            else if (c instanceof JComboBox)
            {
                ((JComboBox) c).setSelectedIndex(0);
            }    
            else if (c instanceof JRadioButton)
            {
                if (((JRadioButton) c).getText().equals("Feminino"))
                {
                ((JRadioButton) c).setSelected(true);
                }
                else if (((JRadioButton) c).getText().equals("Masculino"))
                {
                ((JRadioButton) c).setSelected(false);
                }
            }    
        }                            
    }

    public static void centerForm(JFrame frame) 
    { 
        Dimension windowSize = frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        
        frame.setLocation(dx, dy);       
    }    
}