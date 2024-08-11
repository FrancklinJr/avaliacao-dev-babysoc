<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title><s:text name="label.titulo.pagina.relatorio"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">	
		<div class="container">
			<div class="row mt-5 mb-2">
				<div class="col-sm p-0">
					<s:form action="/gerarHistorico.action">
						<div class="input-group">
						    <span class="input-group-text">
						        <strong><s:text name="label.data.inicial"/></strong>
						    </span>
						    <s:textfield type="date" name="dataInicial" id="dataInicial" cssClass="form-control" />
							
							<span class="input-group-text">
								<strong><s:text name="label.data.final"/></strong>
							</span>
						    <s:textfield type="date" name="dataFinal" id="dataFinal" cssClass="form-control" />

						    <button class="btn btn-primary" type="submit">
						        <s:text name="label.gerar.relatorio"/>
						    </button>
						</div>

					</s:form>			
				</div>				
			</div>
			<div class="row">
				<table class="table table-light table-striped align-middle">
					<thead>
						<tr>
							<th><s:text name="label.exame.id"/></th>
							<th><s:text name="label.exame.nome"/></th>
							<th><s:text name="label.funcionario.id"/></th>
							<th><s:text name="label.funcionario.nome"/></th>
							<th><s:text name="label.data"/></th>
						</tr>
					</thead>
					
					<tbody>
						<s:iterator value="historico" >
							<tr>
								<td>${exame.rowid}</td>
								<td>${exame.nome}</td>
								<td>${funcionario.rowid}</td>
								<td>${funcionario.nome}</td>
								<td>${dataExame}</td>
							</tr>

						</s:iterator>
					</tbody>
					
					<tfoot class="table-secondary">
						<tr>
							<td colspan="2">
								<s:url action="novoHistorico" var="novo">
									<s:param name="funcionarioId" value="historico.funcionario.rowid"></s:param>
								</s:url>
								
								<a href="${novo}" class="btn btn-success">
									<s:text name="label.novo"/>
								</a>
							</td>
							<td colspan="1" class="text-center">
								<s:url action="todosFuncionarios" var="funcionarios"/>
								
								<a href="${funcionarios}" class="btn btn-success">
									<s:text name="label.funcionarios"/>
								</a>
							</td>
							<td colspan="2" class="text-end">
								<s:url action="todosHistorico" var="historico"/>
															
								<a href="${historico}" class="btn btn-success">
									<s:text name="label.historico"/>
								</a>
							</td>
						</tr>
					</tfoot>				
				</table>
			</div>

			<div class="row">
			
			</div>
		</div>

		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>

	</body>
</html>