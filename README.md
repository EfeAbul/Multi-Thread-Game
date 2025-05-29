# Multithreaded Character vs Monster Game

This is a basic multithreaded Java game where the player controls a green square character trying to avoid randomly moving monsters. The game is built using Swing and demonstrates the use of threads for concurrent monster movement.

## Features

- Player character controlled via keyboard (W, A, S, D keys)
- Configurable number of monsters created at runtime
- Each monster is a separate thread that moves independently
- Collision detection ends the game when a monster reaches the player
- Simple GUI rendering using `JFrame` and `Graphics`

## Controls

- `W`: Move up  
- `A`: Move left  
- `S`: Move down  
- `D`: Move right  

## Requirements

- Java JDK 8 or later

## How to Run

### 1. Compile the game

```bash
javac Game.java
```

### 2. Run the game

```bash
java Game <number_of_monsters>
```

Replace `<number_of_monsters>` with the number of monster threads you want to spawn. For example:

```bash
java Game 5
```

## Notes

- All monster threads start simultaneously and move towards the player.
- The game ends when a monster gets close enough to the player's character.
