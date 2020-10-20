# Score Board System

This System Allows you to add match Scores for 2 teams and you can add multiple matches between teams.

## Run Steps

Follow Below Commands

```bash
mvn clean install  : For dependency Install
run as Spring Boot app
```

## Entry Contoller File
```python
ScoreBoardController
```

## API
```python
1 . localhost:8080/setupTeams : to add new team with their over
request  :  {
    "noOfPlayer":1,
    "noOfOvers":2,
    "playerOrderTeam" : ["P1","P2","P3","P4","P5","P6"],
    "over" : ["1","1","1","1","1","2"],
    "name" : "Team 1"
}
respose :  
{
    "noOfPlayer": 1,
    "noOfOvers": 2,
    "playerOrderTeam": [
        "P1",
        "P2",
        "P3",
        "P4",
        "P5",
        "P6"
    ],
    "over": [],
    "name": "Team 1",
    "matchId": 1
}


2 . localhost:8080/addOver : add entry for new Over

request :  {
    "over": ["W","4","4","WD","W","1","6"],
    "matchId": 1
}
response : will be on logs of app.


3.  localhost:8080/changeInnings : whenever we need to change the batting team to start bolwing 

request :  
{
    "playerOrderTeam" : ["P6","P7","P8","P9","P10"],
    "over" : ["4","6","W","W","1","1"],
    "teamName" : "Team 2",
    "matchId" :1
}
response : will be on logs of app.



4. localhost:8080/addOver : add over for above team

request : {
    "over": ["6","1","W","W"],
    "matchId": 1
}
response : will be on logs of app.


```
