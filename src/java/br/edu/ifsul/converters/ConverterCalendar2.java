/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converters;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @author T-Gamer
 */
@FacesConverter(value = "converterCalendar2")
public class ConverterCalendar2 implements Serializable, Converter{

    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Calendar retorno = Calendar.getInstance();
            retorno.setTime(sdf.parse(value));
            return retorno;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 == null){
            return null;
        }
        Calendar obj = (Calendar) arg2;
        return sdf.format(obj.getTime());
    }

}