package br.com.soc.sistema.dao.historico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;
import br.com.soc.sistema.vo.HistoricoVo;

public class HistoricoDao extends Dao {
	
	private ExameBusiness exameBusiness = new ExameBusiness();
	private FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
	
	public void insertHistorico(HistoricoVo historicoVo){
		StringBuilder checkQuery = new StringBuilder("SELECT COUNT(*) FROM funcionario_exame WHERE exame_id = ? AND funcionario_id = ? AND data_exame = ?");
		StringBuilder insertQuery = new StringBuilder("INSERT INTO funcionario_exame (exame_id, funcionario_id, data_exame) values (?, ?, ?)");
		try(
			Connection con = getConexao();
				 PreparedStatement checkStmt = con.prepareStatement(checkQuery.toString());
		         PreparedStatement insertStmt = con.prepareStatement(insertQuery.toString())){
			
			checkStmt.setInt(1, Integer.parseInt(historicoVo.getExame().getRowid()));
	        checkStmt.setInt(2, Integer.parseInt(historicoVo.getFuncionario().getRowid()));
	        checkStmt.setString(3, historicoVo.getDataExame());
	        
	        try (ResultSet rs = checkStmt.executeQuery()) {
	            rs.next();
	            int count = rs.getInt(1);

	            if (count == 0) {
	                insertStmt.setInt(1, Integer.parseInt(historicoVo.getExame().getRowid()));
	                insertStmt.setInt(2, Integer.parseInt(historicoVo.getFuncionario().getRowid()));
	                insertStmt.setString(3, historicoVo.getDataExame());
	                insertStmt.executeUpdate();
	            } else {
	                System.out.println("Registro duplicado. Não foi inserido.");
	            }
	        }
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<HistoricoVo> findAllHistorico(){
		StringBuilder query = new StringBuilder("SELECT exame_id idExame, funcionario_id idFuncionario, data_exame dataExame, rowid id FROM funcionario_exame");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
		
			try(ResultSet rs = ps.executeQuery()){
				HistoricoVo vo =  null;
				List<HistoricoVo> historico = new ArrayList<>();
				while (rs.next()) {
					vo = new HistoricoVo();
					vo.setExame(exameBusiness.buscarExamePor(rs.getString("idExame")));
					vo.setFuncionario(funcionarioBusiness.buscarFuncionarioPor(rs.getString("idFuncionario")));
					vo.setDataExame(rs.getString("dataExame"));
					vo.setRowid(rs.getString("id"));
					
					historico.add(vo);
				}
				return historico;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return Collections.emptyList();
	}
	
	
	public List<HistoricoVo> findAllByExame(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT exame_id idExame, funcionario_id idFuncionario, data_exame dataExame, rowid id FROM funcionario_exame ")
								.append("WHERE exame_id = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				HistoricoVo vo =  null;
				List<HistoricoVo> historico = new ArrayList<>();
				while (rs.next()) {
					vo = new HistoricoVo();
					vo.setExame(exameBusiness.buscarExamePor(rs.getString("idExame")));
					vo.setFuncionario(funcionarioBusiness.buscarFuncionarioPor(rs.getString("idFuncionario")));
					vo.setDataExame(rs.getString("dataExame"));
					vo.setRowid(rs.getString("id"));
					
					historico.add(vo);
				}
				return historico;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public List<HistoricoVo> findAllByFuncionario(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT exame_id idExame, funcionario_id idFuncionario, data_exame dataExame, rowid id FROM funcionario_exame ")
								.append("WHERE funcionario_id = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				HistoricoVo vo =  null;
				List<HistoricoVo> historico = new ArrayList<>();
				while (rs.next()) {
					vo = new HistoricoVo();
					vo.setExame(exameBusiness.buscarExamePor(rs.getString("idExame")));
					vo.setFuncionario(funcionarioBusiness.buscarFuncionarioPor(rs.getString("idFuncionario")));
					vo.setDataExame(rs.getString("dataExame"));
					vo.setRowid(rs.getString("id"));
					
					historico.add(vo);
				}
				return historico;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public List<HistoricoVo> findAllByData(String data){
		StringBuilder query = new StringBuilder("SELECT exame_id idExame, funcionario_id idFuncionario, data_exame dataExame, rowid id FROM funcionario_exame ")
								.append("WHERE data_exame = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, data);
			
			try(ResultSet rs = ps.executeQuery()){
				HistoricoVo vo =  null;
				List<HistoricoVo> historico = new ArrayList<>();
				while (rs.next()) {
					vo = new HistoricoVo();
					vo.setExame(exameBusiness.buscarExamePor(rs.getString("idExame")));
					vo.setFuncionario(funcionarioBusiness.buscarFuncionarioPor(rs.getString("idFuncionario")));
					vo.setDataExame(rs.getString("dataExame"));
					vo.setRowid(rs.getString("id"));
					
					historico.add(vo);
				}
				return historico;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public HistoricoVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT rowid id, funcionario_id idFuncionario, exame_id idExame, data_exame dataExame, rowid id FROM funcionario_exame ")
								.append("WHERE rowid = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				HistoricoVo vo =  null;
				
				while (rs.next()) {
					vo = new HistoricoVo();
					vo.setExame(exameBusiness.buscarExamePor(rs.getString("idExame")));
					vo.setFuncionario(funcionarioBusiness.buscarFuncionarioPor(rs.getString("idFuncionario")));
					vo.setDataExame(rs.getString("dataExame"));
					vo.setRowid(rs.getString("id"));
					
				}
				return vo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	
	public void editarHistorico(HistoricoVo historicoVo) {
		StringBuilder query = new StringBuilder("UPDATE funcionario_exame SET funcionario_id = ?, exame_id = ?, data_exame = ? WHERE rowid = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())) {
			ps.setInt(1, Integer.parseInt(historicoVo.getFuncionario().getRowid()));
			ps.setInt(2, Integer.parseInt(historicoVo.getExame().getRowid()));
			ps.setString(3, historicoVo.getDataExame());
			ps.setInt(4, Integer.parseInt(historicoVo.getRowid()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarHistorico(Integer codigo) {
		StringBuilder query = new StringBuilder("DELETE FROM funcionario_exame WHERE rowid = ?");
		
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())) {
			ps.setInt(1, codigo);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public List<HistoricoVo> buscarPorPeriodo(String dataInicial, String dataFinal) {
        List<HistoricoVo> historico = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT e.rowid AS exame_id, e.nm_exame, f.rowid AS funcionario_id, f.nm_funcionario, h.data_exame ")
        		.append("FROM funcionario_exame h JOIN exame e ON h.exame_id = e.rowid JOIN funcionario f ON h.funcionario_id = f.rowid WHERE h.data_exame BETWEEN ? AND ?");
        
        try (Connection con = getConexao();
        		PreparedStatement stmt = con.prepareStatement(query.toString())) {
            stmt.setString(1, dataInicial);
            stmt.setString(2, dataFinal);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                HistoricoVo vo = new HistoricoVo();
                
                ExameVo exame = new ExameVo();
                exame.setRowid(rs.getString("exame_id"));
                exame.setNome(rs.getString("nm_exame"));
                
                FuncionarioVo funcionario = new FuncionarioVo();
                funcionario.setRowid(rs.getString("funcionario_id"));
                funcionario.setNome(rs.getString("nm_funcionario"));
                
                vo.setExame(exame);
                vo.setFuncionario(funcionario);
                vo.setDataExame(rs.getString("data_exame"));
                
                historico.add(vo);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return historico;
    }
}
