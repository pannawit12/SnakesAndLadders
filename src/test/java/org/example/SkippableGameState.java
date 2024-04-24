//package org.example;
//
//import java.io.IOException;
//
//public class SkippableGameState extends GameState {
//
//    public static void main(String[] args) throws IOException {
//        SkippableGameState game = new SkippableGameState();
//        game.gameRun();
//    }
//
//    public SkippableGameState() throws IOException {
//        super();
//    }
//
//    private void gameRun() throws IOException {
//        System.out.println("Running game...");
//
//        int turnCount = 0;
//
//        while(true) {
//            System.out.println(table.getTableToString());
//
//            for (Player player:players) {
//                System.out.println(player.getName() + " is at " + player.getLocation());
//            }
//
//            Player player = players[turnCount % numberOfPlayers];
//            turn(player);
//
//            if(player.getLocation() == table.getFinishSquareIndex()) {
//                break;
//            }
//
//            turnCount++;
//        }
//
//        System.out.println(players[turnCount % numberOfPlayers].getName() + " Win!!!");
//    }
//
//    String read = "";
//
//    private void turnRun(Player player) throws IOException {
//        String name = player.getName();
//        int diceNumber = player.rollDice(dice);
//        int moveLocation = table.moveCalculation(player.getLocation(), diceNumber);
//        player.setLocation(moveLocation);
//
//        System.out.println("\n" + name + "'s turn");
//        System.out.print("press Enter to roll a dice");
//
//        if (!read.equals("skip")){
//            read = reader.readLine();
//        }
//
//        System.out.println(name + " rolled a " + diceNumber);
//        System.out.println(name + " moved to square " + moveLocation);
//    }
//}
