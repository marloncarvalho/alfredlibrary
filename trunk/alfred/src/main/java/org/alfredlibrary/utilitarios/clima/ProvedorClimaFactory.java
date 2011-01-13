package org.alfredlibrary.utilitarios.clima;

import org.alfredlibrary.utilitarios.clima.climatempo.ClimatempoProvedor;
/**
 * @author Marlon Silva Carvalho
 * @author Mario Jorge Pereira
 * 
 * @since 
 *
 */
public enum ProvedorClimaFactory {
	instancia;
	
	public enum ProvedoresClima {
		CLIMATEMPO;
	}

	public ProvedorClima criar(ProvedoresClima provedor) {
		if ( provedor == ProvedoresClima.CLIMATEMPO )
			return new ClimatempoProvedor();
		return new ClimatempoProvedor();
	}

}