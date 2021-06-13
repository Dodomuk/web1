package javastory.club.stage3.step3.ui.menu;

import javastory.club.stage3.util.ConsoleUtil;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

import java.util.Scanner;

public class MemberMenu {

    private MemberConsole memberConsole;
    private Scanner sc;
    private Narrator narrator;
    private ConsoleUtil consoleUtil;

    public MemberMenu(){
        this.memberConsole = new MemberConsole();
        this.sc = new Scanner(System.in);
        this.narrator = new Narrator(this, TalkingAt.Left);
    }

    public void show() {

        int inputNumber = 0;

        while (true) {
            displayMenu();
            inputNumber = selectMenu();

            switch (inputNumber) {

                case 1:
                    memberConsole.register();
                    break;
                case 2:
                    memberConsole.find();
                    break;
                case 3:
                    memberConsole.modify();
                    break;
                case 4:
                    memberConsole.remove();
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
        narrator.sayln(" Member menu ");
        narrator.sayln("..............................");
        narrator.sayln(" 1. Register");
        narrator.sayln(" 2. Find");
        narrator.sayln(" 3. Modify");
        narrator.sayln(" 4. Remove");
        narrator.sayln("..............................");
        narrator.sayln(" 0. Previous");
        narrator.sayln("..............................");
    }

     public int selectMenu(){
         System.out.print("번호를 입력해주세요 : ");
         int inputNum = sc.nextInt();
       if(inputNum >= 0 && inputNum <= 4){
           //nextLine의 필요 여부 확인하기!
           return inputNum;
       }else{
           narrator.sayln("잘못 입력하셨습니다 ....");
           return -1;
       }
     }
}
