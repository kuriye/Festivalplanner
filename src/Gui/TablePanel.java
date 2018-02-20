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
        tablePanel.setPreferredSize(new Dimension(750, 750));

        DefaultTableModel model = new DefaultTableModel();
        


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
            return 2;
        }

        public int getColumnCount()
        {
            return 6;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {


            switch (columnIndex)
            {
                case 0: return program.getActs(rowIndex).getStage().getName();
                case 1: return program.getActs(rowIndex).getArtist().getName();
                case 2: return program.getActs(rowIndex).getArtist().getGenre();
                case 3: return program.getActs(rowIndex).getStartTime();
                case 4: return program.getActs(rowIndex).getEndTime();
                case 5: return program.getActs(rowIndex).getPopularity();
            }

            return "";
        }

        @Override
        public String getColumnName(int column) {

            switch (column)
            {
                case 0: return "Podium";
                case 1: return "Artiest";
                case 2: return "Genre";
                case 3: return "Begintijd";
                case 4: return "Eindtijd";
                case 5: return "Populariteit Act";
            }

            return ""  ;
        }
    }
}



