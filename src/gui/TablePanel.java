package gui;

import agenda.Artist;
import agenda.Program;
import agenda.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;
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
            JTextField artiestField = new JTextField(20);

            JLabel populariteit = new JLabel("Populariteit: ");
            JTextField populariteitField = new JTextField(20);

            JLabel genre = new JLabel("Genre: ");
            JTextField genreField = new JTextField(20);

            JLabel podium = new JLabel("Podium: ");
            JTextField podiumField = new JTextField(20);

            JLabel capaciteit = new JLabel("Capaciteit: ");
            JTextField capaciteitField = new JTextField(20);

            JLabel lengte = new JLabel("Lengte Podium: ");
            JTextField lengteField = new JTextField(20);

            JLabel breedte = new JLabel("Breedte Podium: ");
            JTextField breedteField = new JTextField(20);

            JLabel startTime = new JLabel("Start (militaire) Tijd: ");
            JTextField startTimeField = new JTextField(20);

            JLabel endTime = new JLabel("Eind (militaire) Tijd: ");
            JTextField endTimeField = new JTextField(20);

            JLabel populariteitPodium = new JLabel("Populariteit podium: ");
            JTextField populariteitPodiumField = new JTextField(20);

            JButton opslaan = new JButton("Opslaan");
            content.add(opslaan);
            opslaan.setBounds(220,300,100,30);

            JLabel[] labelList = {artiest,populariteit,genre,podium,capaciteit,lengte,breedte,startTime,endTime,populariteitPodium};
            JTextField[] textList = {artiestField,populariteitField,genreField,podiumField,capaciteitField,lengteField,breedteField,startTimeField,endTimeField,populariteitPodiumField};

            for(int i = 0; i < labelList.length; i++)
            {
                content.add(labelList[i]);
                labelList[i].setBounds(20,25*i,150,30);
                content.add(textList[i]);
                textList[i].setBounds(200,25*i,300,30);
            }

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
                        if(!exists)
                        {
                            Artist artist2 = new Artist(artiestField.getText(), populariteitInt, genreField.getText());
                            Stage stage = new Stage(podiumField.getText(), capaciteitInt, lengteInt, breedteInt);
                            program.addAct(artist2, stage, startTimeInt, endTimeInt, populariteitPodiumInt);
                            for(int i = 0; i < textList.length; i++)
                            {
                                textList[i].setText("");
                            }
                        }
                    }
                    catch(NumberFormatException exc) {
                        exc.printStackTrace();
                        JLabel errorLabel = new JLabel("Error: Invalid input given, please enter valid input.");
                        content.add(errorLabel);
                        errorLabel.setBounds(20, 250, 400 , 30);
                        errorLabel.setForeground(Color.RED);
                    }

                    Collections.sort(program.getAllActs());

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