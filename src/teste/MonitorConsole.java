package teste;

import java.util.List;

import logica.Monitorar;
import logica.MonitorMovel;
import logica.MonitorEuclidiana;
import logica.MonitorManhattan;
import persistencia.MonitorDAO;
import persistencia.MonitorSQL;

public class MonitorConsole{
	
	public static void main(String[] args){
		
		try {
		Monitorar monitorar = new Monitorar();
		MonitorSQL ml = new MonitorSQL();
		List<MonitorMovel> mn = ml.bcsTdsMonitores();
		System.out.println(((MonitorEuclidiana) mn.get(0)).getCalcularTrajeto(2,2));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}  
}
