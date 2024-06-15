public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean sameDiagonalAs(Position other) {
        // Verifica si están en la misma línea diagonal en sentido / o \
        return (other.getX() + this.getY() == other.getY() + this.getX()) || (this.getX() - other.getX() == other.getY() - this.getY());

    }

    public static int distance(Position pos1, Position pos2) {

        int diference1= pos1.getX()- pos2.getX();
        int diference2= pos1.getY() - pos2.getY();

        return Math.abs(diference1)+ Math.abs(diference2);
    }

    public static Position middle(Position pos1, Position pos2) {
        int pos_x= (pos1.getX()+ pos2.getX())/2;
        int pos_y= (pos1.getY()+ pos2.getY())/2;

        return new Position(pos_x,pos_y);
    }

    // Needed for testing and debugging

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
