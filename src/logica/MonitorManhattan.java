package logica;

public class MonitorManhattan extends MonitorMovel{

private String id;
	
	public MonitorManhattan(Ponto atual,Sensores sensores){
		super(atual,sensores);
		this.setId(id);
	}
	
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	} 
	public Ponto getCalcularTrajeto(Ponto outro){
		double novoAbsc = 0;
		double novoOrd = 0;
		novoAbsc = Math.abs(outro.getAbscissa() - atual.getAbscissa());
		novoOrd = Math.abs(outro.getOrdenada() - atual.getOrdenada());
		atual.setAbscica(novoAbsc);
		atual.setOrdenada(novoOrd);
		return atual;
	}
}
