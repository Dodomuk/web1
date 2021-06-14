package javastory.club.stage3.step3.ui.menu;

import javastory.club.stage3.step3.ui.console.PostingConsole;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

import java.util.Scanner;

public class PostingMenu {

    private PostingConsole postingConsole;

    private Scanner sc;
    private Narrator narrator;

    public PostingMenu() {
        this.sc = new Scanner(System.in);
        this.narrator = new Narrator(this, TalkingAt.Left);

    }

    public void show() {

        int inputNum = 0;

        while (true) {
            displayMenu();
            inputNum = selectMenu();

            switch (inputNum) {

                case 1:
                    postingConsole.findBoard();
                    break;
                case 2:
                    postingConsole.register();
                    break;
                case 3:
                    postingConsole.findByBoardId();
                    break;
                case 4:
                    postingConsole.find();
                    break;
                case 5:
                    postingConsole.modify();
                    break;
                case 6:
                    postingConsole.remove();
                    break;
                case 0:
                    return;

                default:
                    narrator.sayln("Choose again!");
            }
        }
    }

    private void displayMenu() {

        narrator.sayln("");
        narrator.sayln("..............................");
        narrator.sayln(" Posting menu ");
        narrator.sayln("..............................");
        narrator.sayln(" 1. Find a board");
        narrator.sayln(" 2. Register a posting");
        narrator.sayln(" 3. Find postings in the board");
        narrator.sayln(" 4. Find a posting");
        narrator.sayln(" 5. Modify a posting");
        narrator.sayln(" 6. Remove a posting");
        narrator.sayln("..............................");
        narrator.sayln(" 0. Previous");
        narrator.sayln("..............................");
    }

    private int selectMenu() {

        System.out.print("Select: ");
        int menuNumber = sc.nextInt();

        if (menuNumber >= 0 && menuNumber <= 6) {
            sc.nextLine();
            return menuNumber;
        } else {
            narrator.sayln("It's a invalid number --> " + menuNumber);
            return -1;
        }
    }
}



