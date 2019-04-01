package de.stl.saar.internetentw1.view;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import de.stl.saar.internetentw1.view.*;
import de.stl.saar.internetentw1.utils.*;
import de.stl.saar.internetentw1.model.*;
import de.stl.saar.internetentw1.constants.*;


/**
 * This bean managed the internationalization. It contains the available languages and the listeners that are fired
 * when the language is changed. 
 * @author Christopher Olbertz
 *
 */
@ManagedBean
@SessionScoped
public class I18nBean implements Serializable {

	/**
	 * Field serialVersionUID.
	 * (value is 1)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The code of the current locale.
	 */
	private String localeCode;

	/**
	 * Contains the available languages.
	 */
	private List<Language> languages;
	
	private boolean fadeOut;

	/**
	 * Initializes the available languages.
	 */
	@PostConstruct
	public void initialize() {
		languages = new ArrayList<Language>(); 
		languages.add(new Language(Locale.GERMAN, "Deutsch"));
		languages.add(new Language(Locale.ENGLISH, "English"));
		localeCode = Locale.GERMAN.getLanguage();
	}
	
	public void onFadeOutClicked(
			ActionEvent actionEvent) {
		fadeOut = true;
	}
	
	public void onFadeInClicked(
			ActionEvent actionEvent) {
		fadeOut = false;
	}

	/**
	 * Is fired if the user wants to change the language. Looks for the selected language in the list with the 
	 * available languages and sets the new locale.
	 * @param valueChangeEvent The event that was fired. 
	 */
	public void onLocaleCodeChange(ValueChangeEvent valueChangeEvent) {
		final String newLocale = valueChangeEvent.getNewValue().toString();

		for (final Language language: languages) {
			if (StringUtils.areStringsEqual(language.getLanguageDescription(), newLocale)) {
				JsfUtils.setLocale(language.getLocale());
			}
		}
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	/**
	 * Returns the language selected  by the user. If no language is selected the first
	 * language is returned.
	 * @return The selected language or the first language. 
	 */
	public Language getSelectedLanguage() {
		if (StringUtils.isEmpty(localeCode)) {
			for (final Language language: languages) {
				if (StringUtils.areStringsEqual(language.getLanguageCode(), localeCode)) {
					return language;
				}
			}
		}
		
		return languages.get(0);
	}
	
	public boolean isFadeOut() {
		return fadeOut;
	}
	
	public void setFadeOut(boolean fadeOut) {
		this.fadeOut = fadeOut;
	}
	
	public String getUrlLanguage() {
		if (fadeOut) {
			return UrlConstants.LANGUAGE_FADE_OUT;
		} else {
			return UrlConstants.LANGUAGE_FADE_IN;
		}
	}
}