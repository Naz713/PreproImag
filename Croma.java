package Rater;
public class Croma{
	String oldName;
	String newName;
	public static final String[] NumericAtributesNames = new String[]
	{"Tamaño Zona 1", "Calidad Zona 1", "Interaccion Zona 1-2", "Tamaño Zona 2", "Calidad Zona 2",
	"Interaccion Zona 2-3", "Tamaño Zona 3", "Calidad Zona 3", "Interaccion Zona 3-4", "Tamaño Zona 4",
	"Calidad Zona 4", "Terminacion del Croma", "Nacimineto de los Radios (Lejania del Centro)",
	"Evolucion Radial","Error de Ejecucion (Croma o Foto)"};
	public static final int NoNumericAtributes = NumericAtributesNames.length;
	public static final int NumericAtributesSize = 5;
	public static final String[] DescriptiveAtributesNames = new String[]
	{"Cultivo o Vegetacion Primaria", "Ecorregion", "Tamiz", "Solucion", "Tiempo de Revelado" };
	public static final int NoDescriptiveAtributes = DescriptiveAtributesNames.length;
	public static final String[][] DescriptiveAtributesOptions = new String[][]
	{{"Maiz","Frijol","Aguacate","Cafe","Vegetacion Primaria"},{"Ecorregion 1", "Ecorregion 2","Ecorregion 3","Ecorregion 4"},
	{"0.595mm", "0.2mm"},{"NaOH","KOH"},{"10 dias", "15dias"}};
	byte[] NumericAtributes;
	byte[] DescriptiveAtributes;

	public Croma(String Name, byte[] NumericAtributes, byte[] DescriptiveAtributes){
		if (DescriptiveAtributesOptions.length!=NoDescriptiveAtributes) {
			System.out.println("No coinciden no de atributos");
		}
		this.oldName = Name;
		this.newName = Name;
		if(NumericAtributes.length == NoNumericAtributes)
			this.NumericAtributes = NumericAtributes;
		if(DescriptiveAtributes.length == NoDescriptiveAtributes)
			this.DescriptiveAtributes = DescriptiveAtributes;
	}
	public String toString(){
		String ret = newName+"//";
		for (int i=0;i<NoNumericAtributes;i++) {
			ret = ret+NumericAtributesNames[i]+":"+NumericAtributes[i]%NumericAtributesSize+"//";
		}
		for (int i=0;i<NoDescriptiveAtributes;i++) {
			ret = ret+DescriptiveAtributesNames[i]+":"+DescriptiveAtributesOptions[i][DescriptiveAtributes[i]]+"//";
		}
		return ret;
	}
	public static void main(String[] args) {
		//System.out.println(""+NumAtributes);
	}
}