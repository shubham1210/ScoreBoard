package com.io.scoreboard.utils;

import com.io.scoreboard.enumTypes.BallType;
import com.io.scoreboard.exception.MatchEndException;
import com.io.scoreboard.exception.NoPlayerException;
import com.io.scoreboard.model.Ball;
import com.io.scoreboard.model.Match;
import com.io.scoreboard.model.Over;
import com.io.scoreboard.model.Team;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class MatchUtility {
    private static final Logger logger = LogManager.getLogger(MatchUtility.class);

    public static void handleOver(List<String> run, Team currentBattingTeam, Match match) {
        int bollPlayed = run.size();
        if (bollPlayed == 0) return;

        Over over = new Over(6);

        try {
            while (run.size() != 0) {
                String ballRun = run.remove(0);
                if (BallType.BALL_TYPE_SET.contains(ballRun)) {
                    handleExtraBalls(ballRun,over,currentBattingTeam);
                } else {
                    handleNormalBalls(ballRun,over,currentBattingTeam);
                }
                if(match.getScoreChase()!=null && match.getScoreChase()<currentBattingTeam.getCurrentScore())
                  throw new MatchEndException(currentBattingTeam.getName() + "wins");
            }
            currentBattingTeam.addOvers(over);
            currentBattingTeam.switchStriker();
        } catch (NoPlayerException | MatchEndException e) {
            logger.error(e.getMessage());
            currentBattingTeam.addOvers(over);
        }
    }

    /**
     * handle runs for each balls like 1-6 runs.
     * @param ballRun
     * @param over
     * @param currentBattingTeam
     * @throws NoPlayerException
     */
    private static void handleNormalBalls(String ballRun,Over over,Team currentBattingTeam) throws NoPlayerException
    {
        int score = Integer.parseInt(ballRun);
        over.addBall(new Ball(BallType.NORMAL_BALL, score));
        switch (score) {
            case 4: {
                currentBattingTeam.getStriker().hitFour();
                currentBattingTeam.getStriker().addBallPlayed();
                break;
            }
            case 6: {
                currentBattingTeam.getStriker().hitSix();
                currentBattingTeam.getStriker().addBallPlayed();
                break;
            }
            default: {
                if (score % 2 == 0) {
                    currentBattingTeam.getStriker().addSingle(score);
                    currentBattingTeam.getStriker().addBallPlayed();
                } else {
                    currentBattingTeam.getStriker().addSingle(score);
                    currentBattingTeam.getStriker().addBallPlayed();
                    currentBattingTeam.switchStriker();
                }
            }
        }
        currentBattingTeam.addToCurrentScore(score);
    }

    /**
     * method handle extra ball types like, W,WD,NB
     * @param ballRun
     * @param over
     * @param currentBattingTeam
     * @throws NoPlayerException
     */
    private static void handleExtraBalls(String ballRun,Over over,Team currentBattingTeam) throws NoPlayerException
    {
        switch (ballRun) {
            case BallType.NO_BALL: {
                over.addBall(new Ball(BallType.NORMAL_BALL, 1));
                currentBattingTeam.addToCurrentScore(1);
                break;
            }
            case BallType.WICKET_BALL: {
                over.addBall(new Ball(BallType.WICKET_BALL, 0));
                currentBattingTeam.wicketDown();
                break;
            }
            case BallType.WIDE_BALL: {
                over.addBall(new Ball(BallType.WIDE_BALL, 1));
                currentBattingTeam.addToCurrentScore(1);
                break;
            }
        }
    }
}

