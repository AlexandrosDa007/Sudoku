import java.awt.Font;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SudokuControls {
    
    public int selectedNumber = -9;
    private final int MARGIN_LEFT = 150;
    private final int BUTTON_WIDTH = 60;
    private final int MARGIN_BETWEEN_BUTTONS = 10;
    private ButtonGroup group = new ButtonGroup();


    public SudokuControls(SudokuDraw gamepanel){

        

        JToggleButton button;
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        

        //the x coordinate of the pixel to start drawing the buttons
        int pixelToStart = gamepanel.getBoardStartX() + gamepanel.getPanelWidth() + MARGIN_LEFT;
        

        int x = pixelToStart;
        int y = gamepanel.getBoardStartY();
        for(int i=1;i<=App.BOARD_SIZE;i++){
            button = new JToggleButton(String.valueOf(i));
            //Adding a listener for the click event of the butttons
            //When a button gets pressed the selectedNumber variable changes,
            //accordingly with the button's string
            button.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e){
                    selectedNumber = Integer.parseInt(((JToggleButton)e.getSource()).getText());
                }
            });

            group.add(button);
            button.setFont(buttonFont);
            button.setBounds(x, y ,BUTTON_WIDTH,BUTTON_WIDTH);
            button.setMargin(new Insets(5,5,5,5));
            gamepanel.add(button);
            
            //Make a grid of 3 x 3 with buttons
            if(i == 3 || i == 6){
                x = pixelToStart;
                y = y + BUTTON_WIDTH + MARGIN_BETWEEN_BUTTONS;
            }else{
                x = x + BUTTON_WIDTH + MARGIN_BETWEEN_BUTTONS;
                
            }
        }

        
        JButton resetButton = new JButton("Reset"); 
        
        resetButton.setBounds(pixelToStart , 400 ,200,50);
        gamepanel.add(resetButton);
        resetButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            App.frame.dispose();
            App.createGame();
        }
        });
    }

   
   
    
}