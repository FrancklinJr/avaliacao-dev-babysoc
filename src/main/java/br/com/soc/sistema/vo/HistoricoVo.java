package br.com.soc.sistema.vo;

public class HistoricoVo {
	private ExameVo exame;
	private FuncionarioVo funcionario;
	private String dataExame;
	private String rowid;
	
	public HistoricoVo() {}
	
	public String getDataExame() {
		return dataExame;
	}
	public void setDataExame(String dataExame) {
		this.dataExame = dataExame;
	}
	
	public FuncionarioVo getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioVo funcionarioVo) {
		this.funcionario = funcionarioVo;
	}
	public ExameVo getExame() {
		return exame;
	}
	public void setExame(ExameVo exameVo) {
		this.exame = exameVo;
	}
	
	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	
	@Override
	public String toString() {
		return "HistoricoVo [exameId=" + exame.getRowid() + ", funcionarioId=" + funcionario.getRowid() + ", dataExame=" + dataExame + ", rowid=" + rowid +"]";
	}


}
