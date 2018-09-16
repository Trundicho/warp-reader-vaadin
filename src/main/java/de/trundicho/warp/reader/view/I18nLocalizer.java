package de.trundicho.warp.reader.view;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nLocalizer {

    private Locale locale;

    public I18nLocalizer(Locale locale) {
        //set default locale
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String localize(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.MessageBundle", locale);
        return resourceBundle.getString(key);
    }

}
