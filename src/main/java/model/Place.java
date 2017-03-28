package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class Place extends Node {

    private List<Token> tokens;

    public Place(int id) {
        super(id);
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
}
