package persistencia;

import java.util.List;
import logica.MonitorMovel;
import logica.dto.MonitorDTO;

public interface MonitorDAO{

	public void salvar(MonitorMovel monitor) throws Exception;
	public void atualizar(MonitorMovel monitor) throws Exception;
	public void delete(String idMonitor) throws Exception;

	public List<MonitorMovel>  bcsTdsMonitores() throws Exception;
	
	public MonitorMovel busca(String id) throws Exception;

	public void salveOuAtualiza(MonitorMovel monitor) throws Exception;
	
	public List<MonitorDTO> buscarTdUi() throws Exception;
}


