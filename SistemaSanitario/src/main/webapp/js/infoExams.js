$("document").ready(function() {
    
 $("#selectExam").change(function() {
        var examId = $("#selectedExam").val();
        alert("Ciao negro gay1!");
        if(examId !== ""){
            $.get("SistemaSanitario/api/patient/examInfo/"+examId,
                function() { // on success
                    var descrizione;
                    alert("Ciao negro gay2!");
                })
                        .done(function(response){
                            alert("Ciao negro gay3!");
                            descrizione = response;
                            $("#descrizioneEsame").val(descrizione);
                });
        }
    }); 

});