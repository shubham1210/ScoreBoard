package com.io.scoreboard.enumTypes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * TYPES of balls we handle.
 */

public enum BallType {
  WIDE_BALL("WD"),
  NO_BALL("NB"),
  WICKET_BALL("W"),
  NORMAL_BALL("N");

  private String value;

  public String getValue() {
    return this.value;
  }

  public static BallType getFromString(String value)
  {
    for(BallType b: BallType.values())
    {
      if(b.getValue().equals(value))
        return b;
    }
    return null;
  }
  private BallType(String value) {
    this.value = value;
  }

  public static boolean isExtraBall(String value)
  {
    if(WICKET_BALL.getValue().equals(value)  || NO_BALL.getValue().equals(value) || WIDE_BALL.getValue().equals(value))
      return true;
    return false;
  }


}
