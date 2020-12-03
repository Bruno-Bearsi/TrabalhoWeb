/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Paciente;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author T-Gamer
 */
@Stateful
public class PacienteDAO<TIPO> extends DAOGenerico<Paciente> implements Serializable{
    public PacienteDAO(){
        super();
        classePersistente = Paciente.class;
        listaOrdem.add(new Ordem("id","ID","="));
        listaOrdem.add(new Ordem("nome","Nome","like"));
        ordemAtual = listaOrdem.get(1);
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
    
    @Override
    public List<Paciente> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        filtro = filtro.replace("[';-]","");
        if(filtro.length()>0){
            switch(ordemAtual.getOperator()){
                case "=":
                    if(ordemAtual.getAtributo().equals("id")){
                        try{
                            Integer.parseInt(filtro);
                        }catch(Exception e){
                            filtro = "0";
                        }
                    }
                    where += "where" + ordemAtual.getAtributo() + " = '" + filtro + "'";
                    break;
                case "like":
                    where += "where upper(" + ordemAtual.getAtributo() + ") like '" + filtro.toUpperCase() + "%'";
                    break;
            }
        }
        jpql += where;
        jpql += " where tipo = 'PA'";
        jpql += " order by " + ordemAtual.getAtributo();
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
    }
}
