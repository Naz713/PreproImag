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
        	InterfazGrafica iG = (InterfazGrafica)e.getSource();
        	Croma temporal = iG.croma;
            iG.dispose();
            iG = null;
            //System.out.println("listener");
            System.gc();
            JFrame frame = new JFrame("Mensaje de Guardado");
        	JOptionPane.showMessageDialog(frame, "Se han guardado los datos\nProcediendo al siguiente Croma");
        	
            run(temporal);
            return;
        }
    }
	public static void run(Croma croma) {
		System.gc();
		//System.out.println((croma==null)+"");
		File[] list = sourceFile.listFiles();
		//System.out.println(sourceFile.getName()+"/"+list.length);
		if (list.length == 0) {
			JFrame frame = new JFrame("Mensaje de Salida");
        	JOptionPane.showMessageDialog(frame, "Haz acabado con todos los Cromas :)");
			System.exit(0);
		}
		File actual = list[0];
		if (actual.isHidden()) {
			actual.delete();
			run(croma);
			return;
		}
		if(list.length==0){
			if (!sourceFile.getName().equals(Accesser.sourceDN)) {
				//sourceFile.listFiles()[0].delete();
				//System.out.println("-equal");
				File temporal = sourceFile;
				sourceFile = sourceFile.getParentFile();
				if (!temporal.delete()) {
					System.out.println("Carpeta no borrada, no vacia.");
				}
				//System.out.println("regreso");
				run(croma);
				return;
			}else{
				//System.out.println("sale");
				System.exit(0);
			}
		}
		if (actual.isDirectory()) {
			sourceFile = actual;
			//System.out.println("deep");
			run(croma);
			return;
		}
		InterfazGrafica iG = null;
		try{
			iG = new InterfazGrafica(actual,croma);
			iG.addContainerListener(new ClosingListener());
		}catch(Exception e){
			e.printStackTrace();
		}
		iG=null;
	}
	public static void main(String[] args) {
		sourceFile = new File(Accesser.sourceDN);
		//System.out.println("inicial");
		run(null);
		return;
	}
}