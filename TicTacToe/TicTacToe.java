import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.plaf.BorderUIResource;
import java.awt.event.MouseMotionListener;
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

    TicTacToe () {

        windowcontent = new JPanel();
        windowcontent.setLayout(new BorderLayout());
        windowcontent.setBackground(Color.CYAN);

        Font mainFont = new Font("Monospased", Font.BOLD, 20);
        newGameButton = new JButton("New Game");

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



    }


    public static void main(String[] args){
        TicTacToe tic = new TicTacToe();

    }
    
}

class NewGame{
    JButton cross;
    JButton zero;
    JLabel label;
    JPanel windowContent;
    JFrame frame;
    String sign;
    
    NewGame (){
        cross = new JButton("0");
        zero = new JButton("x");
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
}

class NewGameAction implements ActionListener {

    NewGame game;

    NewGameAction (NewGame game){
        this.game = game;
    }

    public void actionPerformed(ActionEvent e) {        
        
        JButton clickButton = (JButton) e.getSource();
        game.sign = clickButton.getText();
        game.close();

    }

}


class Winner {

}

class TicTacToeAction implements ActionListener {

    TicTacToe game;
    NewGame chose;
    String sign;

    TicTacToeAction(TicTacToe game) {
        this.game = game;

    }

    public void actionPerformed(ActionEvent e){

        JButton clickButton = (JButton) e.getSource();

        if (clickButton == game.newGameButton){
            for (JButton but: game.squers){
                but.setText("");
                but.setBackground(Color.WHITE);
            }

            chose = new NewGame();
            chose.open();
            sign = chose.sign;
        }
    }


}