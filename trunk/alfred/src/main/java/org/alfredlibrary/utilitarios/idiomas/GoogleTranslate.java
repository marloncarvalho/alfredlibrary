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
package org.alfredlibrary.utilitarios.idiomas;

import org.alfredlibrary.utilitarios.net.WorldWideWeb;

/**
 * Utilitário que realiza a tradução de uma frase usando o Google Traslator.
 * 
 * @author Marlon Silva Carvalho
 * @since 27/05/2010
 */
final public class GoogleTranslate {
	
	private GoogleTranslate() {
		throw new AssertionError();		
	}

	public enum Idioma {
		AUTO_DETECT(""),
		AFRIKAANS("af"),
		ALBANIAN("sq"),
		AMHARIC("am"),
		ARABIC("ar"),
		ARMENIAN("hy"),
		AZERBAIJANI("az"),
		BASQUE("eu"),
		BELARUSIAN("be"),
		BENGALI("bn"),
		BIHARI("bh"),
		BULGARIAN("bg"),
		BURMESE("my"),
		CATALAN("ca"),
		CHEROKEE("chr"),
		CHINESE("zh"),
		CHINESE_SIMPLIFIED("zh-CN"),
		CHINESE_TRADITIONAL("zh-TW"),
		CROATIAN("hr"),
		CZECH("cs"),
		DANISH("da"),
		DHIVEHI("dv"),
		DUTCH("nl"),
		ENGLISH("en"),
		ESPERANTO("eo"),
		ESTONIAN("et"),
		FILIPINO("tl"),
		FINNISH("fi"),
		FRENCH("fr"),
		GALICIAN("gl"),
		GEORGIAN("ka"),
		GERMAN("de"),
		GREEK("el"),
		GUARANI("gn"),
		GUJARATI("gu"),
		HEBREW("iw"),
		HINDI("hi"),
		HUNGARIAN("hu"),
		ICELANDIC("is"),
		INDONESIAN("id"),
		INUKTITUT("iu"),
		IRISH("ga"),
		ITALIAN("it"),
		JAPANESE("ja"),
		KANNADA("kn"),
		KAZAKH("kk"),
		KHMER("km"),
		KOREAN("ko"),
		KURDISH("ku"),
		KYRGYZ("ky"),
		LAOTHIAN("lo"),
		LATVIAN("lv"),
		LITHUANIAN("lt"),
		MACEDONIAN("mk"),
		MALAY("ms"),
		MALAYALAM("ml"),
		MALTESE("mt"),
		MARATHI("mr"),
		MONGOLIAN("mn"),
		NEPALI("ne"),
		NORWEGIAN("no"),
		ORIYA("or"),
		PASHTO("ps"),
		PERSIAN("fa"),
		POLISH("pl"),
		PORTUGUESE("pt"),
		PUNJABI("pa"),
		ROMANIAN("ro"),
		RUSSIAN("ru"),
		SANSKRIT("sa"),
		SERBIAN("sr"),
		SINDHI("sd"),
		SINHALESE("si"),
		SLOVAK("sk"),
		SLOVENIAN("sl"),
		SPANISH("es"),
		SWAHILI("sw"),
		SWEDISH("sv"),
		TAJIK("tg"),
		TAMIL("ta"),
		TAGALOG("tl"),
		TELUGU("te"),
		THAI("th"),
		TIBETAN("bo"),
		TURKISH("tr"),
		UKRANIAN("uk"),
		URDU("ur"),
		UZBEK("uz"),
		UIGHUR("ug"),
		VIETNAMESE("vi"),
		WELSH("cy"),
		YIDDISH("yi");
		
		private final String idioma;

		private Idioma(final String idioma) {
			this.idioma = idioma;
		}

		@Override
		public String toString() {
			return idioma;
		}
	}

	/**
	 * Traduzir uma frase de um idioma para outro usando o Google Translate.
	 * 
	 * @param frase Frase a ser traduzida.
	 * @param idiomaOrigem Idioma de origem.
	 * @param idiomaFim Idioma fim.
	 * @return Tradução.
	 */
	public static String traduzir(String frase, Idioma idiomaOrigem, Idioma idiomaFim) {
		frase = frase.replaceAll(" ", "%20");
		String url = "http://ajax.googleapis.com/ajax/services/language/translate?v=1.0&langpair=" + idiomaOrigem + "|" + "" + idiomaFim + "&q=" + frase;
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		String traducao = conteudo.substring(36,conteudo.indexOf('}',35)-1);
		return traducao;
	}

}