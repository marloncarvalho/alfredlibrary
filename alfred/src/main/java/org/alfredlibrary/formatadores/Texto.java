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

/**
 * Utilitário para formatar Textos.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 11/04/2011
 */
public final class Texto {

	private Texto() { 
		throw new AssertionError();
	}

	/**
	 * Obtém uma String correspondente a um determinado texto,
	 * de acordo com o formato especificado.
	 * 
	 * 9 - valor numérico
	 * A - alfanumérico (excluindo sinais)
	 * X - alfanumérico (incluindo sinais)
	 * D - dígito verificador (0-9 ou X)
	 * 
	 * @param texto Texto a ser formatado.
	 * @param formato Formato do texto de saída.
	 * @param direcao Direção da formatação do texto:
	 * 					TRUE  -> da esquerda para a direita
	 * 					FALSE -> da direita para a esquerda 
	 * @return String Texto com o formato especificado.
	 */
	public static String formatar (String texto, String formato, boolean direcao) {
		if (texto.length() > formato.length()) {
			throw new AlfredException("Texto maior que o formato!");
		}
		String textoFormatado = "";
		int indiceFormato;
		int tamanho = texto.length() < formato.length() ? texto.length() : formato.length();
		if (direcao) {
			indiceFormato = 0;
			for (int indice = 0; indice < tamanho; indice++) {
				if (texto.charAt(indice) == formato.charAt(indiceFormato)) {
					textoFormatado = textoFormatado + texto.charAt(indice);
					indiceFormato++;
				} else if (formato.charAt(indiceFormato) == '9') {
					if ((texto.charAt(indice) >= '0' && texto.charAt(indice) <= '9')) {
						textoFormatado = textoFormatado + texto.charAt(indice);
						indiceFormato++;
					} else {
						throw new AlfredException("Texto não atende ao formato informado! Caracter literal quando deveria haver um numérico!");
					}
				} else if (formato.charAt(indiceFormato) == 'A') {
					if ((texto.charAt(indice) >= 'a' && texto.charAt(indice) <= 'z') ||
							(texto.charAt(indice) >= 'A' && texto.charAt(indice) <= 'Z') ||
							(texto.charAt(indice) >= '0' && texto.charAt(indice) <= '9')) {
						textoFormatado = textoFormatado + texto.charAt(indice);
						indiceFormato++;
					} else {
						throw new AlfredException("Texto não atende ao formato informado! Caracter não alfanumérico!");
					}
				} else if (formato.charAt(indiceFormato) == 'D') {
					if ((texto.charAt(indice) == 'X') || (texto.charAt(indice) >= '0' && texto.charAt(indice) <= '9')) {
						textoFormatado = textoFormatado + texto.charAt(indice);
						indiceFormato++;
					} else {
						throw new AlfredException("Texto não atende ao formato informado! Caracter não atende à especificação de dígito verificador!");
					}
				} else if (formato.charAt(indiceFormato) == 'X') {
					textoFormatado = textoFormatado + texto.charAt(indice);
					indiceFormato++;
				} else {
					textoFormatado = textoFormatado + formato.charAt(indiceFormato);
					indiceFormato++;
					indice--;
				}
			} 
		} else {
			indiceFormato = formato.length() - 1;
			for (int indice = texto.length() - 1; indice >= 0; indice--) {
				if (texto.charAt(indice) == formato.charAt(indiceFormato)) {
					textoFormatado = texto.charAt(indice) + textoFormatado;
					indiceFormato--;
				} else if (formato.charAt(indiceFormato) == '9') {
					if ((texto.charAt(indice) >= '0' && texto.charAt(indice) <= '9')) {
						textoFormatado = texto.charAt(indice) + textoFormatado;
						indiceFormato--;
					} else {
						throw new AlfredException("Texto não atende ao formato informado! Caracter literal quando deveria haver um numérico!");
					}
				} else if (formato.charAt(indiceFormato) == 'A') {
					if ((texto.charAt(indice) >= 'a' && texto.charAt(indice) <= 'z') ||
							(texto.charAt(indice) >= 'A' && texto.charAt(indice) <= 'Z') ||
							(texto.charAt(indice) >= '0' && texto.charAt(indice) <= '9')) {
						textoFormatado = texto.charAt(indice) + textoFormatado;
						indiceFormato--;
					} else {
						throw new AlfredException("Texto não atende ao formato informado! Caracter não alfanumérico!");
					}
				} else if (formato.charAt(indiceFormato) == 'D') {
					if ((texto.charAt(indice) == 'X') || (texto.charAt(indice) >= '0' && texto.charAt(indice) <= '9')) {
						textoFormatado = texto.charAt(indice) + textoFormatado;
						indiceFormato--;
					} else {
						throw new AlfredException("Texto não atende ao formato informado! Caracter não atende à especificação de dígito verificador!");
					}
				} else if (formato.charAt(indiceFormato) == 'X') {
					textoFormatado = texto.charAt(indice) + textoFormatado;
					indiceFormato--;
				} else {
					textoFormatado = formato.charAt(indiceFormato) + textoFormatado;
					indiceFormato--;
					indice++;
				}
			}
		}
		return textoFormatado;
	}
	
}