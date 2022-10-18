package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST,

    OTHER;

    @Override
    public String toString() {
        switch(this) {
            case NORTH: return "Polnoc";
            case SOUTH: return "Poludnie";
            case WEST: return "Zachod";
            case EAST: return "Wschod";
            default: return "";
        }
    }

    public MapDirection next() {
        switch (this) {
            case NORTH: return EAST;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            case EAST: return SOUTH;
            default: return OTHER;
        }
    }

    public MapDirection previous() {
        switch (this) {
            case NORTH: return WEST;
            case WEST: return SOUTH;
            case SOUTH: return EAST;
            case EAST: return NORTH;
            default: return OTHER;
        }
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(-1,0);
            default -> null;
        };
    }
}