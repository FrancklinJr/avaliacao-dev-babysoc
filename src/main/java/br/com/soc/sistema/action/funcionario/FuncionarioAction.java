package br.com.soc.sistema.action.funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarFuncionarios;
import br.com.soc.sistema.vo.FuncionarioVo;


public class FuncionarioAction extends Action {
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness business = new FuncionarioBusiness();
	private FuncionarioFilter filtrar = new FuncionarioFilter();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	private String rowid;
	
	public String todos() {
		funcionarios.addAll(business.trazerTodosOsFuncionarios());	

		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		funcionarios = business.filtrarFuncionarios(filtrar);
		
		return SUCCESS;
	}
	
	public String novo() {
		if(funcionarioVo.getNome() == null)
			return INPUT;
		
		business.salvarFuncionario(funcionarioVo);
		
		return REDIRECT;
	}
	
	public String editar() {
		if(funcionarioVo.getRowid() == null)
			return REDIRECT;
			
		funcionarioVo = business.buscarFuncionarioPor(funcionarioVo.getRowid());
		return EDIT;
	}
	
	public String update() {
		if(funcionarioVo.getNome() == null)
			return EDIT;

		business.atualizarFuncionario(funcionarioVo);
		return REDIRECT;
	}
	
	public String deletar() {
		if(rowid == null) 
			return REDIRECT;
		
		business.deletarFuncionario(rowid);
		
		return REDIRECT;
	}

	public List<OpcoesComboBuscarFuncionarios> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarFuncionarios.values());
	}
	
	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setfuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
}

