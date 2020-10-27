package logica;

import java.util.ArrayList;

public class MonitorConfig {

	ArrayList<Sensores> sensores = new ArrayList<Sensores>();
	
	public ArrayList<Sensores> getSensores(){
		return sensores;
	}
	public void adicionarMonitor(Sensores sensor){
		sensores.add(sensor);
	}
	public void removerMonitor(Sensores sensor){
		sensores.remove(sensor);
	}
	public void addSensorMonitor(boolean cameraVideo,boolean temperatura,boolean sensorCarbono,boolean sensorMetano){
		if(cameraVideo == true){
			sensores.add(Sensores.cameraVideo);
		}
		if(temperatura == true){
			sensores.add(Sensores.temperatura);
		}
		if(sensorCarbono == true){
			sensores.add(Sensores.sensorCarbono);
		}
		if(sensorMetano == true){
			sensores.add(Sensores.sensorMetano);
		}
		
	}
}



