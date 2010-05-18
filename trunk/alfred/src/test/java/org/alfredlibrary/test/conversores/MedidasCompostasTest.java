/*
 *  This file is part of Alfred Library.
 *
 *  Alfred Library is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Alfred Library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Alfred Library.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfredlibrary.test.conversores;

import java.util.ArrayList;
import java.util.List;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.conversores.Comprimento;
import org.alfredlibrary.conversores.MedidasCompostas;
import org.alfredlibrary.conversores.Tempo;
import org.alfredlibrary.conversores.UnidadeComposta;
import org.alfredlibrary.conversores.Volume;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para Conversor de Volume.
 * 
 * @author Marlon Silva Carvalho
 * @since 11/05/2010
 */
public class MedidasCompostasTest {

	@Test
	public void testarConversaoMSKmH() {
		//try {
			UnidadeComposta uc = new UnidadeComposta(Comprimento.Unidade.METRO, 1, true);
			UnidadeComposta uc2 = new UnidadeComposta(Tempo.Unidade.SEGUNDO, 1, false);
			List <UnidadeComposta> unidadeEntrada = new ArrayList<UnidadeComposta>();
			unidadeEntrada.add(uc);
			unidadeEntrada.add(uc2);
			
			UnidadeComposta uc3 = new UnidadeComposta(Comprimento.Unidade.KM, 1, true);
			UnidadeComposta uc4 = new UnidadeComposta(Tempo.Unidade.HORA, 1, false);
			List <UnidadeComposta> unidadeSaida = new ArrayList<UnidadeComposta>();
			unidadeSaida.add(uc3);
			unidadeSaida.add(uc4);
			
			Assert.assertEquals(3.6D, MedidasCompostas.converter(1, unidadeEntrada, unidadeSaida), 0);
		/*} catch ( Exception e ) {
			Assert.fail();
		}*/
	}
	
	@Test
	public void testarConversaoMS2KmH2() {
		try {
			UnidadeComposta uc = new UnidadeComposta(Comprimento.Unidade.METRO, 1, true);
			UnidadeComposta uc2 = new UnidadeComposta(Tempo.Unidade.SEGUNDO, 2, false);
			List <UnidadeComposta> unidadeEntrada = new ArrayList<UnidadeComposta>();
			unidadeEntrada.add(uc);
			unidadeEntrada.add(uc2);
			
			UnidadeComposta uc3 = new UnidadeComposta(Comprimento.Unidade.KM, 1, true);
			UnidadeComposta uc4 = new UnidadeComposta(Tempo.Unidade.HORA, 2, false);
			List <UnidadeComposta> unidadeSaida = new ArrayList<UnidadeComposta>();
			unidadeSaida.add(uc3);
			unidadeSaida.add(uc4);
			
			Assert.assertEquals(12960D, MedidasCompostas.converter(1, unidadeEntrada, unidadeSaida), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoLSKlH() {
		try {
			UnidadeComposta uc = new UnidadeComposta(Volume.Unidade.LITRO, 1, true);
			UnidadeComposta uc2 = new UnidadeComposta(Tempo.Unidade.SEGUNDO, 1, false);
			List <UnidadeComposta> unidadeEntrada = new ArrayList<UnidadeComposta>();
			unidadeEntrada.add(uc);
			unidadeEntrada.add(uc2);
			
			UnidadeComposta uc3 = new UnidadeComposta(Volume.Unidade.QUILOLITRO, 1, true);
			UnidadeComposta uc4 = new UnidadeComposta(Tempo.Unidade.HORA, 1, false);
			List <UnidadeComposta> unidadeSaida = new ArrayList<UnidadeComposta>();
			unidadeSaida.add(uc3);
			unidadeSaida.add(uc4);
			
			Assert.assertEquals(3.6D, MedidasCompostas.converter(1, unidadeEntrada, unidadeSaida), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}

	@Test
	public void testarConversaoNula() {
		try {
			MedidasCompostas.converter(1, null, null);
			Assert.fail();
		} catch ( AlfredException ae ) {
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoTamanhosDiferentes() {
		try {
			UnidadeComposta uc = new UnidadeComposta(Comprimento.Unidade.METRO, 1, true);
			UnidadeComposta uc2 = new UnidadeComposta(Tempo.Unidade.SEGUNDO, 1, false);
			List <UnidadeComposta> unidadeEntrada = new ArrayList<UnidadeComposta>();
			unidadeEntrada.add(uc);
			unidadeEntrada.add(uc2);
			
			UnidadeComposta uc3 = new UnidadeComposta(Comprimento.Unidade.KM, 1, true);
			List <UnidadeComposta> unidadeSaida = new ArrayList<UnidadeComposta>();
			unidadeSaida.add(uc3);
			
			MedidasCompostas.converter(1, unidadeEntrada, unidadeSaida);
			Assert.fail();
		} catch ( AlfredException ae ) {
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoUnidadesDiferentes() {
		try {
			UnidadeComposta uc = new UnidadeComposta(Comprimento.Unidade.METRO, 1, true);
			UnidadeComposta uc2 = new UnidadeComposta(Tempo.Unidade.SEGUNDO, 1, false);
			List <UnidadeComposta> unidadeEntrada = new ArrayList<UnidadeComposta>();
			unidadeEntrada.add(uc);
			unidadeEntrada.add(uc2);
			
			UnidadeComposta uc3 = new UnidadeComposta(Volume.Unidade.LITRO, 1, true);
			UnidadeComposta uc4 = new UnidadeComposta(Tempo.Unidade.HORA, 1, false);
			List <UnidadeComposta> unidadeSaida = new ArrayList<UnidadeComposta>();
			unidadeSaida.add(uc3);
			unidadeSaida.add(uc4);
			
			MedidasCompostas.converter(1, unidadeEntrada, unidadeSaida);
			Assert.fail();
		} catch ( AlfredException ae ) {
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoPesosDiferentes() {
		try {
			UnidadeComposta uc = new UnidadeComposta(Comprimento.Unidade.METRO, 1, true);
			UnidadeComposta uc2 = new UnidadeComposta(Tempo.Unidade.SEGUNDO, 2, false);
			List <UnidadeComposta> unidadeEntrada = new ArrayList<UnidadeComposta>();
			unidadeEntrada.add(uc);
			unidadeEntrada.add(uc2);
			
			UnidadeComposta uc3 = new UnidadeComposta(Comprimento.Unidade.KM, 1, true);
			UnidadeComposta uc4 = new UnidadeComposta(Tempo.Unidade.HORA, 1, false);
			List <UnidadeComposta> unidadeSaida = new ArrayList<UnidadeComposta>();
			unidadeSaida.add(uc3);
			unidadeSaida.add(uc4);
			
			Assert.assertEquals(3.6D, MedidasCompostas.converter(1, unidadeEntrada, unidadeSaida), 0);
			Assert.fail();
		} catch ( AlfredException ae ) {
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoOperandosDiferentes() {
		try {
			UnidadeComposta uc = new UnidadeComposta(Comprimento.Unidade.METRO, 1, true);
			UnidadeComposta uc2 = new UnidadeComposta(Tempo.Unidade.SEGUNDO, 2, false);
			List <UnidadeComposta> unidadeEntrada = new ArrayList<UnidadeComposta>();
			unidadeEntrada.add(uc);
			unidadeEntrada.add(uc2);
			
			UnidadeComposta uc3 = new UnidadeComposta(Comprimento.Unidade.KM, 1, false);
			UnidadeComposta uc4 = new UnidadeComposta(Tempo.Unidade.HORA, 1, true);
			List <UnidadeComposta> unidadeSaida = new ArrayList<UnidadeComposta>();
			unidadeSaida.add(uc3);
			unidadeSaida.add(uc4);
			
			Assert.assertEquals(3.6D, MedidasCompostas.converter(1, unidadeEntrada, unidadeSaida), 0);
			Assert.fail();
		} catch ( AlfredException ae ) {
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
}