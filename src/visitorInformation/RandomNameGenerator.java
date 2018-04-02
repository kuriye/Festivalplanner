package visitorInformation;

public class RandomNameGenerator {
    VisitorNameLoader nameLoader;
    public RandomNameGenerator()
    {
        nameLoader = new VisitorNameLoader();
    }

    public String nameGenerator()
    {
        int currentVoornaamNumber =(int) (Math.random() * nameLoader.getVoornamen().size());
        int currentAchternaamNumber = (int) (Math.random() * nameLoader.getAchternamen().size());

        String currentVoornaam = nameLoader.getVoornamen().get(currentVoornaamNumber);
        String currentAchternaam = nameLoader.getAchternamen().get(currentAchternaamNumber);

        return currentVoornaam + " " + currentAchternaam;
    }

}
