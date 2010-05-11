package org.alfredlibrary.utilitarios.clima.climatempo;

import java.util.Collection;

import org.alfredlibrary.utilitarios.clima.Clima;
import org.alfredlibrary.utilitarios.clima.ProvedorClima;

public class ClimatempoProvedor implements ProvedorClima {

	@Override
	public Collection<Clima> prover(String idLocalidade) {
		return Climatempo.obterClima(idLocalidade);
	}

}