package ui;

import logica.MonitoraAreaLogica;

public interface MonitorUI {

	public void setLogica(MonitoraAreaLogica logica)throws Exception;
	public void run() throws Exception;
}
