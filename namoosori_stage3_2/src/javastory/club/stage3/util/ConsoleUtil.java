package javastory.club.stage3.util;

import java.util.Scanner;

public class ConsoleUtil {

    private Scanner sc;
    private Narrator narrator;

    public ConsoleUtil(Object target) {
        this.sc = new Scanner(System.in);
        this.narrator = new Narrator(target.getClass().getSimpleName(),TalkingAt.Left);
    }

    public ConsoleUtil(Narrator narrator){
        this.sc = new Scanner(System.in);
        this.narrator = narrator;
    }

    public String getValueOf(String label){
        narrator.say(label + " : ");
        String inputStr = sc.nextLine();
        inputStr = inputStr.trim();
        return inputStr;
    }
}
