import acm.graphics.GDimension;
import acm.graphics.GPoint;

public class Geometry {

    private final int windowWidth;
    private final int windowHeight;
    private final int numCols;
    private final int numRows;
    private final double boardPadding;
    private final double cellPadding;

    public Geometry(int windowWidth, int windowHeight, int numCols, int numRows, double boardPadding, double cellPadding) {
        //throw new UnsupportedOperationException("TODO: Step6");
        this.windowWidth=windowWidth;
        this.windowHeight=windowHeight;
        this.numCols=numCols;
        this.numRows=numRows;
        this.boardPadding=boardPadding;
        this.cellPadding=cellPadding;

    }

    public int getRows() {
        //throw new UnsupportedOperationException("TODO: Step6");
        return this.numRows;
    }

    public int getColumns() {
        //throw new UnsupportedOperationException("TODO: Step6");
        return this.numCols;
    }

    public GDimension boardDimension() {
        //throw new UnsupportedOperationException("TODO: Step6");
        double width = this.windowWidth * (1 - this.boardPadding*2);
        double height = this.windowHeight * (1 - this.boardPadding*2);

        return new GDimension(width, height);

    }

    public GPoint boardTopLeft() {
        //throw new UnsupportedOperationException("TODO: Step6");
        double coordx= this.windowWidth * this.boardPadding;
        double coordy= this.windowHeight * this.boardPadding;
        return new GPoint(coordx,coordy);
    }

    public GDimension cellDimension() {
        //throw new UnsupportedOperationException("TODO: Step6");
        double cellx= boardDimension().getWidth()/this.numCols;
        double celly= boardDimension().getHeight()/this.numRows;
        return new GDimension(cellx,celly);
    }

    public GPoint cellTopLeft(int x, int y) {
        //throw new UnsupportedOperationException("TODO: Step6");
        double cell_x= boardTopLeft().getX()+cellDimension().getWidth()*x;
        double cell_y= boardTopLeft().getY()+cellDimension().getHeight()*y;

        return new GPoint(cell_x,cell_y);

    }

    public GDimension tokenDimension() {
        //throw new UnsupportedOperationException("TODO: Step6");
        double width = this.cellDimension().getWidth() * (1 - this.cellPadding*2);
        double height = this.cellDimension().getHeight() * (1 - this.cellPadding*2);

        return new GDimension(width,height);
    }

    public GPoint tokenTopLeft(int x, int y) {
        //throw new UnsupportedOperationException("TODO: Step6");
        GPoint cell = cellTopLeft(x,y);

        double tokenX = cell.getX() + cellDimension().getWidth() * cellPadding;
        double tokenY = cell.getY() + cellDimension().getHeight() * cellPadding;

        return new GPoint(tokenX,tokenY);
    }

    public GPoint centerAt(int x, int y) {
        //throw new UnsupportedOperationException("TODO: Step6");
        GPoint token= tokenTopLeft(x,y);

        double tokenX = token.getX()+ tokenDimension().getWidth()/2;
        double tokenY = token.getY()+tokenDimension().getHeight()/2;

        return new GPoint (tokenX,tokenY);
    }

    public Position xyToCell(double x, double y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cellDimension = cellDimension();
        return new Position(
                (int) ((x - boardTopLeft.getX()) / cellDimension.getWidth()),
                (int) ((y - boardTopLeft.getY()) / cellDimension.getHeight()));
    }
}
