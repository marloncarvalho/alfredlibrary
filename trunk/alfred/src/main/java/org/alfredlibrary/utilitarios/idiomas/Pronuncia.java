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

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.midia.Audio;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;


/**
 * Utilitário que obtém um arquivo no formato .WAV com a pronúncia de uma palavra em um idioma.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/06/2009
 */
final public class Pronuncia {
	public final static int INGLES = 1;

	/**
	 * Obter o Stream de áudio de uma pronúncia.
	 * 
	 * @param idioma Idioma.
	 * @param palavra Palavra.
	 * @return Stream de áudio.
	 */
	public static AudioInputStream obterPronuncia(int idioma, String palavra) {
		String url = "http://www.merriam-webster.com/dictionary/" + palavra;
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		String arquivoWav = "";
		Pattern padrao = Pattern.compile("return au\\('" + palavra + "[0-9]*");
		Matcher pesquisa = padrao.matcher(conteudo);
		while(pesquisa.find()) {
			arquivoWav = pesquisa.group();
			break;
		}
		if ( "".equals(arquivoWav) )
			throw new AlfredException("Não foi encontrada a pronúncia da palavra " + palavra + ".");
		arquivoWav = arquivoWav.replace("return au('", "");
		url = "http://media.merriam-webster.com/soundc11/" + arquivoWav.charAt(0) + "/" + arquivoWav;
		InputStream is = WorldWideWeb.obterConteudoArquivo(url);		
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
			return audioInputStream;
		} catch (UnsupportedAudioFileException e) {
			throw new AlfredException(e);
		} catch (IOException e) {
			throw new AlfredException(e);
		}
	}

	/**
	 * Ouvir a pronúncia de uma palavra em um idioma.
	 * 
	 * @param idioma Idioma.
	 * @param palavra Palavra.
	 */
	public static void ouvirPronuncia(int idioma, String palavra) {
		AudioInputStream audio = Pronuncia.obterPronuncia(idioma, palavra);
		Audio.executar(audio);
	}

}