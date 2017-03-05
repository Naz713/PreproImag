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
    private JLabel[] nAtribLbls;
    private JLabel[] nAtribValues;
    private JComboBox<String>[] comboAtrib;
    private JLabel[] dAtribLbls;
    private JLabel[] dAtribValues;
    private byte[] numericValues;
    private byte[] descriptiveValues;
    protected JLabel headLabel;
    protected String fileName;
    protected String currentFileName;

	public AtributesField(String fileName, Croma croma){
		super();
        this.fileName = fileName;
		setBackground( Color.gray );
        principal = new JPanel();
		BoxLayout box = new BoxLayout(principal,BoxLayout.Y_AXIS);
		principal.setLayout(box);
        numericValues = new byte[Croma.NoNumericAtributes];
        descriptiveValues = new byte[Croma.NoDescriptiveAtributes];
        setAtributes(croma);
        //principal.setSize(500,700);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        principal.setVisible(true);
        //setSize(500,700);
        setViewportView(principal);
        //principal.setMinimumSize(new Dimension(1000,10));
        //setMinimumSize(new Dimension(1000,10));
        setVisible(true);
	}
    private void setAtributes(Croma croma){
        headLabel = new JLabel();
        sliderAtrib = new JSlider[Croma.NoNumericAtributes];
        nAtribLbls = new JLabel[Croma.NoNumericAtributes];
        nAtribValues = new JLabel[Croma.NoNumericAtributes];
        for (int i=0;i<Croma.NoNumericAtributes;i++  ) {
            int aux = (Croma.NumericAtributesSize/2);
            if (croma!=null) {
                aux=croma.NumericAtributes[i];
            }
            sliderAtrib[i] = new JSlider(1, Croma.NumericAtributesSize, aux );
            sliderAtrib[i].setMinorTickSpacing(1);
            sliderAtrib[i].setMajorTickSpacing(Croma.NumericAtributesSize/2);
            sliderAtrib[i].setPaintTicks(true);
            nAtribLbls[i] = new JLabel(Croma.NumericAtributesNames[i]);
            nAtribValues[i] = new JLabel();
            refreshSliderValue(i);
            sliderAtrib[i].addChangeListener(new AtributesListener(i));
            principal.add(nAtribLbls[i]);
            principal.add(sliderAtrib[i]);
            principal.add(nAtribValues[i]);
        }
        comboAtrib = new JComboBox[Croma.NoDescriptiveAtributes];
        dAtribLbls = new JLabel[Croma.NoDescriptiveAtributes];
        dAtribValues = new JLabel[Croma.NoDescriptiveAtributes];
        for (int i=0;i<Croma.NoDescriptiveAtributes;i++  ) {
            comboAtrib[i] = new JComboBox<String>(Croma.DescriptiveAtributesOptions[i]);
            if (croma!=null) {
                comboAtrib[i].setSelectedIndex(croma.DescriptiveAtributes[i]);
            }
            dAtribLbls[i] = new JLabel(Croma.DescriptiveAtributesNames[i]);
            dAtribValues[i] = new JLabel();
            refreshComboValue(i);
            comboAtrib[i].addActionListener(new DAtributesListener(i));
            principal.add(dAtribLbls[i]);
            principal.add(comboAtrib[i]);
            principal.add(dAtribValues[i]);
        }
        refreshHeadLbl();
    }
    public String getNewFileName(){
        return currentFileName;
    }
    public byte[] getNumericAtributesValues(){
        return numericValues;
    }
    public byte[] getDescriptiveAtributesValues(){
        return descriptiveValues;
    }
    public Croma getCroma(){
        return new Croma(getNewFileName(),getNumericAtributesValues(),getDescriptiveAtributesValues());
    }
    private void refreshHeadLbl(){
        currentFileName = fileName.substring(0,fileName.length()-3)+" [";
        for (int j=0;j<Croma.NoNumericAtributes;j++ ) {
            currentFileName += sliderAtrib[j].getValue()+"";
        }
        for (int j=0;j<Croma.NoDescriptiveAtributes;j++ ) {
            currentFileName += comboAtrib[j].getSelectedIndex()+"";
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
            refreshSliderValue(i);
            refreshHeadLbl();
            //values[i] = (byte)(((JSlider)e.getSource()).getValue());
            //System.out.println(i+""+values[i]);
            //refreshLbl(i);
        }
    }
    public class DAtributesListener implements ActionListener {
        private int i;
        public DAtributesListener(int i){
            this.i=i;
        }
        public void actionPerformed(ActionEvent e) {
            refreshComboValue(i);
            refreshHeadLbl();
            //values[i] = (byte)(((JSlider)e.getSource()).getValue());
            //System.out.println(i+""+values[i]);
            //refreshLbl(i);
        }
    }
    public void refreshSliderValue(int i){
        numericValues[i] = (byte)sliderAtrib[i].getValue();
        refreshNumericLbl(i);
    }
    public void refreshNumericLbl(int i){
        nAtribValues[i].setText(sliderAtrib[i].getValue()+"");
    }
    public void refreshComboValue(int i){
        descriptiveValues[i] = (byte)comboAtrib[i].getSelectedIndex();
        refreshDescriptiveLbl(i);
    }
    public void refreshDescriptiveLbl(int i){
        dAtribValues[i].setText(comboAtrib[i].getItemAt(comboAtrib[i].getSelectedIndex()));
    }
    public static void main(String[] args) {
    	JFrame frame = new JFrame("Prueba Titulo");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	AtributesField panel = new AtributesField("Archivo",null);
    	frame.add(panel,BorderLayout.CENTER);
    	frame.setSize(300,200);
    	frame.setVisible(true);
    }
}