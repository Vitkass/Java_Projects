import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JOptionPane;





public class SimpleCalculater{

    // Объявление всех компонентов калькулятора
    JPanel windowContent;
    JTextField displayField;
    JButton numButtons[] = new JButton [10];
    JButton buttonPoint;
    JButton butSum;
    JButton butSubtraction;
    JButton butDivision;
    JButton butMulty;
    JButton buttonEqual;
    JPanel p1;
    JPanel p2;


    // В конструкторе создаются все компоненты
    // и добавляются на фрейм с помощью комбинации 
    // Borderlayout и Gridlayout
    SimpleCalculater () {

        windowContent = new JPanel();

        // Задаём схему для этой панели
        BorderLayout b1 = new BorderLayout();
        windowContent.setLayout(b1);

        // Создаём и отображаем поле
        // Добавляем его в Северную область окна
        
        displayField = new JTextField(30);
        windowContent.add("North", displayField);

        // Создаём кнопки, используя конструктор 
        // класса JButton, который принимает текст 
        // кнопки в качестве параметра
    

        // Создаём панель с GridLayout
        // которая содержит 12 кнопок - 10 кнопок с числами 
        // и кнопки с точкой и знаком равно 

        p1 = new JPanel();
        GridLayout g1 = new GridLayout(4,2);
        p1.setLayout(g1);

        p2 = new JPanel();
        GridLayout g2 = new GridLayout(4,1);
        p2.setLayout(g2);

        

        // Добовляем кнопки на панель p1
        // Создаём кнопки, используя конструктор 
        // класса JButton, который принимает текст 
        // кнопки в качестве параметра
        
        buttonPoint = new JButton("Ce"); 
        buttonEqual= new JButton("=");
        butMulty = new JButton("*");
        butDivision = new JButton("/");
        butSum = new JButton("+");
        butSubtraction = new JButton("-");

        for (int i=0; i<numButtons.length; i++){
            
            String name = Integer.toString(i);
            numButtons[i] = new JButton(name);
            p1.add(numButtons[i]);

        }

        p2.add(butMulty);
        p2.add(butDivision);
        p2.add(butSum);
        p2.add(butSubtraction);

        p1.add(buttonEqual);
        p1.add(buttonPoint);

        // Помещаем панель p1 в центральную область окна
        windowContent.add("Center", p1);
        windowContent.add("East", p2);

        // Создаём фрейм и задаём его основную панель
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(windowContent);

        // делаем размер окна достаточным
        // для того, чтобы вместить все компоненты
        frame.pack();

        // Наконец, отображаем окно
        frame.setVisible(true);

        CalcEng calcEngine = new CalcEng(this);

        for (int i=0; i<numButtons.length; i++){
            numButtons[i].addActionListener(calcEngine);
        }

        buttonPoint.addActionListener(calcEngine);
        buttonEqual.addActionListener(calcEngine);
        butMulty.addActionListener(calcEngine);
        butDivision.addActionListener(calcEngine);
        butSum.addActionListener(calcEngine);
        butSubtraction.addActionListener(calcEngine);

        

    }

    public static void main(String[] args) {

        SimpleCalculater calc = new SimpleCalculater();
    }

    
}

class Main{
    

    public static boolean elementFind(Object[] array, Object element){
        boolean flag = false;
        
        for (Object el: array){

            if (el == element){
                flag = true;
                break;
            }

        }

        return flag;

    }
}

class CalcEng implements ActionListener {

    SimpleCalculater parent;
    JButton clickButton;
    String displayText;
    Double firstNumber;
    Double secondNumber;
    String sign; 



    CalcEng(SimpleCalculater parent){
        this.parent = parent;
        firstNumber = null;
        secondNumber = null;
        this.sign = "";
    }



    public void actionPerformed(ActionEvent e) {
        
        clickButton = (JButton) e.getSource();
        displayText = parent.displayField.getText();
        System.out.print(clickButton.getText());
        boolean contein = Main.elementFind(parent.numButtons, clickButton);

        if (displayText.equals("") && contein == false)
        {
            JOptionPane.showConfirmDialog(null, "You can not use " + clickButton.getText()+", enter some number", "Just a text", JOptionPane.PLAIN_MESSAGE);
        }

        else {

            switch(clickButton.getText()){

                case("+"):
                    firstNumber = Double.parseDouble(displayText);
                    parent.displayField.setText("");
                    sign = clickButton.getText();
                    break;
                
                case("*"):
                    firstNumber = Double.parseDouble(displayText);
                    parent.displayField.setText("");
                    sign = clickButton.getText();
                    break;
                
                case("/"):
                    firstNumber = Double.parseDouble(displayText);
                    parent.displayField.setText("");
                    sign = clickButton.getText();
                    break;
                
                case("-"):
                    firstNumber = Double.parseDouble(displayText);
                    parent.displayField.setText("");
                    sign = clickButton.getText();
                    break;
                
                case("="):

                    if (firstNumber == null){
                        JOptionPane.showMessageDialog(null, "Please enter first number, and choose operation");
                        break;

                    }
                    
                    else if (displayText.equals("")){
                        JOptionPane.showMessageDialog(null, "Please enter second number");
                        break;

                    }

                    else {

                        secondNumber = Double.parseDouble(displayText);
                        switch(sign){
                            case("+"):
                                parent.displayField.setText(String.valueOf(firstNumber+secondNumber));
                                firstNumber = null;
                                secondNumber = null;
                                sign = "";
                                break;
                            
                            case("-"):
                                parent.displayField.setText(String.valueOf(firstNumber-secondNumber));
                                firstNumber = null;
                                secondNumber = null;
                                sign = "";
                                break;
                            
                            case("*"):
                                parent.displayField.setText(String.valueOf(firstNumber*secondNumber));
                                firstNumber = null;
                                secondNumber = null;
                                sign = "";
                                break;
                            
                            case("/"):
                                parent.displayField.setText(String.valueOf(firstNumber/secondNumber));
                                firstNumber = null;
                                secondNumber = null;
                                sign = "";
                                break;
                        }

                    }
                    break;
                
                case("Ce"):
                    parent.displayField.setText("");
                    firstNumber = null;
                    secondNumber = null;
                    sign = "";
                    break;
                
                default:
                    String presentValue = displayText + clickButton.getText();
                    parent.displayField.setText(presentValue);
                    

                
            }

        }

    }

}



