# 2D Java Game Bachelor Project

This project is a 2D desktop game developed in Java as a bachelor project.  
The game is built using core Java technologies without external libraries, with a focus on object-oriented design, game loops, collision handling, and basic AI behavior.

##Project Overview
The application represents a classic 2D game where the player can:
- move around the map
- interact with NPC characters
- engage in combat with enemies
- navigate through menus
- experience sound effects and background music

The main goal of the project is to demonstrate practical knowledge of Java SE, game architecture, and software design principles.

##Technologies Used
- Java SE
- Java Swing/AWTrendering and UI
- Ant project build tool
- BufferedImage sprite and tile rendering
- Clip(javax.sound.sampled) sound and music
- Object Oriented Programming (OOP)
(No external libraries or game engines were used)

##Features
- Main menu(New Game/Quit)
- Tile based map system
- Player movement and collision detection
- NPC interaction and dialog system
- Enemy entities with basic AI behavior
- Combat system
- Sound effects and background music
- Game states(menu, play, pause, game over)

##Project Structure
Main components of the project include:

- Main application entry point
- GamePanel core game loop and rendering
- KeyHandler keyboard input handling
- UI menus and in-game user interface
- Sound audio management
- GameState control of game flow and states
- Entity classes(Player, NPCs, Enemies)

##How to Run
1. Make sure Java JDK is installed on your system
2. Open the project in any Java IDE(e.g. IntelliJ IDEA, Eclipse, NetBeans)
3. Import the project as an Ant based Java project
4. Build the project
5. Run the Main class

##Purpose
This project was developed as a bachelor project to demonstrate:
- understanding of Java fundamentals
- object oriented programming skills
- basic game architecture
- ability to design and implement a complete software application

##Architecture
The project follows a layered architecture with elements inspired by the Model View Controller(MVC) pattern.
Although it is not a strict MVC implementation, the responsibilities are clearly separated into logical layers, which improves readability, maintainability, and scalability of the codebase.
