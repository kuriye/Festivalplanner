package agenda;

import java.io.IOException;

public class TestDataAgenda
{

    public static void main(String[] args) throws IOException
    {

        Program program = new Program();
        Artist artist1 = new Artist("ALIB", 30, "Rap");
        Artist artist2 = new Artist("Guus Meeuwis", 70, "Nederlands");
        Stage stage = new Stage("P1", 1000, 50, 50);
        Stage stage1 = new Stage("P2", 1000,50,50);
        program.addAct(artist2, stage, 2000, 2100,50);
        program.addAct(artist1,stage1,2200,2300,100);
        program.addAct(artist1,stage,1000,1100,20);
        //program.addAct(artist2,stage1,1200,1300,30);
        //program.addAct(artist2,stage,2222,2444,30);

        program.save(program);

       //System.out.println(program.load());
    }
}
