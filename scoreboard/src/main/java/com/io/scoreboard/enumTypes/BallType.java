package com.io.scoreboard.enumTypes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TYPES of balls we handle.
 */
public interface BallType {
    public final String WIDE_BALL = "WD";
    public final String NO_BALL = "NB";
    public final String WICKET_BALL = "W";
    public final String NORMAL_BALL = "B";
    public final Set<String> BALL_TYPE_SET = new HashSet<String>(Arrays.asList(WICKET_BALL, WIDE_BALL, NO_BALL, NORMAL_BALL));

}
