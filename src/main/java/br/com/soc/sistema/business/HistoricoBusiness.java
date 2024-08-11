package br.com.soc.sistema.business;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.soc.sistema.dao.historico.HistoricoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.HistoricoFilter;
import br.com.soc.sistema.vo.HistoricoVo;

public class HistoricoBusiness {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private HistoricoDao dao;
	
	public HistoricoBusiness() {
		this.dao = new HistoricoDao();
	}
	
	public List<HistoricoVo> trazerTodoOHistorico(){
		return dao.findAllHistorico();
	}	
	
	public void salvarHistorico(HistoricoVo historicoVo) {
		try {
			dao.insertHistorico(historicoVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}	
	
	public List<HistoricoVo> filtrarHistorico(HistoricoFilter filter){
		List<HistoricoVo> historico = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case EXAME:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					historico.addAll(dao.findAllByExame(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case FUNCIONARIO:
				Integer codigo = Integer.parseInt(filter.getValorBusca());
				historico.addAll(dao.findAllByFuncionario(codigo));
			break;
			case DATA:
				historico.addAll(dao.findAllByData(filter.getValorBusca()));
			break;
		}
		
		return historico;
	}
	
	public HistoricoVo buscarHistoricoPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	public void atualizarHistorico(HistoricoVo historicoVo) {
		try {
			
			dao.editarHistorico(historicoVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a edicao do registro");
		}
	}
	
	public void deletarHistorico(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			dao.deletarHistorico(cod);
		} catch(Exception e) {
			throw new BusinessException("Nao foi possivel realizar a exclusao do registro");
		}
	}

	public List<HistoricoVo> filtrarHistoricoData(String dataInicial, String dataFinal) {
		return dao.buscarPorPeriodo(dataInicial, dataFinal);
	}

	public void gerarRelatorio(List<HistoricoVo> historico) throws FileNotFoundException, IOException {

		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			XSSFSheet sheet = workbook.createSheet("Exames");

			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Código do Exame");
			headerRow.createCell(1).setCellValue("Nome do Exame");
			headerRow.createCell(2).setCellValue("Código do Funcionário");
			headerRow.createCell(3).setCellValue("Nome do Funcionário");
			headerRow.createCell(4).setCellValue("Data do Exame");

        
			int rowNum = 1;
			for (HistoricoVo exame : historico) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(exame.getExame().getRowid());
				row.createCell(1).setCellValue(exame.getExame().getNome());
				row.createCell(2).setCellValue(exame.getFuncionario().getRowid());
				row.createCell(3).setCellValue(exame.getFuncionario().getNome());
				row.createCell(4).setCellValue(exame.getDataExame());
			}

			try (FileOutputStream fileOut = new FileOutputStream("src/main/java/relatorio_exames.xlsx")) {
				workbook.write(fileOut);
			}
			
		}
	}
}

