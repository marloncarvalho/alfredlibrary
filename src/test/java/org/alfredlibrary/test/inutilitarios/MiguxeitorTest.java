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
package org.alfredlibrary.test.inutilitarios;

import org.alfredlibrary.inutilitarios.Miguxeitor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de Teste do Inutilitário Miguxeitor.
 * 
 * @author Marlon Silva Carvalho
 * @since 12/05/2010
 */
public class MiguxeitorTest {

	@Test
	public void testMiguxo() {
		String miguxada = Miguxeitor.miguxar("você é muito lindo, menino! Que fofura! Estou meio triste. Mas estou melhor!", 1);
		Assert.assertEquals("vc eh mto lindo, menino!! q fofura!! tou 1/2 triste. mas tou melhor!!", miguxada);
		miguxada = Miguxeitor.miguxar("você é muito lindo, menino! Que fofura! Estou meio triste. Mas estou melhor!", 2);
		Assert.assertEquals("vc eh mtu leenu... meninu!!!!! ke fofura!!!!! to 1/2 tristi...... mas to melhor!!!!!...", miguxada);

	}

}