package org.alfredlibrary.utilitarios.bancos;

public enum Naturalidade {
	TODOS(""), NACIONAL("1"), NACIONAL_COM_PARTICIPACAO_ESTRANGEIRA("2"), NACIONAL_COM_CONTROLE_ESTRANGEIRO("3"),
	ESTRANGEIRO("4"), ESTADUAL("5"), FEDERAL("6");
	
	private final String naturalidade;

	private Naturalidade(final String naturalidade) {
		this.naturalidade = naturalidade;
	}

	@Override
	public String toString() {
		return naturalidade;
	}
}