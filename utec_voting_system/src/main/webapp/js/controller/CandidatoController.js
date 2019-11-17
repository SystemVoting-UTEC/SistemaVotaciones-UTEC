$(document).ready(function() {
		$('#CandidatoTableContainer').jtable({
			title : 'Listado de Tipo de candidatos',
			paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10)
            sorting: true, //Enable sorting
            defaultSorting: 'tcaId ASC',
			actions : {
				listAction : 'Candidato.do?action=list',
				createAction : 'Candidato.do?action=create',
				updateAction : 'Candidato.do?action=update',
				deleteAction : 'Candidato.do?action=delete'
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
					title : 'Candidato',
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
		$('#CandidatoTableContainer').jtable('load');
	});