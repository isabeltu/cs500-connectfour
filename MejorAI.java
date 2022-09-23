public class MejorAI extends ConnectFourPlayer{

    public MejorAI(){

    }

    public int getMove(int[][] board, int player){
        int[] moves = ConnectFourBoard.getMoves(board);
        int bestMove = 0;
        int bestMoveScore = Integer.MIN_VALUE;
        for(int move: moves){
            int x = evaluateMove(board, player, move);
            if(x == 0){
                x = (int)(Math.random()*100);
            }
            if(x > bestMoveScore){
                bestMoveScore = x;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private int evaluateMove(int[][] board, int player, int move){

        //winning move
        int[][] newBoard = ConnectFourBoard.forecastMove(board, move, player);
        if(ConnectFourBoard.hasWinner(newBoard) == player){
            return Integer.MAX_VALUE;
        }
        
        //block their winning move
        newBoard = ConnectFourBoard.forecastMove(board, move, 3-player);
        if(ConnectFourBoard.hasWinner(newBoard) == 3-player){
            return Integer.MAX_VALUE-1;
        }

        //don't make move that would give them winning move
        newBoard = ConnectFourBoard.forecastMove(board, move, player);
        int[] moves = ConnectFourBoard.getMoves(newBoard);
        for(int m: moves){
            int[][] t = ConnectFourBoard.forecastMove(newBoard, m, 3-player);
            if(ConnectFourBoard.hasWinner(t) == 3-player){
                return Integer.MIN_VALUE;
            }
        }
        
        //make move that gives two or more possible wins next move
        newBoard = ConnectFourBoard.forecastMove(board, move, player);
        moves = ConnectFourBoard.getMoves(newBoard);
        int possibleWins = 0;
        for(int m: moves){
            int[][] t = ConnectFourBoard.forecastMove(newBoard, m, player);
            if(ConnectFourBoard.hasWinner(t) == player){
                possibleWins++;
            }
        }
        if(possibleWins >= 2){
            return Integer.MAX_VALUE-2;
        }

        //block them from making move that gives them two or more possible wins next move
        newBoard = ConnectFourBoard.forecastMove(board, move, 3-player);
        moves = ConnectFourBoard.getMoves(newBoard);
        possibleWins = 0;
        for(int m: moves){
            int[][] t = ConnectFourBoard.forecastMove(newBoard, m, 3-player);
            if(ConnectFourBoard.hasWinner(t) == 3-player){
                possibleWins++;
            }
        }
        if(possibleWins >= 2){
            return Integer.MAX_VALUE-3;
        }
        
        //don't make move that would give them a move that gives them two or more possible wins next move
        newBoard = ConnectFourBoard.forecastMove(board, move, player);
        moves = ConnectFourBoard.getMoves(newBoard);
        for(int m:moves){
            int[][] t = ConnectFourBoard.forecastMove(newBoard, m, 3-player);
            int[] moves2 = ConnectFourBoard.getMoves(t);
            int possibleWins2 = 0;
            for(int m2: moves2){
                int[][] t2 = ConnectFourBoard.forecastMove(t, m2, 3-player);
                if(ConnectFourBoard.hasWinner(t2) == 3-player){
                    possibleWins2++;
                }
            }
            if(possibleWins2 >= 2){
                return Integer.MIN_VALUE + 1;
            }
        }
        

        return score(newBoard, player);

        //return (int)(100*Math.random());
        //return 0;

    }


    private int score(int[][] board, int player){

        int WIN_LENGTH = 3;
        int score = 0;

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length - WIN_LENGTH + 1; j++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i][j + k] == player;
                }
                if (won) {
                    score++;
                }
            }
        }
        //look for hori
        for (int j = 0; j < board[0].length; j++){
            for (int i = 0; i < board.length - WIN_LENGTH + 1; i++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i + k][j] == player;
                }
                if (won) {
                    score++;
                }
            }
        }
        //look for pos diag
        for (int i = 0; i < board.length - WIN_LENGTH + 1; i++){
            for (int j = 0; j < board[0].length - WIN_LENGTH + 1; j++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i + k][j + k] == player;
                }
                if (won) {
                    score++;
                }
            }
        }
        //look for neg diag
        for (int i = 0; i < board.length - WIN_LENGTH + 1; i++){
            for (int j = WIN_LENGTH - 1; j < board[0].length; j++){
                boolean won = true;
                for (int k = 0; k < WIN_LENGTH; k++){
                    won = won && board[i + k][j - k] == player;
                }
                if (won) {
                    score++;
                }
            }
        }

        return score;
    }

    
}
