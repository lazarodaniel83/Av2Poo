package logica;

import java.util.List;

import logica.dto.MonitorDTO;
import persistencia.MonitorDAO;
import logica.MonitorConfig;

public interface MonitoraAreaLogica {

		public void setPersistencia(MonitorDAO persistencia) throws Exception;
		public String monitoria(double abscissa,double ordenada,boolean cameraVideo,boolean temperatura,boolean sensorCarbono,boolean sensorMetano) throws Exception;
		public List<MonitorDTO> getMonitores() throws Exception;
		public void salvarMonitores(MonitorMovel monitores) throws Exception;
		public void delete(String idMonitor);
		
}
