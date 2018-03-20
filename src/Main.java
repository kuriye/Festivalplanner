import agenda.Program;
import visitorInformation.RandomNameGenerator;
import visitorInformation.VisitorNameLoader;

public class Main {

    public static void main(String[] args)
    {
        Program program = new Program();
        RandomNameGenerator generator = new RandomNameGenerator();
        generator.nameGenerator();

    }


}
