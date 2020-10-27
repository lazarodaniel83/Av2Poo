package logica;

public class MonitorManhattan extends MonitorMovel{

	
	public MonitorManhattan(String id,double abscissa,double ordenada,MonitorConfig config){
		super(id,abscissa,ordenada,config);
	}
	

	public double getCalcularTrajeto(double outroAbscissa,double outroOrdenada){
		
		double percurso;
		percurso = (double)Math.pow(outroAbscissa - this.getAbscissa(),2) - Math.pow(outroOrdenada - this.getOrdenada(),2);	
		
		return percurso;
	}
}


