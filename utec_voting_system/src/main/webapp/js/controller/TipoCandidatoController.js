$(document).ready(function() {
		$('#TipoCandidatoTableContainer').jtable({
			title : 'Listado de Tipo de candidatos',
			paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10)
            sorting: true, //Enable sorting
            defaultSorting: 'tcaId ASC',
			actions : {
				listAction : 'TipoCandidato.do?action=list',
				createAction : 'TipoCandidato.do?action=create',
				updateAction : 'TipoCandidato.do?action=update',
				deleteAction : 'TipoCandidato.do?action=delete'
			},
			fields : {
				parId : {
					title : 'ID',
					width : '30%',
					key : true,
					list : true,
					edit : false,
					create : false
				},
				parNombre : {
					title : 'Tipo de Candidato',
					width : '30%',
					edit : true
				},
				estado : {
					title : 'Estado',
					width : '30%',
					edit : true
				}
			}
		});
		$('#TipoCandidatoTableContainer').jtable('load');
	});