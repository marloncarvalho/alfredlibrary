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

	/**
	 * Esculhambar, digo, Miguxar uma frase.
	 * 
	 * @param t Texto a ser escu... miguxado.
	 * @param level Nível de esculhambame... miguxamento.
	 * @return Texto escu... miguxado.
	 */
	public static String miguxar(String t, int level) {
		t = t.toLowerCase();
		t = t.replaceAll("\\buma?\\b", "1");
		t = t.replaceAll("\\b(dois|duas)\\b", "2");
		t = t.replaceAll("\\btrês\\b", "3");
		t = t.replaceAll("\\bquatro\\b", "4");
		t = t.replaceAll("\\bcinco\\b", "5");
		t = t.replaceAll("\\bseis\\b", "6");
		t = t.replaceAll("\\bsete\\b", "7");
		t = t.replaceAll("\\boito\\b", "8");
		t = t.replaceAll("\\bnove\\b", "9");
		t = t.replaceAll("\\bdez\\b", "10");
		t = t.replaceAll("\\bonze\\b", "11");
		t = t.replaceAll("\\bdoze\\b", "12");
		t = t.replaceAll("\\btreze\\b", "13");
		t = t.replaceAll("\\b(c|qu)atorze\\b", "14");
		t = t.replaceAll("\\bquinze\\b", "15");
		t = t.replaceAll("\\bdezesseis\\b", "16");
		t = t.replaceAll("\\bdezessete\\b", "17");
		t = t.replaceAll("\\bdezoito\\b", "18");
		t = t.replaceAll("\\bdezenove\\b", "19");
		// Repetições
		t = t.replaceAll("\\b([0-9]+) vez(es)?\\b", "$1x");
		// Horas, minutos
		t = t.replaceAll("\\b(\\d+) horas?\\b", "$1h");
		t = t.replaceAll("\\b(\\d+) minutos?\\b", "$1min");
		if (level < 3) {
			t = t.replaceAll("\\b(\\d+) segundos?\\b", "$1s");
		}
		// Dias da semana
		t = t.replaceAll("\\bsegunda-feira\\b", "2a");
		t = t.replaceAll("\\bterça-feira\\b", "3a");
		t = t.replaceAll("\\bquarta-feira\\b", "4a");
		t = t.replaceAll("\\bquinta-feira\\b", "5a");
		t = t.replaceAll("\\bsexta-feira\\b", "6a");
		if (level < 3) {
			// Ordinais
			t = t.replaceAll("\\bprimeir([ao])\\b", "1$1");
			t = t.replaceAll("\\bsegund([ao])\\b", "2$1");
			t = t.replaceAll("\\bterceir([ao])\\b", "3$1");
			t = t.replaceAll("\\bquart([ao])\\b", "4$1");
			t = t.replaceAll("\\bquint([ao])\\b", "5$1");
			t = t.replaceAll("\\bsext([ao])\\b", "6$1");
			t = t.replaceAll("\\bsétim([ao])\\b", "7$1");
			t = t.replaceAll("\\boitav([ao])\\b", "8$1");
			t = t.replaceAll("\\bnon([ao])\\b", "9$1");
			t = t.replaceAll("\\bdécim([ao])\\b", "10$1");

			// Abreviações não miguxas
			t = t.replaceAll("\\bfi(m|nal) de semana\\b", "fds");
		}
		// Símbolos
		t = t.replaceAll("\\baté mais\\b", "t+");
		t = t.replaceAll("\\bdemais\\b", "d+");
		t = t.replaceAll("\\bmais ou menos\\b", "+-");
		t = t.replaceAll("\\bmais\\b", "+");
		t = t.replaceAll("\\bmenos\\b", "-");
		t = t.replaceAll("\\bmei[ao]\\b", "1/2");
		// Abreviações simples
		t = t.replaceAll("\\bpor\\s?qu[eê]\\b", "pq");
		t = t.replaceAll("\\bhoje\\b", "hj");
		t = t.replaceAll("\\btambém\\b", "tb");
		t = t.replaceAll("\\bbeleza\\\b", "blz");
		t = t.replaceAll("\\bfirmeza\\b", "fmz");
		t = t.replaceAll("\\bquando\\b", "qdo");
		t = t.replaceAll("\\bquant([ao])(s?)\\b", "qt$1$2");
		t = t.replaceAll("\\bmuit([ao])(s?)\\b", "mt$1$2");
		t = t.replaceAll("\\bbeij(o|ão)\\b", "bj$1");
		t = t.replaceAll("\\bbeijos\\b", "bjs");
		if (level == 1) {
			t = t.replaceAll("\\bcom([^\\wáéíóúàâêôãõüç]|$)", "c/$1");
		} else if (level == 3) {
			t = t.replaceAll("\\bcom([^\\wáéíóúàâêôãõüç]|$)", "cum$1");
		}
		if (level > 1) {
			// Abreviações avançadas
			t = t.replaceAll("\\bmesm[ao](s?)\\b", "msm$1");
			t = t.replaceAll("\\bdepois\\b", "dpois");
			t = t.replaceAll("\\bquem\\b", "qm");
			t = t.replaceAll("\\bcomigo\\b", "cmg");
			t = t.replaceAll("\\bcadê", "kd");
			t = t.replaceAll("\\bqualquer\\b", "qq");
			t = t.replaceAll("\\bfalou\\b", "flw");
			t = t.replaceAll("\\bvaleu\\b", "vlw");
			t = t.replaceAll("\\btchau\\b", "xau");
		}
		if (level == 1) {
			t = t.replaceAll("\\bque\\b", "q");
			t = t.replaceAll("\\bvocê", "vc");
			t = t.replaceAll("\\be-?mail(s?)\\b", "mail$1");
		} else if (level == 2) {
			t = t.replaceAll("\\bque\\b", "ke");
			t = t.replaceAll("\\bvocês\\b", "6");
			t = t.replaceAll("\\bvocê", "vc"); // c fica estranho em kd c?
		} else {
			t = t.replaceAll("\\bque\\b", "ki");
			t = t.replaceAll("\\b(adoro você|te adoro)", "adoluxê");
			t = t.replaceAll("\\bamo vocês\\b", "amodolu vocês");
			t = t.replaceAll("\\b(amo você|te amo)", "te amodolu");
			t = t.replaceAll("\\bvocê", "vuxê");
		}
		// Glossário
		t = t.replaceAll("\\btecl(e|ar|ou|amos)\\b", "tc");
		t = t.replaceAll("\\binternet\\b", "net");
		t = t.replaceAll("\\be-?mail(s?)\\b", "meio$1");
		t = t.replaceAll("\\b(grana|dinheiro)\\b", "$$$$$$"); // $$$
		if (level == 2) {
			t = t.replaceAll("\\badicion", "adde"); // Tou t addeando
			t = t.replaceAll("\\bamig([ao])\\b", "mig$1"); // miga
			t = t.replaceAll("\\blind([ao])\\b", "leend$1"); // leenda
			t = t.replaceAll("\\bnovidade(s?)\\b", "9dad$1");
		} else if (level == 3) {
			t = t.replaceAll("\\badicion[\\wáí]+", "add"); // Tou t add
			t = t.replaceAll("\\bamig([ao]s?)\\b", "migux$1"); // miguxa
			t = t.replaceAll("\\blind([ao]s?)\\b", "lindux$1"); // linduxa
			t = t.replaceAll("\\bfof([ao]s?)\\b", "fofux$1"); // fofuxa
			t = t.replaceAll("\\bdormir\\b", "mimir");
			t = t.replaceAll("\\bnome(s?)\\b", "nominho$1");
			t = t.replaceAll("\\besposa\\b", "marida");
			t = t.replaceAll("\\b(de novo|novamente)\\b", "dinovo");
			t = t.replaceAll("\\b(aliás|por exemplo)\\b", "tipo assim");
		}
		// tou, tava, tar
		t = t.replaceAll("\\best(ar|ou|ava|ive|aria|ão)\\b", "t$1");
		t = t.replaceAll("\\bestá([^\\wáéíóúàâêôãõüç]|$)", "tah$1");
		// para
		t = t.replaceAll("\\bpara ([ao]s?)\\b", "pr$1");
		t = t.replaceAll("\\bpara([^\\wáéíóúàâêôãõüç-]|$)", "pra$1");
		if (level == 2) {
			t = t.replaceAll("\\bpr[ao]([^\\wáéíóúàâêôãõüç]|$)", "p$1");
		}
		// Simplifiq: irmos -> ir, acabou -> cabou
		t = t.replaceAll("([aei]r)mos\\b", "$1");
		t = t.replaceAll("\\bacab", "cab");
		if (level > 1) {
			// entaum, naum
			t = t.replaceAll("ão\\b", "aum");
			// andando -> andano, comendo -> comeno (depois fica melhor: andanu,
			// comenu)
			t = t.replaceAll("(\\w[aei])ndo\\b", "$1no");
			// tada$ -> tadeenha (e alguns outros casos), foto -> foteenha,
			// gatinha -> gateenha
			t = t.replaceAll("(\\w[crt]ad)([ao])\\b", "$1eenh$2");
			t = t.replaceAll("foto(s?)\\b", "foteenha$1");
			t = t.replaceAll("(\\w)tinh([ao])\\b", "$1teenh$2");
			if (level > 2) {
				// No Orkut é mais fófi terminar em i
				t = t.replaceAll("\\bse\\b", "si");
				t = t.replaceAll("\\bde\\b", "di");
				t = t.replaceAll("\\bte\\b", "ti");
			} else {
				// No MSN o som da letra vira a palavra
				t = t.replaceAll("\\bse\\b", "c");
				t = t.replaceAll("\\bde\\b", "d");
				t = t.replaceAll("\\bte\\b", "t");
			}
			// CH, SH e QU não existem
			t = t.replaceAll("ch", "x");
			t = t.replaceAll("sh", "x");
			t = t.replaceAll("qu", "k");
			// e -> i (alguns casos)
			t = t.replaceAll("(\\w(ss|[cdgtv]))e(s?)m?\\b", "$1i$3");
			t = t.replaceAll("\\bseg", "sig");
			t = t.replaceAll("\\bdes([^s])", "dis$1");
		}
		// ei -> i (alguns casos) deixa -> dexa
		t = t.replaceAll("eix", "ex");
		if (level > 1) {
			// o -> u (alguns casos)
			t = t.replaceAll("\\bbonit", "bunit");
			// e sozinho -> i
			t = t.replaceAll("\\be\\b", "i");
			if (level > 2) {
				// inglês -> ingleix
				t = t.replaceAll("ês\\b", "eix");
				// atrás -> atraix
				t = t.replaceAll("(\\w)(ás|az)\\b", "$1aix");
			}
		}
		// Acento no final eh moh uoh
		t = t.replaceAll("á([^\\wáéíóúàâêôãõüç]|$)", "ah$1");
		t = t.replaceAll("é([^\\wáéíóúàâêôãõüç]|$)", "eh$1");
		t = t.replaceAll("í([^\\wáéíóúàâêôãõüç]|$)", "ih$1");
		t = t.replaceAll("ó([^\\wáéíóúàâêôãõüç]|$)", "oh$1");
		t = t.replaceAll("ú([^\\wáéíóúàâêôãõüç]|$)", "uh$1");
		// Acentuação? Nunca.
		t = t.replaceAll("[áàâãä]", "a");
		t = t.replaceAll("[éèêë]", "e");
		t = t.replaceAll("[íìîï]", "i");
		t = t.replaceAll("[óòôõö]", "o");
		t = t.replaceAll("[úùûü]", "u");
		if (level == 1) {
			t = t.replaceAll("[ç]", "c");
		} else {
			t = t.replaceAll("[ç]", "ss");
		}
		if (level > 1) {
			// l$ -> u
			t = t.replaceAll("(\\w[a-z])l\\b", "$1u");
			// amo -> amu, todo -> todu (plural também)
			t = t.replaceAll("o(s?)\\b", "u$1");
			t = t.replaceAll("\\b(\\d+)u\\b", "$1o"); // fix 1u > 1o (primeiro)
			// ou -> o (se for parte de palavra)
			if (level == 3) {
				t = t.replaceAll("(\\w)ou\\b", "$1ow"); // Orkut
			} else {
				t = t.replaceAll("(\\w)ou\\b", "$1o"); // bug: 2)sol>sou>so
			}
			t = t.replaceAll("\\bou(\\w)", "o$1");
			t = t.replaceAll("(\\w)ou(\\w)", "$1o$2");
			// ^c -> k (exceções: certo,cidade,c)
			t = t.replaceAll("\\bc([ei\\w])", "k$1");
			// andar -> andah, comer -> come, sentir -> senti
			t = t.replaceAll("ar\\b", "ah");
			t = t.replaceAll("er\\b", "e");
			t = t.replaceAll("ir\\b", "i");
			// eira$ -> era (sonzera, ladera)
			t = t.replaceAll("eira\\b", "era");
			// sa$ -> za, casa -> caza
			t = t.replaceAll("([^s\\w])sa\\b", "$1za");
			// TODO muZica e assemelhados
			// Certas palavras não precisam de plural (mmmmm, deixe quieto)
			// t = t.replaceAll(/(dia)s\\b",			"$1");
		}
		if (level > 2) {
			// O abominável X no fim das palavras no plural
			t = t.replaceAll("([^\\ws])s\\b", "$1x");
			// O abominável H no fim de certas palavras
			t = t.replaceAll("(\\w)a\\b", "$1ah");
		}
		// Risada
		if (level == 1) {
			t = t.replaceAll("\\b(he|ha|hi|ho|hua){2,}h?\\b", "rsrsrs");
		} else if (level == 2) {
			t = t.replaceAll("\\b(he|ha|hi|ho|hua|rs){2,}h?\\b", "huahuahua");
			t = t.replaceAll("[8:][-o]?[D)]", "huahuahua");
		} else {
			t = t.replaceAll("\\b(he|ha|hi|ho|hua|rs){2,}h?\\b", "kkkkkkkkkkk");
			t = t.replaceAll("[8:][-o]?[D)]", "kkkkkkkkkkk");
		}
		if (level > 1) {
			// Somente um ponto final é muito pouco
			t = t.replaceAll("\\.", "......");
			// Pra que vírgula? Pontos são mais legais... vários...
			t = t.replaceAll(",", "...");
			t = t.replaceAll("(\\n|$)", "...$1");
		}
		// Sejamos enfáticos!!!
		if (level == 1) {
			t = t.replaceAll("!", "!!");
			t = t.replaceAll("\\?", "??");
		} else {
			t = t.replaceAll("!", "!!!!!");
			t = t.replaceAll("\\?", "??!?!");
		}
		if (level < 3) {
			// Maiúsculas não existem
			t = t.toLowerCase();
		} else {
			// Alternância aLEaTóRIa de maiúsculas e minúsculas
			String[] letters = t.toLowerCase().split("");
			StringBuilder sb = new StringBuilder();
			for (int i=0; i < letters.length; i++) {
				if (Math.floor(Math.random()*2) == 1) {
					sb.append(letters[i].toUpperCase());
				} else {
					sb.append(letters[i]);
				}
			}

			// E uns ajustes finais para ficar ainda mais fofuxu
			t = t.replaceAll("(x|X)", "xXx");
			t = t.replaceAll("(ss|SS)", "XX");
		}
		return t + "\n\n - Todos créditos para Aurélio Marinho Jargas - http://www.coisinha.com.br/miguxeitor/";
	}

}