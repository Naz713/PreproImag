package Rater;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.imageio.*;
import java.awt.image.*;
import javax.swing.text.StyleConstants;
public class ImageField extends JPanel{
    private static final  String[] filtros = new String[]
    {"Normal", "Edge Detection", "Sharpen","Unsharp Masking 5x5"};
    //private File imageFile;
    private BufferedImage image;
    protected BufferedImage viewImage;
    private int imageNumber;
    private JLabel imageLbl;
    private JComboBox<String> filtCombo;
    //private static final int viewWidth = ;
    //private static final int viewHeight = ;

	public ImageField(File imageFile){
		super();
        //this.imageFile=imageFile;
        image = Accesser.getImage(imageFile);
        viewImage = getViewImage(image);
		setBackground( Color.gray );
		BoxLayout box = new BoxLayout(this,BoxLayout.Y_AXIS);
		setLayout(box);
        filtCombo = new JComboBox<String>(filtros);
        filtCombo.addActionListener(new FilterListener());
        imageLbl = new JLabel(new ImageIcon(viewImage));
        imageNumber = 0;
        refreshImage(0);
        JScrollPane scrollPane = new JScrollPane(imageLbl);
        //scrollPane.setMaximumSize(new Dimension(5+image.getWidth(),20+image.getHeight()));
        add(scrollPane);
        add(filtCombo);
        setMaximumSize(new Dimension(5+viewImage.getWidth(),2000));
		setVisible(true);
	}
    public BufferedImage getOriginalImage(){
        return image;
    }
    public static BufferedImage getViewImage(BufferedImage rsImage){
        if (Math.max(rsImage.getWidth(),rsImage.getHeight())>2000) {
            return Accesser.scaleImage(rsImage,(int)rsImage.getWidth()/10,(int)rsImage.getHeight()/10);
        }else {
            return rsImage;
        }
    }
    public static BufferedImage copyBI(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    //Mejorar codigo para ahorrar memoria, set image to the icon label and change if every time just once
    private void refreshImage(int i){
        if (i==(imageNumber%filtros.length)) {
            return;
        }
        imageNumber = i%(filtros.length);
        BufferedImage auxiliar = copyBI(viewImage);
        ConvolveOp op = null;
        switch (i%(filtros.length)) {
            case 0:
                imageLbl.setIcon(new ImageIcon(auxiliar));
                return;
            case 1:
                op = new ConvolveOp(new Kernel(3,3,new float[]{-1,-1,-1,-1,8,-1,-1,-1,-1}));
                break;
            case 2:
                op = new ConvolveOp(new Kernel(3,3,new float[]{0,-1,0,-1,5,-1,0,-1,0}));
                break;
            case 3:
                op = new ConvolveOp(new Kernel(5,5,new float[]{-1/256,-4/256,-6/256,-4/256,-1/256,-4/256,-16/256,-24/256,-16/256,-4/256,-6/256,-24/256,476/256,-24/256,-6/256,-4/256,-16/256,-24/256,-16/256,-4/256,-1/256,-4/256,-6/256,-4/256,-1/256} ));
                break;
        }
        Graphics2D g2d = (Graphics2D)auxiliar.getGraphics();
        g2d.drawImage(auxiliar,op,0,0);
        imageLbl.setIcon(new ImageIcon(auxiliar));
    }
    public class FilterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //System.out.println("Cambio de filtro");
            refreshImage(((JComboBox)e.getSource()).getSelectedIndex());
        }
    }
    public static void main(String[] args) {
    	JFrame frame = new JFrame("Prueba ImageField");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	ImageField panel = new ImageField(Accesser.Retrive(1));
    	frame.add(panel,BorderLayout.CENTER);
    	//frame.setSize(panel.image.getWidth(),1000);
    	frame.setVisible(true);
    }
}