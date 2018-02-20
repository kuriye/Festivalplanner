package agenda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestDataAgenda
{

    public TestDataAgenda()
    {

    }

    public static void main(String[] args) throws IOException
    {

        Program program = new Program();
        Artist artist1 = new Artist("ALIB", 30, "Rap");
        Artist artist2 = new Artist("Guus Meeuws", 70, "Nederlands");
        Stage stage = new Stage("P1", 1000, 50, 50);
        program.addAct(artist2, stage, 2000, 2100,50);

       // program.save(program);

        System.out.println(program.load());
    }
}
