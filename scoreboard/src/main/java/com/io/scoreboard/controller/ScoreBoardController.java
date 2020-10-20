package com.io.scoreboard.controller;

import com.io.scoreboard.utils.MatchUtility;
import com.io.scoreboard.dto.ChangeInningsDTO;
import com.io.scoreboard.dto.OverThrowDTO;
import com.io.scoreboard.dto.TeamCreationDTO;
import com.io.scoreboard.model.Match;
import com.io.scoreboard.model.Player;
import com.io.scoreboard.model.Team;
import com.io.scoreboard.utils.PrintUtility;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ScoreBoardController {

    //as we are not connecting to db so persisting match information in to db
    final static HashMap<Integer,Match>  matchRunningOn = new HashMap<Integer,Match>();
    //getting match id.
    final static AtomicInteger matchCount = new AtomicInteger(1);

    /**
     * this helps to setup a team and send first over to team A;
     * @param teamCreationDTO
     * @return
     */
    @RequestMapping(value = "/setupTeams", method = RequestMethod.POST)
    public TeamCreationDTO setupTeams(@RequestBody TeamCreationDTO teamCreationDTO) {

        //creating players
        List<Player> players = new LinkedList<Player>();
        teamCreationDTO.getPlayerOrderTeam().stream().forEach(it->players.add(new Player(it)));

        //creating team and Match
        Team team = new Team(players, teamCreationDTO.getName());
        Match match = new Match(team, teamCreationDTO.getNoOfOvers());

        //sending overInfo to handler
        MatchUtility.handleOver(teamCreationDTO.getOver(),match.getCurrentBattingTeam());
        PrintUtility.printScoreBoard(match);

        //get match Id act like primary key
        int matchId = matchCount.getAndIncrement();
        matchRunningOn.put(matchId,match);
        teamCreationDTO.setMatchId(matchId);

        return teamCreationDTO;
    }

    /**
     * adding information for new over played
     * @param overThrowDTO
     */
    @RequestMapping(value = "/addOver", method = RequestMethod.POST)
    public void addOver(@RequestBody OverThrowDTO overThrowDTO) {
        //requester need to send the matchID that is come back from /setupTeams action.
        if(overThrowDTO.getMatchId()==null)return;

        Match match = matchRunningOn.get(overThrowDTO.getMatchId());
        //if no of over still less then we decided then play.
        if(match.getNoOfOverDecided()>match.getCurrentBattingTeam().getOversPlayed().size())
        {
            MatchUtility.handleOver(overThrowDTO.getOver(),match.getCurrentBattingTeam());
        }else
        {
            PrintUtility.printScoreBoard(match);
            if(match.getCurrentBattingTeam().getCurrentScore() < match.getScoreChase())
                System.out.println(match.getTeams().get(0).getName()+" won the match by " + (match.getScoreChase()-match.getCurrentBattingTeam().getCurrentScore()));
            else
                System.out.println(match.getCurrentBattingTeam().getName()+" won the match by " + Math.abs(match.getScoreChase()-match.getCurrentBattingTeam().getCurrentScore()));
            return;
        }
        //printing it on console.
        PrintUtility.printScoreBoard(match);
    }

    /**
     * when innings changes. need to swap information in match object.
     * @param changeInningsDTO
     */
    @RequestMapping(value = "/changeInnings", method = RequestMethod.POST)
    public void addOver(@RequestBody ChangeInningsDTO changeInningsDTO) {
        if(changeInningsDTO.getMatchId()==null)return;
        //taking playerInformation
        List<Player> players = new LinkedList<Player>();
        changeInningsDTO.getPlayerOrderTeam().stream().forEach(it->players.add(new Player(it)));

        Team teamB = new Team(players, changeInningsDTO.getTeamName());
        Match match = matchRunningOn.get(changeInningsDTO.getMatchId());
        //adding othet team to match
        match.getTeams().add(teamB);
        match.changeInnings();

        MatchUtility.handleOver(changeInningsDTO.getOver(),match.getCurrentBattingTeam());
        PrintUtility.printScoreBoard(match);
    }

}
