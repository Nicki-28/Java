public class Game {

    private static final Direction[] WHITE_DIRECTIONS = {Direction.NW, Direction.NE};
    private static final Direction[] BLACK_DIRECTIONS = {Direction.SW, Direction.SE};

    private final Board board;
    private Player currentPlayer;
    private boolean hasWon;

    public Game(Board board) {
        //throw new UnsupportedOperationException("TODO: Step5");
        this.board=board;
        this.currentPlayer= Player.WHITE;
        this.hasWon=false;
    }

    public Player getCurrentPlayer() {
        //throw new UnsupportedOperationException("TODO: Step5");
        return this.currentPlayer;
    }

    public boolean hasWon() {
        //throw new UnsupportedOperationException("TODO: Step5");
        return this.hasWon;
    }
    private boolean moved=false;
    public boolean isValidFrom(Position position) {
        // we ensure that it´s the current player's cell
        if(getCurrentPlayer() != currentPlayer || moved){return false;}

        //Other cases that would lead to errors
        if(board.isEmpty(position) || !currentPlayer(position)){return false;}

        //In order to have the possibility to move, an empty cell is needed.
        return (validDirections(position, currentPlayer));
    }

    // check if the currentPlayer is in the current position
    private boolean currentPlayer(Position position) {
        if (getCurrentPlayer() == Player.WHITE) {
            return board.isWhite(position);
        } else {
            return board.isBlack(position);
        }
    }

    // this method checks if in the positions the player is allowed to move are available.
    private boolean validDirections(Position position, Player currentPlayer) {
        Position captureMove= null;
        boolean hasValidMove=false;
        Direction[]moves= getCurrentPlayer()==Player.WHITE? WHITE_DIRECTIONS:BLACK_DIRECTIONS;
        for(int i=0; i<moves.length; i++) {
            //simple moves
            Position possibleMove = moves[i].apply(position);
            //potential capture move
            if (opponentsPiece(possibleMove, currentPlayer)) {
                captureMove = moves[i].apply(possibleMove);
            }
            if (board.isEmpty(possibleMove) || (opponentsPiece(possibleMove, currentPlayer) && board.isEmpty(captureMove) && position.sameDiagonalAs(captureMove))) {
                hasValidMove=true;
                break;
            }
        }
        return hasValidMove;
    }

    //the opponent depending on the current player
     private boolean opponentsPiece(Position position, Player currentPlayer) {
        if (currentPlayer== Player.WHITE) {
            return (board.isBlack(position));
        } else {
         return (board.isWhite(position));
        }
     }

    // Assumes validFrom is a valid starting position
    public boolean isValidTo(Position validFrom, Position to) {

        if (getCurrentPlayer() != currentPlayer) {return false;}

        Position captureMove = null;
        if(isValidFrom(validFrom)){
            Direction[]moves= getCurrentPlayer()==Player.WHITE? WHITE_DIRECTIONS:BLACK_DIRECTIONS;
            for(int i=0; i<moves.length; i++) {
                //simple move
                Position possibleMove = moves[i].apply(validFrom);
                //potential capture move
                if(opponentsPiece(possibleMove,currentPlayer)){
                    captureMove= moves[i].apply(possibleMove);
                }
                if( (board.isEmpty(possibleMove) && to.equals(possibleMove) )||((opponentsPiece(possibleMove,currentPlayer) && to.equals(captureMove) && board.isEmpty(captureMove) ))){
                    return true;
                }
            }
        }
        return false;
    }


    // Assumes both positions are valid
    public Move move(Position validFrom, Position validTo) {
        Position middle=null ;

        if(isValidFrom(validFrom) && isValidTo(validFrom,validTo) && !hasWon()){

            //just move
            simpleMove(validFrom,validTo);

            //capture case
            if(Position.distance(validFrom,validTo)>2) {
                middle = Position.middle(validFrom, validTo);
                captureMove(validFrom,validTo);
                // Eliminates the captured piece
                board.setEmpty(middle);
                board.setEmpty(validFrom);
            }

            //winning detection

            //opponent's side
            winningLimits(validTo);
            if (hasWon()) {
                return new Move(validFrom, middle, validTo);
            }
            //all capture
            int opponentPieces = getCurrentPlayer() == Player.WHITE ? board.getNumBlacks() : board.getNumWhites();
            if (opponentPieces == 0) {
                this.hasWon = true;
                return new Move(validFrom, middle, validTo);
            }

            //switch player
            moved=true;
            switchPlayer();

            //no legal moves

            Player opponent= getCurrentPlayer()==Player.WHITE? Player.WHITE:Player.BLACK;
            int opponents = opponent==Player.WHITE? board.getNumWhites() : board.getNumBlacks();

            if(!isValidTo(validFrom,validTo) && (opponents==1)){
                switchPlayer();
                this.hasWon=true;
                return new Move(validFrom,middle,validTo);
            }
        }

        return new Move(validFrom,middle,validTo);
    }


    // Handles the captured cases
    private void captureMove(Position validFrom, Position validTo){

        if ( board.isEmpty(validTo)) {
            // Captures
            if(board.isWhite(validFrom)){
                board.setWhite(validTo);
                board.setEmpty(validFrom);
            } else if (board.isBlack(validFrom)){
                board.setBlack(validTo);
                board.setEmpty(validFrom);
            }
        }
    }

    // Switches players after each move
    private void switchPlayer(){
        currentPlayer = (currentPlayer == Player.WHITE) ? Player.BLACK : Player.WHITE;
        moved=false;
    }
    // It handles moves to empty cells
    private void simpleMove(Position validFrom, Position validTo){

        // Takes way the piece in the initial position and puts in the new one.
        if(getCurrentPlayer()==Player.WHITE && board.isWhite(validFrom)){
            board.setWhite(validTo);
            board.setEmpty(validFrom);
        }else if (getCurrentPlayer() == Player.BLACK && board.isBlack(validFrom)) {
            board.setBlack(validTo);
            board.setEmpty(validFrom);
        }
    }

    // Case one of the pieces arrives to the opponent´s limits
    private void winningLimits(Position validTo){
        if(getCurrentPlayer() == Player.WHITE && validTo.getY() == 0){
            this.hasWon=true;
        } else if(getCurrentPlayer() == Player.BLACK && validTo.getY() == board.getHeight()-1) {
            this.hasWon = true;
        }
    }

    // Only for testing
    public void setPlayerForTest(Player player) {
        this.currentPlayer = player;
    }
}
