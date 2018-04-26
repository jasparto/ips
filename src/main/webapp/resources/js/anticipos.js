function validarGuardar() {
    if (trim(get('itDocumentoFuncionario').value)=='') {
        alert('debe digitar el documento del funcionario');
        get('itDocumentoFuncionario').focus();
        return false;
    }
    if (trim(get('itMotivo').value)=='') {
        alert('el motivo es obligatorio');
        get('itMotivo').focus();
        return false;
    }
    if (get('itMotivo').value.length>200) {
        alert('n\xfamero de caracteres excedido para el motivo (m\xe1ximo 200)');
        get('itMotivo').focus();
        return false;
    }
    if (!validarEntero(get('itValorSugerido').value)) {
        alert('valor no v\xe1lido - digite la cantidad sin puntos ni comas');
        get('itValorSugerido').focus();
        return false;
    }
    if (get('somTipoAnticipo').value=='VIAJE') {
        if (get('rcFechaFinalInputDate').value=='') {
            alert('debe seleccionar la fecha final del viaje');
            return false;
        }
    }
    return true;
}

function validarAdicionarGasto() {
    if (get('rcFechaGastoInputDate').value=='') {
        alert('debe seleccionar la fecha');
        return false;
    }
    if (trim(get('itNIT').value)=='') {
        alert('debe digitar el nit o documento de identificacion del proveedor');
        get('itNIT').focus();
        return false;
    }
    if (!validarEntero(get('itNIT').value)) {
        alert('debe digitar el nit sin puntos ni comas y solo los números antes del guion');
        get('itNIT').focus();
        return false;
    }
    if (trim(get('acNombreProveedorInput').value)=='') {
        alert('debe digitar el nombre del proveedor');
        get('acNombreProveedorInput').focus();
        return false;
    }
    if (get('acNombreProveedorInput').value.length>80) {
        alert('n\xfamero de caracteres excedido para el nombre del proveedor (m\xe1ximo 80)');
        get('acNombreProveedorInput').focus();
        return false;
    }
    if (trim(get('itaDetalle').value)=='') {
        alert('debe digitar el detalle');
        get('itaDetalle').focus();
        return false;
    }
    if (get('itaDetalle').value.length>500) {
        alert('n\xfamero de caracteres excedido para el detalle (m\xe1ximo 500)');
        get('itaDetalle').focus();
        return false;
    }
    if (get('itFactura') != null && trim(get('itFactura').value)=='') {
        alert('debe digitar el número de factura');
        get('itFactura').focus();
        return false;
    }
    if (get('itFactura') != null && get('itFactura').value.length > 20) {
        alert('n\xfamero de caracteres excedido para el número de factura (m\xe1ximo 20)');
        get('itFactura').focus();
        return false;
    }
    if (!validarEntero(get('itValor').value) || get('itValor').value=='0' || get('itValor').value=='') {
        alert('valor no v\xe1lido');
        get('itValor').focus();
        return false;
    }
    return true;
}