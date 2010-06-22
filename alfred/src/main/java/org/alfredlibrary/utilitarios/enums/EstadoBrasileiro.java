package org.alfredlibrary.utilitarios.enums;

public enum EstadoBrasileiro {
	ACRE("AC"), ALAGOAS("AL"), AMAPA("AP"), AMAZONAS("AM"), BAHIA("BA"), CEARA("CE"), DISTRITOFEDERAL("DF"), ESPIRITO_STANTO("ES"), GOIAS("GO"), MARANHA("MA"), MATO_GROSSO("MT"),
	MATO_GROSSO_DO_SUL("MS"), MINAS_GERAIS("MG"), PARA("PA"), PARANA("PR"), PERNAMBUCO("PE"), PIAUI("PI"), RIO_DE_JANEIRO("RJ"), RIO_GRANDE_DO_NORTE("RN"), RIO_GRANDE_DO_SUL("RS"),
	RONDONIA("RO"), RORARIMA("RR"), SANTA_CATARINA("SC"), SAO_PAULO("SP"), SERGIPE("SE"), TOCANTINS("TO");
	
	private String estado;
	private EstadoBrasileiro(String est) {
		this.estado = est;
	}
	
	public String toString() {
		return estado;
	}

}