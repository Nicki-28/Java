import java.util.StringTokenizer;

// Only a rectangle of cells. Does not know the game rules.

public class Board {

    private final int width;
    private final int height;
    private final Cell[][] cells;
    private int numBlacks;
    private int numWhites;

    public Board(int width, int height, String board) {
        //throw new UnsupportedOperationException("TODO: Step4");
        this.width=width;
        this.height=height;
        this.cells= new Cell[height][width];
        this.numBlacks=0;
        this.numWhites=0;
        fill(board);
        count();

    }
    // Auxiliary method, to fill cells with the board
    private void fill(String board) {
        StringTokenizer token = new StringTokenizer(board, "\n");
        for (int i = 0;i < height && token.hasMoreTokens() ; i++) {
            String line= token.nextToken();
            for (int j = 0; j < width && j < line.length(); j++) {
                this.cells[i][j] = Cell.fromChar(line.charAt(j));
            }
        }
    }
    // Auxiliary method, it will count the pieces black and white
    private void count (){
        for(int i=0; i<this.height;i++){
            for(int j=0; j<this.width; j++) {
                if (this.cells[i][j].isWhite()) {
                    this.numWhites += 1;
                } else if (this.cells[i][j].isBlack()) {
                    this.numBlacks += 1;
                }
            }
        }
    }

    public int getWidth() {
        //throw new UnsupportedOperationException("TODO: Step4");
        return this.width;
    }

    public int getHeight() {
        //throw new UnsupportedOperationException("TODO: Step4");
        return this.height;
    }

    public int getNumBlacks() {
        //throw new UnsupportedOperationException("TODO: Step4");
        return numBlacks;
    }

    public int getNumWhites() {
        //throw new UnsupportedOperationException("TODO: Step4");
        return numWhites;
    }

    public boolean isForbidden(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        if(getWidth() <= pos.getX() || getHeight() <= pos.getY()){
            return true;
        }else if( this.cells[pos.getY()][pos.getX()].isForbidden()){
            return true;
        }else{
            return false;
        }

    }

    public boolean isBlack(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        if (getWidth() <= pos.getX() || getHeight() <= pos.getY()) {
            return false;
        }
        return  (this.cells[pos.getY()][pos.getX()].isBlack());

    }

    public boolean isWhite(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        if (getWidth() <= pos.getX() || getHeight() <= pos.getY()) {
            return false;
        }
        return  (this.cells[pos.getY()][pos.getX()].isWhite());
    }

    public boolean isEmpty(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        if (getWidth() <= pos.getX() || getHeight() <= pos.getY()) {
            return false;
        }
        return  (this.cells[pos.getY()][pos.getX()].isEmpty());
    }

    public void setBlack(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        this.cells[pos.getY()][pos.getX()]=Cell.BLACK;
        this.numBlacks+=1;
    }

    public void setWhite(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        this.cells[pos.getY()][pos.getX()]=Cell.WHITE;
        this.numWhites+=1;
    }

    public void setEmpty(Position pos) {
        Cell cellType = this.cells[pos.getY()][pos.getX()]; // Almacenar el tipo de la celda antes de cambiarla a vacío
        this.cells[pos.getY()][pos.getX()] = Cell.EMPTY;

        // Verificar el tipo de celda original y actualizar el contador de piezas correspondiente
        if (cellType.isWhite()) {
            this.numWhites-=1;
        } else if (cellType.isBlack()) {
            this.numBlacks-=1;
        }
    }


    // For testing and debugging

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(cells[y][x].toString());
            }
            if (y != height - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
