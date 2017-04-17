package model;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class Transition extends Node {

    private static int transitionId;

    private int countNeededToken;

    public Transition() {
        super(++ transitionId);
        countNeededToken = -1;
    }

    public static int getTransitionId() {
        return transitionId;
    }

    public static void setTransitionId(int id) {
        transitionId = id;
    }

    public int getCountNeededToken() {
        return countNeededToken;
    }

    public void setCountNeededToken(int countNeededToken) {
        this.countNeededToken = countNeededToken;
    }
}
