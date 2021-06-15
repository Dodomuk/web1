package javastory.club.stage3.step3.ui.menu;

import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

import java.util.Scanner;

public class MainMenu {

    private ClubMenu clubMenu;
    private MemberMenu memberMenu;
    private BoardMenu boardMenu;

    private Scanner sc;
    private Narrator narrator;

    public MainMenu() {
        this.clubMenu = new ClubMenu();
        this.memberMenu = new MemberMenu();
        this.boardMenu = new BoardMenu();
        this.sc = new Scanner(System.in);
        this.narrator = new Narrator(this, TalkingAt.Left);
    }

    public void show(){

        int inputNum = 0;

        while(true){
            displayMenu();
            inputNum = selectMenu();

            switch(inputNum){
                case 1:
                    clubMenu.show();
                    break;
                case 2:
                    memberMenu.show();
                    break;
                case 3:
                    boardMenu.show();
                    break;
                case 0 :
                    this.exitProgram();
                default:
                    narrator.sayln("다시 입력해주세요.");
            }
        }
    }

    private void displayMenu(){

        narrator.sayln(System.lineSeparator());
        narrator.sayln("-------------------------");
        narrator.sayln("메인 메뉴");
        narrator.sayln("-------------------------");
        narrator.sayln(" 1. Club menu");
        narrator.sayln(" 2. Member menu");
        narrator.sayln(" 3. Board menu");
        narrator.sayln("-------------------------");
        narrator.sayln(" 0. Exit program");
        narrator.sayln("-------------------------");

    }

    private int selectMenu(){
        narrator.say("select : ");
        int inputNum = sc.nextInt();

        if(inputNum >= 0 && inputNum <= 3){
            sc.nextLine();
            return inputNum;
        }else{
            narrator.sayln("잘못 입력하셨습니다...");
            return -1;
        }
    }

    private void exitProgram(){
        narrator.sayln("프로그램을 종료합니다 ......");
        sc.close();
        System.exit(0);
    }

}
