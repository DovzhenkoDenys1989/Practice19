package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class PlayRoom {
    public static void main(String[] args) {
        Game.GameDisk[] disks = {
                Game.getDisk("FIFA2020", Genre.SPORT, "Football"),
                Game.getDisk("Counter Strike 1.6", Genre.ACTION, "Shooting"),
                Game.getDisk("Need For Speed", Genre.RACE, "Highway racing"),
                Game.getDisk("GTA", Genre.ACTION, "Mission in the city")
        };

        Game.VirtualGame[] virtualGames = {
                Game.getVirtualGame("Formula 1 Online", Genre.RACE),
                Game.getVirtualGame("Serious Sam", Genre.ACTION),
                Game.getVirtualGame("Golf", Genre.SPORT),
                Game.getVirtualGame("Half-Life", Genre.ACTION)
        };

        GameConsole gameConsole = new GameConsole(Brand.MICROSOFT, "Xbox Series X");

        Arrays.sort(disks, new Comparator<Game.GameDisk>() {
            @Override
            public int compare(Game.GameDisk o1, Game.GameDisk o2) {
                return o1.getData().getGenre().compareTo(o2.getData().getGenre());
            }
        });

        Arrays.sort(virtualGames, new Comparator<Game.VirtualGame>() {
            @Override
            public int compare(Game.VirtualGame o1, Game.VirtualGame o2) {
                return Integer.compare(o1.getRating(), o2.getRating());
            }
        });

//        TODO: checking the work of laboratory methods
//        for (Game.GameDisk disk : disks) {
//            System.out.println(disk.getData().getGenre());
//        }

//        for (Game.VirtualGame virtualGame : virtualGames) {
//            System.out.println(virtualGame.getRating());
//        }

//        gameConsole.getSecondGamepad().powerOff();

//        System.out.println(gameConsole.getSecondGamepad().getConnectedNumber());
        gameConsole.loadGame(virtualGames[1].getData());
        int count = 0;
        while (count < 20) {
            gameConsole.playGame();
            count++;
        }
    }
}

