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

package org.alfredlibrary.test.utilitarios.trabalhista;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.alfredlibrary.formatadores.Data;
import org.alfredlibrary.utilitarios.trabalhista.CalculoSalarial;
import org.alfredlibrary.utilitarios.tributo.INSS.INSS;
import org.alfredlibrary.utilitarios.tributo.IR.IRRF;
import org.junit.Test;

/**
 * Classe de Teste para o utilitário de Cálculo Salarial.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 01/10/2010
 */
public class CalculoSalarialTest {
	
	// Localização e instanciação de arquivos .XML de tributos, que trazem consigo o .DTD 
	private static final File ARQUIVOIRRF = new File("src" + System.getProperty("file.separator") +
			"test" + System.getProperty("file.separator") +
			"resources" + System.getProperty("file.separator") +
			"org" + System.getProperty("file.separator") +
			"alfredlibrary" + System.getProperty("file.separator") +
			"utilitarios" + System.getProperty("file.separator") +
			"tributos" + System.getProperty("file.separator") +
			"IRRF.xml");
	private static final File ARQUIVOINSS = new File("src" + System.getProperty("file.separator") +
			"test" + System.getProperty("file.separator") +
			"resources" + System.getProperty("file.separator") +
			"org" + System.getProperty("file.separator") +
			"alfredlibrary" + System.getProperty("file.separator") +
			"utilitarios" + System.getProperty("file.separator") +
			"tributos" + System.getProperty("file.separator") +
			"INSS.xml");
	private static final File ARQUIVOFGTS = new File("src" + System.getProperty("file.separator") +
			"test" + System.getProperty("file.separator") +
			"resources" + System.getProperty("file.separator") +
			"org" + System.getProperty("file.separator") +
			"alfredlibrary" + System.getProperty("file.separator") +
			"utilitarios" + System.getProperty("file.separator") +
			"tributos" + System.getProperty("file.separator") +
			"FGTS.xml");
	private static final File ARQUIVOFGTSMR = new File("src" + System.getProperty("file.separator") +
			"test" + System.getProperty("file.separator") +
			"resources" + System.getProperty("file.separator") +
			"org" + System.getProperty("file.separator") +
			"alfredlibrary" + System.getProperty("file.separator") +
			"utilitarios" + System.getProperty("file.separator") +
			"tributos" + System.getProperty("file.separator") +
			"FGTS_MultaRescisoria.xml");
	 
	@Test
	public void testCalculoSalarial() {
		double esperado = 5000D;
		esperado -= INSS.calcular(esperado, INSS.obterAliquotas(ARQUIVOINSS, Data.formatar("01/10/2010", "dd/MM/yyyy"), "Empregado"), false);
		esperado -= IRRF.calcular(esperado, IRRF.obterAliquotas(ARQUIVOIRRF, Data.formatar("01/10/2010", "dd/MM/yyyy"), null), true);
		Map<String, Object[]> mapaTributario = new HashMap<String, Object[]>();
		mapaTributario.put("IRRF", new Object[] {ARQUIVOIRRF, null});
		mapaTributario.put("INSS", new Object[] {ARQUIVOINSS, "Empregado"});
		Assert.assertEquals(esperado, CalculoSalarial.calcular(mapaTributario, 5000D, Data.formatar("01/10/2010", "dd/MM/yyyy")));
	}
	
	@Test
	public void testFGTS() {
		Assert.assertEquals(400D, CalculoSalarial.calcularFGTS(ARQUIVOFGTS, 5000D, Data.formatar("01/10/2010", "dd/MM/yyyy"), "Produtor Rural - Pes. Física - Fat. 1.200.000,00"));
	}
	
	@Test
	public void testFGTSMultaRescisoria() {
		Assert.assertEquals(2500D, CalculoSalarial.calcularFGTSRescisao(ARQUIVOFGTSMR, 5000D, Data.formatar("01/10/2010", "dd/MM/yyyy"), "Produtor Rural - Pes. Física - Fat. 1.200.000,00"));
	}

	
}
