package logica;

import java.util.List;

import logica.Sensores;
import logica.MonitorConfig;
import exception.MonitorMovelException;

public abstract class MonitorMovel {

		public MonitorConfig config; ;
		public double abscissa;
		public double ordenada;
		public String id;
		
		public MonitorMovel(){
			
		}
		
		public MonitorMovel(String id,double abscissa,double ordenada,MonitorConfig config){
			super();
			this.setId(id);
			this.setAbscissa(abscissa);
			this.setOrdenada(ordenada);
			this.config = config;
			
		}
		
		public void setAbscissa(double abscissa){
			this.abscissa = abscissa;
		}
		public void setOrdenada(double ordenada){
			this.ordenada = ordenada;
		}
		public void setId(String id){
			this.id = id;
		}
		public String getId(){
			return this.id;
		} 
		public double getAbscissa(){
			return abscissa;
		}
		public double getOrdenada(){
			return ordenada;
		}
		private void setLocalizacao(double abscissa,double ordenada){
			this.abscissa = abscissa;
			this.ordenada = ordenada;
		}
		public MonitorConfig getConfig(){
			return config;
		}
		public void setMonitorConfig(MonitorConfig config){
			this.config = config;
		}
		
		@Override
		public String toString(){
			return "MonitorMovel[Posições Abscissa : "+getAbscissa()+getOrdenada()+
					"Sensores: "+config.getSensores()+"]";
		}
		public boolean equals(MonitorMovel monitor){
			return this.getId().contentEquals(monitor.getId());
		}
		
		public abstract double getCalcularTrajeto(double abscissa,double ordenada);
}
