package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class Place extends Node {

    private static int placeId;

    private List<Token> tokens;
    public Place() {
        super(++placeId);
        tokens = new ArrayList<Token>();
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void removeToken(Token token) {
        tokens.remove(token);
    }

    public static int getPlaceId() {
        return placeId;
    }

    public static void setPlaceId(int id) {
        placeId = id;
    }
}
