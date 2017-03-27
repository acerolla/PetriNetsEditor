package model;

import java.util.List;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class Place extends Node {

    private List<Token> tokens;

    public Place() {

    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
