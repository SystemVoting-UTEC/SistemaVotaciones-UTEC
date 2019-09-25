// Login Form

$(function() {
    var button = $('#BTN_Login');
    var box = $('#loginBox');
    var form = $('#loginForm');
    button.removeAttr('href');
    button.mouseup(function(login) {
        box.toggle();
        button.toggleClass('active');
    });
    form.mouseup(function() { 
        return false;
    });
    $(this).mouseup(function(login) {
        if(!($(login.target).parent('#BTN_Login').length > 0)) {
            button.removeClass('active');
            box.hide();
        }
    });
});
$(function() {
	var SliderModule = (function() {
		var pb = {};
		pb.el = $('#slider');
		pb.items = {
			panel: pb.el.find('li')
		}

		var SliderInterval,
			currentSlider = 0,
			nextSlider = 1,
			lengthSlider = pb.items.panel.length;

		pb.init = function(settings) {
			this.settings = settings || {duration: 8000} 
			var output = '';

			SliderInit();

			for(var i = 0; i < lengthSlider; i++) {
				if (i == 0) {
					output += '<li class="active"></li>'; 
				} else {
					output += '<li></li>';
				}
			}

			$('#slider-controls').html(output).on('click', 'li', function (e){
				var $this = $(this);
				if (currentSlider !== $this.index()) {
					changePanel($this.index());
				};
			});
		}

		var SliderInit = function() {
			SliderInterval = setInterval(pb.startSlider, pb.settings.duration);
		}

		pb.startSlider = function() {
			var panels = pb.items.panel,
				controls = $('#slider-controls li');

			if (nextSlider >= lengthSlider) {
				nextSlider = 0;
				currentSlider = lengthSlider-1;
			}

			controls.removeClass('active').eq(nextSlider).addClass('active');
			panels.eq(currentSlider).fadeOut('slow');
			panels.eq(nextSlider).fadeIn('slow');

			currentSlider = nextSlider;
			nextSlider += 1; 
		}

		var changePanel = function(id) {
			clearInterval(SliderInterval);
			var panels = pb.items.panel,
				controls = $('#slider-controls li');

			if (id >= lengthSlider) {
				id = 0;
			} else if (id < 0) {
				id = lengthSlider-1;
			}

			controls.removeClass('active').eq(id).addClass('active');
			panels.eq(currentSlider).fadeOut('slow');
			panels.eq(id).fadeIn('slow');

			currentSlider = id;
			nextSlider = id+1;

			SliderInit();
		}


		return pb;
	}());
	SliderModule.init({duration: 4000});
});
var v=true;
        $("span.help-block").hide();

        function verificar(){

            var v1=0,v2=0,v3=0,v4=0,v5=0,v6=0,v7=0,v8=0,v9=0,v10=0,v11=0,v12=0,v13=0;
            v1=validacion('dui');
            v2=validacion('nombres');
            v3=validacion('apellidos');
            v4=validacion('genero');
            v5=validacion('fecnac');
            v6=validacion('fecexp');
            v7=validacion('residencia');
            v8=validacion('municipio');
            v9=validacion('departamento');
            v10=validacion('estado');
            v11=validacion('madre');
            v12=validacion('padre');
            v13=validacion('email');
            
            if (v1===false || v2===false || v3===false || v4===false || v5===false || v6===false || v7===false || v8===false || v9===false || v10===false || v11===false || v12===false || v13===false) {
                 $("#exito").hide();
                 $("#error").show();
            }else{
                $("#error").hide();
                $("#exito").show();
            }
        } 
        
        function validacion(campo){
            var a=0;
            
            if (campo==='dui'){
                dui = document.getElementById(campo).value;
                if( dui == null || dui.length == 0 || /^\s+$/.test(dui) ) {
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;               
                }
                else
                {
                    if (!(/^\d{8}-\d{1}$/.test(dui))) {
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                        $('#'+campo).parent().children('span').text("Debe ingresar solamente 9 digitos").show();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                        return false;
                    }
                    else{
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                        $('#'+campo).parent().children('span').hide();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");            
                        return true;
                    }                   
                }              
            }
            if (campo==='id'){
                id = document.getElementById(campo).value;
                if( id == null || id.length == 0 || /^\s+$/.test(id) ) {
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;               
                }
                else
                {
                    if (!(/^\d{4}$/.test(id))) {
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                        $('#'+campo).parent().children('span').text("Debe ingresar solamente 4 digitos").show();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                        return false;
                    }
                    else{
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                        $('#'+campo).parent().children('span').hide();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");            
                        return true;
                    }                   
                }              
            }
            if (campo==='nombres'){
                nombres = document.getElementById(campo).value;
                if( nombres == null || nombres.length == 0 ||  /^[A-z][a-z]*/.test(nombres)==false) {                
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese solamente caracteres").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;
                } 
            }
            if (campo==='apellidos'){
                apellido = document.getElementById(campo).value;
                if( apellido == null || apellido.length == 0 || /^[A-z][a-z]*/.test(apellido)==false) {              
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese solamente caracteres").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                 
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;   
                } 
            }
            if (campo==='genero'){
                indice = document.getElementById(campo).selectedIndex;
                if( indice == null || indice == 0 ) {
                    $('#genero').parent().parent().attr("class", "form-group has-error");
                    return false;
                }
                else{
                    $('#genero').parent().parent().attr("class", "form-group has-success");
                    return true;
                }
            }
            if (campo==='partido'){
                partido = document.getElementById(campo).value;
                if( partido == null || partido.length == 0 ||  /^[A-z][a-z]*/.test(partido)==false) {                
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese solamente caracteres").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;
                } 
            }
            if (campo==='fecven'){
                fecven = document.getElementById(campo).value;
                if( fecven == null || fecven.length == 0 || /^\s+$/.test(fecven) ) {                  
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                   
                }
                else{
                    if (!(/^\d{2}-\d{2}-\d{4}$/.test(fecven))) {
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                        $('#'+campo).parent().children('span').text("Formato (DD-MM-AAAA)").show();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                        return false;
                    }
                    else{
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                        $('#'+campo).parent().children('span').hide();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");            
                        return true;
                    }                 
                } 
            }
            if (campo==='fecnac'){
                fecnac = document.getElementById(campo).value;
                if( fecnac == null || fecnac.length == 0 || /^\s+$/.test(fecnac) ) {                  
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                   
                }
                else{
                    if (!(/^\d{2}-\d{2}-\d{4}$/.test(fecnac))) {
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                        $('#'+campo).parent().children('span').text("Formato (DD-MM-AAAA)").show();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                        return false;
                    }
                    else{
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                        $('#'+campo).parent().children('span').hide();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");            
                        return true;
                    }                 
                } 
            }
            if (campo==='fecexp'){
                fecexp = document.getElementById(campo).value;
                if( fecexp == null || fecexp.length == 0 || /^\s+$/.test(fecexp) ) {                   
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                
                }
                else{
                    if (!(/^\d{2}-\d{2}-\d{4}$/.test(fecexp))) {
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                        $('#'+campo).parent().children('span').text("Formato (DD-MM-AAAA)").show();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                        return false;
                    }
                    else{
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                        $('#'+campo).parent().children('span').hide();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");            
                        return true;
                    }                  
                } 
            }
            if (campo==='residencia'){
                residencia = document.getElementById(campo).value;
                if( residencia == null || residencia.length == 0 || /^\s+$/.test(residencia)) {
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                 
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;
                } 
            }
            if (campo==='edad'){
                edad = document.getElementById(campo).value;
                if( edad == null || edad.length == 0 || /^([0-9])*$/.test(edad)==false) {
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                 
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;
                } 
            }
            if (campo==='municipio'){
                municipio = document.getElementById(campo).value;
                if( municipio == null || municipio.length == 0 || /^[A-z][a-z]*/.test(municipio)==false) {
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese solamente caracteres").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                 
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;
                } 
            }
            if (campo==='dep'){
                dep = document.getElementById(campo).value;
                if( dep == null || dep.length == 0 || /^[A-z][a-z]*/.test(dep)==false) {
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese solamente caracteres").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                 
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;
                } 
            }
            if (campo==='departamento'){
                indice = document.getElementById(campo).selectedIndex;
                if( indice == null || indice == 0 ) {
                    $('#departamento').parent().parent().attr("class", "form-group has-error");
                    return false;
                }
                else{
                    $('#departamento').parent().parent().attr("class", "form-group has-success");
                    return true;
                }
            }
            if (campo==='tpusuario'){
                tpusuario = document.getElementById(campo).value;
                if( tpusuario == null || tpusuario.length == 0 ||  /^[A-z][a-z]*/.test(tpusuario)==false) {                
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese solamente caracteres").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;
                } 
            }
            if (campo==='estado'){
                indice = document.getElementById(campo).selectedIndex;
                if( indice == null || indice == 0 ) {
                    $('#estado').parent().parent().attr("class", "form-group has-error");
                    return false;
                }
                else{
                    $('#estado').parent().parent().attr("class", "form-group has-success");
                    return true;
                }
            }
            if (campo==='slcTipo'){
                indice = document.getElementById(campo).selectedIndex;
                if( indice == null || indice == 0 ) {
                    $('#slcTipo').parent().parent().attr("class", "form-group has-error");
                    return false;
                }
                else{
                    $('#slcTipo').parent().parent().attr("class", "form-group has-success");
                    return true;
                }
            }
            if (campo==='pass'){
                pass = document.getElementById(campo).value;
                if( pass == null || pass.length == 0 || /^\s+$/.test(pass)) {
                    
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;            
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;                 
                } 
            }
            if (campo==='pass2'){
                pass2 = document.getElementById(campo).value;
                if( pass2 == null || pass2.length == 0 || /^\s+$/.test(pass2)) {
                    
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;            
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;                 
                } 
            }
            if (campo==='email'){
                email = document.getElementById(campo).value;
                if( email == null || email.length == 0 || /^\s+$/.test(email) ) {
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algun Email").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");                     
                    return false;                    
                }
                else{
                    if (!(/\S+@\S+\.\S+/.test(email))) {
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                        $('#'+campo).parent().children('span').text("Ingrese un Email valido").show();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                        return false;
                    }
                    else{
                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                        $('#'+campo).parent().children('span').hide();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");                
                        return true;
                    }
                }
            }
            if (campo==='madre'){
                madre = document.getElementById(campo).value;
                if( madre == null || madre.length == 0 || /^[A-z][a-z]*/.test(madre)==false) {
                    
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese solamente caracteres").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;            
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;                 
                } 
            }
            if (campo==='padre'){
                padre = document.getElementById(campo).value;
                if( padre == null || padre.length == 0 || /^[A-z][a-z]*/.test(padre)==false) {
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese solamente caracteres").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;                 
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;
                } 
            }
        }


/*$(document).ready(function(){
    $('#FRM_Registro').validate({
        rules : {
            usuario : {
                required : true,
                minlength : 4,
                maxlength : 25
            },
            pass : {
                required : true,
                minlength : 8,
                maxlength : 15,
                xxx : true
            },
            pass2 : {
                required : true,
                equalTo : "#pass",
                minlength : 8,
                maxlength : 15
            },
            email : {
                required : true,
                correo : true
            }
        },
        messages : {
            usuario : {
                required : "El campo usuario es obligatorio",
                minlength : "El usuario debe contener al menos 5 caracteres",
                maxlength : "El usuario debe contener no mas de 25 caracteres"
            },
            pass : {
                required : "El campo password es obligatorio",
                minlength : "La contraseña debe contener al menos 8 caracteres",
                maxlength : "La contraseña debe contener no mas de 12 caracteres",
                xxx : "Ingrese una mayuscula una minuscula y un numero"
            },
            pass2 :{
                required : "El campo verificar password es obligatorio",
                equalTo : "Las contraseñas tienen que ser iguales",
                minlength : "La contraseña debe contener al menos 8 caracteres",
                maxlength : "La contraseña debe contener no mas de 12 caracteres"
            },
            email : {
                required : "El campo email es obligatoria",
                correo : "No es un email valido"
            }
        },
        submitHandler: function (form){
            var data = $("#FRM_Registro").serialize();
            $.post("Registrar", data, function (respuesta, estado, jqXHR){
                if(respuesta === '1'){                   
                    swal("Felicidades!", "Se registro correctamente!", "success");
                    var timer = 2000;
                    setTimeout(function(){ window.location = "index.jsp"; }, timer);
                }else{
                    swal("Error al Registrar!", "Intente nuevamente!", "error");
                }
                $("#usuario").val("");
                $("#pass").val("");
                $("#pass2").val("");
                $("#email").val("");
            });
        }
    });
    
    $.validator.addMethod("xxx",function(value, element) {
        return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/.test(value);
    });
    $.validator.addMethod("correo",function(value, element) {
        return /[a-z0-9._+-]+@[a-z]+\.[a-z]{2,3}$/.test(value);
    });
    
});

$(document).ready(function(){
    $("#FRM_Login").validate({
        rules : {
            usuario : {
                required : true,
                minlength : 4,
                maxlength : 25
            },
            pass : {
                required : true,
                minlength : 8,
                maxlength : 15,
                xxx : true
            }
        },
        messages : {
            usuario : {
                required : "El campo usuario es obligatorio",
                minlength : "El usuario debe contener al menos 5 caracteres",
                maxlength : "El usuario debe contener no mas de 25 caracteres"
            },
            pass : {
                required : "El campo password es obligatorio",
                minlength : "La contraseña debe contener al menos 8 caracteres",
                maxlength : "La contraseña debe contener no mas de 12 caracteres",
                xxx : "Ingrese una mayuscula una minuscula y un numero"
            }
        },
        submitHandler: function (form){
            var data = $("#FRM_Login").serialize();
            $.post("Login", data, function (respuesta, estado, jqXHR){
                if(respuesta === '1'){
                    swal({
                        title: "Credenciales Correctas",
                        text: "Redireccionando...",
                        type: "success",
                        timer: 1000,
                        showConfirmButton: false
                    });
                    //alert("Credenciales Correctas");                    
                    var delay = 1000;
                    setTimeout(function(){ window.location = "Menu.jsp"; }, delay);
                }else{
                    swal("Oops...", "Credenciales Incorrectas!", "error");
                    //alert("Credenciales Incorrectas");
                }
                $("#usuario").val("");
                $("#pass").val("");
            });
        }
    });
    $.validator.addMethod("xxx",function(value, element) {
        return /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/.test(value);
    });
});
*/
function validarLetras(e){
    var expresion=/[a-zA-Z\b]/;
    if(expresion.test(String.fromCharCode(e.which))){
        return expresion.test(String.fromCharCode(e.which));
    }
    else{
        //alert('Solo letras');
        return false;
    }
}

function validarCorreo(e){
    var expresion=/[a-zA-Z0-9._+-@\b]/;
    if(expresion.test(String.fromCharCode(e.which))){
        return expresion.test(String.fromCharCode(e.which));
    }
    else{
        //alert('Solo letras, numeros sin espacio');
        return false;
    }
}

function validarPass(e){
    var expresion=/[a-zA-Z0-9\b]/;
    if(expresion.test(String.fromCharCode(e.which))){
        return expresion.test(String.fromCharCode(e.which));
    }
    else{
        //alert('Solo letras, numeros sin espacio');
        return false;
    }
}

document.ondragstart = function(){
    return false;
};
document.oncontextmenu = function(){
    //alert("Pagina protegida perro");
    return false;
};

var version = navigator.appVersion;
function showKeyCode(e) {
    var keycode = (window.event) ? event.keyCode : e.keyCode;
    if ((version.indexOf('MSIE') !== -1)) {
        if (keycode === 116 || keycode === 123) {//quitar f5 y f12 por seguridad
            event.keyCode = 0;
            event.returnValue = false;
            return false;
        }
    }
    else {
        if (keycode === 116 || keycode === 123) {
            return false;
        }
    }
}

//Mensajes en Pantalla para CRUD
$(document).ready(function(){
     $('#frmDatos').validate({
    submitHandler: function(form){
            var data = $("#frmDatos").serialize();
            $.post("Partido", data, function (respuesta, estado, jqXHR){
                if(respuesta === '1'){                   
                    swal("Felicidades!", "Se inserto correctamente!", "success");
                    var timer = 2000;
                    setTimeout(function(){ window.location = "adminpart.jsp"; }, timer);
                }else{
                    swal("Fallo al insertar!", "error");
                }
                $("#usuario").val("");
                $("#pass").val("");
                $("#pass2").val("");
                $("#email").val("");
            });
        }
    });
});
$("#next-btn").click(function(){
    alert(111);
    //$("#carousel-example-generic").carousel("next");
    alert(222);
});

window.onload=function(){if(window.jQuery){$(document).ready(function(){$(".sidebarNavigation .navbar-collapse").hide().clone().appendTo("body").removeAttr("class").addClass("sideMenu").show();$("body").append("<div class='overlay'></div>");$(".navbar-toggle, .navbar-toggler").on("click",function(){$(".sideMenu").addClass($(".sidebarNavigation").attr("data-sidebarClass"));$(".sideMenu, .overlay").toggleClass("open");$(".overlay").on("click",function(){$(this).removeClass("open");$(".sideMenu").removeClass("open")})});$("body").on("click",".sideMenu.open .nav-item",function(){if(!$(this).hasClass("dropdown")){$(".sideMenu, .overlay").toggleClass("open")}});$(window).resize(function(){if($(".navbar-toggler").is(":hidden")){$(".sideMenu, .overlay").hide()}else{$(".sideMenu, .overlay").show()}})})}else{console.log("sidebarNavigation Requires jQuery")}}