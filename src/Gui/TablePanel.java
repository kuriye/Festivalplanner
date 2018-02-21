package Gui;

import agenda.Program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



// Voor uitleg over TableColumModel bekijk:
//http://www.java2s.com/Code/Java/Swing-JFC/TableColumnModelandTableColumnModelListener.htm

public class TablePanel extends JTable{

    private Program program = new Program();

    public TablePanel()
    {
        try
        {
            program = program.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPannel = new JPanel(new FlowLayout());
        JButton toevoegen = new JButton("Toevoegen");
        JButton verwijderen = new JButton("Verwijderen");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");

        buttonPannel.add(toevoegen);
        buttonPannel.add(verwijderen);
        buttonPannel.add(load);
        buttonPannel.add(save);

        panel.add(new JScrollPane(new JTable(new TableModel())), BorderLayout.CENTER);
        panel.add(buttonPannel, BorderLayout.SOUTH);
        panel.setSize(1900,860);
        this.add(panel);

        toevoegen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PopUpWindow popUpWindow = new PopUpWindow();

            }
        });
    }

    class PopUpWindow extends JFrame
    {

        public PopUpWindow()
        {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel content = new JPanel(null);
            setContentPane(content);

            JLabel lblNaam = new JLabel("Artiest: ");
            content.add(lblNaam);
            JTextField naam = new JTextField(20);
            content.add(naam);

            JLabel lblEmail = new JLabel("Podium: ");
            content.add(lblEmail);
            JTextField email = new JTextField(20);
            content.add(email);

            JLabel lblTelefoonnummer = new JLabel("Genre: ");
            content.add(lblTelefoonnummer);
            JTextField telefoonnummer = new JTextField(20);
            content.add(telefoonnummer);

            JButton opslaan = new JButton("Opslaan");
            content.add(opslaan);


            lblNaam.setBounds(20, 0 , 150 , 30);
            naam.setBounds(200, 0,300,30);
            lblEmail.setBounds(20,25,150,30);
            email.setBounds(200, 25, 300,30);
            lblTelefoonnummer.setBounds(20, 50,150,30);
            telefoonnummer.setBounds(200,50,300,30);
            opslaan.setBounds(220,100,100,30);

            setVisible(true);
            setSize(600,600);


        }
    }


    class TableModel extends AbstractTableModel
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
    }
}