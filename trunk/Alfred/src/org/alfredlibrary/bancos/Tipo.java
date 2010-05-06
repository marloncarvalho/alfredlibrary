package org.alfredlibrary.bancos;

public enum Tipo {
	TODOS(""), MULTIPLO("1"), COMERCIAL("2"), CAIXA("3"), INVESTIMENTO("4"), LEASING("5"), FINANCEIRA("6");
	
	private final String tipo;

	private Tipo(final String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return tipo;
	}
}