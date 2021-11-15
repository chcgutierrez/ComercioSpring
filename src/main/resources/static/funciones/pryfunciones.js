/**
 * 
 */

$(document).ready(function() {
    var table = $('#tblProductos').table( {
        responsive: true
    } );
 
    new $.fn.table.FixedHeader( table );
} );