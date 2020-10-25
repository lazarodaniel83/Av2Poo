package logica;
import persistencia.MonitorDAO;
import logica.dto.MonitorDTO;
import java.util.List;

public interface MonitoraAreaLogica {

		public void setPersistencia(MonitorDAO persistencia) throws Exception;
		public String monitoria(Ponto local,Sensores Sensores) throws Exception;
		public List<MonitorDTO> getMonitor() throws Exception;
		public void monitorSalvo(MonitorMovel monitor)throws Exception;
		public void delete(int id)throws Exception;
}
