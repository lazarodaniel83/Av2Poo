package persistencia;

import java.util.List;
import logica.MonitorMovel;
import logica.dto.MonitorDTO;

public interface MonitorDAO{

	public void salvar(MonitorMovel monitor) throws Exception;
	public List<MonitorMovel>  monitores() throws Exception;
	public MonitorMovel busc(String Udidade_id) throws Exception;
	public void atualizar(MonitorMovel monitor) throws Exception;
	public void salveOuAtualiza(MonitorMovel monitor) throws Exception;
	public void delete(int idMonitor) throws Exception;
	
}


