$(document).ready(function() {
		$('#PartidoTableContainer').jtable({
			title : 'Listado de generos',
			actions : {
				listAction : 'partido.do?action=list',
				createAction : 'partido.do?action=create',
				updateAction : 'partido.do?action=update',
				deleteAction : 'partido.do?action=delete'
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
					title : 'Partido',
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
		$('#PartidoTableContainer').jtable('load');
	});