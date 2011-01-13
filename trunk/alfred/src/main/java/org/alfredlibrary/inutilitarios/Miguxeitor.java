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
package org.alfredlibrary.inutilitarios;

/**
 * Inutilitário máximo do Alfred.
 * Transformar frases corretas em Miguxês!
 * 
 * Mas a ideia não foi minha, ainda bem! Foi do Aurélio Verde!
 * Acesse o site em http://www.coisinha.com.br/miguxeitor/
 * 
 * Todo este código foi "extraído" de lá, com pequenas modificações para funcionar em Java.
 * Todos créditos são do Aurélio!
 * 
 * @author Marlon Silva Carvalho
 * @since 10/05/2010
 */
final public class Miguxeitor {
	
	public Miguxeitor() {
		throw new AssertionError();
	}

	/**
	 * Esculhambar, digo, Miguxar uma frase.
	 * 
	 * @param text Texto a ser escu... miguxado.
	 * @param level Nível de esculhambame... miguxamento.
	 * @return Texto escu... miguxado.
	 */
	public static String miguxar(String text, final int level) {
		text = text.toLowerCase();
		text = text.replaceAll("\\buma?\\b", "1");
		text = text.replaceAll("\\b(dois|duas)\\b", "2");
		text = text.replaceAll("\\btrês\\b", "3");
		text = text.replaceAll("\\bquatro\\b", "4");
		text = text.replaceAll("\\bcinco\\b", "5");
		text = text.replaceAll("\\bseis\\b", "6");
		text = text.replaceAll("\\bsete\\b", "7");
		text = text.replaceAll("\\boito\\b", "8");
		text = text.replaceAll("\\bnove\\b", "9");
		text = text.replaceAll("\\bdez\\b", "10");
		text = text.replaceAll("\\bonze\\b", "11");
		text = text.replaceAll("\\bdoze\\b", "12");
		text = text.replaceAll("\\btreze\\b", "13");
		text = text.replaceAll("\\b(c|qu)atorze\\b", "14");
		text = text.replaceAll("\\bquinze\\b", "15");
		text = text.replaceAll("\\bdezesseis\\b", "16");
		text = text.replaceAll("\\bdezessete\\b", "17");
		text = text.replaceAll("\\bdezoito\\b", "18");
		text = text.replaceAll("\\bdezenove\\b", "19");
		// Repetições
		text = text.replaceAll("\\b([0-9]+) vez(es)?\\b", "$1x");
		// Horas, minutos
		text = text.replaceAll("\\b(\\d+) horas?\\b", "$1h");
		text = text.replaceAll("\\b(\\d+) minutos?\\b", "$1min");
		if (level < 3) {
			text = text.replaceAll("\\b(\\d+) segundos?\\b", "$1s");
		}
		// Dias da semana
		text = text.replaceAll("\\bsegunda-feira\\b", "2a");
		text = text.replaceAll("\\bterça-feira\\b", "3a");
		text = text.replaceAll("\\bquarta-feira\\b", "4a");
		text = text.replaceAll("\\bquinta-feira\\b", "5a");
		text = text.replaceAll("\\bsexta-feira\\b", "6a");
		if (level < 3) {
			// Ordinais
			text = text.replaceAll("\\bprimeir([ao])\\b", "1$1");
			text = text.replaceAll("\\bsegund([ao])\\b", "2$1");
			text = text.replaceAll("\\bterceir([ao])\\b", "3$1");
			text = text.replaceAll("\\bquart([ao])\\b", "4$1");
			text = text.replaceAll("\\bquint([ao])\\b", "5$1");
			text = text.replaceAll("\\bsext([ao])\\b", "6$1");
			text = text.replaceAll("\\bsétim([ao])\\b", "7$1");
			text = text.replaceAll("\\boitav([ao])\\b", "8$1");
			text = text.replaceAll("\\bnon([ao])\\b", "9$1");
			text = text.replaceAll("\\bdécim([ao])\\b", "10$1");

			// Abreviações não miguxas
			text = text.replaceAll("\\bfi(m|nal) de semana\\b", "fds");
		}
		// Símbolos
		text = text.replaceAll("\\baté mais\\b", "t+");
		text = text.replaceAll("\\bdemais\\b", "d+");
		text = text.replaceAll("\\bmais ou menos\\b", "+-");
		text = text.replaceAll("\\bmais\\b", "+");
		text = text.replaceAll("\\bmenos\\b", "-");
		text = text.replaceAll("\\bmei[ao]\\b", "1/2");
		// Abreviações simples
		text = text.replaceAll("\\bpor\\s?qu[eê]\\b", "pq");
		text = text.replaceAll("\\bhoje\\b", "hj");
		text = text.replaceAll("\\btambém\\b", "tb");
		text = text.replaceAll("\\bbeleza\\\b", "blz");
		text = text.replaceAll("\\bfirmeza\\b", "fmz");
		text = text.replaceAll("\\bquando\\b", "qdo");
		text = text.replaceAll("\\bquant([ao])(s?)\\b", "qt$1$2");
		text = text.replaceAll("\\bmuit([ao])(s?)\\b", "mt$1$2");
		text = text.replaceAll("\\bbeij(o|ão)\\b", "bj$1");
		text = text.replaceAll("\\bbeijos\\b", "bjs");
		if (level == 1) {
			text = text.replaceAll("\\bcom([^\\wáéíóúàâêôãõüç]|$)", "c/$1");
		} else if (level == 3) {
			text = text.replaceAll("\\bcom([^\\wáéíóúàâêôãõüç]|$)", "cum$1");
		}
		if (level > 1) {
			// Abreviações avançadas
			text = text.replaceAll("\\bmesm[ao](s?)\\b", "msm$1");
			text = text.replaceAll("\\bdepois\\b", "dpois");
			text = text.replaceAll("\\bquem\\b", "qm");
			text = text.replaceAll("\\bcomigo\\b", "cmg");
			text = text.replaceAll("\\bcadê", "kd");
			text = text.replaceAll("\\bqualquer\\b", "qq");
			text = text.replaceAll("\\bfalou\\b", "flw");
			text = text.replaceAll("\\bvaleu\\b", "vlw");
			text = text.replaceAll("\\btchau\\b", "xau");
		}
		if (level == 1) {
			text = text.replaceAll("\\bque\\b", "q");
			text = text.replaceAll("\\bvocê", "vc");
			text = text.replaceAll("\\be-?mail(s?)\\b", "mail$1");
		} else if (level == 2) {
			text = text.replaceAll("\\bque\\b", "ke");
			text = text.replaceAll("\\bvocês\\b", "6");
			text = text.replaceAll("\\bvocê", "vc"); // c fica estranho em kd c?
		} else {
			text = text.replaceAll("\\bque\\b", "ki");
			text = text.replaceAll("\\b(adoro você|te adoro)", "adoluxê");
			text = text.replaceAll("\\bamo vocês\\b", "amodolu vocês");
			text = text.replaceAll("\\b(amo você|te amo)", "te amodolu");
			text = text.replaceAll("\\bvocê", "vuxê");
		}
		// Glossário
		text = text.replaceAll("\\btecl(e|ar|ou|amos)\\b", "tc");
		text = text.replaceAll("\\binternet\\b", "net");
		text = text.replaceAll("\\be-?mail(s?)\\b", "meio$1");
		text = text.replaceAll("\\b(grana|dinheiro)\\b", "$$$$$$"); // $$$
		if (level == 2) {
			text = text.replaceAll("\\badicion", "adde"); // Tou t addeando
			text = text.replaceAll("\\bamig([ao])\\b", "mig$1"); // miga
			text = text.replaceAll("\\blind([ao])\\b", "leend$1"); // leenda
			text = text.replaceAll("\\bnovidade(s?)\\b", "9dad$1");
		} else if (level == 3) {
			text = text.replaceAll("\\badicion[\\wáí]+", "add"); // Tou t add
			text = text.replaceAll("\\bamig([ao]s?)\\b", "migux$1"); // miguxa
			text = text.replaceAll("\\blind([ao]s?)\\b", "lindux$1"); // linduxa
			text = text.replaceAll("\\bfof([ao]s?)\\b", "fofux$1"); // fofuxa
			text = text.replaceAll("\\bdormir\\b", "mimir");
			text = text.replaceAll("\\bnome(s?)\\b", "nominho$1");
			text = text.replaceAll("\\besposa\\b", "marida");
			text = text.replaceAll("\\b(de novo|novamente)\\b", "dinovo");
			text = text.replaceAll("\\b(aliás|por exemplo)\\b", "tipo assim");
		}
		// tou, tava, tar
		text = text.replaceAll("\\best(ar|ou|ava|ive|aria|ão)\\b", "t$1");
		text = text.replaceAll("\\bestá([^\\wáéíóúàâêôãõüç]|$)", "tah$1");
		// para
		text = text.replaceAll("\\bpara ([ao]s?)\\b", "pr$1");
		text = text.replaceAll("\\bpara([^\\wáéíóúàâêôãõüç-]|$)", "pra$1");
		if (level == 2) {
			text = text.replaceAll("\\bpr[ao]([^\\wáéíóúàâêôãõüç]|$)", "p$1");
		}
		// Simplifiq: irmos -> ir, acabou -> cabou
		text = text.replaceAll("([aei]r)mos\\b", "$1");
		text = text.replaceAll("\\bacab", "cab");
		if (level > 1) {
			// entaum, naum
			text = text.replaceAll("ão\\b", "aum");
			// andando -> andano, comendo -> comeno (depois fica melhor: andanu,
			// comenu)
			text = text.replaceAll("(\\w[aei])ndo\\b", "$1no");
			// tada$ -> tadeenha (e alguns outros casos), foto -> foteenha,
			// gatinha -> gateenha
			text = text.replaceAll("(\\w[crt]ad)([ao])\\b", "$1eenh$2");
			text = text.replaceAll("foto(s?)\\b", "foteenha$1");
			text = text.replaceAll("(\\w)tinh([ao])\\b", "$1teenh$2");
			if (level > 2) {
				// No Orkut é mais fófi terminar em i
				text = text.replaceAll("\\bse\\b", "si");
				text = text.replaceAll("\\bde\\b", "di");
				text = text.replaceAll("\\bte\\b", "ti");
			} else {
				// No MSN o som da letra vira a palavra
				text = text.replaceAll("\\bse\\b", "c");
				text = text.replaceAll("\\bde\\b", "d");
				text = text.replaceAll("\\bte\\b", "t");
			}
			// CH, SH e QU não existem
			text = text.replaceAll("ch", "x");
			text = text.replaceAll("sh", "x");
			text = text.replaceAll("qu", "k");
			// e -> i (alguns casos)
			text = text.replaceAll("(\\w(ss|[cdgtv]))e(s?)m?\\b", "$1i$3");
			text = text.replaceAll("\\bseg", "sig");
			text = text.replaceAll("\\bdes([^s])", "dis$1");
		}
		// ei -> i (alguns casos) deixa -> dexa
		text = text.replaceAll("eix", "ex");
		if (level > 1) {
			// o -> u (alguns casos)
			text = text.replaceAll("\\bbonit", "bunit");
			// e sozinho -> i
			text = text.replaceAll("\\be\\b", "i");
			if (level > 2) {
				// inglês -> ingleix
				text = text.replaceAll("ês\\b", "eix");
				// atrás -> atraix
				text = text.replaceAll("(\\w)(ás|az)\\b", "$1aix");
			}
		}
		// Acento no final eh moh uoh
		text = text.replaceAll("á([^\\wáéíóúàâêôãõüç]|$)", "ah$1");
		text = text.replaceAll("é([^\\wáéíóúàâêôãõüç]|$)", "eh$1");
		text = text.replaceAll("í([^\\wáéíóúàâêôãõüç]|$)", "ih$1");
		text = text.replaceAll("ó([^\\wáéíóúàâêôãõüç]|$)", "oh$1");
		text = text.replaceAll("ú([^\\wáéíóúàâêôãõüç]|$)", "uh$1");
		// Acentuação? Nunca.
		text = text.replaceAll("[áàâãä]", "a");
		text = text.replaceAll("[éèêë]", "e");
		text = text.replaceAll("[íìîï]", "i");
		text = text.replaceAll("[óòôõö]", "o");
		text = text.replaceAll("[úùûü]", "u");
		if (level == 1) {
			text = text.replaceAll("[ç]", "c");
		} else {
			text = text.replaceAll("[ç]", "ss");
		}
		if (level > 1) {
			// l$ -> u
			text = text.replaceAll("(\\w[a-z])l\\b", "$1u");
			// amo -> amu, todo -> todu (plural também)
			text = text.replaceAll("o(s?)\\b", "u$1");
			text = text.replaceAll("\\b(\\d+)u\\b", "$1o"); // fix 1u > 1o (primeiro)
			// ou -> o (se for parte de palavra)
			if (level == 3) {
				text = text.replaceAll("(\\w)ou\\b", "$1ow"); // Orkut
			} else {
				text = text.replaceAll("(\\w)ou\\b", "$1o"); // bug: 2)sol>sou>so
			}
			text = text.replaceAll("\\bou(\\w)", "o$1");
			text = text.replaceAll("(\\w)ou(\\w)", "$1o$2");
			// ^c -> k (exceções: certo,cidade,c)
			text = text.replaceAll("\\bc([ei\\w])", "k$1");
			// andar -> andah, comer -> come, sentir -> senti
			text = text.replaceAll("ar\\b", "ah");
			text = text.replaceAll("er\\b", "e");
			text = text.replaceAll("ir\\b", "i");
			// eira$ -> era (sonzera, ladera)
			text = text.replaceAll("eira\\b", "era");
			// sa$ -> za, casa -> caza
			text = text.replaceAll("([^s\\w])sa\\b", "$1za");
			// TODO muZica e assemelhados
			// Certas palavras não precisam de plural (mmmmm, deixe quieto)
			// t = t.replaceAll(/(dia)s\\b",			"$1");
		}
		if (level > 2) {
			// O abominável X no fim das palavras no plural
			text = text.replaceAll("([^\\ws])s\\b", "$1x");
			// O abominável H no fim de certas palavras
			text = text.replaceAll("(\\w)a\\b", "$1ah");
		}
		// Risada
		if (level == 1) {
			text = text.replaceAll("\\b(he|ha|hi|ho|hua){2,}h?\\b", "rsrsrs");
		} else if (level == 2) {
			text = text.replaceAll("\\b(he|ha|hi|ho|hua|rs){2,}h?\\b", "huahuahua");
			text = text.replaceAll("[8:][-o]?[D)]", "huahuahua");
		} else {
			text = text.replaceAll("\\b(he|ha|hi|ho|hua|rs){2,}h?\\b", "kkkkkkkkkkk");
			text = text.replaceAll("[8:][-o]?[D)]", "kkkkkkkkkkk");
		}
		if (level > 1) {
			// Somente um ponto final é muito pouco
			text = text.replaceAll("\\.", "......");
			// Pra que vírgula? Pontos são mais legais... vários...
			text = text.replaceAll(",", "...");
			text = text.replaceAll("(\\n|$)", "...$1");
		}
		// Sejamos enfáticos!!!
		if (level == 1) {
			text = text.replaceAll("!", "!!");
			text = text.replaceAll("\\?", "??");
		} else {
			text = text.replaceAll("!", "!!!!!");
			text = text.replaceAll("\\?", "??!?!");
		}
		if (level < 3) {
			// Maiúsculas não existem
			text = text.toLowerCase();
		} else {
			// Alternância aLEaTóRIa de maiúsculas e minúsculas
			String[] letters = text.toLowerCase().split("");
			StringBuilder sb = new StringBuilder();
			for (int i=0; i < letters.length; i++) {
				if (Math.floor(Math.random()*2) == 1) {
					sb.append(letters[i].toUpperCase());
				} else {
					sb.append(letters[i]);
				}
			}

			// E uns ajustes finais para ficar ainda mais fofuxu
			text = text.replaceAll("(x|X)", "xXx");
			text = text.replaceAll("(ss|SS)", "XX");
		}
		return text;
	}

}