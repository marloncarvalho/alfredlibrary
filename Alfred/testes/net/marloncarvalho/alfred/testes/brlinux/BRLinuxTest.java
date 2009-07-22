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
package net.marloncarvalho.alfred.testes.brlinux;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import net.marloncarvalho.alfred.brlinux.BRLinux;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * Teste das noticias do BRLinux.
 * 
 * @author Carlos Daniel de Mattos Mercer
 * @since 21/07/2009
 */
public class BRLinuxTest {

	@Test
	public void testarResultadoNoticias() {
		try {
			String[] resultado = BRLinux.obterNoticiasBRLinux();
			if (resultado.length < 32)
				Assert.fail();
			for (int i = 0; i < resultado.length; i++) {
				if (resultado[i] == null || "".equals(resultado[i]))
					Assert.fail();
			}
		} catch (SAXException e) {
			Assert.fail();
		} catch (IOException e) {
			Assert.fail();
		} catch (ParserConfigurationException e) {
			Assert.fail();
		}
	}

}