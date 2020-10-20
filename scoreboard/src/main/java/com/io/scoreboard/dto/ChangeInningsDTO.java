package com.io.scoreboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class ChangeInningsDTO {
    LinkedList<String> playerOrderTeam;
    LinkedList<String> over;
    String teamName;
    Integer matchId;
}
