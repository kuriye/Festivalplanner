package visitorInformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VisitorNameLoader {
    ArrayList<String> voornamen = new ArrayList<>();
    ArrayList<String> achternamen = new ArrayList<>();
    Scanner scanner;
    int currentName = -1;

    public VisitorNameLoader() {
        try {
            scanner = new Scanner(new File("resources//RandomNameGenerator.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        LoadNames();
    }

    public ArrayList<String> getVoornamen() { return voornamen;}
    public ArrayList<String> getAchternamen() { return achternamen;}

    public void LoadNames() {
        while(scanner.hasNextLine())
        {
            String currentLine = scanner.nextLine();
            if(currentLine.equals("#Voornamen#")) {
                currentName = 0;
                currentLine = scanner.nextLine();

            }
            if(currentLine.equals("#Achternamen#")) {
                currentName = 1;
                currentLine = scanner.nextLine();
            }

            if(currentName == -1)
            {
                return;
            }
            else if(currentName == 0 && !currentLine.equals(""))
            {
                voornamen.add(currentLine);
            }
            else if(currentName == 1 && !currentLine.equals(""))
            {
                achternamen.add(currentLine);
            }

        }
    }
}
