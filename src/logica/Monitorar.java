package logica;

import java.util.ArrayList;

import java.sql.SQLException;
import java.util.List;

import persistencia.MonitorSQL;
import persistencia.MonitorDAO;
import exception.MonitorMovelException;
import logica.dto.MonitorDTO;

public class Monitorar implements MonitoraAreaLogica{
	
	private List<MonitorMovel> monitores;
	private List<MonitorDTO> monitoresDTO;
	private MonitorDAO monitorDAO;
	private MonitorSQL monitorSQL;

	public void salvarMonitores(MonitorMovel monitores) throws Exception{
		monitorDAO.salvar(monitores);
	}
	
	public void setPersistencia(MonitorDAO persistencia) throws Exception{
		this.monitorDAO = persistencia;
	}
	
	public void delete(String idMonitor){
		monitorSQL.delete(idMonitor);
	}
	
	public String monitoria(double abscissa,double ordenada,boolean cameraVideo,boolean temperatura,boolean sensorCarbono,boolean sensorMetano) throws Exception{
		String retorno;
		this.monitorSQL = new MonitorSQL();
		this.monitores = this.monitorSQL.bcsTdsMonitores();
		List<MonitorMovel> monitoresCompativeis = new ArrayList<MonitorMovel>();
		ArrayList<Sensores> sensoresMinimos = new ArrayList<>();
		
		if(this.monitores == null || this.monitores.isEmpty())
			throw new Exception("Sem unidades disponiveis!!");
		
		for(MonitorMovel monitor : this.monitores){
			if(cameraVideo == true )
				sensoresMinimos.add(Sensores.cameraVideo);
			if(temperatura == true )
				sensoresMinimos.add(Sensores.temperatura);
			if(sensorCarbono == true)
				sensoresMinimos.add(Sensores.sensorCarbono);
			if(sensorMetano == true)
				sensoresMinimos.add(Sensores.sensorMetano);
			monitoresCompativeis.add(monitor);
		}
		if(sensoresMinimos == null || sensoresMinimos.isEmpty())
			throw new Exception("Não ha sensores compativel!!!");
		if(monitoresCompativeis == null || monitoresCompativeis.isEmpty())
			throw new Exception("Monitores Compativeis!!!");
		MonitorMovel proximo = (MonitorMovel)monitoresCompativeis.get(0);
		
		for(MonitorMovel monitor : monitoresCompativeis)
			proximo = monitor.getCalcularTrajeto(abscissa, ordenada)<proximo.getCalcularTrajeto(abscissa, ordenada)?monitor: proximo;
		retorno ="O Monitor movel"+" "+proximo.getId()+ " "+" foi ativada! \n"+"Distancia: "+ proximo.getCalcularTrajeto(abscissa, ordenada);
		proximo.setAbscissa(abscissa);
		proximo.setOrdenada(ordenada);
		
		return retorno;
	}
	public List<MonitorDTO> getMonitores() throws Exception{
		this.monitorSQL = new MonitorSQL();
		this.monitoresDTO = this.monitorSQL.buscarTdUi();
		return this.monitoresDTO;
	}

	
	 
	

}




