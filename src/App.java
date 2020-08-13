import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App {


    //A static reference to the window of the game
    public static JFrame frame;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 720;
    public static final int BOARD_SIZE = 9;
    public static final int NUMBER_OF_DIGITS_TO_REMOVE = 45;
    
    public static void main(String[] args) throws Exception {
        createGame();
    }


    /*
    Setting up the JFrame, the game panel and adding listener for mouse click
    */
    public static void createGame(){
        // setting up JFrame
        frame = new JFrame("Sudoku Puzzle by alexdaltsis");
        
        frame.setSize(WIDTH, HEIGHT);
        
        //Setting the look and feel of the swing components
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        //Setting up the board
        SudokuMaker sudoku = new SudokuMaker(BOARD_SIZE, NUMBER_OF_DIGITS_TO_REMOVE);
        sudoku.fillBoard();

        //Setting up the game panel
        SudokuDraw gamePanel = new SudokuDraw();
        SudokuControls sudokuControls = new SudokuControls(gamePanel);
        
        gamePanel.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e) {
                /*  
                    find the row and col and get the number
                    find a way to check if mouse is over a 
                    empty spot to place number
                    otherwise dont let it
                */
                Rectangle[][] list = gamePanel.getCells();
                for(int i=0;i<BOARD_SIZE;i++){
                    for(int j=0;j<BOARD_SIZE;j++){
                        if(list[i][j].contains(e.getPoint())){
                            if(sudoku.getBoard()[i][j] != 0)
                                JOptionPane.showMessageDialog(frame, "You can't change theese numbers!", "Wrong input", JOptionPane.ERROR_MESSAGE);
                            else if(sudoku.addOnBoard(i, j, sudokuControls.selectedNumber)){
                                gamePanel.repaint();
                                sudoku.checkIfGameIsOver();
                            }
                            else
                                JOptionPane.showMessageDialog(frame, "Wrong number!", "Wrong input", JOptionPane.ERROR_MESSAGE);
                            
                            break;
                        }
                    } 
                }
                
                
            }

            
        });


        gamePanel.setLayout(null);
        gamePanel.setSize(WIDTH,HEIGHT);
        gamePanel.setBoard(sudoku.getBoard());
        
        frame.add(gamePanel);
        frame.setVisible(true);
    }
}
