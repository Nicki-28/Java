public class Direction {

    public static final Direction NW = new Direction(-1,-1);
    public static final Direction NE = new Direction (1,-1);
    public static final Direction SW = new Direction (-1,1);
    public static final Direction SE = new Direction (1,1);

    private final int dx;
    private final int dy;

    private Direction(int dx, int dy) {
        //throw new UnsupportedOperationException("TODO: Step2");
        this.dx=dx;
        this.dy=dy;
    }

    public Position apply(Position from) {
        //throw new UnsupportedOperationException("TODO: Step2");
        int newX = Math.abs(from.getX() + this.dx);
        int newY = Math.abs(from.getY() + this.dy);

        // we ensure to always be working in the board limits
        if( newX>8 || newY>8){
            newX= newX % 8;
            newY= newY % 8;
        }
        return new Position(newX, newY);
    }
}
