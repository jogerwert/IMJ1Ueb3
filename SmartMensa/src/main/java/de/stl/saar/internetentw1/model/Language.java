package de.stl.saar.internetentw1.model;

import java.io.Serializable;
import java.util.Locale;

public class Language implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4428826020775540630L;
	private String languageCode;
	private String languageDescription;
	private Locale locale;
	
	public Language(Locale locale, String languageDescription) {
		this.languageDescription = languageDescription;
		this.locale = locale;
		this.languageCode = locale.getLanguage();
	}
	
	public String getLanguageCode() {
		return languageCode;
	}
	
	public String getLanguageDescription() {
		return languageDescription;
	}
	
	public Locale getLocale() {
		return locale;
	}

	@Override
	public String toString() {
		return languageCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languageDescription == null) ? 0 : languageDescription.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (languageDescription == null) {
			if (other.languageDescription != null)
				return false;
		} else if (!languageDescription.equals(other.languageDescription))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		return true;
	}
}