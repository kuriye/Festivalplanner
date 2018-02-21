package Gui;

import agenda.Artist;
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
        panel.setSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),860));
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

            JLabel artiest = new JLabel("Artiest: ");
            content.add(artiest);
            JTextField artiestField = new JTextField(20);
            content.add(artiestField);

            JLabel populariteit = new JLabel("Populariteit: ");
            content.add(populariteit);
            JTextField populariteitField = new JTextField(20);
            content.add(populariteitField);

            JLabel genre = new JLabel("Genre: ");
            content.add(genre);
            JTextField genreField = new JTextField(20);
            content.add(genreField);

            JLabel podium = new JLabel("Podium");
            content.add(podium);
            JTextField podiumField = new JTextField(20);
            content.add(podiumField);

            JLabel capaciteit = new JLabel("Capaciteit");
            content.add(capaciteit);
            JTextField capaciteitField = new JTextField(20);
            content.add(capaciteitField);

            JLabel lengte = new JLabel("lengte");
            content.add(lengte);
            JTextField lengteField = new JTextField(20);
            content.add(lengteField);

            JLabel breedte = new JLabel("breedte");
            content.add(breedte);
            JTextField breedteField = new JTextField(20);
            content.add(breedteField);


            JLabel startTime = new JLabel("Start Tijd");
            content.add(startTime);
            JTextField startTimeField = new JTextField(20);
            content.add(startTimeField);

            JLabel endTime = new JLabel("Eind Tijd");
            content.add(endTime);
            JTextField endTimeField = new JTextField(20);
            content.add(endTimeField);

            JLabel populariteitPodium = new JLabel("Populariteit podium");
            content.add(populariteitPodium);
            JTextField populariteitPodiumField = new JTextField(20);
            content.add(populariteitPodiumField);

            JButton opslaan = new JButton("Opslaan");
            content.add(opslaan);


            artiest.setBounds(20, 0 , 150 , 30);
            artiestField.setBounds(200, 0,300,30);
            populariteit.setBounds(20,25,150,30);
            populariteitField.setBounds(200, 25, 300,30);
            genre.setBounds(20, 50,150,30);
            genreField.setBounds(200,50,300,30);
            podium.setBounds(20, 75,150,30);
            capaciteit.setBounds(20,100,150,30);
            lengte.setBounds(20,125,150,30);
            breedte.setBounds(20,150,150,30);
            startTime.setBounds(20,175,150,30);
            endTime.setBounds(20,200,150,30);
            populariteitPodium.setBounds(20,225,150,30);

            podiumField.setBounds(200,75,300,30);
            capaciteitField.setBounds(200,100,300,30);
            lengteField.setBounds(200,125,300,30);
            breedteField.setBounds(200,150,300,30);
            startTimeField.setBounds(200,175,300,30);
            endTimeField.setBounds(200,200,300,30);
            populariteitPodiumField.setBounds(200,225,300,30);

            opslaan.setBounds(220,300,100,30);

            opslaan.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                   // program.addAct();
                }
            });


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