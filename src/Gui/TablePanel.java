package Gui;

import javax.swing.*;
import agenda.Program;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
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

        JTable tablePanel = new JTable(new Table());
        panel.add(tablePanel);
        tablePanel.setRowHeight(50);
        tablePanel.setPreferredSize(new Dimension(800, 800));



        frame.setSize(800, 800);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        TablePanel tablePanel = new TablePanel();
    }

    private class Table extends AbstractTableModel {
        public int getRowCount()
        {
            return 1;
        }

        public int getColumnCount()
        {
            return 5;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {

            program.getActs(0);
            System.out.println(program.getActs(rowIndex).getStage().getName());
            switch (columnIndex)
            {
                case 0: program.getActs(0).getStage();
            }

            return "";
        }
    }
}



