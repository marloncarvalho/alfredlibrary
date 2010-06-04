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
package org.alfredlibrary.test.utilitarios.correios;

import java.util.Collection;

import junit.framework.Assert;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.formatadores.Data;
import org.alfredlibrary.utilitarios.correios.Rastreamento;
import org.alfredlibrary.utilitarios.correios.RegistroRastreamento;
import org.junit.Test;

/**
 * Classe de Teste para o Utilitário de Bancos da Febraban.
 * 
 * @author Marlon Silva Carvalho
 * @since 12/06/2010
 */
public class RastreamentoTest {

	@Test
	public void testRastrear() {
		try {
			String[][] esperado = {
					{"10/05/2010 16:55", "CDD CIDADELA - SALVADOR/BA", "Entregue", null},
					{"10/05/2010 12:32", "CDD CIDADELA - SALVADOR/BA", "Saiu para entrega", null}, 
					{"04/05/2010 17:34", "ACCI AMANRRA - RIO DE JANEIRO/RJ", "Encaminhado", "Em trânsito para CTE BENFICA - RIO DE JANEIRO/RJ"},
					{"04/05/2010 16:05", "ACCI AMANRRA - RIO DE JANEIRO/RJ", "Postado", null},
			};
			Collection<RegistroRastreamento> colRegistoRastreamento = Rastreamento.rastrear("RB087729223HK");
			int iteration = 0;
			for(RegistroRastreamento rr : colRegistoRastreamento) {
				if (!Data.formatar(esperado[iteration][0], "dd/MM/yyyy HH:mm").equals(rr.getDataHora())) {
					Assert.fail();
				}
				Assert.assertEquals(esperado[iteration][1], rr.getLocal());
				Assert.assertEquals(esperado[iteration][2], rr.getAcao());
				Assert.assertEquals(esperado[iteration][3], rr.getDetalhe());
				iteration++;
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testValidar() {
		try {
			try {
				@SuppressWarnings("unused")
				Collection<RegistroRastreamento> colRegistoRastreamento = Rastreamento.rastrear("RJ377032643BR");
			} catch (AlfredException ae) {
				Assert.fail(ae.getMessage());
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}