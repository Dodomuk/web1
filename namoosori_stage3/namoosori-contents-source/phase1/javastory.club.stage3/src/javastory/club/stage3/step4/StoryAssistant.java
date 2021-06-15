package javastory.club.stage3.step4;

import javastory.club.stage3.step4.ui.menu.MainMenu;

public class StoryAssistant {

    public static void main(String[] args) {

        StoryAssistant storyAssistant = new StoryAssistant();
        storyAssistant.Start();
    }

    public void Start(){

        MainMenu mainMenu = new MainMenu();
        mainMenu.show();

    }

}
