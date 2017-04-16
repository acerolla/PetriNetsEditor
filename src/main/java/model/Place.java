package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acerolla on 27.03.2017.
 */
public class Place extends Node {

    private static int placeId;

    private int countNetToken,
            countBasicToken;

    private String label;

    private List<Token> tokens;
    public Place() {
        super(++placeId);
        tokens = new ArrayList<Token>();
        label = "";
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

    public int getCountNetToken() {
        return countNetToken;
    }

    public int getCountBasicToken() {
        return countBasicToken;
    }

    public void setCountNetToken(int countNetToken) {
        this.countNetToken = countNetToken;
    }

    public void setCountBasicToken(int countBasicToken) {
        this.countBasicToken = countBasicToken;
    }

    public void incCountNetToken() {
        countNetToken ++ ;
    }

    public void decCountNetToken() {
        countNetToken -- ;
        for (Token token : tokens) {
            if (token.getClass() == NetToken.class) {
                String subId = ((NetToken)token).getId().substring(((NetToken) token).getId().indexOf(':') + 1);
                int id = Integer.parseInt(subId);
                if (id > countNetToken) {
                    countNetToken ++ ;
                }
            }
        }
    }

    public void incCountBasicToken() {
        countBasicToken ++ ;
    }

    public void decCountBasicToken() {
        countBasicToken -- ;
    }


    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }
}
