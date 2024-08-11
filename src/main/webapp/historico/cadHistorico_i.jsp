<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.cadastro"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">

		<div class="container">
			<s:form action="/novoHistorico.action">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosHistorico" var="todos"/>				
								<a href="${todos}" class="btn btn-success" >Histórico</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">Novo Registro</h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="idExame" class="col-sm-1 col-form-label text-center">
								Código do Exame:
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="idExame" name="exameId"/>							
							</div>	
						</div>
						<div class="row align-items-center">
							<label for="idFuncionario" class="col-sm-1 col-form-label text-center">
								Código do Funcionário:
							</label>	
							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="idFuncionario" name="funcionarioId"/>							
							</div>	
						</div>
						<div class="row align-items-center mt-3">
							<label for="data" class="col-sm-1 col-form-label text-center">
								Data:
							</label>	

							<div class="col-sm-5">
								<s:textfield type="date" cssClass="form-control" id="data" name="historicoVo.dataExame"/>							
							</div>
							<div class="col-sm-2">
								<s:textfield type="hidden" cssClass="form-control" name="historicoVo.rowid"/>							
							</div>
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
							<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulario</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>