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
package org.alfredlibrary.bancos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfredlibrary.net.WorldWideWeb;

/**
 * Utilitário para obter informações relativas a banco da Febraban.
 * http://www.febraban.org.br/buscabanco/
 * 
 * @author Marlon Silva Carvalho
 * @since 05/05/2010
 */
final public class Febraban {

	public static Collection<Banco> obterListaBancos(Tipo tipo, Origem origem, Naturalidade naturalidade) {
		Collection<Banco> bancos = new ArrayList<Banco>();
		String url = "http://www.febraban.org.br/buscabanco/AgenciasBancos.asp?uf=&ordem=banco&wbanco=&tipo=" + tipo + "&origem=" + origem + "&natural=" + naturalidade;
		String conteudo = WorldWideWeb.getConteudoSite(url);
		Pattern padrao = Pattern.compile("<span class=\"Estilo3_negrito\">\\d</span>");  
		Matcher pesquisa = padrao.matcher(conteudo);
		while(pesquisa.find()) {
			System.out.println(pesquisa.group());
		}
		return bancos;
	}

	public static void main(String[] args) {
		Febraban.obterListaBancos(Tipo.TODOS, Origem.TODOS, Naturalidade.TODOS);
	}

}