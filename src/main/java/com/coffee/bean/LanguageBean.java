package com.coffee.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String localeCode;
    private Locale currentLocale;

    @PostConstruct
    public void init() {
        this.currentLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    private static Map<String,Object> countries;
    static{
        countries = new LinkedHashMap<String,Object>();
        countries.put("Russian", new Locale("ru", "RU"));
        countries.put("English", new Locale("en", "EN"));
    }

    public Map<String, Object> getCountriesInMap() {
        return countries;
    }


    public String getLocaleCode() {
        return localeCode;
    }


    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }


    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }


    public void countryLocaleCodeChanged(ValueChangeEvent e){

        String newLocaleValue = e.getNewValue().toString();

        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if(entry.getValue().toString().equals(newLocaleValue)){

                currentLocale = (Locale) entry.getValue();
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale(currentLocale);


            }
        }
    }

}
