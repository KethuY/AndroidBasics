package com.kethu.basicsapp.models;

import java.util.ArrayList;

/**
 * Created by satya on 02-Dec-17.
 */

public class Tennis {
    private boolean status;
    private String message;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    private ArrayList<Player> players;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
