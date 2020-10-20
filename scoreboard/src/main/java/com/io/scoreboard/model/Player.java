package com.io.scoreboard.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
    private String name;
    private Integer ballPlayed;
    private Integer fours;
    private Integer sixes;
    private Integer singles;
    private Boolean isOut;

    public Player(String name) {
        this.name = name;
        this.ballPlayed = new Integer(0);
        this.fours =new Integer(0);
        this.sixes =new Integer(0);
        this.singles = new Integer(0);
        this.isOut =false;
    }

    public void hitSix()
    {
        sixes++;
    }

    public void hitFour()
    {
        fours++;
    }

    public void addSingle(int run)
    {
        singles+=run;
    }
    public int totalRun()
    {
        return sixes*6+fours*4+singles;
    }
    public void out()
    {
        isOut=true;
    }

    public String getName() {
        return name;
    }

    public Integer getBallPlayed() {
        return ballPlayed;
    }

    public void addBallPlayed() {
        ballPlayed++;
    }

    public Integer getFours() {
        return fours;
    }

    public Integer getSixes() {
        return sixes;
    }

    public Boolean isOut() {
        return isOut;
    }
}
