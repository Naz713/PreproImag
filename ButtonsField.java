package Rater;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.imageio.*;
import javax.swing.text.StyleConstants;
public class ButtonsField extends JPanel{
	private JButton listo;
    private JButton cancelar;
    private int state;

	public ButtonsField(){
		super();
		setBackground( Color.gray );
		BoxLayout box = new BoxLayout(this,BoxLayout.X_AXIS);
		setLayout(box);
        setButtons();
        setVisible(true);
    }
    void setButtons(){
        state = 0;
        listo = new JButton("Listo!");
        cancelar = new JButton("Cancelar y Terminar");
        listo.addActionListener(new ClickAction());
        cancelar.addActionListener(new ClickAction());
        add(cancelar);
        add(listo);
    }
    public int getState(){
        return state;
    }
    public class ClickAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(listo)) {
                //System.out.println("Listo!");
                //ActionEvent action = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Listo");
                state = 1;
            }else if (e.getSource().equals(cancelar)) {
                //System.out.println("Cancelar");
                //ActionEvent action = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Cancelar");
                state = -1;
            }
            remove(cancelar);
        }
    }
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	ButtonsField panel = new ButtonsField();
    	frame.add(panel,BorderLayout.CENTER);
    	frame.setSize(300,200);
    	frame.setVisible(true);
    }
}