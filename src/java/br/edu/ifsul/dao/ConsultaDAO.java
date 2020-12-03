/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Receituario;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author T-Gamer
 */
@Stateful
public class ConsultaDAO<TIPO> extends DAOGenerico<Consulta> implements Serializable{
    public ConsultaDAO(){
        super();
        classePersistente = Consulta.class;
        listaOrdem.add(new Ordem("id","ID","="));
        listaOrdem.add(new Ordem("diaMes","Data","="));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
    
    @Override
    public Consulta getObjectById(Object id)throws Exception{
        Consulta obj = em.find(Consulta.class, id);
        obj.getExames().size();
        obj.getReceituarios().size();
        for(Receituario re: obj.getReceituarios()){
            re.getMedicamento().size();
        }
        return obj;
    }
}