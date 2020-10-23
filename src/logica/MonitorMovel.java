package logica;

import logica.Sensores;
import logica.Ponto;
import exception.MonitorMovelException;
import java.util.List;

public abstract class MonitorMovel {

		private Sensores  sensores;
		private Ponto inicial;
		private Ponto atual;
		private int id;
		
		public MonitorMovel(Ponto inicial,Sensores sensores,int id){
			this.setInicial(inicial);
			this.setSensores(sensores);
			this.setId(id);
		}
		public MonitorMovel(Sensores sensores){
			this.setSensores(sensores);
		}
		public MonitorMovel(Ponto atual){
			this.setAtual(atual);
		}
		
		public void setSensores(Sensores sensores){
			this.sensores = sensores;
		}
		public void setInicial(Ponto inicial){
			this.inicial = inicial;
		}
		public void setAtual(Ponto atual){
			this.atual = atual;
		}
		public void setId(int id){
			this.id = id;
		}
		
	
		
		
		
		
		
		
		
}
