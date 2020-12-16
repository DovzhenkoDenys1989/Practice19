package com.company;

public class Game {
    private final String name;
    private Genre genre;
    private Type type;

    public enum Type{
        VIRTUAL, PHYSICAL
    }

    private Game(String name, Genre genre, Type type) {
        this.name = name;
        this.genre = genre;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Type getType() {
        return type;
    }

    public static class GameDisk{
        private final String description;
        private final Game data;

        public GameDisk(String name, Genre genre, String description) {
            data = new Game(name, genre,Type.PHYSICAL);
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public Game getData() {
            return data;
        }
    }

    public static class VirtualGame{
        private int rating = (int) (Math.random() * 5);
        private final Game data;

        private VirtualGame(String name, Genre genre) {
            data = new Game(name,genre,Type.VIRTUAL);
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public Game getData() {
            return data;
        }
    }

    public static GameDisk getDisk (String name, Genre genre, String description){
        return new GameDisk(name,genre,description);
    }

    public static VirtualGame getVirtualGame(String name, Genre genre){
        return new VirtualGame(name,genre);
    }
}
