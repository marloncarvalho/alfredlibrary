package org.alfredlibrary.gov.comprasnet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.gov.comprasnet.catalogo.materiais.retorno.Item;


/**
 * 
 * @author mariojp
 * 
 */
public class CatalogoMateriasService {

	/**
	 * <erroxml>-1072897660</erroxml> <descxml>The element:
	 * '{cnet_consultamatserv}mod' has an invalid value according to its data
	 * type.</descxml> Ocorre quando o conteúdo de um elemento não segue a regra
	 * definida pelo schema. Favor consulte a documentação referente ao elemento
	 * em questão.
	 */
	public static final long ERRO_XML_1072897660 = -1072897660l;

	/**
	 * <erroxml>-1072898028</erroxml> <descxml>Element content is invalid
	 * according to the DTD/Schema. Expecting: {cnet_aviso}cpf.</descxml> Quando
	 * algum elemento não segue a seqüência de elementos definida pelo schema,
	 * ou quando o elemento encontra-se escrito erroneamente, ocorre o erro
	 * citado acima. Queira verificar se o elemento não foi digitado
	 * incorretamente ou se ele encontra-se fora de posição no XML, segundo o
	 * esquema XSD.
	 */

	public static final long ERRO_XML_1072898028 = -1072898028l;

	/**
	 * <erroxml>-1072896749</erroxml> <descxml>Whitespace is not allowed at this
	 * location.</descxml> Este erro geralmente ocorre devido a uma limitação no
	 * XML não poder processar alguns caracteres especiais como por exemplo &?><
	 * Neste caso, uma solução mais simples é delimitar o conteúdo do elemento
	 * em questão pelo elemento CDATA. Segue abaixo um exemplo de como delimitar
	 * o conteúdo com CDATA. Abaixo o XML irá apresentar o erro descrito devido
	 * ao caracter &. <objeto>teste & teste LTDA</objeto> Delimitado o conteúdo
	 * com CDATA, o problema é resolvido <objeto><![CDATA[teste & teste
	 * LTDA]]></objeto>
	 * 
	 */
	public static final long ERRO_XML_1072896749 = -1072896749l;

	/**
	 * <erroxml>988</erroxml> <descxml>Divulgação.
	 * 
	 */
	public static final long ERRO_XML_988 = 988l;

	/**
	 * <erroxml>990</erroxml> <descxml>Sistema informado deve ser diferente de
	 * COMPRASNET.</descxml> O valor do elemento sistema repassado no elemento
	 * XML de entrada deve ser diferente de ComprasNet.
	 */
	public static final long ERRO_XML_990 = 990l;

	/**
	 * <erroxml>991</erroxml> <descxml>Elemento ambiente não condiz com o ponto
	 * de entrada para o ambiente desejado.</descxml> O ComprasNet possuí dois
	 * pontos de entrada, um para treinamento e outro para produção. O elemento
	 * do XML ambiente deve sempre condizer com qual ambiente esta sendo usado.
	 * Caso isto não ocorra, é retornado este erro ao usuário.
	 */
	public static final long ERRO_XML_991 = 991l;

	/**
	 * <erroxml>992</erroxml> <descxml>Erro na comunicação com o Grande Porte,
	 * queira tentar novamente mais tarde. Caso persista o erro, por favor entre
	 * em contato com a equipe de suporte</descxml> Quando a comunicação com o
	 * mainframe falha por qualquer motivo, é retornado este erro a usuário.
	 * 
	 * 
	 */
	public static final long ERRO_XML_992 = 992l;

	/**
	 * <erroxml>994</erroxml> <descxml>Usuário não habilitado na transação
	 * {CONITEMMAT}</descxml> Para poder fazer manutenção ou consulta de Item de
	 * Material, é necessário que o usuário possua a respectiva transação no
	 * senha rede. Caso não seja possível validar, é retornado ao usuário o nome
	 * da transação o qual não possui habilitação.
	 */
	public static final long ERRO_XML_994 = 994l;

	/**
	 * <erroxml>995</erroxml> <descxml>Problema na Validação do Login e
	 * Senha</descxml> Ocorreu um problema na validação do CPF e senha no
	 * Senha-Rede Favor verifique se os seus dados de CPF e senha estejam
	 * corretos e que a sua senha não esteja espirada ou revogada. Geralmente a
	 * mensagem de retorno é auto explicativa. As principais mensagens do
	 * senha-rede são exibidas abaixo: · Senha do usuário inválida. · Senha
	 * Inválida. · Usuário não cadastrado. · Usuário bloqueado ou inativo no
	 * senha rede. · Habilitação inválida. · Não foi possível validar o CPF. ·
	 * Problemas na recuperação do sistema. · Sistema inativo. · Senha expirada,
	 * troque sua senha e tente novamente. · Problemas na gravação do
	 * Logon/Logoff. · Usuário revogado no sistema. · Problemas no SAAR. · Uma
	 * nova senha deve ser informada no senha-rede. · Transação excedeu a
	 * capacidade da tabela de transações. · Aplicação de validação do usuário
	 * no senha-rede não disponível ou com problemas momentâneos. Por favor,
	 * tente novamente mais tarde. Caso persista o erro, por favor, entre em
	 * contato com a equipe de suporte do ComprasNet.
	 */
	public static final long ERRO_XML_995 = 995l;

	/**
	 * <erroxml>999</erroxml> <descxml>Processo falho - Erro interno no sistema.
	 * Por favor entre em contato com a equipe de suporte</descxml> Ocorre
	 * quando o processamento do XML falhou devido a um erro na aplicação do
	 * ComprasNet. Por favor, entre em contato com a equipe de suporte do
	 * ComprasNet nesta situação.
	 */
	public static final long ERRO_XML_999 = 999l;
	/**
	 * <erroxml>998</erroxml> <descxml>NameSpace não corresponde ao esperado
	 * {cnet_consultamatserv} </descxml> Verifique se o NameSpace padrão do XML
	 * é o cnet_consultamatserv. Para isto, certifique-se que o elemento raiz do
	 * XML <cnet> seja escrito da seguinte maneira. <cnet
	 * xmlns="cnet_consultamatserv">
	 */
	public static final long ERRO_XML_998 = 998l;

	/**
	 * <erronat>0999</erronat><descnat>NÃO EXISTEM REGISTROS QUE ATENDAM ESTA
	 * SOLICITAÇÃO</descnat>
	 */
	public static final long ERRO_NAT_0999 = 999l;

	/**
	 * <erronat>1310</erronat><descnat>LIMITE DE PESQUISA ATINGIDO. ENTRE COM
	 * MAIS RADICAIS</descnat>
	 */
	public static final long ERRO_NAT_1310 = 1310l;

	/**
	 * cnet.setCpf(26397900568l); cnet.setCodigoItem("000200060");
	 * cnet.setSenha("siljulu2010");
	 * 
	 * @param codigoItem
	 * @param usuario
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public Item obterItemPorCodigo(String codigoItem, Long cpf, String senha)
			throws Exception {
		org.alfredlibrary.gov.comprasnet.catalogo.materiais.consulta.Cnet cnet = new org.alfredlibrary.gov.comprasnet.catalogo.materiais.consulta.ObjectFactory()
				.createCnet();
		cnet.setAcao("consulta material");
		cnet.setAmbiente("produção");
		cnet.setSistema("XML");
		cnet.setCpf(cpf);
		cnet.setCodigoItem(codigoItem);
		cnet.setSenha(senha);
		cnet.setRadical1("");
		cnet.setRadical2("");
		cnet.setRadical3("");
		cnet.setSustentavel("");
		String dados = marshaller(cnet);
		String xml = obterDados(dados);
		org.alfredlibrary.gov.comprasnet.catalogo.materiais.retorno.Cnet cnetRetorno = unmarshaller(xml);
		Item item = null;
		if (trataErro(cnetRetorno)) {
			item = cnetRetorno.getItens().getItem().get(0);
		}
		return item;
	}

	/**
	 * 
	 * @param radical1
	 * @param cpf
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public List<Item> obterItensPorRadical(String radical1, Long cpf,
			String senha) throws Exception {
		return obterItensPorRadical(radical1, "", cpf, senha);
	}

	/**
	 * 
	 * @param radical1
	 * @param radical2
	 * @param cpf
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public List<Item> obterItensPorRadical(String radical1, String radical2,
			Long cpf, String senha) throws Exception {
		return obterItensPorRadical(radical1, radical2, "", cpf, senha);
	}

	/**
	 * 
	 * @param radical1
	 * @param radical2
	 * @param radical3
	 * @param cpf
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public List<Item> obterItensPorRadical(String radical1, String radical2,
			String radical3, Long cpf, String senha) throws Exception {
		return obterItensPorRadical(radical1, radical2, radical3, "N", cpf,
				senha);
	}

	/**
	 * 
	 * @param radical1
	 * @param radical2
	 * @param radical3
	 * @param sustentavel
	 * @param cpf
	 * @param senha
	 * @return
	 * @throws Exception
	 */
	public List<Item> obterItensPorRadical(String radical1, String radical2,
			String radical3, String sustentavel, Long cpf, String senha)
			throws Exception {
		org.alfredlibrary.gov.comprasnet.catalogo.materiais.consulta.Cnet cnet = new org.alfredlibrary.gov.comprasnet.catalogo.materiais.consulta.ObjectFactory()
				.createCnet();
		cnet.setAcao("consulta material");
		cnet.setAmbiente("produção");
		cnet.setSistema("XML");
		cnet.setCpf(cpf);
		cnet.setCodigoItem("");
		cnet.setSenha(senha);
		cnet.setRadical1(radical1);
		cnet.setRadical2(radical2);
		cnet.setRadical3(radical3);
		cnet.setSustentavel(sustentavel);
		String dados = marshaller(cnet);
		String xml = obterDados(dados);
		org.alfredlibrary.gov.comprasnet.catalogo.materiais.retorno.Cnet cnetRetorno = unmarshaller(xml);
		List<Item> itens = null;
		if (trataErro(cnetRetorno)) {
			itens = cnetRetorno.getItens().getItem();
		}
		return itens;
	}

	/**
	 * 
	 * @param cnetRetorno
	 * @return
	 * @throws Exception
	 */
	private boolean trataErro(org.alfredlibrary.gov.comprasnet.catalogo.materiais.retorno.Cnet cnetRetorno) throws Exception{
		boolean valido = false;
		String mensagem = "";
		int erroNAT = 0;
		int erroXML = 0;
		if (cnetRetorno.getErronat() != null)
			erroNAT = cnetRetorno.getErronat().intValue();
		if (cnetRetorno.getErroxml() != null)
			erroXML = cnetRetorno.getErroxml().intValue();
		
		if (erroXML == 0 && erroNAT == 0 ) {
			valido = true;
		} else {
			if (erroXML != 0) {
				mensagem = erroXML(erroXML);
			}else if (erroNAT != 0) {
				mensagem = erroNAT(erroNAT);
			}
		} 
		
		if(!valido){
			throw new Exception(mensagem);
		}
		return valido;
	}


	/**
	 * 
	 * @param erroNAT
	 * @return
	 */
	private String erroNAT(int erroNAT) {
		String mensagem ="";
		switch (erroNAT) {
		case (int)ERRO_NAT_0999:
			mensagem = "NÃO EXISTEM REGISTROS QUE ATENDAM ESTA SOLICITAÇÃO.";
			break;

		case (int)ERRO_NAT_1310:
			mensagem = "LIMITE DE PESQUISA ATINGIDO. ENTRE COM MAIS RADICAIS.";
			break;
		default:
			mensagem = "ERRO NAT NÃO ESPERADO!";
		}
		return mensagem;
		
	}

	/**
	 * 
	 * @param erroXML
	 * @return
	 */
	private String erroXML(int erroXML) {
		String mensagem ="";
		switch (erroXML) {
		case (int) ERRO_XML_988:
			mensagem = "Divulgação.".toUpperCase();
			break;
		case (int) ERRO_XML_990:
			mensagem = "Sistema informado deve ser diferente de COMPRASNET.".toUpperCase();
			break;
		case (int) ERRO_XML_991:
			mensagem = "Elemento ambiente não condiz com o ponto de entrada para o ambiente desejado.".toUpperCase();
			break;
		case (int) ERRO_XML_992:
			mensagem = "Erro na comunicação com o Grande Porte, queira tentar novamente mais tarde. Caso persista o erro, por favor entre em contato com a equipe de suporte".toUpperCase();
			break;
		case (int) ERRO_XML_994:
			mensagem = "Usuário não habilitado na transação {CONITEMMAT}".toUpperCase();
			break;
		case (int) ERRO_XML_995:
			mensagem = "Problema na Validação do Login e Senha".toUpperCase();
			break;
		case (int) ERRO_XML_998:
			mensagem = "NameSpace não corresponde ao esperado {cnet_consultamatserv}".toUpperCase();
			break;
		case (int) ERRO_XML_999:
			mensagem = "Processo falho - Erro interno no sistema. Por favor entre em contato com a equipe de suporte".toUpperCase();
			break;
		case (int) ERRO_XML_1072896749:
			mensagem = "Whitespace is not allowed at this location.".toUpperCase();
			break;
		case (int) ERRO_XML_1072897660:
			mensagem = "The element: '{cnet_consultamatserv}mod' has an invalid value according to its data type.".toUpperCase();
			break;
		case (int) ERRO_XML_1072898028:
			mensagem = "Element content is invalid according to the DTD/Schema. Expecting: {cnet_aviso}cpf.".toUpperCase();
			break;
		default:
			mensagem = "Erro do XML não esperado".toUpperCase();
			break;
		}
		return mensagem;
	}

	/**
	 * 
	 * @param cnet
	 * @return
	 */
	private String marshaller(
			org.alfredlibrary.gov.comprasnet.catalogo.materiais.consulta.Cnet cnet) {
		JAXBContext context = null;
		Marshaller marshaller = null;
		try {
			context = JAXBContext
					.newInstance("org.alfredlibrary.gov.comprasnet.catalogo.materiais.consulta");
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		StringWriter sw = new StringWriter();
		try {
			marshaller.marshal(cnet, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	private String obterDados(String dados) {
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("xml", dados);
		return obterConteudoSite(
				"http://www.comprasnet.gov.br/xml/producao/consultamatserv.asp",
				"ISO-8859-1", parametros);
	}

	private static String obterConteudoSite(String u, String encode,
			Map<String, String> parametros) {
		URL url;
		try {
			StringBuilder strParams = new StringBuilder();
			if (parametros != null) {
				for (String chave : parametros.keySet()) {
					strParams.append(URLEncoder.encode(chave, "UTF-8"));
					strParams.append("=");
					strParams.append(URLEncoder.encode(parametros.get(chave),
							encode));
					strParams.append("&");
				}
			}
			url = new URL(u);
			URLConnection conn = null;
			conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(strParams.toString());
			wr.flush();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), encode));
			String line;
			StringBuilder resultado = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				resultado.append(line);
			}
			wr.close();
			rd.close();
			return resultado.toString();
		} catch (MalformedURLException e) {
			throw new AlfredException(
					"Não foi possível obter contato com o site " + u, e);
		} catch (IOException e) {
			throw new AlfredException(
					"Não foi possível obter contato com o site " + u, e);
		}
	}

	private org.alfredlibrary.gov.comprasnet.catalogo.materiais.retorno.Cnet unmarshaller(
			String xml) {
		JAXBContext retornoContext = null;
		Unmarshaller unmarshaller = null;
		ByteArrayInputStream input = null;
		Object jaxbObject = null;
		try {
			retornoContext = JAXBContext
					.newInstance("org.alfredlibrary.gov.comprasnet.catalogo.materiais.retorno");
			unmarshaller = retornoContext.createUnmarshaller();
			input = new ByteArrayInputStream(xml.getBytes("ISO-8859-1"));
			jaxbObject = unmarshaller.unmarshal(input);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return (org.alfredlibrary.gov.comprasnet.catalogo.materiais.retorno.Cnet) jaxbObject;
	}

}
