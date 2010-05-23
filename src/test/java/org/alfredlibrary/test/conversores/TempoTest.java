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

import org.alfredlibrary.conversores.Tempo;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para Conversor de Volume.
 * 
 * @author Marlon Silva Carvalho
 * @since 11/05/2010
 */
public class TempoTest {

	@Test
	public void testarConversaoHoraMinuto() {
		try {
			Assert.assertEquals(60D, Tempo.converter(1, Tempo.Unidade.HORA, Tempo.Unidade.MINUTO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoHoraSegundo() {
		try {
			Assert.assertEquals(3600D, Tempo.converter(1, Tempo.Unidade.HORA, Tempo.Unidade.SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoHoraDecimoSegundo() {
		try {
			Assert.assertEquals(36000D, Tempo.converter(1, Tempo.Unidade.HORA, Tempo.Unidade.DECIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoHoraCentesimoSegundo() {
		try {
			Assert.assertEquals(360000D, Tempo.converter(1, Tempo.Unidade.HORA, Tempo.Unidade.CENTESIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}

	@Test
	public void testarConversaoHoraMilesimoSegundo() {
		try {
			Assert.assertEquals(3600000D, Tempo.converter(1, Tempo.Unidade.HORA, Tempo.Unidade.MILESIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoMinutoSegundo() {
		try {
			Assert.assertEquals(60D, Tempo.converter(1, Tempo.Unidade.MINUTO, Tempo.Unidade.SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoMinutoDecimoSegundo() {
		try {
			Assert.assertEquals(600D, Tempo.converter(1, Tempo.Unidade.MINUTO, Tempo.Unidade.DECIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoMinutoCentesimoSegundo() {
		try {
			Assert.assertEquals(6000D, Tempo.converter(1, Tempo.Unidade.MINUTO, Tempo.Unidade.CENTESIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoMinutoMilesimoSegundo() {
		try {
			Assert.assertEquals(60000D, Tempo.converter(1, Tempo.Unidade.MINUTO, Tempo.Unidade.MILESIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoSegundoDecimoSegundo() {
		try {
			Assert.assertEquals(10D, Tempo.converter(1, Tempo.Unidade.SEGUNDO, Tempo.Unidade.DECIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoSegundoCentesimoSegundo() {
		try {
			Assert.assertEquals(100D, Tempo.converter(1, Tempo.Unidade.SEGUNDO, Tempo.Unidade.CENTESIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoSegundoMilesimoSegundo() {
		try {
			Assert.assertEquals(1000D, Tempo.converter(1, Tempo.Unidade.SEGUNDO, Tempo.Unidade.MILESIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoDecimoSegundoCentesimoSegundo() {
		try {
			Assert.assertEquals(10D, Tempo.converter(1, Tempo.Unidade.DECIMO_SEGUNDO, Tempo.Unidade.CENTESIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoDecimoSegundoMilesimoSegundo() {
		try {
			Assert.assertEquals(100D, Tempo.converter(1, Tempo.Unidade.DECIMO_SEGUNDO, Tempo.Unidade.MILESIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoCentesimoSegundoMilesimoSegundo() {
		try {
			Assert.assertEquals(10D, Tempo.converter(1, Tempo.Unidade.CENTESIMO_SEGUNDO, Tempo.Unidade.MILESIMO_SEGUNDO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
}