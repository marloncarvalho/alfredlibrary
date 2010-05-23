package org.alfredlibrary.utilitarios.clima;

import java.util.Collection;


public interface ProvedorClima {

	Collection<Clima> prover(String idLocalidade);

}