package com.jayson.demo.ui.language.bean;

import java.io.Serializable;
import java.util.Locale;

/**
 * 创建人：jayson
 * 创建时间：2019/8/24
 * 创建内容：
 */
public class LanguageRegion implements Serializable {
    private String displayCountry;
    private String displayLanguage;
    private String lanCountry;
    private Locale locale;

    private String showName;
    private boolean isSelected;

    public LanguageRegion() {
    }

    public LanguageRegion(String displayCountry, String displayLanguage, String lanCountry) {
        this.displayCountry = displayCountry;
        this.displayLanguage = displayLanguage;
        this.lanCountry = lanCountry;
    }

    public LanguageRegion(String displayCountry, String displayLanguage,Locale locale) {
        this.displayCountry = displayCountry;
        this.displayLanguage = displayLanguage;
        this.locale = locale;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getDisplayCountry() {
        return displayCountry;
    }

    public void setDisplayCountry(String displayCountry) {
        this.displayCountry = displayCountry;
    }

    public String getDisplayLanguage() {
        return displayLanguage;
    }

    public void setDisplayLanguage(String displayLanguage) {
        this.displayLanguage = displayLanguage;
    }

    public String getLanCountry() {
        return lanCountry;
    }

    public void setLanCountry(String lanCountry) {
        this.lanCountry = lanCountry;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "LanguageRegion{" +
                "displayCountry='" + displayCountry + '\'' +
                ", displayLanguage='" + displayLanguage + '\'' +
                ", lanCountry='" + lanCountry + '\'' +
                ", locale=" + locale +
                ", showName='" + showName + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
