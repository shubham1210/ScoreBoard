package com.io.scoreboard.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
/**
 * Match is the linking class between 2 teams and scores
 */
public class Match {
    private List<Team> teams;
    private Team currentBattingTeam;
    private Integer scoreChase;
    private Integer noOfOverDecided;

    public Match(Team currentBattingTeam,Integer noOfOverDecided) {
        this.teams = new ArrayList<>(2);
        this.currentBattingTeam = currentBattingTeam;
        this.teams.add(currentBattingTeam);
        this.noOfOverDecided =noOfOverDecided;
    }


    /**
     * when we change team from betting to balling.
     */
    public void changeInnings()
    {
        scoreChase=currentBattingTeam.getCurrentScore();
        currentBattingTeam.setIsBatting(false);
        currentBattingTeam = teams.get(1);
    }
}
