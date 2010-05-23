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

import org.alfredlibrary.conversores.Volume;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para Conversor de Volume.
 * 
 * @author Marlon Silva Carvalho
 * @since 11/05/2010
 */
public class VolumeTest {

	@Test
	public void testarConversaoQuilolitroHectolitro() {
		try {
			Assert.assertEquals(10D, Volume.converter(1, Volume.Unidade.QUILOLITRO, Volume.Unidade.HECTOLITRO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}

	@Test
	public void testarConversaoBarrilCervejaLitro() {
		try {
			Assert.assertEquals(117.34776530399999D, Volume.converter(1, Volume.Unidade.BARRIL_CERVEJA, Volume.Unidade.LITRO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}

	@Test
	public void testarConversaoQuilolitroDecalitro() {
		try {
			Assert.assertEquals(100D, Volume.converter(1, Volume.Unidade.QUILOLITRO, Volume.Unidade.DECALITRO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoQuilolitroLitro() {
		try {
			Assert.assertEquals(1000D, Volume.converter(1, Volume.Unidade.QUILOLITRO, Volume.Unidade.LITRO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
	
	@Test
	public void testarConversaoQuilolitroDecilitro() {
		try {
			Assert.assertEquals(10000D, Volume.converter(1, Volume.Unidade.QUILOLITRO, Volume.Unidade.DECILITRO), 0);
		} catch ( Exception e ) {
			Assert.fail();
		}
	}
}