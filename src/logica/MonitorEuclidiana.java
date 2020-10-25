package logica;


import logica.Sensores;

import logica.Ponto;


public class MonitorEuclidiana extends MonitorMovel{

	private String id;
	
	public MonitorEuclidiana(Ponto atual,Sensores sensores){
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
		novoAbsc = Math.sqrt(Math.exp(outro.getAbscissa() - atual.getAbscissa()));
		novoOrd = Math.sqrt(Math.exp(outro.getOrdenada() - atual.getOrdenada()));
		atual.setAbscica(novoAbsc);
		atual.setOrdenada(novoOrd);
		return atual;
	}
	
	
}