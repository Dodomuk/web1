package javastory.club.stage3.step2.ui.menu;

import javastory.club.stage3.step2.ui.console.BoardConsole;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

import java.util.Scanner;

public class BoardMenu {

    private BoardConsole boardConsole;
    private PostingMenu postingMenu;

    private Scanner sc;
    private Narrator narrator;

    public BoardMenu() {
        this.boardConsole = new BoardConsole();
        this.postingMenu = new PostingMenu();
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
                    boardConsole.register();
                    break;
                case 2:
                    boardConsole.findByName();
                    break;
                case 3:
                    boardConsole.modify();
                    break;
                case 4:
                    boardConsole.remove();
                    break;
                case 5:
                    postingMenu.show();
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
        narrator.sayln(" Board menu ");
        narrator.sayln("..............................");
        narrator.sayln(" 1. Register a board");
        narrator.sayln(" 2. Find boards by name");
        narrator.sayln(" 3. Modify a board");
        narrator.sayln(" 4. Remove a board");
        narrator.sayln("..............................");
        narrator.sayln(" 5. Posting Menu");
        narrator.sayln("..............................");
        narrator.sayln(" 0. Previous");
        narrator.sayln("..............................");
    }

    private int selectMenu() {
        System.out.print("번호 입력 : ");
        int inputNum = sc.nextInt();

        if (inputNum >= 0 && inputNum <= 5) {
            sc.nextLine();
            return inputNum;
        }
        narrator.sayln("잘못 입력하셨습니다.");
        return -1;

    }
}
