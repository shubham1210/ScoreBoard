package com.io.scoreboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class TeamCreationDTO {
    Integer noOfPlayer;
    Integer noOfOvers;
    LinkedList<String> playerOrderTeam;
    LinkedList<String> over;
    String name;
    Integer matchId;
}
