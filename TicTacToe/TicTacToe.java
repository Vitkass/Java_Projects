import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.plaf.BorderUIResource;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JOptionPane;

public class TicTacToe {

    JButton squers[] = new JButton [9];
    JPanel  windowcontent;
    JButton newGameButton;
    Label score;
    int emptySquaresLeft=9;
    JFrame frame1;
    String sign;
    Integer cross[] = new Integer[9];
    Integer zero []  = new Integer[9];
    Font mainFont;

    TicTacToe () {

        windowcontent = new JPanel();
        windowcontent.setLayout(new BorderLayout());
        windowcontent.setBackground(Color.CYAN);
        sign = "";

        mainFont = new Font("Monospased", Font.BOLD, 20);
        newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.ORANGE);

        JPanel p1 = new JPanel();
        GridLayout g1 = new GridLayout(3,3);
        p1.setLayout(g1);

        for (int i=0;i<squers.length; i++){
            squers[i] = new JButton("");
            p1.add(squers[i]);
        }

        windowcontent.add("Center", p1);
        windowcontent.add("South", newGameButton);


        frame1 = new JFrame("TicTacToe");
        frame1.setContentPane(windowcontent);
        frame1.pack();
        frame1.setBounds(300, 400, 400, 300);
        frame1.setVisible(true);
        TicTacToeAction action = new TicTacToeAction(this);
        newGameButton.addActionListener(action);
        Arrays.fill(cross, 0);
        Arrays.fill(cross, 0);
        


        for (JButton but: squers){
            but.addActionListener(action);
        }



    }


    public static void main(String[] args){
        TicTacToe tic = new TicTacToe();

    }
    
}

class NewGame{

    TicTacToe game;
    JButton cross;
    JButton zero;
    JLabel label;
    JPanel windowContent;
    JFrame frame;
    String sign;
    
    NewGame (TicTacToe game){
        this.game = game;
        cross = new JButton("0");
        zero = new JButton("x");
        zero.setBackground(Color.black);
        label = new JLabel("Who do you want to play?");
        windowContent = new JPanel();
        windowContent.setLayout(new BorderLayout());

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,2));

        p1.add(zero);
        p1.add(cross);

        windowContent.add("North", label);
        windowContent.add("Center", p1);

        frame = new JFrame();
        frame.setContentPane(windowContent);
        frame.setBounds(300,400, 200, 100);

        NewGameAction action = new NewGameAction(this);

        zero.addActionListener(action);
        cross.addActionListener(action);

        

    }

    public void close() {
        frame.setVisible(false);
    }

    public void open() {
        frame.setVisible(true);
    }

    public void CloseOpen(boolean visible) {
        frame.setVisible(visible);
    }

    public String choosenSign() {
        this.open();
        return "ds";
        

    }
}

class NewGameAction implements ActionListener {

    NewGame game;

    NewGameAction (NewGame game){
        this.game = game;
    }

    public void actionPerformed(ActionEvent e) {        
        
        JButton clickButton = (JButton) e.getSource();
        game.game.sign = clickButton.getText();
        game.close();

    }

}


class Winner {

    

    public static boolean win(Integer[] indexes) {
        
        boolean win = false;
        int first_horisontal = indexes[0]*indexes[1]*indexes[2];
        int second_horisontal = indexes[3]*indexes[4]*indexes[5];
        int third_horisontal = indexes[6]*indexes[7]*indexes[8];
        int first_vertical = indexes[0]*indexes[3]*indexes[6];
        int second_vertical = indexes[1]*indexes[4]*indexes[7];
        int third_vertical = indexes[2]*indexes[5]*indexes[8];
        int first_diagonal = indexes[0]*indexes[4]*indexes[8];
        int second_diagonal = indexes[2]*indexes[4]*indexes[6];

        if (first_diagonal==1 || second_diagonal==1 || first_horisontal==1 || second_horisontal==1 || third_horisontal==1 || first_vertical==1 || second_vertical==1 || third_vertical==1){
            win = true;
        }
        return win;
    }

}

class TicTacToeAction implements ActionListener {

    TicTacToe game;
    NewGame chose;
    String sign;
    Integer cross[];
    Integer zero [];
    String winner;
    boolean end = false;

    TicTacToeAction(TicTacToe game) {
        this.game = game;
        zero = game.zero;
        cross = game.cross;
        chose = new NewGame(game);
        

    }

    public void actionPerformed(ActionEvent e){

        JButton clickButton = (JButton) e.getSource();

        if (clickButton == game.newGameButton){
            for (JButton but: game.squers){
                but.setText("");
                but.setBackground(Color.WHITE);
            }

            end = false;
            winner = "";
            Arrays.fill(cross, 0);
            Arrays.fill(zero, 0);

            chose.open();


        }
        
        else {
            
            if (end){
                JOptionPane.showMessageDialog(null, winner + " Wins!\nPlease press New Game and choose your side");
                
            }
            
            sign = game.sign;
            switch(sign) {
                
                case(""):
                    JOptionPane.showMessageDialog(null, "Please press New Game and choose your side");
                    break;
                
                case("x"):
                    int index = Arrays.asList(game.squers).indexOf(clickButton);
                    cross[index] = 1;
                    game.squers[index].setText("x");
                    game.squers[index].setFont(game.mainFont);
                    game.sign = "0";
                    end = Winner.win(cross);
                    if (end){
                        winner = "X";
                        JOptionPane.showMessageDialog(null, winner + " Wins!\nPlease press New Game and choose your side");
                    }
                    break;
                
                case("0"):
                    int index1 = Arrays.asList(game.squers).indexOf(clickButton);
                    zero[index1] = 1;
                    game.squers[index1].setText("0");
                    game.squers[index1].setFont(game.mainFont);
                    game.sign = "x";
                    end = Winner.win(zero);
                    if (end){
                        winner = "O";
                        JOptionPane.showMessageDialog(null, winner + " Wins!\nPlease press New Game and choose your side");
                    }
                    break;
                    
                    
            }


        }
    }

    


}