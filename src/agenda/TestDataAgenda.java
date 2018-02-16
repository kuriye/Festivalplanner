package agenda;

public class TestDataAgenda
{
    public TestDataAgenda()
    {
        Program program = new Program();
        Artist artist1 = new Artist("ALIB", 30, "Rap");
        Artist artist2 = new Artist("Guus Meeuws", 70, "Nederlands");
        Stage stage = new Stage("P1", 1000, 50, 50);
        program.addAct(artist1, stage, 2000, 2100,50);
        System.out.println(program.toString());
    }

    public static void main(String[] args)
    {
        TestDataAgenda testDataAgenda = new TestDataAgenda();
    }
}
