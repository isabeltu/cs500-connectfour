public class SimpleAI extends ConnectFourPlayer{

    public SimpleAI(){

    }

    public int getMove(int[][] board,int player){
        int[] moves = ConnectFourBoard.getMoves(board);
        for(int i = 0; i<moves.length;i++){
            int[][] newBoard = ConnectFourBoard.forecastMove(board, moves[i], player);
            if(ConnectFourBoard.hasWinner(newBoard) == player){
                return moves[i];
            }
        }

        int m = (int) (moves.length * Math.random());
        return moves[m];
    }
    
}
