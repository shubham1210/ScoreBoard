package com.io.scoreboard.utils;

import com.io.scoreboard.model.Match;
import com.io.scoreboard.model.Over;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class PrintUtility {
    private static final Logger logger = LogManager.getLogger(PrintUtility.class);

    public static void printScoreBoard(Match match) {
        System.out.println("==========================================");
        match.getCurrentBattingTeam().getPlayerList().forEach(it -> {
            System.out.print(it.getName());
            if (!it.isOut() && it.getBallPlayed()!=0) System.out.print("*");
            else
                System.out.print(" ");
            System.out.print(it.totalRun() + " ");
            System.out.print(it.getFours() + " ");
            System.out.print(it.getSixes() + " ");
            System.out.print(it.getBallPlayed() + " ");
            System.out.println();
        });

        System.out.print("Total : " + match.getCurrentBattingTeam().getCurrentScore());
        System.out.println("/" + match.getCurrentBattingTeam().getWicketDown());
        List<Over> overs = match.getCurrentBattingTeam().getOversPlayed();
        int ballsPlayedInLastOver = 0;
        if (overs.get(overs.size() - 1).getBallsPlaced() < 6)
            ballsPlayedInLastOver = overs.get(overs.size() - 1).getBallsPlaced();
        if (ballsPlayedInLastOver > 0)
            System.out.println("Overs :" + (match.getCurrentBattingTeam().getOversPlayed().size() - 1) + "." + ballsPlayedInLastOver);
        else
            System.out.println("Overs :" + (match.getCurrentBattingTeam().getOversPlayed().size()) + "." + ballsPlayedInLastOver);
    }
}
