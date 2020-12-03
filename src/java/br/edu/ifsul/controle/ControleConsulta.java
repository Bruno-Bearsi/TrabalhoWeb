/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.ExameDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.dao.ReceituarioDAO;
import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.modelo.Receituario;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author T-Gamer
 */
@Named(value = "controleConsulta")
@ViewScoped
public class ControleConsulta implements Serializable {
    
    @EJB
    private ConsultaDAO<Consulta> dao;
    private Consulta objeto;
    
    @EJB
    private MedicoDAO<Medico> daoMedico;
    @EJB
    private PacienteDAO<Paciente> daoPaciente;
    @EJB
    private ExameDAO<Exame> daoExame;
    private Exame e;
    private boolean ne;
    @EJB
    private ReceituarioDAO<Receituario> daoReceituario;
    private Receituario r;
    private boolean nr;
    
    public ControleConsulta(){
    }

    public String listar(){
        return "/privado/consulta/listar?faces-redirect=true";
    }
    
    public void imprimeConsulta(){
        HashMap p = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioConsulta", p, dao.getListaTodos());
    }
    public void imprimeConsultaSubRelatorios(Integer id) throws Exception{
        objeto = dao.getObjectById(id);
        List<Consulta> lista = new ArrayList<>();
        lista.add(objeto);
        HashMap p = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioConsulta", p, lista);
    }
    
    public void novo(){
        objeto = new Consulta();
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
    
     public void novoExame(){
        e = new Exame();
        ne = true;
    }
    
    public void alterarExame(int index){
        e = objeto.getExames().get(index);
        ne = false;
    }
    
    public void salvarExame(){
        if (ne){
            objeto.adicionarExame(e);
        }
        Util.mensagemInformacao("Serviço adicionado com sucesso!");
    }
    
    public void removerExame(int index){
        objeto.removerExame(index);
        Util.mensagemInformacao("Serviço removido com sucesso!");
    }
    
    public void novoReceituario(){
        r = new Receituario();
        nr = true;
    }
    
    public void alterarReceituario(int index){
        r = objeto.getReceituarios().get(index);
        nr = false;
    }
    
    public void salvarReceituario(){
        if (nr){
            objeto.adicionarReceituario(r);
        }
        Util.mensagemInformacao("Serviço adicionado com sucesso!");
    }
    
    public void removerReceituario(int index){
        objeto.removerReceituario(index);
        Util.mensagemInformacao("Serviço removido com sucesso!");
    }
    
    public ConsultaDAO<Consulta> getDao() {
        return dao;
    }

    public void setDao(ConsultaDAO<Consulta> dao) {
        this.dao = dao;
    }

    public Consulta getObjeto() {
        return objeto;
    }

    public void setObjeto(Consulta objeto) {
        this.objeto = objeto;
    }

    public MedicoDAO<Medico> getDaoMedico() {
        return daoMedico;
    }

    public void setDaoMedico(MedicoDAO<Medico> daoMedico) {
        this.daoMedico = daoMedico;
    }

    public PacienteDAO<Paciente> getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDAO<Paciente> daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public ExameDAO<Exame> getDaoExame() {
        return daoExame;
    }

    public void setDaoExame(ExameDAO<Exame> daoExame) {
        this.daoExame = daoExame;
    }

    public ReceituarioDAO<Receituario> getDaoReceituario() {
        return daoReceituario;
    }

    public void setDaoReceituario(ReceituarioDAO<Receituario> daoReceituario) {
        this.daoReceituario = daoReceituario;
    }

    public Exame getE() {
        return e;
    }

    public void setE(Exame e) {
        this.e = e;
    }

    public Receituario getR() {
        return r;
    }

    public void setR(Receituario r) {
        this.r = r;
    }
}
