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
package org.alfredlibrary.formatadores;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.inscricaoestadual.InscricaoEstadual.PadraoInscricaoEstadual;
import org.alfredlibrary.utilitarios.texto.Texto;
import org.alfredlibrary.validadores.Numeros;

/**
 * Formatador de Inscrição Estadual.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 11/06/2010
 */
final public class InscricaoEstadual {
	
	private InscricaoEstadual() {}
	
	/**
	 * Obter uma Inscrição Estadual qualquer e formatá-la. Qualquer caracter
	 * diferente de números será ignorado.
	 * 
	 * @param ie Número da Inscrição Estadual.
	 * @return Inscrição Estadual formatada.
	 */
	public static String formatar(PadraoInscricaoEstadual padrao, String ie) {
		String ieSoNumeros = limpar(ie);
		// Verificar tamanho da Inscrição Estadual.
		if (ieSoNumeros.length() != Texto.removerPontuacao(padrao.getFormato()).length())
			throw new AlfredException("Inscrição Estadual inválida. Tamanho de uma Inscrição Estadual válida para a UF informada é " +
					Texto.removerPontuacao(padrao.getFormato()).length() +
					". Esta Inscrição Estadual possui " + ieSoNumeros.length() + " números.");
		StringBuilder sb = new StringBuilder();
		int indicePadrao = 0;
		for (int i = 0; i < ieSoNumeros.length(); i++) {
			if (padrao.getFormato().charAt(indicePadrao) == 'P') {
				sb.append(padrao.getFormato().charAt(indicePadrao));
				indicePadrao++;
			}
			if (padrao.getFormato().charAt(indicePadrao) == '.'
				|| padrao.getFormato().charAt(indicePadrao) == '-'
				|| padrao.getFormato().charAt(indicePadrao) == '/') {
				sb.append(padrao.getFormato().charAt(indicePadrao));
				indicePadrao++;
			}
			sb.append(ieSoNumeros.charAt(i));
			indicePadrao++;
		}
		return sb.toString();
	}
	
	/**
	 * Limpar a Inscrição Estadual, mantendo somente os números.
	 * Não verifica se é uma Inscrição Estaudal válida.
	 * 
	 * @param ie Inscrição Estadual que deve ser limpa.
	 * @return Inscrição Estadual apenas com números.
	 */
	public static String limpar(String ie) {
		if (ie == null)
			throw new AlfredException("A Inscrição Estadual informada é nula.");
		if ("".equals(ie))
			throw new AlfredException("A Inscrição Estadual informada é vazia.");
		char[] chars = ie.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int indice = 0; indice < chars.length; indice++) {
			if (Numeros.isInteger(String.valueOf(chars[indice]))) {
				sb.append(chars[indice]);
			}
		}
		return sb.toString();
	}
}
