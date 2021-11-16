
function eliminar(idProducto) {
	console.log(idProducto);
	swal({
		title: "Esta Seguro de Eliminar?",
		text: "Una vez eliminado deberá agregar de nuevo!",
		type: "warning",
		showCancelButton: true,
		confirmButtonClass: "btn-danger",
		confirmButtonText: "Sí, Eliminar!",
		cancelButtonText: "No, Cancelar!",
		closeOnConfirm: false,
		closeOnCancel: false
	}, function(isConfirm) {
		if (isConfirm) {
			$.ajax({
				url: "productos/eliminar/" + idProducto,
				success: function(res) {
					console.log(res);
				}
			});
			setTimeout(function() {
				parent.location.href = "/productos";
			}, 1800);
			swal("Eliminado!", "Producto eliminado correctamente", "success");			
		} else {
			swal("Cancelado", "Cancelaste la eliminación", "error");
		}
	});
}

