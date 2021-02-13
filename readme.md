# Tic Tac Toe Spring-Angular Backend

## Planning

#### Application Stack

- Backend: Spring Boot 2 / OpenJDK15 Maven / Jooq / FlyWay
- Database: Postgres 13
- Frontend: Angular (Repository: )
- Version Control: Git
- CI/CD: Gitlab-CI
- Production
  - Backend: Heroku/Docker
  - Database: Heroku Postgres
  - Frontend: Netlify

#### Application Plan
##### Game logic
```
- As we open the website we will see a start button
- With the start button we can start the game
- After the button clicked the site renders an empty and clickable 3x3 map
- Displayed randomly who starts: X/O
- Every square is clickable once
- Win: same sign 3-in-a-row
- Draw: all squares cliecked and not 3-in-a-row
- Restart button (if there will be time)
```
##### Code logic
```
- On page load the Angular won't have the gameId and without it there will displayed a start button
- Start button calls the GET/v1/game/start endpoint
  Response:
  {
     data: {
       gameId: \d+,
       starterPlayerId: 0|1
     }
  }
- Every cell click will post the playerId and the fieldId to POST/v1/game/{gameId}/choose-place
  Request:
  {
    playerId: \d+,
    position: [0-9]
  }
  Response:
  {
    data: {
       finished: 0|1,
       winer: 0|1|2|null,
       nextPlayerId: 0|1|null,
       currentGameState: [1, 0, null, null, 1, 0, null, null, 1]
    }
  }
```

## Development steps in bullet points:

- Setup local env: IDEA / Local db / OpenJDK 15
- RevNum: 135b30082f44715587bd8d2c8ac204e7228e2463  
   `GET/v1/game/start` endpoint: e2e test for the controller  
   create controller  
   integration test for game service  
   create the game `service.start()`  
   create init migration for FylWay  
   set the fylway xml conf to the db  
   generate class db structure from the table structure
   `start()` endpoint will write the time to the db and return the new auto increment id  
   Ok when: all test green
- RevNum: 12cef815a0fcd852f95ef91a35052bd2144f8e8f
   Complete with TDD the `GameService.startNewGame()`
- RevNum:  
   Create choose-place action with TDD



