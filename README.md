# Mario Game-Playing Agent

A Java-based autonomous agent developed as a university programming assignment for a provided Super Mario-style game environment.

> The game engine and framework were provided by the course.  
> My contribution is the implementation of the decision-making strategy in `Agent.java`.

## Goal

The objective of the agent is to navigate Mario through the level while:

- progressing as far as possible toward the end of the level,
- collecting coins and rewards,
- avoiding hazards such as gaps,
- and maximizing the overall score.

## Approach

The agent evaluates possible actions by simulating future game states.

For each available action, it:

1. creates a copy of the current game state,
2. applies the candidate action,
3. simulates possible future actions,
4. evaluates the resulting state using a heuristic scoring function,
5. selects the action with the highest estimated value.

The evaluation considers both the score obtained during the simulation and Mario's progress through the level.

## Technologies and Concepts

- Java
- Object-Oriented Programming
- Algorithms
- Heuristic evaluation
- Simulation
- Autonomous decision-making

## Repository Contents

- `Agent.java` – my implementation of the autonomous game-playing agent

The game engine itself is not included in this repository.
