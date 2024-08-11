package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarHistorico;

public class HistoricoFilter {
	private OpcoesComboBuscarHistorico opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public HistoricoFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarHistorico getOpcoesCombo() {
		return opcoesCombo;
	}

	public HistoricoFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarHistorico.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static HistoricoFilter builder() {
		return new HistoricoFilter();
	}
}
