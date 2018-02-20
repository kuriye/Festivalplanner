package Gui;

import agenda.Program;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;


// Voor uitleg over TableColumModel bekijk:
//http://www.java2s.com/Code/Java/Swing-JFC/TableColumnModelandTableColumnModelListener.htm

public class TablePanel extends JPanel{

    private Program program = new Program();

    public TablePanel()
    {
        setSize(800,800);
        try
        {
            program = program.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


        TableModel tm = new AbstractTableModel()
        {
            String headers[] = {"Podium", "Artiest", "Genre", "Begintijd", "Eindtijd", "Populariteit"};

            @Override
            public int getRowCount()
            {
                return program.getSize();
            }

            @Override
            public int getColumnCount()
            {
                return 6;
            }

            public String getColumnName(int col)
            {
                return headers[col];
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex)
            {
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
        };

        TableColumnModel cm = new DefaultTableColumnModel()
        {

            public void addColumn(TableColumnModel tc)
            {
                super.addColumn((TableColumn) tc);
            }
        };

        TableColumnModel rowHeaderModel = new DefaultTableColumnModel()
        {
          public void addColumn(TableColumn tc)
          {
                  super.addColumn(tc);
          }
        };

        JTable jt = new JTable(tm, cm);

        JTable headerColumn = new JTable(tm, rowHeaderModel);
        jt.createDefaultColumnsFromModel();
        headerColumn.createDefaultColumnsFromModel();


        jt.setSelectionModel(headerColumn.getSelectionModel());


        JViewport jv = new JViewport();
        jv.setView(headerColumn);
        jv.setPreferredSize(headerColumn.getMaximumSize());


        jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);



        JScrollPane jsp = new JScrollPane(jt);
        JPanel panel = new JPanel(new BorderLayout());
        jsp.setRowHeader(jv);
        jsp.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, headerColumn.getTableHeader());

        //Als ik er een frame van maak en de volgende methode uncommend werkt het dus wel:
       //getContentPane().add(jsp, BorderLayout.CENTER);

        panel.add(jsp, BorderLayout.CENTER);
        panel.setVisible(true);

    }
    // werkt ook alleen als classe extends JFrame
    public static void main(String args[])
    {
        TablePanel tp = new TablePanel();
        tp.setVisible(true);
    }

}