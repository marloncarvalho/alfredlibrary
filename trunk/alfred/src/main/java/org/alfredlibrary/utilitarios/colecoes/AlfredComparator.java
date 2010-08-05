package org.alfredlibrary.utilitarios.colecoes;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Classe de comparação para coleções.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/04/2010
 */
@SuppressWarnings("all")
public class AlfredComparator implements Comparator {
	
	private boolean ascendente;
	private String atributo;
	private Collection <String> colAtributo;
	private Class<?> clazz;
        
	public Collection<String> getColAtributo() {
		return colAtributo;
	}

	public void setColAtributo(Collection<String> colAtributo) {
		this.colAtributo = colAtributo;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public boolean isAscendente() {
		return ascendente;
	}

	public void setAscendente(boolean ascendente) {
		this.ascendente = ascendente;
	}

	/**
	 * Instancia o comparador com o tipo de ordenação a ser feita quando o atributo
	 * pertence ao objeto da Collection.
	 * 
	 * @param ascendente Se a ordenação será ascendente (true) ou descendente (false).
	 * @param atributo Nome do campo base para ordenação.
	 * @return Clazz classe do atributo.
	 */
	public AlfredComparator(boolean ascendente, String atributo, Class<?> clazz) {
        this.setAscendente(ascendente);
        this.setAtributo(atributo);
        this.setClazz(clazz);
    }
	
	/**
	 * Instancia o comparador com o tipo de ordenação a ser feita quando o atributo
	 * base da ordenação é descendente (em algum grau) do objeto da Collection.
	 * 
	 * @param ascendente Se a ordenação será ascendente (true) ou descendente (false).
	 * @param colAtributo Cadeia de nome de campo para chegar no atributo base para
	 * 						a ordenação.
	 * @return Clazz classe do atributo.
	 */
	public AlfredComparator(boolean ascendente, Collection <String> colAtributo, Class<?> clazz) {
        this.setAscendente(ascendente);
        this.setColAtributo(colAtributo);
        this.setClazz(clazz);
    }

	/**
	 * Comparador de objetos usado no momento da ordenação.
	 * Compara objetos das classes String, Long, Date, Boolean ou outros que implementem a interface Comparable 
	 */
    public int compare(Object o1, Object o2) {
    	int v = 0; // Resultado da comparação
    	if (this.getAtributo() != null) { // Atributos de profundidade 1
	    	try {
		    	if (this.getClazz().equals(String.class)) {
		    		String arg1 = (String)o1.getClass().getMethod("get"+Texto.capitalizarIniciais(this.getAtributo())).invoke(o1);
		    		String arg2 = (String)o2.getClass().getMethod("get"+Texto.capitalizarIniciais(this.getAtributo())).invoke(o2);
		    		if (arg1 == null) {
		    			arg1 = "";
		    		}
		    		if (arg2 == null) {
		    			arg2 = "";
		    		}
		    		v = arg1.compareTo(arg2);
		    	} else if (this.getClazz().equals(Long.class)) {
		    		Long arg1 = (Long)o1.getClass().getMethod("get"+Texto.capitalizarIniciais(this.getAtributo())).invoke(o1);
		    		Long arg2 = (Long)o2.getClass().getMethod("get"+Texto.capitalizarIniciais(this.getAtributo())).invoke(o2);
		    		if (arg1 == null) {
		    			arg1 = new Long(0);
		    		}
		    		if (arg2 == null) {
		    			arg2 = new Long(0);
		    		}
		    		v = arg1.compareTo(arg2);
		    	} else if (this.getClazz().equals(Date.class)) {
		    		Date arg1 = (Date)o1.getClass().getMethod("get"+Texto.capitalizarIniciais(this.getAtributo())).invoke(o1);
		    		Date arg2 = (Date)o2.getClass().getMethod("get"+Texto.capitalizarIniciais(this.getAtributo())).invoke(o2);
		    		if (arg1 == null) {
		    			arg1 = Calendar.getInstance().getTime();
		    		}
		    		if (arg2 == null) {
		    			arg2 = Calendar.getInstance().getTime();
		    		}
		    		v = arg1.compareTo(arg2);
		    	} else if (this.getClazz().equals(Boolean.class)) {
		    		Long arg1 = ((Boolean)o1.getClass().getMethod("get"+Texto.capitalizarIniciais(this.getAtributo())).invoke(o1)).booleanValue() == true ? new Long(1) : new Long(0);
		    		Long arg2 = ((Boolean)o2.getClass().getMethod("get"+Texto.capitalizarIniciais(this.getAtributo())).invoke(o2)).booleanValue() == true ? new Long(1) : new Long(0);
		    		v = arg1.compareTo(arg2);
		    	} else {
		    		Object arg1 = o1.getClass().getMethod("get"+Texto.capitalizarIniciais(this.getAtributo())).invoke(o1);
		    		Object arg2 = o2.getClass().getMethod("get"+Texto.capitalizarIniciais(this.getAtributo())).invoke(o2);
					Comparable c1 = (Comparable) arg1;
					Comparable c2 = (Comparable) arg2;
					if (c1 != null) {
						v = c1.compareTo(c2);
					} else if (c2 != null) {
					    v = -1;
					} else {
						v = 0;
					}
		    	}
	    	} catch (IllegalArgumentException e) {
	    		throw new AlfredException(e);
			} catch (SecurityException e) {
				throw new AlfredException(e);
			} catch (IllegalAccessException e) {
				throw new AlfredException(e);
			} catch (InvocationTargetException e) {
				throw new AlfredException(e);
			} catch (NoSuchMethodException e) {
				throw new AlfredException("Não existe o método de acesso ao campo informado. Verifique se sua classe implementa o padrão JavaBean.",e);
			}
    	} else { // Atributos de profundidade maior que 1
    		try {
	    		Object arg1 = o1;
	    		Object arg2 = o2;
	    		// Obtém o atributo para comparação
	    		for (String atributo : this.getColAtributo()) {
	    			if (arg1 != null || arg2 != null) {
		    			if (arg1 != null) {
		    				arg1 = arg1.getClass().getMethod("get"+Texto.capitalizarIniciais(atributo)).invoke(arg1);
		    			}
		    			if (arg2 != null) {
		    				arg2 = arg2.getClass().getMethod("get"+Texto.capitalizarIniciais(atributo)).invoke(arg2);
		    			}
	    			} else {
	    				break;
	    			}
	    		}
	    		// Se não houver o atributo informado, inicia um valor padrão
	    		if (arg1 == null) {
	    			if (this.getClazz().equals(String.class)) {
			    		arg1 = "";
			    	} else if (this.getClazz().equals(Long.class)) {
			    		arg1 = new Long(0);
			    	} else if (this.getClazz().equals(Date.class)) {
			    		arg1 = Calendar.getInstance().getTime();
			    	} else if (this.getClazz().equals(Boolean.class)) {
			    		arg1 = new Boolean(false);
			    	}
	    		}
	    		// Se não houver o atributo informado, inicia um valor padrão	    		
	    		if (arg2 == null) {
	    			if (this.getClazz().equals(String.class)) {
			    		arg2 = "";
			    	} else if (this.getClazz().equals(Long.class)) {
			    		arg2 = new Long(0);
			    	} else if (this.getClazz().equals(Date.class)) {
			    		arg2 = Calendar.getInstance().getTime();
			    	} else if (this.getClazz().equals(Boolean.class)) {
			    		arg2 = new Boolean(false);
			    	}
	    		}
	    		// Faz a comparação
	    		if (this.getClazz().equals(String.class)) {
		    		v = ((String)arg1).compareTo((String)arg2);
		    	} else if (this.getClazz().equals(Long.class)) {
		    		v = ((Long)arg1).compareTo((Long)arg2);
		    	} else if (this.getClazz().equals(Date.class)) {
		    		v = ((Date)arg1).compareTo((Date)arg2);
		    	} else if (this.getClazz().equals(Boolean.class)) {
		    		arg1 = ((Boolean)arg1).booleanValue() == true ? new Long(1) : new Long(0);
		    		arg2 = ((Boolean)arg2).booleanValue() == true ? new Long(1) : new Long(0);
		    		v = ((Long)arg1).compareTo((Long)arg2);
		    	} else {
		    		Comparable c1 = (Comparable) arg1;
					Comparable c2 = (Comparable) arg2;
					if (c1 != null) {
						v = c1.compareTo(c2);
					} else if (c2 != null) {
					    v = -1;
					} else {
						v = 0;
					}
		    	}
    		} catch (IllegalArgumentException e) {
	    		throw new AlfredException(e);
			} catch (SecurityException e) {
				throw new AlfredException(e);
			} catch (IllegalAccessException e) {
				throw new AlfredException(e);
			} catch (InvocationTargetException e) {
				throw new AlfredException(e);
			} catch (NoSuchMethodException e) {
				throw new AlfredException("Não existe o método de acesso a um campo da cadeia informada. Verifique se sua classe implementa o padrão JavaBean.",e);
			}
    	}
        return this.isAscendente() ? v: -v;
    }

}
