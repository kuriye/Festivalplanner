package gui;

import agenda.Artist;
import agenda.Program;
import agenda.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TablePanel extends JTable{

    private Program program = new Program();
    private JButton verwijderen;
    private JButton bewerken;
    private JButton toevoegen;
    private TableModel model;
    private JTable jTable;


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
        bewerken.addActionListener(e -> bewerkenButton());
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
        bewerken = new JButton("Bewerken");
        verwijderen = new JButton("Verwijderen");

        buttonPannel.add(toevoegen);
        buttonPannel.add(bewerken);
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

    public void bewerkenButton(){ PopupEditWindow popupEditWindow = new PopupEditWindow(); }

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

            JLabel populariteit = new JLabel("Populariteit: % ");
            JTextField populariteitField = new JTextField(20);

            JLabel genre = new JLabel("Genre: ");
            JTextField genreField = new JTextField(20);

            JLabel podium = new JLabel("Podium: ");
            JTextField podiumField = new JTextField(20);

            JLabel capaciteit = new JLabel("Capaciteit podium: ");
            JTextField capaciteitField = new JTextField(20);

            JLabel lengte = new JLabel("Lengte Podium: (m) ");
            JTextField lengteField = new JTextField(20);

            JLabel breedte = new JLabel("Breedte Podium: (m) ");
            JTextField breedteField = new JTextField(20);

            JLabel startTime = new JLabel("Start (militaire) Tijd: ");
            JTextField startTimeField = new JTextField(20);

            JLabel endTime = new JLabel("Eind (militaire) Tijd: ");
            JTextField endTimeField = new JTextField(20);

            JLabel populariteitPodium = new JLabel("Populariteit podium: % ");
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
                            for (JTextField aTextList : textList) {
                                aTextList.setText("");
                            }
                        }
                    }
                    catch(NumberFormatException exc) {
                        String part[] = exc.getMessage().split(": ");
                        JLabel errorLabel = new JLabel("Error: Verkeerde invoer type bij: " + part[1]);
                        content.add(errorLabel);
                        errorLabel.setBounds(20, 250, 700 , 30);
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

    private class PopupEditWindow extends JFrame
    {
        private PopupEditWindow()
        {
            String[] names = new String[program.getAllActs().size()];
            for(int i = 0; i < program.getAllActs().size(); i++){
                names[i] = (program.getActs(i).getArtist().getName());
            }

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel content = new JPanel(null);
            setContentPane(content);

            JComboBox<String> optredens = new JComboBox<>(names);

            JLabel artiest = new JLabel("Artiest: ");
            JTextField artiestField = new JTextField(program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getArtist().getName(),20);

            JLabel populariteit = new JLabel("Populariteit (%): ");
            JTextField populariteitField = new JTextField("" + program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getPopularity(),20);

            JLabel genre = new JLabel("Genre: ");
            JTextField genreField = new JTextField(program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getArtist().getGenre(),20);

            JLabel podium = new JLabel("Podium: ");
            JTextField podiumField = new JTextField(program.getAllActs().get
                    (program.getAllActs().indexOf(program.searchByName(
                            (String) optredens.getSelectedItem()))).getStage().getName(),20);

            JLabel capaciteit = new JLabel("Capaciteit podium: ");
            JTextField capaciteitField = new JTextField("" + program.getAllActs().get
                    (program.getAllActs().indexOf(program.searchByName(
                            (String) optredens.getSelectedItem()))).getStage().getCapacity(),20);

            JLabel lengte = new JLabel("Lengte Podium (meters): ");
            JTextField lengteField = new JTextField("" + program.getAllActs().get(program.getAllActs().indexOf
                    (program.searchByName((String) optredens.getSelectedItem()))).getStage().getLength(), 20);

            JLabel breedte = new JLabel("Breedte Podium (meters): ");
            JTextField breedteField = new JTextField( "" + program.getAllActs().get(program.getAllActs().indexOf
                    (program.searchByName((String) optredens.getSelectedItem()))).getStage().getWidth(),20);

            JLabel startTime = new JLabel("Start (militaire) Tijd: ");
            JTextField startTimeField = new JTextField("" + program.getAllActs().get(program.getAllActs().indexOf
                    (program.searchByName((String) optredens.getSelectedItem()))).getStartTime(),20);

            JLabel endTime = new JLabel("Eind (militaire) Tijd: ");
            JTextField endTimeField = new JTextField("" + program.getAllActs().get(program.getAllActs().indexOf
                    (program.searchByName((String) optredens.getSelectedItem()))).getEndTime(),20);

            JLabel populariteitPodium = new JLabel("Populariteit podium (%): ");
            JTextField populariteitPodiumField = new JTextField("" + program.getAllActs().get(program.getAllActs().
                    indexOf(program.searchByName((String) optredens.getSelectedItem()))).getPopularity(),20);

            JButton bewerk = new JButton("Bewerken");
            content.add(bewerk);
            bewerk.setBounds(220,300,100,30);

            JLabel[] labelList =
                    {artiest,populariteit,genre,podium,capaciteit,lengte,breedte,startTime,endTime,populariteitPodium};
            JTextField[] textList = {artiestField,populariteitField,genreField,podiumField,capaciteitField,lengteField,
                    breedteField,startTimeField,endTimeField,populariteitPodiumField};

            content.add(optredens);
            optredens.setBounds(200,0,150,30);
            for(int i = 0; i < labelList.length; i++)
            {
                content.add(labelList[i]);
                labelList[i].setBounds(20,25*i + 30,150,30);
                content.add(textList[i]);
                textList[i].setBounds(200,25*i + 30,300,30);
            }

            optredens.addActionListener((ActionEvent e) ->{
                    artiestField.setText(program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getArtist().getName());
                    populariteitField.setText("" + program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getPopularity());
                    genreField.setText(program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getArtist().getGenre());
                    podiumField.setText(program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getStage().getName());
                    capaciteitField.setText("" + program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getStage().getCapacity());
                    lengteField.setText("" + program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getStage().getLength());
                    breedteField.setText("" + program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getStage().getWidth());
                    startTimeField.setText("" + program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getStartTime());
                    endTimeField.setText("" + program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getEndTime());
                    populariteitPodiumField.setText("" + program.getAllActs().get(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem()))).getPopularity());
                    });
            bewerk.addActionListener(e ->
            {
                {
                    //System.out.println((String) optredens.getSelectedItem());
                    //System.out.println((program.searchByName((String) optredens.getSelectedItem())));


                    boolean exists = false;
                    try {
                        program.removeAct(program.getAllActs().indexOf(program.searchByName((String) optredens.getSelectedItem())));
                        int populariteitInt = Integer.parseInt(populariteitField.getText());
                        int capaciteitInt = Integer.parseInt(capaciteitField.getText());
                        int lengteInt = Integer.parseInt(lengteField.getText());
                        int breedteInt = Integer.parseInt(breedteField.getText());
                        int startTimeInt = Integer.parseInt(startTimeField.getText());
                        int endTimeInt = Integer.parseInt(endTimeField.getText());
                        int populariteitPodiumInt = Integer.parseInt(populariteitPodiumField.getText());

                        if(!exists)
                        {
                            Artist artist2 = new Artist(artiestField.getText(), populariteitInt, genreField.getText());
                            Stage stage = new Stage(podiumField.getText(), capaciteitInt, lengteInt, breedteInt);
                            program.addAct(artist2, stage, startTimeInt, endTimeInt, populariteitPodiumInt);
                            for (JTextField aTextList : textList) {
                                aTextList.setText("");
                            }
                        }
                    }
                    catch(NumberFormatException exc) {
                        String part[] = exc.getMessage().split(": ");
                        JLabel errorLabel = new JLabel("Error: Verkeerde invoer type bij: " + part[1]);
                        content.add(errorLabel);
                        errorLabel.setBounds(20, 250, 700 , 30);
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