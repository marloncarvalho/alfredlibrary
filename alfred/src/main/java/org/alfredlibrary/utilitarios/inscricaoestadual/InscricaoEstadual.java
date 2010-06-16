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

import java.util.ArrayList;
import java.util.List;

import org.alfredlibrary.utilitarios.digitoverificador.Modulo10;
import org.alfredlibrary.utilitarios.digitoverificador.Modulo11;
import org.alfredlibrary.utilitarios.digitoverificador.PesoPersonalizado;
import org.alfredlibrary.utilitarios.digitoverificador.PesoPosicional;
import org.alfredlibrary.utilitarios.texto.Texto;

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
		 * GG = Constante de Goiás (pode ser 10, 11 ou 15)
		 * TT = 01 = Produtor Rural ( não possui CGC),
		 *		02 = Industria e Comércio
		 *		03 = Empresas Rudimentares
		 *		99 = Empresas do Cadastro Antigo (SUSPENSAS)
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
		BAHIA ("METODOVARIAVEL", "NNNNNN-DD"),
		/*
		 * Para Inscrições cujo primeiro dígito da I.E. é 0,1,2,3,4,5,8 cálculo pelo módulo 10
		 * Para Inscrições cujo primeiro dígito da I.E. é 6, 7 ou 9 cálculo pelo módulo 11
		 */
		CEARA("MOD11BASE10", "NNNNNNNN-D"),
		DISTRITO_FEDERAL("MOD11BASE10", "073.NNNNN.MMM-DD"),
		ESPIRITO_SANTO("MOD11BASE10", "NNNNNNNN-D"),
		GOIAS("MOD11BASE10","GG.NNN.NNN-D"),
		/* 
		 * Quando o resto da divisão for zero (0), o dígito verificador será zero (0);
		 * Quando o resto da divisão for um (1), e a inscrição for maior ou igual a 10103105 e menor ou igual a 10119997, o dígito verificador será um (1);
		 * Quando o resto da divisão for um (1), e a inscrição estiver fora do intervalo citado acima, o dígito verificador será zero (0);
		 */
		MARANHAO("MOD11BASE10", "12NNNNNN-D"),
		MATO_GROSSO("MOD11BASE10", "NNNNNNNNNN-D"),
		MATO_GROSSO_DO_SUL("MOD11BASE10", "28NNNNNN-D"),
		MINAS_GERAIS("MOD10|MOD11BASE12", "CCC.NNN.NNN/MMDD"),
		PARA("MOD11BASE10", "15-NNNNNN-D"),
		PARAIBA("MOD11BASE10", "NN.NNN.NNN-D"),
		PARANA("MOD11BASE08", "NNN.NNNNN-DD"),
		PERNAMBUCO("PESOPROPRIO", "NN.N.NNN.NNNNNNN-D"),
		PIAUI("MOD11BASE10", "NNNNNNNN-D"),
		RIO_DE_JANEIRO("MOD11BASE08", "NN.NNN.NN-D"),
		RIO_GRANDE_DO_NORTE("MOD11BASE10", "NN.NNN.NNN-D"),
		RIO_GRANDE_DO_SUL("MOD11BASE10", "CCC/NNNNNND"),
		// Código de município varia de 001 a 467
		RONDONIA("MOD11BASE10", "CCCNNNNND"),
		// Despreza-se os três primeiros dígitos no cálculo do DV
		RORAIMA("PESOPOSICIONAL", "24NNNNNN-D"),
		SANTA_CATARINA("MOD11BASE10", "NNN.NNN.NND"),
		SAO_PAULO_INDUSTRIAIS_COMERCIANTES("PESOPROPRIO|RESTO11SP", "NNN.NNN.NND.NND"),
		/* 
		 * I – Industriais e comerciantes (exceto produtores rurais a eles não equiparados):
		 *		> Formato: 12 dígitos sendo que o 9º e o 12º são dígitos verificadores
		 *		> Exemplo: Inscrição Estadual 110.042.490.114
		 */
		SAO_PAULO_PRODUTOR_RURAL("PESOPROPRIO", "P-NNNNNNNN.D/NNN"),
		/* 
		 * II – Inscrição estadual de Produtor Rural (Não equiparado a industrial ou comerciante, cujas inscrições obedecem a Regra descrita no item anterior):
		 *		> Formato: - P0MMMSSSSD000
		 *		- 13 caracteres dos quais o 10º caracter contado a partir da esquerda ("D") é o dígito verificador 
		 *		- Inicia sempre com "P" e apresenta a sequência 0MMMSSSSD000, onde:
		 *		0MMMSSSS-algarismos que serão utilizados no cálculo do dígito verificador "D"
		 *		"D" - Dígito verificador que consiste os 8 (oito)dígitos imediatamente anteriores
		 *		000 - 3 (três) dígitos que compõem o nº de inscrição mas não utilizados no cálculo do dígito verificador 
		 *		> Exemplo: Inscrição Estadual P-01100424.3/002
		 */
		SERGIPE("MOD11BASE10", "NNNNNNNN-D"),
		TOCANTINS("MOD11BASE10", "NN.TT.NNNNNN-D")
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
            			String codigoMunicipio = numero.toString();
        				for (int iTexto = 0; iTexto < 3 - codigoMunicipio.length(); iTexto++) {
        					codigoMunicipio = "0" + codigoMunicipio; 
        				}
        				iniciais.append(codigoMunicipio);
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
        		numero = Integer.valueOf((int) (Math.random() * Math.pow(10, tamanho)));
            	String codigoFilial = numero.toString();
            	for (int iTexto = 0; iTexto < tamanho - codigoFilial.length(); iTexto++) {
            		codigoFilial = "0" + codigoFilial; 
            	}
            	iniciais.append(codigoFilial);
        	} else if (mascara.charAt(i) == 'G') {
        		/* 
        		 * GG = Constante de Goiás (pode ser 10, 11 ou 15)
        		 */
        		i++; // Salta 1, pois o conteúdo de GG sempre tem 2 caracteres
        		do {
        			numero = Integer.valueOf((int) (Math.random() * 100));
            		String codigoGoias = numero.toString();
            		for (int iTexto = 0; iTexto < 2 - codigoGoias.length(); iTexto++) {
            			codigoGoias = "0" + codigoGoias; 
            		}
            		if (codigoGoias.equals("10") || codigoGoias.equals("11") || codigoGoias.equals("15")) {
            			iniciais.append(codigoGoias);
            			atribuido = true;
            		}
        		} while (!atribuido);
        	} else if (mascara.charAt(i) == 'T') {
         		/* TT = 01 = Produtor Rural ( não possui CGC),
         		 *		02 = Industria e Comércio
         		 *		03 = Empresas Rudimentares
         		 *		99 = Empresas do Cadastro Antigo (SUSPENSAS)
         		 */
         		i++; // Salta 1, pois o conteúdo de GG sempre tem 2 caracteres
         		do {
         			numero = Integer.valueOf((int) (Math.random() * 100));
             		String codigoTocantins = numero.toString();
             		for (int iTexto = 0; iTexto < 2 - codigoTocantins.length(); iTexto++) {
             			codigoTocantins = "0" + codigoTocantins; 
             		}
             		if (codigoTocantins.equals("01") || codigoTocantins.equals("02")
             				|| codigoTocantins.equals("03") || codigoTocantins.equals("99")) {
             			iniciais.append(codigoTocantins);
             			atribuido = true;
             		}
         		} while (!atribuido);
         	} else if (mascara.charAt(i) == 'P') {
         		iniciais.append('P');
         	}
        }
        if (padrao.equals(PadraoInscricaoEstadual.SAO_PAULO_INDUSTRIAIS_COMERCIANTES)) {
        	List <String> dvSplit = new ArrayList<String>();
        	String dvGerado = gerarDigitoVerificador(padrao, iniciais.toString());
        	StringBuilder sbSplitter = new StringBuilder();
			for (int splitter = 0; splitter < dvGerado.length(); splitter++) {
				if (splitter == dvGerado.length() - 1) {
					sbSplitter.append(dvGerado.charAt(splitter));
					dvSplit.add(sbSplitter.toString());
					sbSplitter = new StringBuilder();
				} else if (dvGerado.charAt(splitter) == '|') {
					dvSplit.add(sbSplitter.toString());
					sbSplitter = new StringBuilder();
				} else {
					sbSplitter.append(dvGerado.charAt(splitter));
				}
			}
        	return iniciais.substring(0, 8) + dvSplit.get(0) + iniciais.substring(8) + dvSplit.get(1);
        } else if (padrao.equals(PadraoInscricaoEstadual.SAO_PAULO_PRODUTOR_RURAL)) {
        	return iniciais.toString().substring(0,9) + gerarDigitoVerificador(padrao, Texto.manterNumeros(iniciais.toString()))
        			+ iniciais.toString().substring(9,12);
        } else {
        	return iniciais.toString() + gerarDigitoVerificador(padrao, iniciais.toString());
        }
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
		if (padrao.equals(PadraoInscricaoEstadual.RONDONIA)) {
			// Despreza-se os três primeiros dígitos no cálculo do DV
			num = num.substring(3);
		} else if (padrao.equals(PadraoInscricaoEstadual.TOCANTINS)) {
			num = num.substring(0, 2) + num.substring(4);
		} else if (padrao.equals(PadraoInscricaoEstadual.SAO_PAULO_PRODUTOR_RURAL)) {
			num = num.substring(0, 8);
		}
		// Obtem a sequência de métodos a serem aplicados para o cálculo dos DVs
		List<String> metodoCalculo = new ArrayList<String>();
		/* 
		 * Para Inscrições cujo primeiro dígito da I.E. é 0,1,2,3,4,5,8 cálculo pelo módulo 10
		 * Para Inscrições cujo primeiro dígito da I.E. é 6, 7 ou 9 cálculo pelo módulo 11
		 */
		if (padrao.equals(PadraoInscricaoEstadual.BAHIA)) {
			if (num.charAt(0) == '6' || num.charAt(0) == '7' || num.charAt(0) == '9') {
				metodoCalculo.add("MOD11BASE10");
			} else {
				metodoCalculo.add("MOD10BASE10");
			}
		} else {
			StringBuilder sbSplitter = new StringBuilder();
			for (int splitter = 0; splitter < padrao.getCalculoDv().length(); splitter++) {
				if (splitter == padrao.getCalculoDv().length() - 1) {
					sbSplitter.append(padrao.getCalculoDv().charAt(splitter));
					metodoCalculo.add(sbSplitter.toString());
					sbSplitter = new StringBuilder();
				} else if (padrao.getCalculoDv().charAt(splitter) == '|') {
					metodoCalculo.add(sbSplitter.toString());
					sbSplitter = new StringBuilder();
				} else {
					sbSplitter.append(padrao.getCalculoDv().charAt(splitter));
				}
			}
		}
		/* Permite a inclusão de N DVs ao final, sem precisar repetir o método de cálculo,
		 * pela inclusão de parâmetro de quantidade de DVs. A necessidade atual indica
		 * que apenas a repetição ao final resolve, pois há apenas multiplicidade no
		 * número de DV ou nos métodos de cálculo (um para cada DV).
		 */
		int[] qtdDigitos = new int[metodoCalculo.size()];
		int posMetodo = 0;
		String mascara = padrao.formato;
		for (int i = 0; i < mascara.length(); i++) {
        	if (mascara.charAt(i) == 'D') {
        		if (metodoCalculo.size() - 1 < i) {
        			qtdDigitos[posMetodo]++;
        		} else {
        			qtdDigitos[i] = 1;
        			posMetodo++;
        		}
        	}
        }
		if (padrao.equals(PadraoInscricaoEstadual.MINAS_GERAIS)) {
			// Registra a quantidade de dígitos de cada método - 1+1 no caso de MG 
			qtdDigitos[0] = 1;
			qtdDigitos[1] = 1;
		}
		// Cria uma constante para calcular o DV e registra as variáveis de substituição
		char subZero = '0';
		char subUm = '1';
		int constante = 0;
		if (padrao.equals(PadraoInscricaoEstadual.PARANA)
				|| padrao.equals(PadraoInscricaoEstadual.RIO_GRANDE_DO_SUL)
				|| padrao.equals(PadraoInscricaoEstadual.DISTRITO_FEDERAL)) {
			subUm = '0';
		} else if (padrao.equals(PadraoInscricaoEstadual.GOIAS)) {
			/* 
			 * Quando o resto da divisão for zero (0), o dígito verificador será zero (0);
			 * Quando o resto da divisão for um (1), e a inscrição for maior ou igual a 10103105 e menor ou igual a 10119997, o dígito verificador será um (1);
			 * Quando o resto da divisão for um (1), e a inscrição estiver fora do intervalo citado acima, o dígito verificador será zero (0);
			 */
			if (Long.valueOf(num.substring(0,8)).compareTo(Long.valueOf("10103105")) >= 0
					&& Long.valueOf(num.substring(0,8)).compareTo(Long.valueOf("10119997")) <= 0) {
				subUm = '1';
			} else {
				subUm = '0';
			}
		} else if (padrao.equals(PadraoInscricaoEstadual.AMAPA)) {
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
		for (int indice = 0; indice < metodoCalculo.size(); indice++) {
			int modulo = 0, base = 0;
			if (metodoCalculo.get(indice).indexOf("MOD") == 0) {
				modulo = Integer.valueOf(metodoCalculo.get(indice).substring(3,5)); 
				if (metodoCalculo.get(indice).indexOf("BASE") != -1) {
					base = Integer.valueOf(metodoCalculo.get(indice).substring(metodoCalculo.get(indice).indexOf("BASE") + 4,metodoCalculo.get(indice).indexOf("BASE") + 6));
				} else {
					base = 0;
				}
				if (modulo == 11) {
					if (padrao.equals(PadraoInscricaoEstadual.AMAPA)) {
						dv = dv + Modulo11.obterDVBaseParametrizadaComConstante(num + dv, base, subZero, subUm, constante);
					} else if (padrao.equals(PadraoInscricaoEstadual.GOIAS)) {
						if (qtdDigitos[indice] == 1) {
							dv = dv + Modulo11.obterDVBaseParametrizada(num + dv, base, subZero, subUm);
						} else if (qtdDigitos[indice] > 1) {
							dv = dv + Modulo11.obterDVBaseParametrizada(num + dv, base, subZero, subUm, qtdDigitos[indice]);
						}
					} else {
						if (qtdDigitos[indice] == 1) {
							dv = dv + Modulo11.obterDVBaseParametrizada(num + dv, base, subZero, subUm);
						} else if (qtdDigitos[indice] > 1) {
							dv = dv + Modulo11.obterDVBaseParametrizada(num + dv, base, subZero, subUm, qtdDigitos[indice]);
						}
					}
				} else if (modulo == 10) {
					String numMod10 = new String(num);
					if (padrao.equals(PadraoInscricaoEstadual.MINAS_GERAIS)) {
						// Inclui um "0" após o código do município para calcular o primeiro DV
						numMod10 = Texto.manterNumeros(numMod10).substring(0,3) + "0" + Texto.manterNumeros(numMod10).substring(3,11);
					}
					if (base == 0) {
						if (qtdDigitos[indice] == 1) {
							dv = dv + Modulo10.obterDV(numMod10 + dv);
						} else if (qtdDigitos[indice] > 1) {
							dv = dv + Modulo10.obterDV(numMod10 + dv, qtdDigitos[indice]);
						}
					} else {
						if (qtdDigitos[indice] == 1) {
							dv = dv + Modulo10.obterDVBaseParametrizada(numMod10 + dv, base, subZero, subUm);
						} else if (qtdDigitos[indice] > 1) {
							dv = dv + Modulo10.obterDVBaseParametrizada(numMod10 + dv, base, subZero, subUm, qtdDigitos[indice]);
						}
					}
				}
			} else if (metodoCalculo.get(indice).indexOf("PESOPOSICIONAL") == 0) {
				dv = dv + PesoPosicional.obterDV(num);
			} else if (metodoCalculo.get(indice).indexOf("PESOPROPRIO") == 0) {
				if (padrao.equals(PadraoInscricaoEstadual.PERNAMBUCO)) {
					dv = dv + PesoPersonalizado.obterDV(Texto.manterNumeros(num), "5|4|3|2|1|9|8|7|6|5|4|3|2", "mod11");
				} else if (padrao.equals(PadraoInscricaoEstadual.SAO_PAULO_PRODUTOR_RURAL)) {
					dv = dv + PesoPersonalizado.obterDV(num, "1|3|4|5|6|7|8|10", "caracterDireito");
				} else if (padrao.equals(PadraoInscricaoEstadual.SAO_PAULO_INDUSTRIAIS_COMERCIANTES)) {
					dv = dv + PesoPersonalizado.obterDV(num.substring(0, 8), "1|3|4|5|6|7|8|10", "caracterDireito");
					num = num.substring(0, 8) + dv + num.substring(8);
					dv = dv + "|";
				}
			} else if (metodoCalculo.get(indice).indexOf("RESTO11SP") == 0) {
				dv = dv + Modulo11.obterDVResto11BaseParametrizada(num, 11, '0');
			}
		}
		return dv;
	}
}
