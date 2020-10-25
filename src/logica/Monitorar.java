package logica;

import java.util.ArrayList;
import java.util.List;

import persistencia.*;
import exception.MonitorMovelException;
import logica.dto.MonitorDTO;

public class Monitorar implements MonitoraAreaLogica{
	private List<MonitorMovel> unidades;
	private List<MonitorDTO> unidadeDTO;
	private MonitorSQL monitorSql;
	private MonitorDAO monitorDAO;
	
}
