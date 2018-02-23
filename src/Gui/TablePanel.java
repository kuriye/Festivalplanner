package Gui;

import agenda.Artist;
import agenda.Program;
import agenda.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TablePanel extends JTable{

    private Program program = new Program();
    private JButton verwijderen;
    private JButton toevoegen;
    TableModel model;
    JTable jTable;


    /**
     * Load Json file with Program object and make TablePanel
     */
    public TablePanel()
    {
        try
        {
            program = program.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        makeTablePanel();

        toevoegen.addActionListener(e -> toevoegenButton());
        verwijderen.addActionListener(e -> verwijderenButton());
    }

    /**
     * The method paints the JTable with 2 buttons
     */
    public void makeTablePanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPannel  = new JPanel(new FlowLayout());
        toevoegen = new JButton("Toevoegen");
        verwijderen = new JButton("Verwijderen");

        buttonPannel.add(toevoegen);
        buttonPannel.add(verwijderen);
        jTable = new JTable(model = new TableModel());
        panel.add(new JScrollPane(jTable), BorderLayout.CENTER);
        panel.add(buttonPannel, BorderLayout.SOUTH);
        panel.setSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),860));

        this.add(panel);
    }

    /**
     * This method will show a Pop Up window when "toevoegen" button is pressed.
     */

    public void toevoegenButton()
    {
        PopUpWindow popUpWindow = new PopUpWindow();
    }

    /**
     * This method removes a selected row in the JTable and in the Json file.
     */

    public void verwijderenButton()
    {
        program.removeAct(jTable.getSelectedRow());

        try
        {
            program.save(program);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        model.fireTableDataChanged();
    }

    /**
     * This class makes the PopUp window where you can add a Program object.
     */
    private class PopUpWindow extends JFrame
    {


        private PopUpWindow()
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

            JLabel podium = new JLabel("Podium: ");
            content.add(podium);
            JTextField podiumField = new JTextField(20);
            content.add(podiumField);

            JLabel capaciteit = new JLabel("Capaciteit: ");
            content.add(capaciteit);
            JTextField capaciteitField = new JTextField(20);
            content.add(capaciteitField);

            JLabel lengte = new JLabel("Lengte Podium: ");
            content.add(lengte);
            JTextField lengteField = new JTextField(20);
            content.add(lengteField);

            JLabel breedte = new JLabel("Breedte Podium: ");
            content.add(breedte);
            JTextField breedteField = new JTextField(20);
            content.add(breedteField);

            JLabel startTime = new JLabel("Start (militaire) Tijd: ");
            content.add(startTime);
            JTextField startTimeField = new JTextField(20);
            content.add(startTimeField);

            JLabel endTime = new JLabel("Eind (militaire) Tijd: ");
            content.add(endTime);
            JTextField endTimeField = new JTextField(20);
            content.add(endTimeField);

            JLabel populariteitPodium = new JLabel("Populariteit podium: ");
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

            opslaan.addActionListener(e ->
            {
                {
                    boolean exists = false;

                    try {
                        int populariteitInt = Integer.parseInt(populariteitField.getText());
                        int capaciteitInt = Integer.parseInt(capaciteitField.getText());
                        int lengteInt = Integer.parseInt(lengteField.getText());
                        int breedteInt = Integer.parseInt(breedteField.getText());
                        int startTimeInt = Integer.parseInt(startTimeField.getText());
                        int endTimeInt = Integer.parseInt(endTimeField.getText());
                        int populariteitPodiumInt = Integer.parseInt(populariteitPodiumField.getText());

                        for (int i = 0; i < program.takeGrootte(); i++)
                        {
                            if (program.getActs(i).getStage().getName().equals(podiumField.getText()) &&  program.getActs(i).getStartTime() == startTimeInt )
                            {
                                exists = true;
                                JOptionPane.showMessageDialog(null, "Verander de starttijd of het podium. Een podium kan niet op hetzelfde moment meerdere keren gebruikt worden");
                                break;
                            }
                        }
                        if(exists == false)
                        {
                            Artist artist2 = new Artist(artiestField.getText(), populariteitInt, genreField.getText());
                            Stage stage = new Stage(podiumField.getText(), capaciteitInt, lengteInt, breedteInt);
                            program.addAct(artist2, stage, startTimeInt, endTimeInt, populariteitPodiumInt);
                            artiestField.setText("");
                            populariteitField.setText("");
                            genreField.setText("");
                            podiumField.setText("");
                            capaciteitField.setText("");
                            lengteField.setText("");
                            breedteField.setText("");
                            startTimeField.setText("");
                            endTimeField.setText("");
                            populariteitPodiumField.setText("");

                        }
                    }
                    catch(NumberFormatException exc) {
                        exc.printStackTrace();
                        JLabel errorLabel = new JLabel("Error: Invalid input given, please enter valid input.");
                        content.add(errorLabel);
                        errorLabel.setBounds(20, 250, 400 , 30);
                        errorLabel.setForeground(Color.RED);
                    }

                    try
                    {
                        program.save(program);
                    } catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }

                    try
                    {
                        program.load();
                    } catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }

                    model.fireTableDataChanged();
                }
            });
            setVisible(true);
            setSize(550,400);
            setLocationRelativeTo(null);

        }
    }


    class TableModel extends AbstractTableModel
    {
        String headers[] = {"Podium", "Artiest", "Genre", "Begintijd", "Eindtijd", "Populariteit"};

        @Override
        public int getRowCount()
        {
            return program.takeGrootte();
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