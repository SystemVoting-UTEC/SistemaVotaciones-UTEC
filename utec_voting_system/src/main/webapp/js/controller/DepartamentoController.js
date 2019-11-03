$(document).ready(function() {
		$('#DepartamentoTableContainer').jtable({
			title : 'Listado de generos',
			actions : {
				listAction : 'departamento.do?action=list',
				createAction : 'departamento.do?action=create',
				updateAction : 'departamento.do?action=update',
				deleteAction : 'departamento.do?action=delete'
			},
			fields : {
				depId : {
					title : 'ID',
					width : '30%',
					key : true,
					list : true,
					edit : false,
					create : false
				},
				depNombre : {
					title : 'Nombre',
					width : '30%',
					edit : true
				}
			}
		});
		$('#DepartamentoTableContainer').jtable('load');
	});