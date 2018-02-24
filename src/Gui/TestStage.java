package Gui;

import agenda.Stage;

import java.util.HashSet;

public class TestStage {
    public static void main(String[] args) {
        TestStage testStage = new TestStage();
    }

    public TestStage(){
        Stage stage1 = new Stage("bennie", 100,100,100);
        Stage stage2 = new Stage("bennie", 100,100,100);
        System.out.println(stage1.equals(stage2));
        HashSet<Stage> stSet = new HashSet<>();
        stSet.add(stage1);
        stSet.add(stage2);
        System.out.println(stSet.size());
    }
}
