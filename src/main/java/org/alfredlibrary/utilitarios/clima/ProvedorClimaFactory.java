package org.alfredlibrary.utilitarios.clima;

import org.alfredlibrary.utilitarios.clima.climatempo.ClimatempoProvedor;

final public class ProvedorClimaFactory {
	private static ProvedorClimaFactory instancia;
	
	public enum ProvedoresClima {
		CLIMATEMPO;
	}

	public static ProvedorClimaFactory getInstancia() {
		if ( instancia == null ) {
			instancia = new ProvedorClimaFactory();
		}
		return instancia;
	}

	public ProvedorClima criar(ProvedoresClima provedor) {
		if ( provedor == ProvedoresClima.CLIMATEMPO )
			return new ClimatempoProvedor();
		return new ClimatempoProvedor();
	}

}