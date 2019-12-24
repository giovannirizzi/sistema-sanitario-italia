$("document").ready(function() {
    
 $("#selectedExam").change(function() {
        var examId = $("#selectedExam").val();
        alert(examId);
        if(examId !== ""){
            $.get("/SistemaSanitario/api/patient/examInfo/"+examId,
                function(response) { // on success
                    $("#descrizioneEsame").val(response);
                })
        }
    }); 

});