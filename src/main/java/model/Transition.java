package model;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class Transition extends Node {

    private static int transitionId;

    public Transition() {
        super(++ transitionId);
    }

    public static int getTransitionId() {
        return transitionId;
    }

    public static void setTransitionId(int id) {
        transitionId = id;
    }

}
