package com.io.scoreboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class OverThrowDTO {
    Integer matchId;
    LinkedList<String> over;
}
