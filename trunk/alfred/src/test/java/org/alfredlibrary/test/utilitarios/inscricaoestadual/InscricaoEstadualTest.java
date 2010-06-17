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
package org.alfredlibrary.test.utilitarios.inscricaoestadual;

import junit.framework.Assert;

import org.alfredlibrary.utilitarios.inscricaoestadual.InscricaoEstadual;
import org.alfredlibrary.utilitarios.inscricaoestadual.InscricaoEstadual.PadraoInscricaoEstadual;
import org.alfredlibrary.utilitarios.texto.Texto;
import org.junit.Test;

/**
 * Classe de Teste para o utilitário de Inscrição Estadual.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 15/06/2010
 */
public class InscricaoEstadualTest {

	@Test
	public void testGerarDigitoVerificador() {
		Assert.assertEquals("0", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.ACRE, "01407423"));
		Assert.assertEquals("8", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.ALAGOAS, "24000004"));
		Assert.assertEquals("9", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.AMAPA, "03012345"));
		Assert.assertEquals("36", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.BAHIA, "123456"));
		Assert.assertEquals("99", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.BAHIA, "623456"));
		Assert.assertEquals("5", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.CEARA, "06000001"));
		Assert.assertEquals("09", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.DISTRITO_FEDERAL, "07300001001"));
		Assert.assertEquals("0", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.ESPIRITO_SANTO, "99999999"));
		Assert.assertEquals("7", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.GOIAS, "10987654"));
		Assert.assertEquals("5", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.MARANHAO, "12000038"));
		Assert.assertEquals("9", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.MATO_GROSSO, "0013000001"));
		Assert.assertEquals("81", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.MINAS_GERAIS, "06230790400"));
		Assert.assertEquals("5", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.PARA, "15999999"));
		Assert.assertEquals("5", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.PARAIBA, "16004017"));
		Assert.assertEquals("50", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.PARANA, "12345678"));
		Assert.assertEquals("9", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.PERNAMBUCO, "1810010000004"));
		Assert.assertEquals("9", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.PIAUI, "01234567"));
		Assert.assertEquals("3", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.RIO_DE_JANEIRO, "9999999"));
		Assert.assertEquals("1", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.RIO_GRANDE_DO_NORTE, "20040040"));
		Assert.assertEquals("2", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.RIO_GRANDE_DO_SUL, "224365879"));
		Assert.assertEquals("5", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.RONDONIA, "10100213"));
		Assert.assertEquals("1", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.RORAIMA, "24006628"));
		Assert.assertEquals("2", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.SANTA_CATARINA, "25104085"));
		Assert.assertEquals("0|4", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.SAO_PAULO_INDUSTRIAIS_COMERCIANTES, "1100424911"));
		Assert.assertEquals("3", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.SAO_PAULO_PRODUTOR_RURAL, Texto.manterNumeros("P01100424002")));
		Assert.assertEquals("3", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.SERGIPE, "27123456"));
		Assert.assertEquals("6", InscricaoEstadual.gerarDigitoVerificador(PadraoInscricaoEstadual.TOCANTINS, "2901022783"));
	}
	
	@Test
	public void testGerarInscricaoEstadual() {
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.ACRE, InscricaoEstadual.gerar(PadraoInscricaoEstadual.ACRE)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.ALAGOAS, InscricaoEstadual.gerar(PadraoInscricaoEstadual.ALAGOAS)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.AMAPA, InscricaoEstadual.gerar(PadraoInscricaoEstadual.AMAPA)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.AMAZONAS, InscricaoEstadual.gerar(PadraoInscricaoEstadual.AMAZONAS)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.BAHIA, InscricaoEstadual.gerar(PadraoInscricaoEstadual.BAHIA)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.CEARA, InscricaoEstadual.gerar(PadraoInscricaoEstadual.CEARA)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.DISTRITO_FEDERAL, InscricaoEstadual.gerar(PadraoInscricaoEstadual.DISTRITO_FEDERAL)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.ESPIRITO_SANTO, InscricaoEstadual.gerar(PadraoInscricaoEstadual.ESPIRITO_SANTO)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.GOIAS, InscricaoEstadual.gerar(PadraoInscricaoEstadual.GOIAS)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.MARANHAO, InscricaoEstadual.gerar(PadraoInscricaoEstadual.MARANHAO)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.MATO_GROSSO, InscricaoEstadual.gerar(PadraoInscricaoEstadual.MATO_GROSSO)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.MATO_GROSSO_DO_SUL, InscricaoEstadual.gerar(PadraoInscricaoEstadual.MATO_GROSSO_DO_SUL)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.MINAS_GERAIS, InscricaoEstadual.gerar(PadraoInscricaoEstadual.MINAS_GERAIS)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.PARA, InscricaoEstadual.gerar(PadraoInscricaoEstadual.PARA)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.PARAIBA, InscricaoEstadual.gerar(PadraoInscricaoEstadual.PARAIBA)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.PARANA, InscricaoEstadual.gerar(PadraoInscricaoEstadual.PARANA)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.PERNAMBUCO, InscricaoEstadual.gerar(PadraoInscricaoEstadual.PERNAMBUCO)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.PIAUI, InscricaoEstadual.gerar(PadraoInscricaoEstadual.PIAUI)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.RIO_DE_JANEIRO, InscricaoEstadual.gerar(PadraoInscricaoEstadual.RIO_DE_JANEIRO)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.RIO_GRANDE_DO_NORTE, InscricaoEstadual.gerar(PadraoInscricaoEstadual.RIO_GRANDE_DO_NORTE)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.RIO_GRANDE_DO_SUL, InscricaoEstadual.gerar(PadraoInscricaoEstadual.RIO_GRANDE_DO_SUL)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.RONDONIA, InscricaoEstadual.gerar(PadraoInscricaoEstadual.RONDONIA)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.RORAIMA, InscricaoEstadual.gerar(PadraoInscricaoEstadual.RORAIMA)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.SANTA_CATARINA, InscricaoEstadual.gerar(PadraoInscricaoEstadual.SANTA_CATARINA)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.SAO_PAULO_INDUSTRIAIS_COMERCIANTES, InscricaoEstadual.gerar(PadraoInscricaoEstadual.SAO_PAULO_INDUSTRIAIS_COMERCIANTES)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.SAO_PAULO_PRODUTOR_RURAL, InscricaoEstadual.gerar(PadraoInscricaoEstadual.SAO_PAULO_PRODUTOR_RURAL)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.SERGIPE, InscricaoEstadual.gerar(PadraoInscricaoEstadual.SERGIPE)));
		Assert.assertTrue(org.alfredlibrary.validadores.InscricaoEstadual.isValido(PadraoInscricaoEstadual.TOCANTINS, InscricaoEstadual.gerar(PadraoInscricaoEstadual.TOCANTINS)));
	}

	@Test
	public void testExaustivoGerarInscricaoEstadual() {
		for (int i = 0; i <= 100; i++) {
			testGerarInscricaoEstadual();
		}
	}
}