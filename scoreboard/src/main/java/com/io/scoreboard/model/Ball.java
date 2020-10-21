package com.io.scoreboard.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ball {

    private String ballType;
    private Integer run;

    public Ball(String ballType, Integer run) {
        this.ballType = ballType;
        this.run = run;
    }

}
