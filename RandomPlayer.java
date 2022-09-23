/**
 * A simple AI that makes a random move each turn
 * Also includes an example of how to create a game and play ot
 */

public class RandomPlayer extends ConnectFourPlayer 
{
    
    public static void main(String [] args)
    {
        ConnectFourPlayer cpu = new RandomPlayer();
        ConnectFourPlayer ourAI = new SimpleAI();
        ConnectFourPlayer first = new ColumnOne();
        ConnectFourPlayer betterAI = new BetterAI();
        ConnectFourBoard board = new ConnectFourBoard(9, 7);
        ConnectFourPlayer me = new HumanPlayer();
        ConnectFourPlayer mejor = new MejorAI();
        
        //board.play(cpu, ourAI, true, false);
        /*
        int won1 = 0;
        int tied1 = 0;
        for(int i = 0; i<1000; i++){
            int winner = board.play(cpu, first, false, false);
            if(winner == 2){
                won1++;
            }
            else if(winner == -1){
                tied1++;
            }
        }

        int won2 = 0;
        int tied2 = 0;
        for(int i = 0; i<1000; i++){
            int winner = board.play(cpu, ourAI, false, false);
            if(winner == 2){
                won2++;
            }
            else if(winner == -1){
                tied2++;
            }
        }

        int won3 = 0;
        int tied3 = 0;
        for(int i = 0; i<1000; i++){
            int winner = board.play(cpu, betterAI, false, false);
            if(winner == 2){
                won3++;
            }
            else if(winner == -1){
                tied3++;
            }
        }
        */

        int won4 = 0;
        int tied4 = 0;
        for(int i = 0; i<1000; i++){

            int winner = board.play(betterAI, mejor, false, false);
            if(winner == -1){
                tied4++;
            }
            if(winner == 2){
                won4++;
            }
            /*
            
            else{
                //board.plainDraw();
            }
            */
            

        }

        //System.out.println("first column: " + (double)won1/1000 + " " + (double)tied1/1000);
        //System.out.println("simple ai: " + (double)won2/1000 + " " + (double)tied2/1000);
        //System.out.println("better ai: " + (double)won3/1000 + " " + (double)tied3/1000);
        System.out.println("mejor ai: " + (double)won4/1000 + " " + (double)tied4/1000);
        board.play(me, mejor, true, false);
    }
    
    //returns a value between 0 and board.length
    //which corresponds to the column you want to put your piece in
    public int getMove(int [][] board, int player){
        int [] moves = ConnectFourBoard.getMoves(board);
        int m = (int) (moves.length * Math.random());
        return moves[m];
    }
}
