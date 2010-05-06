package org.alfredlibrary.bancos;

public enum Origem {
	TODOS(""), PRIVADO("1"), PUBLICO("2");
	
	private final String origem;

	private Origem(final String origem) {
		this.origem = origem;
	}

	@Override
	public String toString() {
		return origem;
	}
}
