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
	
	protected static File sourceFile;

	public static class ClosingListener implements ContainerListener {
        public void componentAdded(ContainerEvent e){}
        public void componentRemoved(ContainerEvent e){
        	//JFrame frame = new JFrame("Mensaje de Guardado2");
        	//JOptionPane.showMessageDialog(frame, "Procediendo al siguiente Croma");
            ((InterfazGrafica)e.getSource()).dispose();
            //System.out.println("listener");
            run();
            return;
        }
    }
	public static void run() {
		File[] list = sourceFile.listFiles();
		//System.out.println(sourceFile.getName()+"/"+list.length);
		if(list.length<=1){
			if (!sourceFile.getName().equals(Accesser.sourceDN)) {
				sourceFile.listFiles()[0].delete();
				//System.out.println("-equal");
				File temporal = sourceFile;
				sourceFile = sourceFile.getParentFile();
				if (!temporal.delete()) {
					System.out.println("Carpeta no borrada, no vacia.");
				}
				//System.out.println("regreso");
				run();
				return;
			}else{
				//System.out.println("sale");
				System.exit(0);
			}
		}
		File actual = list[1];
		if (actual.isDirectory()) {
			sourceFile = actual;
			//System.out.println("deep");
			run();
			return;
		}
		InterfazGrafica iG = null;
		try{
			iG = new InterfazGrafica(actual);
			iG.addContainerListener(new ClosingListener());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		sourceFile = new File(Accesser.sourceDN);
		//System.out.println("inicial");
		run();
		return;
	}
}