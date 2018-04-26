// jQuery Alert Dialogs Plugin
//
// Version 1.1
//
// Cory S.N. LaViska
// A Beautiful Site (http://abeautifulsite.net/)
// 14 May 2009
//
// Visit http://abeautifulsite.net/notebook/87 for more information
//
// Usage:
//		jAlert( message, [title, callback] )
//		jConfirm( message, [title, callback] )
//		jPrompt( message, [value, title, callback] )
// 
// History:
//
//		1.00 - Released (29 December 2008)
//
//		1.01 - Fixed bug where unbinding would destroy all resize events
//
// License:
// 
// This plugin is dual-licensed under the GNU General Public License and the MIT License and
// is copyright 2008 A Beautiful Site, LLC. 
//

(function($) {
	
	$.alerts = {
		
		// These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
		
		verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,               // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .350,                // transparency level of overlay
		overlayColor: '#000000',               // base color of overlay
		draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
		okButton: '&nbsp;ACEPTAR&nbsp;',         // text for the OK button
		cancelButton: '&nbsp;CANCELAR&nbsp;', // text for the Cancel button
		dialogClass: null,                  // if specified, this class will be applied to all dialogs
		
		// Public methods
		
		alert: function(message, title, callback) {
			if( title == null ) title = 'Alert';
			$.alerts._show(title, message, null, 'alert', function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title, callback) {
			if( title == null ) title = 'Confirm';
			$.alerts._show(title, message, null, 'confirm', function(result) {
				if( callback ) callback(result);
			});
		},
			
		prompt: function(message, value, title, callback) {
			if( title == null ) title = 'Prompt';
			$.alerts._show(title, message, value, 'prompt', function(result) {
				if( callback ) callback(result);
			});
		},
		
                mensaje: function(message, title, callback) {
			if( title == null ) title = 'Mensaje';
			$.alerts._show(title, message, null, 'mensaje', function(result) {
				if( callback ) callback(result);
			});
		},
                
                process: function(message, title, callback) {
			if( title == null ) title = 'Procesando';
			$.alerts._show(title, message, null, 'process', false);
		},
                
                seleccion: function(message, title, callback) {
			if( title == null ) title = 'Confirmar';
			$.alerts._show(title, message, null, 'seleccion', function(result) {
				if( callback ) callback(result);
			});
		},
                
                pregunta: function(message, title, callback) {
			if( title == null ) title = 'Confirmar';
			$.alerts._show(title, message, null, 'pregunta', function(result) {
				if( callback ) callback(result);
			});
		},
		// Private methods
		
		_show: function(title, msg, value, type, callback) {
			
			$.alerts._hide();
			$.alerts._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container">' +
			    '<h1 id="popup_title"></h1>' +
			    '<div id="popup_content">' +
			      '<div id="popup_message"></div>' +
				'</div>' +
			  '</div>');
			
			if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
			
			// IE6 Fix
			var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
			
			$("#popup_container").css({
				position: pos,
				zIndex: 99999,
				padding: 0,
				margin: 0
			});
			
			$("#popup_title").text(title);
			$("#popup_content").addClass(type);
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
			
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
			
			$.alerts._reposition();
			$.alerts._maintainPosition(true);
			
			switch( type ) {
				case 'alert':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						callback(true);
					});
					$("#popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
				case 'confirm':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				case 'prompt':
					$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").click( function() {
						var val = $("#popup_prompt").val();
						$.alerts._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
					if( value ) $("#popup_prompt").val(value);
					$("#popup_prompt").focus().select();
				break;
                                
                                case 'mensaje':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" class="button"/></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						callback(true);  
					});
					$("#popup_ok").focus();
                                        $("#popup_ok").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
                                                if( e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
                                
                                case 'pregunta':
					$("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" class="button"/> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" class="button"/></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
                                        $("#popup_ok").keypress( function(e) {
                                                if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
					});
					$("#popup_cancel").keypress( function(e) {
                                                if( e.keyCode == 13 ) $("#popup_cancel").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
                                        
				break;
			}
			
			// Make draggable
			if( $.alerts.draggable ) {
				try {
					$("#popup_container").draggable({handle: $("#popup_title")});
					$("#popup_title").css({cursor: 'move'});
				} catch(e) { /* requires jQuery UI draggables */ }
			}
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.alerts.verticalOffset;
			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix
			if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			
			$("#popup_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.alerts._reposition);
					break;
					case false:
						$(window).unbind('resize', $.alerts._reposition);
					break;
				}
			}
		}
		
	}
        
        $.status = {
		// These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
		verticalOffset: -85,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,               // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .250,                // transparency level of overlay
		overlayColor: '#000000',               // base color of overlay
		draggable: true,                    // make the dialogs draggable (requires UI Draggables plugin)
                estado: 'I', // text for the Cancel button
		dialogClass: null,                  // if specified, this class will be applied to all dialogs
		
		// Public methods
		
                process: function(message, title, callback) {
			if( title == null ) title = 'Procesando';
			$.status._show(title, message, null, 'process', false);
		},
               
		// Private methods
		
		_show: function(title, msg, value, type, callback) {
			
			$.status._hide();
			$.status._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container_status">' +
			    '<h1 id="popup_title_status"></h1>' +
			    '<div id="popup_content_status">' +
			      '<div id="popup_message_status" align="center"></div>' +
				'</div>' +
			  '</div>');
			
			if( $.status.dialogClass ) $("#popup_container_status").addClass($.status.dialogClass);
			
			// IE6 Fix
			var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
			
			$("#popup_container_status").css({
				position: pos,
				zIndex: 99999,
				padding: 0,
				margin: 0
			});
			
			$("#popup_title_status").text(title);
			$("#popup_content_status").addClass(type);
			$("#popup_message_status").text(msg);
			$("#popup_message_status").html( $("#popup_message_status").text().replace(/\n/g, '<br />') );
			
			$("#popup_container_status").css({
				minWidth: $("#popup_container_status").outerWidth(),
				maxWidth: $("#popup_container_status").outerWidth()
			});
			
			$.status._reposition();
			$.status._maintainPosition(true);
			
			// Make draggable
			if( $.status.draggable ) {
				try {
					$("#popup_container_status").draggable({handle: $("#popup_title_status")});
					$("#popup_title_status").css({cursor: 'move'});
				} catch(e) { /* requires jQuery UI draggables */ }
			}
		},
		
		_hide: function() {
			$("#popup_container_status").remove();
			$.status._overlay('hide');
			$.status._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.status._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			var top = (($(window).height() / 2) - ($("#popup_container_status").outerHeight() / 2)) + $.status.verticalOffset;
			var left = (($(window).width() / 2) - ($("#popup_container_status").outerWidth() / 2)) + $.status.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix
			if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			
			$("#popup_container_status").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.status.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.status._reposition);
					break;
					case false:
						$(window).unbind('resize', $.status._reposition);
					break;
				}
			}
		}
	}
	
	// Shortuct functions
	jPrompt = function(message, value, title, callback) {
		$.alerts.prompt(message, value, title, callback);
	};
        
        jMensaje = function(message, title, labelSI) {
                if(labelSI != null) $.alerts.okButton = labelSI;
		$.alerts.mensaje(message, title, false);
        }
        jMensaje = function(message, title, labelSI, callback) {
                if(labelSI != null) $.alerts.okButton = '&nbsp;' + labelSI + '&nbsp;';
		$.alerts.mensaje(message, title, callback);
        }
        jEstado = function(message, title, process) {
                if($.status.estado=='I') {
                    $.status.estado='P';
                    $.status.process('&nbsp;' + message, title, false);
                }
                if (process == 'hide'){
                    $.status._hide();
                    $.status.estado='I';
                }
        }
        jPregunta = function(message, title, labelSI, labelNO, callback) {
		if(labelNO != null) $.alerts.okButton = '&nbsp;' + labelSI + '&nbsp;';
                if(labelSI != null) $.alerts.cancelButton = '&nbsp;' + labelNO + '&nbsp;';
                $.alerts.pregunta(message, title, callback);
	};
})(jQuery);