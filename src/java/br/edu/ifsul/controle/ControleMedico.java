/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EspecialidadeDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.modelo.Especialidade;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author T-Gamer
 */
@Named(value = "controleMedico")
@ViewScoped
public class ControleMedico implements Serializable {
    
    @EJB
    private MedicoDAO<Medico> dao;
    private Medico objeto;
   
    @EJB
    private EspecialidadeDAO<Especialidade> daoEspecialidade;
    
    public ControleMedico(){
    }

    public String listar(){
        return "/privado/medico/listar?faces-redirect=true";
    }
    
    public void imprimeMedicos(){
        HashMap p = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioMedicos", p, dao.getListaTodos());
    }
    
    public void novo(){
        objeto = new Medico();
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception ex) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(ex));
        }
    }
    
    public void excluir(Object id){
        try{
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        }catch (Exception ex) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(ex));
        }
    }
    
    public void salvar(){
        try{
            if(objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        }catch (Exception ex) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(ex));
        }
    }
    
    public MedicoDAO<Medico> getDao() {
        return dao;
    }

    public void setDao(MedicoDAO<Medico> dao) {
        this.dao = dao;
    }

    public Medico getObjeto() {
        return objeto;
    }

    public void setObjeto(Medico objeto) {
        this.objeto = objeto;
    }

    public EspecialidadeDAO<Especialidade> getDaoEspecialidade() {
        return daoEspecialidade;
    }

    public void setDaoEspecialidade(EspecialidadeDAO<Especialidade> daoEspecialidade) {
        this.daoEspecialidade = daoEspecialidade;
    }
}
