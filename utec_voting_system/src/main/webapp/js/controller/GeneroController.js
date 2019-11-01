$(document).ready(function() {
		$('#GeneroTableContainer').jtable({
			title : 'Listado de generos',
			actions : {
				listAction : 'genero.do?action=list',
				createAction : 'genero.do?action=create',
				updateAction : 'genero.do?action=update',
				deleteAction : 'genero.do?action=delete'
			},
			fields : {
				genId : {
					title : 'ID',
					width : '30%',
					key : true,
					list : true,
					edit : false,
					create : false
				},
				genGenero : {
					title : 'Genero',
					width : '30%',
					edit : true
				}
			}
		});
		$('#GeneroTableContainer').jtable('load');
	});