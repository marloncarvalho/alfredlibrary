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
package org.alfredlibrary.validadores;
 

import org.alfredlibrary.utilitarios.inscricaoestadual.InscricaoEstadual.PadraoInscricaoEstadual;
import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Validador de Inscrição Estadual.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 11/06/2010
 */
final public class InscricaoEstadual {

	private InscricaoEstadual() {
	}

	/**
	 * Verificar se uma Inscrição Estadual é válida.
	 * 
	 * @param ie Inscrição Estadual a ser verificada.
	 * @return Verdadeiro caso seja válida. Falso, caso contrário.
	 */
	public static boolean isValido(PadraoInscricaoEstadual padrao, String ie) {
		// Se não estiver formatado, formatar
		if (Texto.manterNumeros(ie).length() == ie.length()) {
			ie = org.alfredlibrary.formatadores.InscricaoEstadual.formatar(padrao, ie);
		}
        if (ie.length() != padrao.getFormato().length())
            return false;
        for (int i = 0; i < ie.length(); i++) {
        	if (padrao.getFormato().charAt(i) == '.'
				|| padrao.getFormato().charAt(i) == '-'
				|| padrao.getFormato().charAt(i) == '/') {
				if (padrao.getFormato().charAt(i) != ie.charAt(i)) {
					return false;
				}
			} else if (padrao.getFormato().charAt(i) >= '0'
				&& padrao.getFormato().charAt(i) <= '9') {
				if (padrao.getFormato().charAt(i) != ie.charAt(i)) {
					return false;
				}
			} else if (padrao.getFormato().charAt(i) >= 'X') {
        		/* X = Tipo de empresa (0-Normal, 3-Produtor Rural, 5-Substituta,
        		 *		7- Micro-Empresa Ambulante, 8-Micro-Empresa)
        		 */
				if (padrao.getFormato().charAt(i) != '0'
					&& padrao.getFormato().charAt(i) != '3'
					&& padrao.getFormato().charAt(i) != '5'
					&& padrao.getFormato().charAt(i) != '7'
					&& padrao.getFormato().charAt(i) != '8') {
					if (padrao.getFormato().charAt(i) != ie.charAt(i)) {
						return false;
					}
				}
        	} else if (padrao.getFormato().charAt(i) == 'D') {
        		/* 
        		 * M = 1 se matriz, o restante para filiais
        		 */
        		StringBuilder sb = new StringBuilder();
        		int w = i;
        		do {
        			sb.append(ie.charAt(w));
        			w++;
        		} while (padrao.getFormato().charAt(w) == 'D');
        		if (ie.substring(i,i + sb.length()).compareTo(org.alfredlibrary.utilitarios.inscricaoestadual.InscricaoEstadual.gerarDigitoVerificador(padrao, Texto.manterNumeros(ie))) != 0) {
    				return false;
    			}
        		i = --w;
        	} else if (padrao.getFormato().charAt(i) == 'C') {
        		/* 
        		 * CCC = Código do município
        		 */
        		if (padrao.equals(PadraoInscricaoEstadual.RIO_GRANDE_DO_SUL)) {
        			if (Integer.valueOf(ie.substring(i,i+3)).compareTo(1) < 0
        					|| Integer.valueOf(ie.substring(i,i+3)).compareTo(467) > 0) {
        				return false;
        			}
        		}
        		i = i + 2; // Salta 2 posições, pois o código do município é sempre em 3
        	} else if (padrao.getFormato().charAt(i) == 'M') {
        		/* 
        		 * M = 1 se matriz, o restante para filiais
        		 */
        		StringBuilder sb = new StringBuilder();
        		int w = i;
        		do {
        			sb.append(ie.charAt(w));
        			w++;
        		} while (padrao.getFormato().charAt(w) == 'M');
        		if (Integer.valueOf(ie.substring(i,i + sb.length())).compareTo(Integer.valueOf(1)) < 0
    					|| Integer.valueOf(ie.substring(i,i+3)).compareTo(Integer.valueOf(sb.toString())) > 0) {
    				return false;
    			}
        		i = --w;
        	}
		}
        return true;
	}

}