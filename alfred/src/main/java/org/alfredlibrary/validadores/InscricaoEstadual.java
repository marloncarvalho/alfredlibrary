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
 

import org.alfredlibrary.utilitarios.digitoverificador.Modulo11;
import org.alfredlibrary.utilitarios.digitoverificador.PesoPersonalizado;
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
        int numDig = 1; // Para saber em que sequencia de digitos do padrão de SP está a validação
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
        		StringBuilder sb = new StringBuilder();
        		int w = i;
        		do {
        			sb.append(ie.charAt(w));
        			w++;
        		} while (padrao.getFormato().charAt(w) == 'D');
        		if (padrao.equals(PadraoInscricaoEstadual.SAO_PAULO_INDUSTRIAIS_COMERCIANTES)) {
        			if (numDig == 1) {
                		if (ie.substring(i, i + sb.length()).compareTo(PesoPersonalizado.obterDV(Texto.manterNumeros(ie.substring(0, i)), "1|3|4|5|6|7|8|10")) != 0) {
                			return false;
                		}
                	} else {
                		if (ie.substring(i, i + sb.length()).compareTo(Modulo11.obterDVBaseParametrizada(Texto.manterNumeros(ie.substring(0, i)), 11, '1', '0')) != 0) {
                			return false;
                		}
                	}
                } else {
                	if (ie.substring(i,i + sb.length()).compareTo(org.alfredlibrary.utilitarios.inscricaoestadual.InscricaoEstadual.gerarDigitoVerificador(padrao, Texto.manterNumeros(ie.substring(0, i)))) != 0) {
        				return false;
        			}
                }
        		i = --w;
        		numDig++;
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
        	} else if (padrao.getFormato().charAt(i) == 'G') {
        		/* 
        		 * GG = Constante de Goiás (pode ser 10, 11 ou 15)
        		 */
        		if (ie.charAt(i) != '1') {
        			return false;
        		}
        		i++; // Salta 1, pois o conteúdo de GG sempre tem 2 caracteres
        		if (ie.charAt(i) != '0' && ie.charAt(i) != '1' && ie.charAt(i) != '5') {
        			return false;
        		}
        	} else if (padrao.getFormato().charAt(i) == 'G') {
         		/* TT = 01 = Produtor Rural ( não possui CGC),
         		 *		02 = Industria e Comércio
         		 *		03 = Empresas Rudimentares
         		 *		99 = Empresas do Cadastro Antigo (SUSPENSAS)
         		 */
        		
        		if (!ie.substring(i, i + 2).equals("01") && !ie.substring(i, i + 2).equals("02")
        				&& !ie.substring(i, i + 2).equals("03") && !ie.substring(i, i + 2).equals("99")) {
        			return false;
        		}
        		i++; // Salta 1, pois o conteúdo de GG sempre tem 2 caracteres
         	} else if (padrao.getFormato().charAt(i) == 'P') {
				if (padrao.getFormato().charAt(i) != ie.charAt(i)) {
					return false;
				}
			}
		}
        return true;
	}

}