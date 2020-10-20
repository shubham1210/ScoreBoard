package com.io.scoreboard.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Over {

    private Integer noOfBalls;
    private List<Ball> ballsPlaced;

    public Over(int noOfBalls) {
        this.noOfBalls = noOfBalls;
        this.ballsPlaced = new ArrayList<Ball>();
    }

    public int getBallsPlaced() {
        return ballsPlaced.size();
    }

    public void addBall(Ball ball)
    {
        ballsPlaced.add(ball);
    }
}
