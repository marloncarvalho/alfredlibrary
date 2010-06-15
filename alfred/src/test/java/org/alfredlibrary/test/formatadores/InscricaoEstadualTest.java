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
package org.alfredlibrary.test.formatadores;

import org.alfredlibrary.formatadores.InscricaoEstadual;
import org.alfredlibrary.utilitarios.inscricaoestadual.InscricaoEstadual.PadraoInscricaoEstadual;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para o Formatador de Inscrição Estadual.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 15/06/2010
 */
public class InscricaoEstadualTest {

	@Test
	public void testarFormatacao() {
		Assert.assertEquals("01.40.7423-0", InscricaoEstadual.formatar(PadraoInscricaoEstadual.ACRE, "014074230"));
		Assert.assertEquals("240000048", InscricaoEstadual.formatar(PadraoInscricaoEstadual.ALAGOAS, "240000048"));
		Assert.assertEquals("030123459", InscricaoEstadual.formatar(PadraoInscricaoEstadual.AMAPA, "030123459"));
		Assert.assertEquals("123456-63", InscricaoEstadual.formatar(PadraoInscricaoEstadual.BAHIA, "12345663"));
		Assert.assertEquals("623456-63", InscricaoEstadual.formatar(PadraoInscricaoEstadual.BAHIA, "62345663"));
		Assert.assertEquals("06000001-5", InscricaoEstadual.formatar(PadraoInscricaoEstadual.CEARA, "060000015"));
		Assert.assertEquals("073.00001.001-09", InscricaoEstadual.formatar(PadraoInscricaoEstadual.DISTRITO_FEDERAL, "0730000100109"));
		Assert.assertEquals("99999999-0", InscricaoEstadual.formatar(PadraoInscricaoEstadual.ESPIRITO_SANTO, "999999990"));
		Assert.assertEquals("10.987.654-7", InscricaoEstadual.formatar(PadraoInscricaoEstadual.GOIAS, "109876547"));
		Assert.assertEquals("12000038-5", InscricaoEstadual.formatar(PadraoInscricaoEstadual.MARANHAO, "120000385"));
		Assert.assertEquals("0013000001-9", InscricaoEstadual.formatar(PadraoInscricaoEstadual.MATO_GROSSO, "00130000019"));
		Assert.assertEquals("062.307.904/0081", InscricaoEstadual.formatar(PadraoInscricaoEstadual.MINAS_GERAIS, "0623079040081"));
		Assert.assertEquals("15-999999-5", InscricaoEstadual.formatar(PadraoInscricaoEstadual.PARA, "159999995"));
		Assert.assertEquals("16.004.017-5", InscricaoEstadual.formatar(PadraoInscricaoEstadual.PARAIBA, "160040175"));
		Assert.assertEquals("123.45678-50", InscricaoEstadual.formatar(PadraoInscricaoEstadual.PARANA, "1234567850"));
		Assert.assertEquals("18.1.001.0000004-9", InscricaoEstadual.formatar(PadraoInscricaoEstadual.PERNAMBUCO, "18100100000049"));
		Assert.assertEquals("01234567-9", InscricaoEstadual.formatar(PadraoInscricaoEstadual.PIAUI, "012345679"));
		Assert.assertEquals("99.999.99-3", InscricaoEstadual.formatar(PadraoInscricaoEstadual.RIO_DE_JANEIRO, "99999993"));
		Assert.assertEquals("20.040.040-1", InscricaoEstadual.formatar(PadraoInscricaoEstadual.RIO_GRANDE_DO_NORTE, "200400401"));
		Assert.assertEquals("224/3658792", InscricaoEstadual.formatar(PadraoInscricaoEstadual.RIO_GRANDE_DO_SUL, "2243658792"));
		Assert.assertEquals("101002135", InscricaoEstadual.formatar(PadraoInscricaoEstadual.RONDONIA, "101002135"));
		Assert.assertEquals("24006628-1", InscricaoEstadual.formatar(PadraoInscricaoEstadual.RORAIMA, "240066281"));
		Assert.assertEquals("251.040.852", InscricaoEstadual.formatar(PadraoInscricaoEstadual.SANTA_CATARINA, "251.040.852"));
		Assert.assertEquals("110.042.490.114", InscricaoEstadual.formatar(PadraoInscricaoEstadual.SAO_PAULO_INDUSTRIAIS_COMERCIANTES, "110042490114"));
		Assert.assertEquals("P-01100424.3/002", InscricaoEstadual.formatar(PadraoInscricaoEstadual.SAO_PAULO_PRODUTOR_RURAL, "P011004243002"));
		Assert.assertEquals("27123456-3", InscricaoEstadual.formatar(PadraoInscricaoEstadual.SERGIPE, "271234563"));
		Assert.assertEquals("29.01.022783-6", InscricaoEstadual.formatar(PadraoInscricaoEstadual.TOCANTINS, "29010227836"));
	}

}