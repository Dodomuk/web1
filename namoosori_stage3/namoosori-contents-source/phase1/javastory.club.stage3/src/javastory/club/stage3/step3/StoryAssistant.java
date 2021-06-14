package javastory.club.stage3.step3;

import javastory.club.stage3.step3.ui.menu.MainMenu;

public class StoryAssistant {

    public static void main(String[] args) {

        StoryAssistant storyAssistant = new StoryAssistant();
        storyAssistant.start();

    }
    public void start(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }

}
