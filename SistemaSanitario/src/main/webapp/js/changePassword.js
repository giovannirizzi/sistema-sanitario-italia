$("document").ready(function() {
    // Add an event that triggers when the submit
    // button is pressed.
    $("#submitPassword").click(function() {
        var oldpassword = $("#oldpassword").val();
        var newpassword = $("#newpassword").val();
        var confirmpassword = $("#confirmpassword").val();
        if (newpassword!=confirmpassword){
            $("#error").html("<div class='alert alert-danger' role='alert'> <b>Le passoword inserite non coincidono.</b> </div>");
                $("#error").removeClass("d-none").addClass("d-block");
                $("#modalPassword").reload();
        }
        else{
            
        
            // Ajax POST request, similar to the GET request.
            $.post('../services/changepassword',{"oldpassword": oldpassword, "newpassword": newpassword},
                function() { // on success
                    $("#error").html("<div class='alert alert-success' role='alert'> <b>Password cambiata con successo.</b> </div>");
                    $("#error").removeClass("d-none").addClass("d-block");
                    $("#modalPassword").reload();
                })
                .fail(function(error) { //on failure
                    if(error.status===400){
                        $("#error").html("<div class='alert alert-danger' role='alert'> <b>Impossibile cambiare la password.</b> Errore. </div>");
                        $("#error").removeClass("d-bloc").addClass("d-none");
                        $("#modalPassword").reload();
                    }
                    if(error.status===490){
                        $("#error").html("<div class='alert alert-danger' role='alert'> <b>Impossibile cambiare la password.</b> La nuova password non rispetta le policy di sicurezza. </div>");
                        $("#error").removeClass("d-none").addClass("d-block");
                        $("#modalPassword").reload();
                    }
                    if(error.status===491){
                        $("#error").html("<div class='alert alert-danger' role='alert'> <b>Impossibile cambiare la password.</b> La password attuale non coincide. </div>");
                        $("#error").removeClass("d-none").addClass("d-block");
                        $("#modalPassword").reload();
                    }
                    if(error.status===500){
                        $("#error").html("<div class='alert alert-danger' role='alert'> <b>Impossibile cambiare la password.</b> Riprova piu' tardi. </div>");
                        $("#error").removeClass("d-none").addClass("d-block");
                        $("#modalPassword").reload();
                    }
                })
                .done(function(msg) { // ok
                    $("#error").html("<div class='alert alert-success' role='alert'> <b>Password cambiata con successo.</b> </div>");
                    $("#error").removeClass("d-none").addClass("d-block");
                    $("#modalPassword").reload();
                });
        }
    $("#formChange").trigger("reset");        
    }); 

});