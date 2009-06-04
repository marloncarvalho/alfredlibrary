/*
 *  This file is part of Alfred Library.
 *
 *  Foobar is free software: you can redistribute it and/or modify
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
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.marloncarvalho.alfred.colecoes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.texto.Texto;

/**
 * Utilitários para Coleções.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
final public class Colecoes {

	/**
	 * Remover de uma coleção todos os itens que possuam o campo "nomeCampo" com o valor "valor".
	 * Não passar coleção de tipos primitivos como parâmetro.
	 * A coleção deve ser composta de objetos que seguem o padrão JavaBean.
	 * <example>
	 * 	class MeuObjeto {
	 * 		private String id;
	 * 
	 * 		public String getId() {
	 * 			return id;
	 * 		}
	 * 
	 * 		public void setId(String id) {
	 * 			this.id = id;
	 * 		}
	 * 	}
	 * 
	 * 	Collection c = new ArrayList();
	 *  MeuObjeto mo = new MeuObjeto();
	 *  mo.setId("1");
	 *  c.add(mo);
	 *  
	 *  c = Colecoes.removerItem(c,"id", "1");
	 *  
	 *  // Irá remover o item anteriormente adicionado.
	 * </example>
	 * 
	 * @param colecao Coleção.
	 * @param nomeCampo Nome do campo que será checado.
	 * @param valor Valor do campo.
	 * @return Coleção sem os itens.
	 */
	@SuppressWarnings("all")
	public static Collection removerItem(Collection colecao, String nomeCampo, Object valor) {
		try {
			Collection retorno = colecao.getClass().newInstance();
			for(Iterator it = colecao.iterator(); it.hasNext() ; ) {
				Object o = it.next();
				Method m = o.getClass().getMethod("get" + Texto.capitalizarIniciais(nomeCampo), null);
				Object r = m.invoke(o, null);
				if ( r != null && r.equals(valor) )
					continue;
				retorno.add(o);
			}
			return retorno;
		} catch (InstantiationException e) {
			throw new AlfredException("Não foi possível instanciar um tipo de coleção igual ao tipo informado.");
		} catch (IllegalAccessException e) {
			throw new AlfredException(e);
		} catch (SecurityException e) {
			throw new AlfredException(e);
		} catch (NoSuchMethodException e) {
			throw new AlfredException("Não existe o método de acesso ao campo informado. Verifique se sua classe implementa o padrão JavaBean." ,e);
		} catch (IllegalArgumentException e) {
			throw new AlfredException(e);
		} catch (InvocationTargetException e) {
			throw new AlfredException(e);
		}
	}

}