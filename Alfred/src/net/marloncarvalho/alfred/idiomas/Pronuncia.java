/*
 *  This file is part of Alfred Library.
 *
 *  Foobar is free software: you can redistribute it and/or modify
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
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.marloncarvalho.alfred.idiomas;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.net.WorldWideWeb;

/**
 * Utilitário que obtém um arquivo no formato .WAV com a pronúncia de uma palavra em um idioma.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/06/2009
 */
final public class Pronuncia {
	public static int INGLES = 1;

	/**
	 * Obter a pronúncia de uma palavra em um idioma.
	 * 
	 * @param idioma Idioma.
	 * @param palavra Palavra.
	 */
	public static void obterPronuncia(int idioma, String palavra) {
		String url = "http://www.merriam-webster.com/dictionary/" + palavra;
		String conteudo = WorldWideWeb.getConteudoSite(url);
		String arquivoWav = "";
		///cgi-bin/audio.pl?dictio04.wav=dictionary
		//
		//Pattern padrao = Pattern.compile("//audio\\.pl\\?[a-z0-9]*\\.wav=" + palavra);  
		Pattern padrao = Pattern.compile("[a-z0-9]*\\.wav");
		Matcher pesquisa = padrao.matcher(conteudo);
		while(pesquisa.find()) {
			arquivoWav = pesquisa.group();
		}
		if ( "".equals(arquivoWav) )
			throw new AlfredException("Não foi encontrada a pronúncia da palavra " + palavra + ".");
		url = "http://media.merriam-webster.com/soundc11/" + arquivoWav.charAt(0) + "/" + arquivoWav;
		InputStream is = WorldWideWeb.getConteudoArquivo(url);
		
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
			AudioFormat format = audioInputStream.getFormat();
			SourceDataLine auline = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
	 
			try {
				auline = (SourceDataLine) AudioSystem.getLine(info);
				auline.open(format);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
				return;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
	 
			auline.start();
			int nBytesRead = 0;
			byte[] abData = new byte[524288];
	 
			try {
				while (nBytesRead != -1) {
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
					if (nBytesRead >= 0)
						auline.write(abData, 0, nBytesRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			} finally {
				auline.drain();
				auline.close();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}