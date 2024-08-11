package br.com.soc.sistema.infra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.soc.sistema.exception.BusinessException;

public enum OpcoesComboBuscarHistorico {
	EXAME("1", "EXAME"), 
	FUNCIONARIO("2", "FUNCIONARIO"),
	DATA("3", "DATA");
	
	private String codigo;
	private String descricao;
	private final static Map<String, OpcoesComboBuscarHistorico> opcoes = new HashMap<>();
	
	static {
		Arrays.asList(OpcoesComboBuscarHistorico.values())
		.forEach(
			opcao -> opcoes.put(opcao.getCodigo(), opcao)
		);
	}
	
	private OpcoesComboBuscarHistorico(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static OpcoesComboBuscarHistorico buscarPor(String codigo) throws IllegalArgumentException {
		if(codigo == null)
			throw new IllegalArgumentException("informe um codigo valido");
		
		OpcoesComboBuscarHistorico opcao = getOpcao(codigo)
				.orElseThrow(() -> new BusinessException("Codigo informado nao existe"));
		
		return opcao;
	}
	
	private static Optional<OpcoesComboBuscarHistorico> getOpcao(String codigo){
		return Optional.ofNullable(opcoes.get(codigo));
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
