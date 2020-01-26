$(document).on("click", "button[name='showReportBtn']", function(e) {
  
    var reportId = $(this).val();
    $('#reportModal').modal('show');
    
    $.ajax({
      url: "/SistemaSanitario/api/patient/report/"+reportId,
      type: "get",
      dataType : 'json',
      success: function(data) {
 
        $('#esameText').val(data.esame);
        $('#dataText').val(data.dataString);
        $('#autoreText').val(data.autore);
        $('#reportTextArea').text(data.descrizione);
        
      }
    });
});

$('#reportModal').on('hidden.bs.modal', function (e) {
    $('#esameText').val("");
    $('#dataText').val("");
    $('#autoreText').val("");
    $('#reportTextArea').text("");
});
