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
package org.alfredlibrary.utilitarios.bancos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.alfredlibrary.utilitarios.texto.HTML;
import org.alfredlibrary.validadores.Numeros;

/**
 * Utilitário para obter informações relativas a banco da Febraban.
 * http://www.febraban.org.br/buscabanco/
 * 
 * @author Marlon Silva Carvalho
 * @since 05/05/2010
 */
final public class Febraban {
	
	private Febraban() {
		throw new AssertionError();
	}

	/**
	 * Obter uma Lista de Bancos do site da Febraban.
	 * 
	 * @param tipo Tipo do Banco.
	 * @param origem Origem do Banco.
	 * @param naturalidade Naturalidade do Banco.
	 * @return Lista de bancos encontrados.
	 */
	public static Collection<Banco> obterListaBancos(Tipo tipo, Origem origem, Naturalidade naturalidade) {
		Collection<Banco> bancos = new ArrayList<Banco>();
		String url = "http://www.febraban.org.br/buscabanco/AgenciasBancos.asp?uf=&ordem=banco&wbanco=&tipo=" + tipo + "&origem=" + origem + "&natural=" + naturalidade;
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		BufferedReader br = new BufferedReader(new StringReader(conteudo));
		String linha = null;
		try {
			while( (linha = br.readLine()) != null ) {
				if ( linha.indexOf("<td width=\"80\" class=\"Estilo2\">") > -1 ) {
					String codigo = HTML.removerTags(linha).trim();
					Banco banco = new Banco();
					banco.setCodigo(codigo);
					br.readLine(); br.readLine(); br.readLine();
					linha = br.readLine();
					banco.setNome(linha.trim());
					br.readLine(); br.readLine(); br.readLine();
					linha = br.readLine();
					String[] links = HTML.acharLinks(linha);
					boolean hasLinks = false;
					if ( links != null && links.length > 0 ) {
						banco.setSite(links[0]);
						hasLinks = true;
					}
					br.readLine(); br.readLine(); br.readLine();
					br.readLine(); br.readLine(); br.readLine();
					if ( hasLinks ) { 
						br.readLine(); br.readLine();
					}
					linha = br.readLine().trim().replaceAll("\\.","");
					if ( linha != null && !"".equals(linha) && Numeros.isInteger(linha)) {
						banco.setQuantidadeAgencias(Integer.valueOf(linha));
					}
					bancos.add(banco);
				}
			}
		} catch (IOException e) {
			throw new AlfredException(e);
		}
		return bancos;
	}

}