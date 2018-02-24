package Gui;

import agenda.Act;
import agenda.Artist;
import agenda.Stage;

public class TestPopUpFrame {
    public static void main(String[] args) {
        TestPopUpFrame testPopUpFrame = new TestPopUpFrame();
    }

    public TestPopUpFrame(){
        Act act = new Act(new Artist("Ronnie Flex", 67, "Rap"),new Stage("P1", 100,100,100),1000, 1200,78);
        PopUpFrame frame = new PopUpFrame(act);
    }
}
