package br.com.soc.sistema.action.historico;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;
import br.com.soc.sistema.vo.HistoricoVo;
import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.business.HistoricoBusiness;
import br.com.soc.sistema.filter.HistoricoFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarHistorico;

public class HistoricoAction extends Action{
	private List<HistoricoVo> historico = new ArrayList<>();
	private HistoricoBusiness historicoBusiness = new HistoricoBusiness();
	private ExameBusiness exameBusiness = new ExameBusiness();
	private FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
	private HistoricoFilter filtrar = new HistoricoFilter();
	private HistoricoVo historicoVo = new HistoricoVo();
	private ExameVo exameVo = new ExameVo();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	private String funcionarioId;
	private String exameId;
	private String idHistorico;
	private String dataInicial;
	private String dataFinal;
	
	public String todos() {
		historico.addAll(historicoBusiness.trazerTodoOHistorico());	

		return SUCCESS;
	}
	
	public String relatorio() {
		historico.addAll(historicoBusiness.trazerTodoOHistorico());
		
		return RESUME;
	}
	
	public String gerar(){
		
		historico = historicoBusiness.filtrarHistoricoData(dataInicial, dataFinal);
		
		try {
			historicoBusiness.gerarRelatorio(historico);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return RESUME;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		historico = historicoBusiness.filtrarHistorico(filtrar);
		
		return SUCCESS;
	}
	
	public String novo() {
		if(historicoVo.getRowid() == null)
			return INPUT;
		
		exameVo = exameBusiness.buscarExamePor(exameId);
		funcionarioVo = funcionarioBusiness.buscarFuncionarioPor(funcionarioId);
		historicoVo.setExame(exameVo);
		historicoVo.setFuncionario(funcionarioVo);
		historicoBusiness.salvarHistorico(historicoVo);
		
		return REDIRECT;
	}

	public String editar() {
		if(idHistorico == null)
			return REDIRECT;
			
		historicoVo = historicoBusiness.buscarHistoricoPor(idHistorico);
		return EDIT;
	}
	
	public String update() {
		if(historicoVo.getRowid() == null)
			return EDIT;

		historicoBusiness.atualizarHistorico(historicoVo);
		return REDIRECT;
	}
	
	public String deletar() {
		if(idHistorico == null) 
			return REDIRECT;
		
		historicoBusiness.deletarHistorico(idHistorico);
		
		return REDIRECT;
	}
	
	public List<OpcoesComboBuscarHistorico> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarHistorico.values());
	}
	
	public List<HistoricoVo> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoVo> historico) {
		this.historico = historico;
	}

	public HistoricoBusiness getBusiness() {
		return historicoBusiness;
	}

	public void setBusiness(HistoricoBusiness business) {
		this.historicoBusiness = business;
	}

	public HistoricoFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(HistoricoFilter filtrar) {
		this.filtrar = filtrar;
	}

	public HistoricoVo getHistoricoVo() {
		return historicoVo;
	}

	public void setHistoricoVo(HistoricoVo historicoVo) {
		this.historicoVo = historicoVo;
	}

	public String getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(String funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public HistoricoBusiness getHistoricoBusiness() {
		return historicoBusiness;
	}

	public void setHistoricoBusiness(HistoricoBusiness historicoBusiness) {
		this.historicoBusiness = historicoBusiness;
	}

	public ExameBusiness getExameBusiness() {
		return exameBusiness;
	}

	public void setExameBusiness(ExameBusiness exameBusiness) {
		this.exameBusiness = exameBusiness;
	}

	public FuncionarioBusiness getFuncionarioBusiness() {
		return funcionarioBusiness;
	}

	public void setFuncionarioBusiness(FuncionarioBusiness funcionarioBusiness) {
		this.funcionarioBusiness = funcionarioBusiness;
	}

	public String getExameId() {
		return exameId;
	}

	public void setExameId(String exameId) {
		this.exameId = exameId;
	}

	public ExameVo getExameVo() {
		return exameVo;
	}

	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}

	public String getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(String idHistorico) {
		this.idHistorico = idHistorico;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	
	
	
}
