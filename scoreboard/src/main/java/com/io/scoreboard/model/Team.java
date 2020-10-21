package com.io.scoreboard.model;

import com.io.scoreboard.exception.NoPlayerException;

import java.util.LinkedList;
import java.util.List;

public class Team {
    private List<Player> playerList;
    private Integer currentScore;
    private Boolean isBatting;//Batting Bolling
    private Integer wicketDown;
    private Player striker;
    private Player nonStriker;
    private List<Over> oversPlayed;
    private Integer nextPlayerIndex;
    private String name;

    public Team(List<Player> playerList,String name) {
        if(playerList==null || playerList.size()<2) return;

        this.playerList = playerList;
        this.isBatting = true;
        wicketDown = new Integer(0);
        currentScore = new Integer(0);
        striker= playerList.get(0);
        nonStriker = playerList.get(1);
        oversPlayed = new LinkedList<>();
        nextPlayerIndex =2;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    private Player nextPlayer()
    {
        if(nextPlayerIndex <playerList.size())
        {
            Player player = playerList.get(nextPlayerIndex);
            nextPlayerIndex++;
            return player;
        }
        return null;
    }
    public void addOvers(Over over) {
        this.oversPlayed.add(over);
    }

    public List<Over> getOversPlayed() {
        return oversPlayed;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void addToCurrentScore(Integer currentScore) {
        this.currentScore+=currentScore;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public Boolean getIsBatting() {
        return isBatting;
    }

    public void setIsBatting(Boolean isBatting) {
        this.isBatting = isBatting;
    }

    public Integer getWicketDown() {
        return wicketDown;
    }

    public void wicketDown() throws NoPlayerException{
        this.wicketDown++;
        this.striker.out();
        this.striker = nextPlayer();
        if(this.striker==null) throw new NoPlayerException("All Wicket Down");
    }

    public Player getStriker() {
        return striker;
    }

    public void setStriker(Player striker) {
        this.striker = striker;
    }

    public Player getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(Player nonStriker) {
        this.nonStriker = nonStriker;
    }

    public void switchStriker()
    {
        Player temp =striker;
        striker = nonStriker;
        nonStriker =temp;
    }
}
