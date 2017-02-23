package Rater;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.imageio.*;
import javax.swing.text.StyleConstants;
public class Principal{
	public static class ClosingListener implements ContainerListener {
        public void componentAdded(ContainerEvent e){}
        public void componentRemoved(ContainerEvent e){
        	JFrame frame = new JFrame("Mensaje de Guardado");
        	JOptionPane.showMessageDialog(frame, "Se han Guardado los datos");
            run();
        }
    }
	public static void run() {
		InterfazGrafica iG = null;
		try{
			iG = new InterfazGrafica(Accesser.Retrive(1));
			iG.addContainerListener(new ClosingListener());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		run();
	}
}