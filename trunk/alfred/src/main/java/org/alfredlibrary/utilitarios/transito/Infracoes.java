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
package org.alfredlibrary.utilitarios.transito;

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
 * Utilitário para obter as infrações de trânsito através do site https://wwws.detrannet.mg.gov.br/detran/tbinfr.asp
 * 
 * @author Marlon Silva Carvalho
 * @since 13/05/2010
 */
public class Infracoes {
	
	public Infracoes() {
		throw new AssertionError();
	}

	/**
	 * Obter todas as infrações de trânsito disponíveis.
	 * 
	 * @return Infrações.
	 */
	public static Collection<Infracao> obterInfracoes() {
		Collection<Infracao> infracoes = new ArrayList<Infracao>();
		String conteudo = WorldWideWeb.obterConteudoSite("https://wwws.detrannet.mg.gov.br/detran/tbinfr.asp", "UTF-8");
		BufferedReader br = new BufferedReader(new StringReader(conteudo));
		String linha = null;
		try {
			while( (linha = br.readLine()) != null ) {
				if ( linha.trim().startsWith("<center>") ) {
					Infracao infracao = new Infracao();
					linha = br.readLine();
					infracao.setCodigo(HTML.removerTags(linha).trim());
					br.readLine();
					br.readLine();
					linha = br.readLine();
					infracao.setDescricao(HTML.removerTags(linha).trim());
					linha = br.readLine();
					infracao.setAmparoLegal(HTML.removerTags(linha).trim());
					linha = br.readLine();
					infracao.setInfrator(HTML.removerTags(linha).trim());
					br.readLine();
					br.readLine();
					linha = HTML.removerTags(br.readLine()).trim();
					if ( Numeros.isInteger(linha) ) {
						infracao.setPontos(Integer.valueOf(linha));
					}
					br.readLine();
					linha = HTML.removerTags(br.readLine()).trim().replace(",",".");
					if ( Numeros.isDouble(linha)) {
						infracao.setValor(Double.valueOf(linha));
					}
					infracoes.add(infracao);
				}
			}
		} catch (IOException e) {
			throw new AlfredException(e);
		}
		return infracoes;
	}

}