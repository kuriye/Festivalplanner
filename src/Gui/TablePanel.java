package Gui;

import javax.swing.*;



import agenda.Program;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class TablePanel extends JPanel {

    private Program program = new Program();

    public TablePanel() {
        JFrame frame = new JFrame("test");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            program = program.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JTable tablePanel = new JTable(program.getSize(), 10);
        panel.add(tablePanel);
        DefaultTableModel model = new DefaultTableModel();
        Object[] row = {"Tijd", "Artiesten"};
        model.addRow(row);
        tablePanel.setModel(model);

        frame.setSize(800, 800);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TablePanel tablePanel = new TablePanel();
    }
}



