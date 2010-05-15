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

import java.util.Calendar;
import java.util.Date;

import org.alfredlibrary.formatadores.Data;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste para formatador de Data.
 * 
 * @author Marlon Silva Carvalho
 * @since 12/05/2010
 */
public class DataTest {

	@Test
	public void testFormatacao1() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 12);
		calendar.set(Calendar.MONTH, 05);
		calendar.set(Calendar.YEAR, 2010);
		String data = Data.formatar(calendar.getTime(), "dd/MM/yyyy");
		Assert.assertEquals(data.charAt(2), '/');
		Assert.assertEquals(data.charAt(5), '/');
		Assert.assertEquals("12", data.substring(0,2));
	}

	@Test
	public void testFormatacao2() {
		Date data = Data.formatar("12/05/2010", "dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		Assert.assertEquals(calendar.get(Calendar.DAY_OF_MONTH), 12);
		Assert.assertEquals(calendar.get(Calendar.MONTH), Calendar.MAY);
		Assert.assertEquals(calendar.get(Calendar.YEAR), 2010);
	}

	@Test
	public void testFormatacao3() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 12);
		calendar.set(Calendar.MONTH, 05);
		calendar.set(Calendar.YEAR, 2010);
		String data = Data.formatarPorExtenso(calendar.getTime());
		Assert.assertEquals("12 DE JUNHO DE 2010", data.toUpperCase());
	}

}