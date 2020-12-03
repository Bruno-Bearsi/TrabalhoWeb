/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Especialidade;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author T-Gamer
 */
@Stateful
public class EspecialidadeDAO<TIPO> extends DAOGenerico<Especialidade> implements Serializable{
    public EspecialidadeDAO(){
        super();
        classePersistente = Especialidade.class;
        listaOrdem.add(new Ordem("id","ID","="));
        listaOrdem.add(new Ordem("descricao","Descrição","like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
}
