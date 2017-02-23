package Rater;
public class Croma{
	String oldName;
	String newName;
	public static final String[] AtributesNames = new String[]
	{"Color Zona 1","Color Zona 2", "Color Zona 3", "Color Zona 4","Transcicion Zona 1-2","Transcicion Zona 2-3","Transcicion Zona 3-4","Evolucion Radial", "Oxigenacion","Riqueza Mineral", "Riqueza Materia Organica","Riqueza Nutricional" };
	public static final int NumAtributes = AtributesNames.length;
	byte[] Atributes;

	public Croma(String Name, byte[] Atributes){
		this.oldName = Name;
		this.newName = Name;
		if(Atributes.length == NumAtributes)
			this.Atributes = Atributes;
	}
	public String toString(){
		String ret = newName+"//";
		for (int i=0;i<NumAtributes;i++) {
			ret = ret+AtributesNames[i]+":"+Atributes[i]+"//";
		}
		return ret;
	}
	public static void main(String[] args) {
		System.out.println(""+NumAtributes);
	}
}