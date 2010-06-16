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
package org.alfredlibrary.utilitarios.digitoverificador;

import java.util.ArrayList;
import java.util.List;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Utilitário para Geração de DV pela atribuição dos pesos parametrizada.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 14/06/2010
 */
public final class PesoPersonalizado {
	
	/**
	 * Calcular um dígito verificador a partir de uma sequência de números
	 * enviada e de uma sequencia de pesos.
	 * 
	 * @param fonte Sequência de números para cálculo do DV
	 * @param peso Sequência de pesos para cálculo do DV
	 * @return DV gerado.
	 */
	public static String obterDV (String fonte, String peso, String forma) {
		List<String> pesoSplit = new ArrayList<String>();
		StringBuilder sbSplitter = new StringBuilder();
		for (int splitter = 0; splitter < peso.length(); splitter++) {
			if (splitter == peso.length() - 1) {
				sbSplitter.append(peso.charAt(splitter));
				pesoSplit.add(sbSplitter.toString());
				sbSplitter = new StringBuilder();
			} else if (peso.charAt(splitter) == '|') {
				pesoSplit.add(sbSplitter.toString());
				sbSplitter = new StringBuilder();
			} else {
				sbSplitter.append(peso.charAt(splitter));
			}
		}
		validar(fonte, pesoSplit);
		int dv = 0;
		for (int i = 0; i < fonte.length(); i++) {
			dv += Integer.valueOf(String.valueOf(fonte.charAt(i))) * Integer.parseInt(String.valueOf(pesoSplit.get(i)));
		}
		dv = dv % 11;
		if (forma.equals("caracterDireito")) {
			if (dv < 10) {
				return String.valueOf(dv);
			} 
			return String.valueOf(dv - 10);
		} else if (forma.equals("mod11")) {
			dv = 11 - dv;
			if (dv > 9) dv -= 10;
			return String.valueOf(dv);			
		} else {
			return null;
		}
	}
	
	/**
	 * Verifica se a fonte possui apenas números.
	 * 
	 * @param fonte Texto a ser validado
	 * @param peso Peso a ser validado
	 */
	private static void validar(String fonte, List<String> peso) {
		for (int i = 0; i < peso.size(); i++) {
			try {
				Integer.valueOf(peso.get(i));
			} catch (Exception e) {
				throw new AlfredException("Peso informado contém caracteres não numéricos!");
			}
		}
		if (Texto.manterNumeros(fonte).length() != peso.size()) {
			throw new AlfredException("Texto e peso possuem tamanhos diferentes!");
		}
	}

}
