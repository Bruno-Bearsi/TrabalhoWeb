/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Exame;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author T-Gamer
 */
@Stateful
public class ExameDAO<TIPO> extends DAOGenerico<Exame> implements Serializable{
    public ExameDAO(){
        super();
        classePersistente = Exame.class;
        listaOrdem.add(new Ordem("id","ID","="));
        listaOrdem.add(new Ordem("nome","Nome","like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
    
}
