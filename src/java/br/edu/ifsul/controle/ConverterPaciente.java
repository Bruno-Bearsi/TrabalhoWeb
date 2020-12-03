/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;


import br.edu.ifsul.converters.*;
import br.edu.ifsul.modelo.Paciente;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author T-Gamer
 */
@Named(value = "converterPaciente")
@RequestScoped
public class ConverterPaciente implements Serializable, Converter{

    @PersistenceContext(unitName="TrabalhoWebPU")
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.equals("Selecione um objeto")){
            return null;
        }
        return em.find(Paciente.class,Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value == null){
            return null;
        }
        Paciente obj = (Paciente) value;
        return obj.getId().toString();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
