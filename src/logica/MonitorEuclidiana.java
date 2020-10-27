package logica;

public class MonitorEuclidiana extends MonitorMovel{

	
	public MonitorEuclidiana(String id,double abscissa,double ordenada,MonitorConfig config){
		super(id,abscissa,ordenada,config);

	}
	
	@Override
	public double getCalcularTrajeto(double outroAbscissa,double outroOrdenada){
		double percurso;
		percurso =(double) Math.sqrt(Math.exp(outroAbscissa - this.getAbscissa())) + Math.sqrt(Math.exp(outroOrdenada - this.getOrdenada()));
		
		return percurso;
	}
	
	
}
