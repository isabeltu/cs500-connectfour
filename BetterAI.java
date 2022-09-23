public class BetterAI extends ConnectFourPlayer{

    public BetterAI(){

    }

    public int getMove(int[][] board,int player){
        int[] moves = ConnectFourBoard.getMoves(board);

        
        for(int i = 0; i<moves.length;i++){
            int[][] newBoard = ConnectFourBoard.forecastMove(board, moves[i], player);
            if(ConnectFourBoard.hasWinner(newBoard) == player){
                return moves[i];
            }
        }

        for(int i = 0; i<moves.length; i++){
            int[][] newBoard = ConnectFourBoard.forecastMove(board, moves[i], 3-player);
            if(ConnectFourBoard.hasWinner(newBoard) == 3-player){
                return moves[i];
            }
        }

        
        

        int x = (int) (moves.length * Math.random());
        return moves[x];
    }

    
}
