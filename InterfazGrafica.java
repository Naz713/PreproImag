package Rater;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.image.*;
import java.awt.Image.*;
import javax.imageio.*;
import javax.swing.text.StyleConstants;
import java.time.LocalDateTime;

public class InterfazGrafica extends JFrame{
	//private BufferedImage image;
    private File imageFile;
	//private String currentFileName;
    private ImageField iField;
    private AtributesField aField;
    private ButtonsField bField;
    //private NameField nField;
    private int state;
    protected Croma croma;

	public InterfazGrafica(File imageFile, Croma croma){
		super("Organizando Cromas");
        state = 0;
        this.croma = croma;
        //currentIteNum = i;
        this.imageFile = imageFile;
        //System.out.println(imageFile.getName());
		//image = Accesser.getImage(imageFile);
        //currentFileName = image.getName();
		//System.out.println(image.getName());
		setDefaultVentPrinc(croma);
	}
    //guardar 2 veces una para DB otra para observador, buscar/preguntar dimensiones
    private void finalSaving(){
        if(state==1){
            BufferedImage imGuardar = iField.getOriginalImage();
            String directoryName = imageFile.getPath();
            directoryName=directoryName.substring(Accesser.sourceDN.length(),directoryName.length() - imageFile.getName().length());
            Accesser.saveImage(Accesser.cromasViewingDN+directoryName,imGuardar,imageFile.getName());
            Accesser.saveImage(Accesser.imageDBDN,imGuardar,LocalDateTime.now().toString()+" "+aField.getNewFileName());
            croma = aField.getCroma();
            Accesser.savetoDB(croma);
            imGuardar = null;
            imageFile.delete();
            //JFrame frame = new JFrame("Mensaje de Guardado1");
            //JOptionPane.showMessageDialog(frame, "Se han Guardado los datos");
        }
        if (state==-1) {
            System.exit(0);
        }
    }
	private void setDefaultVentPrinc(Croma croma){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground( Color.gray );
		setLayout(new BorderLayout());
		iField = new ImageField(imageFile);
        add(iField,BorderLayout.CENTER);
        aField = new AtributesField(imageFile.getName(),croma);
        //aField.setMinimumSize(new Dimension(100,10));
        add(aField,BorderLayout.LINE_END);
        bField = new ButtonsField();
        bField.addContainerListener(new ButtonsListener());
        add(bField,BorderLayout.PAGE_END);
        //nField = new NameField(currentFileName);
        add(aField.headLabel,BorderLayout.PAGE_START);
        setSize(new Dimension(iField.viewImage.getWidth()+300,iField.viewImage.getHeight()+100));
      	setVisible(true);
	}
    public class ButtonsListener implements ContainerListener {
        public void componentAdded(ContainerEvent e){}
        public void componentRemoved(ContainerEvent e){
            state = bField.getState();
            setVisible(false);
            finalSaving();
            removeAll();
            //remove(aField);
        }
    } 

    public static void main(String[] args) {
    	InterfazGrafica algo = new InterfazGrafica(Accesser.Retrive(1),null);
    }
}