
//
function eliminar(idProducto) {

	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger'
		},
		buttonsStyling: true
	})

	swalWithBootstrapButtons.fire({
		title: 'Está seguro?',
		text: "No se puede deshacer esta accion.",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Eliminar',
		cancelButtonText: 'Cancelar',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "productos/eliminar/" + idProducto,
				success: function(res) {
					console.log(res);
				}
			});
			
			setTimeout(function() {
				parent.location.href = "/productos";
			}, 2100);

			swalWithBootstrapButtons.fire(
				'Eliminado!',
				'Producto eliminado correctamente.',
				'success'
			)

		} else if (
			/* Read more about handling dismissals below */
			result.dismiss === Swal.DismissReason.cancel
		) {
			swalWithBootstrapButtons.fire(
				'Cancelar',
				'No se ha realizado ninguna acción.',
				'error'
			)
		}
	});
}

//

//function eliminar(idProducto) {
//	console.log(idProducto);
//	swal({
//		title: "Esta Seguro de Eliminar?",
//		text: "No es posible revertir esta acción.",
//		type: "warning",
//		showCancelButton: true,
//		confirmButtonClass: "btn-danger",
//		confirmButtonText: "Eliminar",
//		cancelButtonText: "Cancelar",
//		closeOnConfirm: false,
//		closeOnCancel: false
//	}, function(isConfirm) {
//		if (isConfirm) {
//			$.ajax({
//				url: "productos/eliminar/" + idProducto,
//				success: function(res) {
//					console.log(res);
//				}
//			});
//			setTimeout(function() {
//				parent.location.href = "/productos";
//			}, 1800);
//			swal("Eliminado!", "Producto eliminado correctamente", "success");
//		} else {
//			swal("Cancelado", "Cancelaste la eliminación", "error");
//		}
//	});
//}

