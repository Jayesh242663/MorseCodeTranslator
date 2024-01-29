package morseCodeTranslator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class menu extends JFrame implements ActionListener {
    Font f1 = new Font("tahoma",Font.BOLD,20);
    private static final HashMap<Character, String> charToMorseMap = new HashMap<>();
     //hash map for text to morsecode
    static {
        charToMorseMap.put('A', ".-");
        charToMorseMap.put('B', "-...");
        charToMorseMap.put('C', "-.-.");
        charToMorseMap.put('D', "-..");
        charToMorseMap.put('E', ".");
        charToMorseMap.put('F', "..-.");
        charToMorseMap.put('G', "--.");
        charToMorseMap.put('H', "....");
        charToMorseMap.put('I', "..");
        charToMorseMap.put('J', ".---");
        charToMorseMap.put('K', "-.-");
        charToMorseMap.put('L', ".-..");
        charToMorseMap.put('M', "--");
        charToMorseMap.put('N', "-.");
        charToMorseMap.put('O', "---");
        charToMorseMap.put('P', ".--.");
        charToMorseMap.put('Q', "--.-");
        charToMorseMap.put('R', ".-.");
        charToMorseMap.put('S', "...");
        charToMorseMap.put('T', "-");
        charToMorseMap.put('U', "..-");
        charToMorseMap.put('V', "...-");
        charToMorseMap.put('W', ".--");
        charToMorseMap.put('X', "-..-");
        charToMorseMap.put('Y', "-.--");
        charToMorseMap.put('Z', "--..");
        charToMorseMap.put('0', "-----");
        charToMorseMap.put('1', ".----");
        charToMorseMap.put('2', "..---");
        charToMorseMap.put('3', "...--");
        charToMorseMap.put('4', "....-");
        charToMorseMap.put('5', ".....");
        charToMorseMap.put('6', "-....");
        charToMorseMap.put('7', "--...");
        charToMorseMap.put('8', "---..");
        charToMorseMap.put('9', "----.");
        charToMorseMap.put(' ', " ");
    }
    private static final HashMap<String, Character> morseToCharMap = new HashMap<>();
    //hash map for Morse code to text
    static {
        morseToCharMap.put(".-", 'A');
        morseToCharMap.put("-...", 'B');
        morseToCharMap.put("-.-.", 'C');
        morseToCharMap.put("-..", 'D');
        morseToCharMap.put(".", 'E');
        morseToCharMap.put("..-.", 'F');
        morseToCharMap.put("--.", 'G');
        morseToCharMap.put("....", 'H');
        morseToCharMap.put("..", 'I');
        morseToCharMap.put(".---", 'J');
        morseToCharMap.put("-.-", 'K');
        morseToCharMap.put(".-..", 'L');
        morseToCharMap.put("--", 'M');
        morseToCharMap.put("-.", 'N');
        morseToCharMap.put("---", 'O');
        morseToCharMap.put(".--.", 'P');
        morseToCharMap.put("--.-", 'Q');
        morseToCharMap.put(".-.", 'R');
        morseToCharMap.put("...", 'S');
        morseToCharMap.put("-", 'T');
        morseToCharMap.put("..-", 'U');
        morseToCharMap.put("...-", 'V');
        morseToCharMap.put(".--", 'W');
        morseToCharMap.put("-..-", 'X');
        morseToCharMap.put("-.--", 'Y');
        morseToCharMap.put("--..", 'Z');
        morseToCharMap.put("-----", '0');
        morseToCharMap.put(".----", '1');
        morseToCharMap.put("..---", '2');
        morseToCharMap.put("...--", '3');
        morseToCharMap.put("....-", '4');
        morseToCharMap.put(".....", '5');
        morseToCharMap.put("-....", '6');
        morseToCharMap.put("--...", '7');
        morseToCharMap.put("---..", '8');
        morseToCharMap.put("----.", '9');
        morseToCharMap.put(" ", ' '); // Space between words
    }
    JLabel input,outcome;
    JTextField Message,output;
    public static String output1;
    JButton translate;
    menu(){
        setSize(400,500);

        input = new JLabel("ENTER MORSE CODE OR MESSAGE");
        input.setBounds(20,70,350,40);
        input.setForeground(Color.decode("#0000"));
        input.setFont(new Font("tahoma",Font.BOLD,18));
        add(input);

        outcome = new JLabel("OUTPUT:");
        outcome.setBounds(155,135,150,40);
        outcome.setForeground(Color.decode("#0000"));
        outcome.setFont(new Font("tahoma",Font.BOLD,18));
        add(outcome);

        Message = new JTextField();
        Message.setBounds(100,105,200,30);
        Message.setFont(new Font("tahoma",Font.PLAIN,16));
        Message.addActionListener(e -> translate.requestFocusInWindow());
        Message.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(Message.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"ENTER THE MESSAGE");
                    }else {
                        String message = Message.getText();
                        if (message.contains(".")||message.contains("-")){
                            output1 = morseToText(message);
                            System.out.println(output1);
                            output.setText(menu.output1);
                        }else{
                            output1= textToMorse(message);
                            System.out.println(output1);
                            output.setText(menu.output1);
                        }
                    }
                }
            }
        });
        add(Message);

        output = new JTextField(menu.output1);
        output.setBounds(100,170,200,30);
        output.setFont(new Font("tahoma",Font.PLAIN,16));
        add(output);

        translate = new JButton("TRANSLATE");
        translate.setBounds(150,250,110,40);
        translate.setBackground(Color.decode("#112b1e"));
        translate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered (MouseEvent e){
               translate.setBackground(Color.decode("#040908"));
            }
            @Override
            public void mouseExited(MouseEvent e){
                translate.setBackground(Color.decode("#112b1e"));
            }
        });
        translate.addActionListener(this);
        add(translate);

        this.getContentPane().setBackground(Color.decode("#112b1e"));
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public static String textToMorse(String text) {
        StringBuilder morseCode = new StringBuilder();
        for (char character : text.toUpperCase().toCharArray()) {
            String code = charToMorseMap.get(character);
            if (code != null) {
                morseCode.append(code).append(" ");
            }else{

               JOptionPane.showMessageDialog(null,"ENTER PROPER INPUT");
               break;
            }
        }

        return morseCode.toString();
    }
    public static String morseToText(String morseCode) {
        StringBuilder text = new StringBuilder();
        String[] words = morseCode.split(" / ");
        //Arrays.toString(words);
        System.out.println(words);
        for (String word : words) {
            String[] symbols = word.split(" ");
            for (String symbol : symbols) {
                Character character = morseToCharMap.get(symbol);
                if (character != null) {
                    text.append(character);
                } else {
                    text.append("?"); // Handle unknown symbols
                    JOptionPane.showMessageDialog(null,"ENTER PROPER INPUT");
                }
            }
            text.append(' ');
        }
        return text.toString().trim();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UIManager.put("OptionPane.messageFont",f1);
        if (e.getSource()==translate){
            if(Message.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"ENTER THE MESSAGE");
            }else {
                String message = Message.getText();
                if (message.contains(".")||message.contains("-")){
                    output1 = morseToText(message);
                    System.out.println(output1);
                    output.setText(menu.output1);
                }else{
                    output1= textToMorse(message);
                    System.out.println(output1);
                    output.setText(menu.output1);
                }
            }
        }
    }

    public static void main(String[] args) {

        new menu();
    }

}
