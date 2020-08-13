
import javax.swing.JPanel;

import java.awt.*;



public class SudokuDraw extends JPanel{


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final int BOARD_START_X = 30;
    private final int BOARD_START_Y = 30;
    private final int PANEL_WIDTH = 540;
    private final int PANEL_HEIGHT = 540;
    private int[][] board;
    private Rectangle[][] cells = new Rectangle[App.BOARD_SIZE][App.BOARD_SIZE];
    
    private void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        

        //Draw the outline --- START
        //--------------------------
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(BOARD_START_X, BOARD_START_Y, PANEL_WIDTH, PANEL_HEIGHT);

        g2d.setColor(Color.black);
        int start = BOARD_START_X;
        for(int i=0;i<App.BOARD_SIZE;i++){
            if(i == 3 || i == 6)
                g2d.setStroke(new BasicStroke(3));
            else
                g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(BOARD_START_X, start, PANEL_WIDTH+BOARD_START_X, start);
            start = start + PANEL_WIDTH/App.BOARD_SIZE;
            
        }
        start = BOARD_START_Y;
        for(int i=0;i<App.BOARD_SIZE;i++){
            if(i == 3 || i == 6)
                g2d.setStroke(new BasicStroke(3));
            else
                g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(start, BOARD_START_Y, start, PANEL_HEIGHT + BOARD_START_Y);
            start = start + PANEL_HEIGHT/App.BOARD_SIZE;
            
        }
        //------------------------
        //Draw the outline --- END
        drawBoard(board, g2d);

    }


    public void drawBoard(int[][] board, Graphics2D g){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){

                //fill an array of rectangles to represent 
                //board cells
                createCells(i,j);

                if(board[i][j] !=0 )
                    drawCenteredStringInGrid(String.valueOf(board[i][j]), i, j, g, new Font("Arial", Font.BOLD, 24));
               
            }
        }
    }

    private void createCells(int row, int col){
        int cellX = BOARD_START_X + col*(PANEL_WIDTH/App.BOARD_SIZE);
        int cellWidth = BOARD_START_X + col*(PANEL_WIDTH/App.BOARD_SIZE) + PANEL_WIDTH/App.BOARD_SIZE - cellX;
        int cellY = BOARD_START_Y + row*(PANEL_HEIGHT/App.BOARD_SIZE);
        int cellHeight = BOARD_START_Y + row*(PANEL_HEIGHT/App.BOARD_SIZE) + PANEL_HEIGHT/App.BOARD_SIZE - cellY;
        /*
            When creating the rectangles add a small margin of 2 pixels around them
        */
        cells[row][col] = new Rectangle(cellX+2,cellY+2,cellWidth-4,cellHeight-4);
        
    }

    private void drawCenteredStringInGrid(String str, int row, int col, Graphics2D g, Font font){
        //Calculate using row and col the bounds of the string

        FontMetrics metrics = g.getFontMetrics(font);

        //build rectangle
        int x = BOARD_START_X + col*(PANEL_WIDTH/App.BOARD_SIZE);
        int xx = BOARD_START_X + col*(PANEL_WIDTH/App.BOARD_SIZE) + PANEL_WIDTH/App.BOARD_SIZE;
        int y = BOARD_START_Y + row*(PANEL_HEIGHT/App.BOARD_SIZE);
        int yy = BOARD_START_Y + row*(PANEL_HEIGHT/App.BOARD_SIZE) + PANEL_HEIGHT/App.BOARD_SIZE;
        
        int startX = x + ((xx-x) - metrics.stringWidth(str)) / 2;
        int startY = y + (((yy-y) - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setFont(font);
        g.drawString(str, startX, startY);

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
       
        
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    } 

    public int getBoardStartX() {
        return BOARD_START_X;
    }

    public int getBoardStartY() {
        return BOARD_START_Y;
    }

    public int getPanelWidth() {
        return PANEL_WIDTH;
    }

    public int getPanelHeight() {
        return PANEL_HEIGHT;
    }

    public Rectangle[][] getCells() {
        return cells;
    }

    public void setCells(Rectangle[][] cells) {
        this.cells = cells;
    }
    
}