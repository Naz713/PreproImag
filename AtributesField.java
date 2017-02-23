package Rater;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.imageio.*;
import javax.swing.text.StyleConstants;
public class AtributesField extends JScrollPane{
    private JPanel principal;
    private JSlider[] sliderAtrib;
    private JLabel[] atribLbls;
    private JLabel[] atribValues;
    private byte[] values;
    protected JLabel headLabel;
    protected String fileName;
    protected String currentFileName;

	public AtributesField(String fileName){
		super();
        this.fileName = fileName;
		setBackground( Color.gray );
        principal = new JPanel();
		BoxLayout box = new BoxLayout(principal,BoxLayout.Y_AXIS);
		principal.setLayout(box);
        values = new byte[Croma.NumAtributes];
        setAtributes();
        //principal.setSize(500,700);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        principal.setVisible(true);
        //setSize(500,700);
        setViewportView(principal);
        //principal.setMinimumSize(new Dimension(1000,10));
        //setMinimumSize(new Dimension(1000,10));
        setVisible(true);
	}
    private void setAtributes(){
        headLabel = new JLabel();
        sliderAtrib = new JSlider[Croma.NumAtributes];
        atribLbls = new JLabel[Croma.NumAtributes];
        atribValues = new JLabel[Croma.NumAtributes];
        for (int i=0;i<Croma.NumAtributes;i++  ) {
            sliderAtrib[i] = new JSlider(1, 5, 3);
            sliderAtrib[i].setMinorTickSpacing(1);
            sliderAtrib[i].setMajorTickSpacing(5);
            sliderAtrib[i].setPaintTicks(true);
            atribLbls[i] = new JLabel(Croma.AtributesNames[i]);
            atribValues[i] = new JLabel();
            refreshValues(i);
            sliderAtrib[i].addChangeListener(new AtributesListener(i));
            principal.add(atribLbls[i]);
            principal.add(sliderAtrib[i]);
            principal.add(atribValues[i]);
        }
        refreshHeadLbl();
    }
    public String getNewFileName(){
        return currentFileName;
    }
    public byte[] getAtributesValues(){
        return values;
    }
    public Croma getCroma(){
        return new Croma(getNewFileName(),getAtributesValues());
    }
    public void refreshLbl(int i){
        atribValues[i].setText(sliderAtrib[i].getValue()+"");
    }
    private void refreshHeadLbl(){
        currentFileName = fileName.substring(0,fileName.length()-3)+" [";
        for (int j=0;j<Croma.NumAtributes;j++ ) {
            currentFileName += sliderAtrib[j].getValue()+"";
        }
        currentFileName +="]";
        headLabel.setText("Se guarda bajo el nombre: "+currentFileName);
    }
    public class AtributesListener implements ChangeListener {
        private int i;
        public AtributesListener(int i){
            this.i=i;
        }
        public void stateChanged(ChangeEvent e) {
            refreshValues(i);
            refreshHeadLbl();
            //values[i] = (byte)(((JSlider)e.getSource()).getValue());
            //System.out.println(i+""+values[i]);
            //refreshLbl(i);
        }
    }
    public void refreshValues(int i){
        values[i] = (byte)sliderAtrib[i].getValue();
        refreshLbl(i);
    }
    public static void main(String[] args) {
    	JFrame frame = new JFrame("Prueba Titulo");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	AtributesField panel = new AtributesField("Archivo");
    	frame.add(panel,BorderLayout.CENTER);
    	frame.setSize(300,200);
    	frame.setVisible(true);
    }
}