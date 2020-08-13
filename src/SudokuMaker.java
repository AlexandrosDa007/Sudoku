import javax.swing.JOptionPane;

public class SudokuMaker{


    private int[][] board;
    private int size;
    private int squareRootSize;
    private int removedDigits; //How many digits to remove from sudoku

 
    SudokuMaker(int size, int removedDigits){
        this.size = size;
        this.removedDigits = removedDigits;

        Double squareRootSizeDouble = Math.sqrt(size);
        squareRootSize = squareRootSizeDouble.intValue();
        board = new int[size][size];
    }

    public void fillBoard(){
        fillDiagonal();

        fillRemaining(0,squareRootSize);

        removeDigits();
    }

    void fillDiagonal(){
        for(int i=0;i<size;i=i+squareRootSize)
            fillBox(i,i);
    }

    void fillBox(int row, int col){
        int randomNumber;
        for(int i=0;i<squareRootSize;i++){
            for(int j=0;j<squareRootSize;j++){
                do{
                    randomNumber = randomGenerator(size);
                }while(!unUsedInBox(row,col,randomNumber));
                
                board[row+i][col+j] = randomNumber;
            }
        }
    }

    int randomGenerator(int number){
        return (int) Math.floor((Math.random()*number+1));
    }

    boolean unUsedInBox(int rowStart, int colStart, int number){
        for(int i=0;i<squareRootSize;i++)
            for(int j=0;j<squareRootSize;j++)
                if(board[rowStart+i][colStart+j] == number)
                    return false;
        
        return true;
    }

    boolean unUsedInRow(int i, int num){
        for(int j=0;j<size;j++)
            if(board[i][j] == num)
                return false;
        

        return true;
    }

    boolean unUsedInCol(int j, int num){
        for(int i=0;i<size;i++)
            if(board[i][j] == num)
                return false;

        return true;
    }

    boolean checkIfSafe(int i,int j, int num){
        return (unUsedInRow(i,num) && unUsedInCol(j,num) && 
        unUsedInBox(i-i%squareRootSize, j-j%squareRootSize, num));
    }

    boolean fillRemaining(int i,int j){
        if(j >= size && i < size-1){
            i = i + 1;
            j = 0;
        }

        if(i >= size && j >= size)
            return true;

        if(i<squareRootSize){
            if(j<squareRootSize)
                j = squareRootSize;
        }else if(i<size-squareRootSize){
            if(j == (int)(i/squareRootSize)*squareRootSize)
                j = j + squareRootSize;
        }else{
            if(j == size-squareRootSize){
                i = i + 1;
                j = 0;
                if(i >= size)
                    return true;
            }
        }

        for(int num=1;num <= size;num++){
            if(checkIfSafe(i,j,num)){
                board[i][j] = num;
                if(fillRemaining(i, j+1))
                    return true;

                board[i][j] = 0;
            }
        }

        return false;
    }


    public void removeDigits(){
        int count = removedDigits;
        while(count != 0){
            int cellId = randomGenerator(size*size) - 1;

            int i = (cellId/size);
            int j = cellId % 9;

            if(board[i][j] != 0){
                count--;
                board[i][j] = 0;
            }
        }
    }


    public void printSudoku(){
        for (int i = 0; i<size; i++) 
        { 
            for (int j = 0; j<size; j++) 
                System.out.print(board[i][j] + " "); 
            System.out.println(); 
        } 
        System.out.println(); 
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public boolean addOnBoard(int row, int col, int number){
        //returns true if adding was successfull

        //check if unique
        if(!checkIfSafe(row, col, number))
            return false;
        

        board[row][col] = number;
      
        return true;
        
    }

    public boolean isGameDone(){
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                if(board[i][j] == 0)
                    return false;
            }
        }

        
        return true;
    }

    public void checkIfGameIsOver(){
        if(isGameDone()){
            JOptionPane.showMessageDialog(null, "Congratulations!! You finished the game!");
        }

    }


}