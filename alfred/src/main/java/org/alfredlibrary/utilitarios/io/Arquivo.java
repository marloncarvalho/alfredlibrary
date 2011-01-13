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
package org.alfredlibrary.utilitarios.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.alfredlibrary.AlfredException;

/**
 * Utilitarios para Arquivos.
 * 
 * @author Mario Jorge Pereira
 * @author Marlon Silva Carvalho
 * @since 09/06/2009
 */
final public class Arquivo {
	
	

	private Arquivo() {
		throw new AssertionError();
	}

	/**
	 * Salvar um arquivo.
	 *  
	 * @param local Caminho do arquivo.
	 * @param fileData Dados.
	 * @param nome Nome do arquivo.
	 * @return Verdadeiro caso a opera tenha sido realizada com sucesso.
	 */
	public static boolean salvar(String local, byte[] fileData, String nome) {
		FileOutputStream out = null;
		File f = new File(local + nome);
		if (f.exists()) {
			return false;
		} else {
			try {
				out = new FileOutputStream(f);
				out.write(fileData);
				out.flush();
				out.close();
			} catch (FileNotFoundException ex) {
				throw new AlfredException("Arquivo n�o encontrado! ", ex);
			} catch (IOException ex) {
				throw new AlfredException("Erro ao gravar o arquivo! ", ex);
			}

		}
		return true;
	}

	/**
	 * Sobrescrever um arquivo.
	 * 
	 * @param local Caminho do arquivo.
	 * @param fileData Dados.
	 * @param nome Nome do arquivo.
	 * @return Verdadeiro caso a opera tenha sido realizada com sucesso.
	 */
	public static boolean sobrescrever(String local, byte[] fileData, String nome) {
		FileOutputStream out = null;
		File f = new File(local + nome);
		if (f.exists()) {
			f.delete();
		}
		try {
			out = new FileOutputStream(f);
			out.write(fileData);
			out.flush();
			out.close();
		} catch (FileNotFoundException ex) {
			throw new AlfredException("Arquivo n�o encontrado! ", ex);
		} catch (IOException ex) {
			throw new AlfredException("Erro ao gravar o arquivo! ", ex);
		}
		return true;
	}

	/**
	 * Listar arquivos espec�ficos de um determinado diret�rio.
	 * 
	 * @param local Diret�rio.
	 * @param terminacoes Termina��es dos arquivos.
	 * @return Lista de arquivos encontrados.
	 */
	public static File[] listarArquivosEspecificosDiretorio(String local, final List<String> terminacoes) {
		File f = new File(local);
		File[] arquivos = f.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				String arquivo = pathname.getName().toLowerCase();
				boolean resposta = false;
				for(String extensao : terminacoes){
					if(arquivo.endsWith(extensao.toLowerCase())){
						resposta=true;
					}
				}
				return resposta;
			}
		});
		return arquivos;
	}

	/**
	 * Listar arquivos de um diret�rio conforme um filtro e podendo ser recursivo.
	 * 
	 * @param directory Diret�rio.
	 * @param filter Filtro.
	 * @param recurse Se � recursivo.
	 * @return Lista de arquivos.
	 */
	public static File[] listarArquivosArray(File directory, FilenameFilter filter, boolean recurse) {
		Collection<File> files = listarArquivos(directory, filter, recurse);
		File[] arr = new File[files.size()];
		return files.toArray(arr);
	}

	/**
	 * Listar arquivos de um diret�rio conforme um filtro e podendo ser recursivo.
	 * 
	 * @param directory Diret�rio.
	 * @param filter Filtro.
	 * @param recurse Se � recursivo.
	 * @return Lista de arquivos.
	 */
	public static Collection<File> listarArquivos(File directory,FilenameFilter filter, boolean recurse) {
		Vector<File> files = new Vector<File>();
		File[] entries = directory.listFiles();
		for (File entry : entries) {
			if (filter == null || filter.accept(directory, entry.getName())) {
				files.add(entry);
			}
			if (recurse && entry.isDirectory()) {
				files.addAll(listarArquivos(entry, filter, recurse));
			}
		}
		return files;
	}

	/**
	 * Obter a extens�o de um arquivo.
	 * 
	 * @param file Arquivo.
	 * @return Extens�o.
	 */
	public static String obterExtensao(File file) {
		String nome = file.getName();
		return obterExtensao(nome);
	}

	/**
	 * Obter a extens�o de um arquivo.
	 * 
	 * @param nome Nome do arquivo.
	 * @return Extens�o.
	 */
	public static String obterExtensao(String nome) {
		String resp = "";
		int i = nome.lastIndexOf(".");
		if (i != -1) {
			resp = nome.substring(i + 1);
		}
		return resp.toLowerCase();
	}

}
