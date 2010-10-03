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

package org.alfredlibrary.test.utilitarios.tributo.FGTS;

import java.io.File;

import junit.framework.Assert;

import org.alfredlibrary.formatadores.Data;
import org.alfredlibrary.utilitarios.tributo.FGTS.FGTS;
import org.junit.Test;

/**
 * Classe de Teste para o utilitário de FGTS.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 01/10/2010
 */
public class FGTSTest {
	
	// Localização e instanciação do arquivo FGTS.XML, que traz consigo o .DTD 
	private static final File ARQUIVO = new File("src" + System.getProperty("file.separator") +
			"test" + System.getProperty("file.separator") +
			"resources" + System.getProperty("file.separator") +
			"org" + System.getProperty("file.separator") +
			"alfredlibrary" + System.getProperty("file.separator") +
			"utilitarios" + System.getProperty("file.separator") +
			"tributos" + System.getProperty("file.separator") +
			"FGTS.xml");
	
	// Localização e instanciação do arquivo FGTS_MultaRescisoria.XML, que traz consigo o .DTD 
	private static final File ARQUIVOMR = new File("src" + System.getProperty("file.separator") +
			"test" + System.getProperty("file.separator") +
			"resources" + System.getProperty("file.separator") +
			"org" + System.getProperty("file.separator") +
			"alfredlibrary" + System.getProperty("file.separator") +
			"utilitarios" + System.getProperty("file.separator") +
			"tributos" + System.getProperty("file.separator") +
			"FGTS_MultaRescisoria.xml");
	
	@Test
	public void testFGTS() {
		Assert.assertEquals(400D, FGTS.calcular(5000D, FGTS.obterAliquotas(ARQUIVO, Data.formatar("01/10/2010", "dd/MM/yyyy"), "Produtor Rural - Pes. Física - Fat. 1.200.000,00"), false));
	}
	
	@Test
	public void testFGTSMultaRescisoria() {
		Assert.assertEquals(2500D, FGTS.calcular(5000D, FGTS.obterAliquotas(ARQUIVOMR, Data.formatar("01/10/2010", "dd/MM/yyyy"), "Produtor Rural - Pes. Física - Fat. 1.200.000,00"), false));
	}

}
