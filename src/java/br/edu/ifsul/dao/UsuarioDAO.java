/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

/**
 *
 * @author T-Gamer
 */
@Stateful
public class UsuarioDAO<TIPO> extends DAOGenerico<Usuario> implements Serializable{
    public UsuarioDAO(){
        super();
        classePersistente = Usuario.class;
        listaOrdem.add(new Ordem("nomeUsuario","Nome","="));
        ordemAtual = listaOrdem.get(0);
        converterOrdem = new ConverterOrdem(listaOrdem);
        System.out.println("Criou o usuario dao");
    }
    @Override
    public Usuario getObjectById(Object id) throws Exception{
        Usuario obj = em.find(Usuario.class,id);
        obj.getPermissoes().size();
        return obj;
    }
    public boolean verificaUnicidadeNomeUsuario(String n){
        String jpql = "from Usuario where nomeUsuario = :pNomeUsuario";
        Query query = em.createQuery(jpql);
        query.setParameter("pNomeUsuario",n);
        if(query.getResultList().size()>0)return false;
        else return true;
    }
}
