package logica;

import logica.Sensores;
import logica.Ponto;
import exception.MonitorMovelException;
import java.util.List;

public abstract class MonitorMovel {
	
		public static final String cameraVideo = "cftv";
		public static final String temperatura = "temp";
		public static final String sensorCarbono = "Co2";
		public static final String sensorMetano = "ch4";

		protected Sensores sensores;
		protected Ponto inicial;
		protected Ponto atual;
		
		public MonitorMovel(Ponto inicial,Sensores sensores){
			this.setInicial(inicial);
			this.sensores = sensores;
			
		}
		public MonitorMovel(Sensores sensores){
			this.sensores =sensores;
		}
		public MonitorMovel(Ponto atual){
			this.setAtual(atual);
		}
		
		
		public void setInicial(Ponto inicial){
			this.inicial = inicial;
		}
		public void setAtual(Ponto atual){
			this.atual = atual;
		}		
	
		@Override
		public String toString(){
			return "MonitorMovel[Posições Atuais : "+atual.getOrdenada()+atual.getAbscissa()+
					"Sensores: "+sensores.getSensores()+"]";
		}
		
		public abstract Ponto getCalcularTrajeto(Ponto outro);
}
