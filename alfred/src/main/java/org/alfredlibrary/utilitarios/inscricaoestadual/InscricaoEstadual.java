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
package org.alfredlibrary.utilitarios.inscricaoestadual;

import org.alfredlibrary.utilitarios.digitoverificador.Modulo10;
import org.alfredlibrary.utilitarios.digitoverificador.Modulo11;

/**
 * Utilitários de Inscrição Estadual.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 10/06/2010
 */
final public class InscricaoEstadual {

	public enum PadraoInscricaoEstadual {
		/* N = Número
		 * D = Dígito Verificador
		 * X = Tipo de empresa (0-Normal, 3-Produtor Rural, 5-Substituta,
		 *		7- Micro-Empresa Ambulante, 8-Micro-Empresa) 
		 * M = 1 se matriz, o restante para filiais
		 * CCC = Código do município
		 */
		ACRE("MOD11BASE10", "NN.NN.NNNN-D"),
		ALAGOAS("MOD11BASE10", "24XNNNNND"), 
		AMAPA("MOD11BASE10", "03NNNNNND"),
		/* Define-se dois valores, p e d, de acordo com as seguintes faixas de Inscrição Estadual:
		 *		De 03000001 a 03017000 => p = 5 e d = 0
		 *		De 03017001 a 03019022 => p = 9 e d = 1
		 *		De 03019023 em diante ===>p = 0 e d = 0
		 * P é somado ao cálculo do somatório de números multiplicados pelos pesos
		 * D é substitutivo de resto 0
		 * 0 é substitutivo de resto 1
		 */
		AMAZONAS("MOD11BASE10", "NN.NNN.NNN-D"),
		// TODO BAHIA (),
		CEARA("MOD11BASE10", "NNNNNNNN-D"),
		DISTRITO_FEDERAL("MOD11BASE10", "073.NNNNN.MMM-DD"),
		ESPIRITO_SANTO("MOD11BASE10", "NNNNNNNN-D"),
		// TODO GOIAS(),
		MARANHAO("MOD11BASE10", "12NNNNNN-D"),
		MATO_GROSSO("MOD11BASE10", "NNNNNNNNNN-D"),
		MATO_GROSSO_DO_SUL("MOD11BASE10", "28NNNNNN-D"),
		MINAS_GERAIS("MOD10|MOD11BASE12", "CCC.NNN.NNN/MMDD"),
		PARA("MOD11BASE10", "15-NNNNNN-D"),
		PARAIBA("MOD11BASE10", "NNNNNNNN-D"),
		PARANA("MOD11BASE07", "NNNNNNNN-DD"),
		// TODO PERNAMBUCO(),
		PIAUI("MOD11BASE10", "NNNNNNNN-D"),
		RIO_DE_JANEIRO("MOD11BASE07", "NN.NNN.NN-D"),
		RIO_GRANDE_DO_NORTE("MOD11BASE10", "NN.NNN.NNN-D"),
		RIO_GRANDE_DO_SUL("MOD11BASE10", "CCC/NNNNNND"),
		// Código de município varia de 001 a 467
		RONDONIA("MOD11BASE10", "CCCNNNNND"),
		// Despreza-se os três primeiros dígitos no cálculo do DV
		// TODO RORAIMA(),
		SANTA_CATARINA("MOD11BASE10", "NNN.NNN.NND"),
		// TODO SAO_PAULO(),
		SERGIPE("MOD11BASE10", "NNNNNNNN-D")//,
		// TODO TOCANTINS()
		;
				
		private String calculoDv;
		private String formato;
		private PadraoInscricaoEstadual(String calculoDv, String formato) {
			this.calculoDv = calculoDv;
			this.formato = formato;
		}
		
		public String getCalculoDv() {
			return calculoDv;
		}
		
		public String getFormato() {
			return formato;
		}
	}
	
	/**
     * Gerar uma Inscrição Estadual arbitrária.
 	 *  
 	 * @param padrao Indica qual a UF da Inscrição Estadual, para orientar a geração específica
     * @return Inscrição Estadual gerada arbitrariamente.
     */
    public static String gerar(PadraoInscricaoEstadual padrao) {
        StringBuilder iniciais = new StringBuilder();
        String mascara = padrao.formato;
        Integer numero;
        for (int i = 0; i < mascara.length(); i++) {
        	boolean atribuido = false;
        	if (mascara.charAt(i) >= '0' && mascara.charAt(i) <= '9') {
        		iniciais.append(mascara.charAt(i));
        	} else if (mascara.charAt(i) == 'N') {
        		numero = Integer.valueOf((int) (Math.random() * 10));
                iniciais.append(numero.toString());
        	} else if (mascara.charAt(i) >= 'X') {
        		/* X = Tipo de empresa (0-Normal, 3-Produtor Rural, 5-Substituta,
        		 *		7- Micro-Empresa Ambulante, 8-Micro-Empresa)
        		 */
        		do {
        			numero = Integer.valueOf((int) (Math.random() * 10));
        			if (numero.equals(0) || numero.equals(3) || numero.equals(5)
        					|| numero.equals(7) || numero.equals(8)) {
        				iniciais.append(numero.toString());
        				atribuido = true;
        			}
        		} while (!atribuido);
        	} else if (mascara.charAt(i) == 'D') {
        		// Dígito verificador. Gerado Posteriormente
        	} else if (mascara.charAt(i) == 'C') {
        		/* 
        		 * CCC = Código do município
        		 */
        		i = i + 2; // Salta 2 posições, pois o código do município é sempre em 3
        		do {
        			numero = Integer.valueOf((int) (Math.random() * 1000));
            		// RIO_GRANDE_DO_SUL :: Código de município varia de 001 a 467
            		if (padrao.equals(PadraoInscricaoEstadual.RIO_GRANDE_DO_SUL)) {
            			if (numero.compareTo(1) >= 0 && numero.compareTo(467) <= 0) {
            				atribuido = true;
            			} else {
            				atribuido = true;
            			}
            			if (atribuido) {
            				String codigoMunicipio = numero.toString();
            				for (int iTexto = 0; iTexto < 3 - codigoMunicipio.length(); iTexto++) {
            					codigoMunicipio = "0" + codigoMunicipio; 
            				}
            				iniciais.append(codigoMunicipio);
            				atribuido = true;
            			}
            		} else {
            			atribuido = true;
            		}
        		} while (!atribuido);
        	} else if (mascara.charAt(i) == 'M') {
        		/* 
        		 * M = 1 se matriz, o restante para filiais
        		 */
        		int tamanho = 1;
        		while (mascara.charAt(i + 1) == 'M') {
        			i++; // Avança para poder criar um código randômico de tamanhos diversos
        			tamanho++;
        		}
        		do {
        			numero = Integer.valueOf((int) (Math.random() * Math.pow(10, tamanho)));
            		String codigoFilial = numero.toString();
            		for (int iTexto = 0; iTexto < tamanho - codigoFilial.length(); iTexto++) {
            			codigoFilial = "0" + codigoFilial; 
            		}
            		iniciais.append(codigoFilial);
            		atribuido = true;
        		} while (!atribuido);
        	}
        }
        return iniciais.toString() + gerarDigitoVerificador(padrao, iniciais.toString());
    }
    
	/**
	 * Método que calcula o dígito verificador, observando se está correto.
	 * 
	 * @param num
	 * @param padrao Indica qual a UF da Inscrição Estadual, para orientar o cálculo específico
	 * @return Dígito verificador.
	 */
	public static String gerarDigitoVerificador(PadraoInscricaoEstadual padrao, String num) {
		// Retira números que devem ser desprezados na hora de calcular o DV
		if (!padrao.equals(PadraoInscricaoEstadual.RONDONIA)) {
			// Despreza-se os três primeiros dígitos no cálculo do DV
			num = num.substring(3);
		}
		// Obtem a sequência de métodos a serem aplicados para o cálculo dos DVs
		String[] metodoCalculo = padrao.calculoDv.split("|");
		/* Permite a inclusão de N DVs ao final, sem precisar repetir o método de cálculo,
		 * pela inclusão de parâmetro de quantidade de DVs. A necessidade atual indica
		 * que apenas a repetição ao final resolve, pois há apenas multiplicidade no
		 * número de DV ou nos métodos de cálculo (um para cada DV).
		 */
		int[] qtdDigitos = new int[metodoCalculo.length];
		int posMetodo = 0;
		String mascara = padrao.formato;
		for (int i = 0; i < mascara.length(); i++) {
        	if (mascara.charAt(i) == 'D') {
        		if (metodoCalculo.length < i) {
        			qtdDigitos[posMetodo]++;
        		} else {
        			qtdDigitos[i] = 1;
        			posMetodo++;
        		}
        	}
        }
		// Cria uma constante para calcular o DV e registra as variáveis de substituição
		char subZero = '0';
		char subUm = '1';
		int constante = 0;
		if (!padrao.equals(PadraoInscricaoEstadual.AMAPA)) {
			/* Define-se dois valores, p e d, de acordo com as seguintes faixas de Inscrição Estadual:
			 *		De 03000001 a 03017000 => p = 5 e d = 0
			 *		De 03017001 a 03019022 => p = 9 e d = 1
			 *		De 03019023 em diante ===>p = 0 e d = 0
			 * P é somado ao cálculo do somatório de números multiplicados pelos pesos
			 * D é substitutivo de resto 0
			 * 0 é substitutivo de resto 1
			 */
			subUm = '0';
			if (Long.valueOf(num).compareTo(Long.valueOf("03000001")) >= 0 && Long.valueOf(num).compareTo(Long.valueOf("03017000")) <= 0) {
				constante = 5;
				subZero = '0';
			} else if (Long.valueOf(num).compareTo(Long.valueOf("03017001")) >= 0 && Long.valueOf(num).compareTo(Long.valueOf("03019022")) <= 0) {
				constante = 9;
				subZero = '1';
			} else if (Long.valueOf(num).compareTo(Long.valueOf("03019023")) >= 0) {
				constante = 0;
				subZero = '0';
			}
		}
		// Calcula os DV de acordo com o método e a quantidade de DVs para cada um deles
		String dv = "";
		for (int indice = 0; indice < metodoCalculo.length; indice++) {
			int modulo = 0, base = 0;
			if (metodoCalculo[indice].indexOf("MOD") == 0) {
				modulo = Integer.valueOf(metodoCalculo[indice].substring(3,5)); 
				base = Integer.valueOf(metodoCalculo[indice].substring(metodoCalculo[indice].indexOf("BASE") + 4,5));
			}
			if (modulo == 11) {
				if (!padrao.equals(PadraoInscricaoEstadual.AMAPA)) {
					if (qtdDigitos[indice] == 1) {
						dv = dv + Modulo11.obterDVBaseParametrizada(num + dv, base, subZero, subUm);
					} else if (qtdDigitos[indice] > 1) {
						dv = dv + Modulo11.obterDVBaseParametrizada(num + dv, base, subZero, subUm, qtdDigitos[indice]);
					}
				} else {
					dv = dv + Modulo11.obterDVBaseParametrizadaComConstante(num + dv, base, subZero, subUm, constante);
				}
			} else if (modulo == 10) {
				if (qtdDigitos[indice] == 1) {
					dv = dv + Modulo10.obterDV(num + dv);
				} else if (qtdDigitos[indice] > 1) {
					dv = dv + Modulo10.obterDV(num + dv, qtdDigitos[indice]);
				}
			}
		}
		return dv;
	}
}
