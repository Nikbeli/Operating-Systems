import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.lang.String;

public class StartDialog extends JFrame {
    public StartDialog() {
        super("Файловая система");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());

        final JLabel label = new JLabel("Задайте размер диска");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new java.awt.Font("Arial", 1, 18));

        JFormattedTextField memory = new JFormattedTextField(50);
        memory.setSize(100,20);
        memory.setAlignmentX(CENTER_ALIGNMENT);

        JButton start = new JButton("Запуск");
        start.setAlignmentX(CENTER_ALIGNMENT);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                // Schedule a job for the event-dispatching thread:
                // creating and showing this application's GUI.
                int RAM = (int)memory.getValue();
                if (RAM > 560)
                {
                    RAM = 560;
                }
                Disk disk = new Disk(RAM);
                DynamicTreeDemo lab4 = new DynamicTreeDemo(disk);
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        lab4.createAndShowGUI(disk);
                    }
                });
            }
        });

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(30, 10)));
        panel.add(memory);
        panel.add(Box.createRigidArea(new Dimension(30, 10)));
        panel.add(start);
        getContentPane().add(panel);

        setPreferredSize(new Dimension(300, 150));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}