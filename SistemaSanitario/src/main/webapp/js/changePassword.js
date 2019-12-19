$("document").ready(function() {
    // Add an event that triggers when the submit
    // button is pressed.
    $("#myForm").submit(function() {
        var oldpassword = $("#oldpassword").val();
        var newpassword = $("#newpassword").val();
        alert("hai fatto il submit gay");
        alert(oldpassword);
        alert(newpassword);

        // Ajax POST request, similar to the GET request.
        $.post('../services/changepassword',{"oldpassword": oldpassword, "newpassword": newpassword},
            function() { // on success
                alert("Insertion successful!");
            })
            .fail(function() { //on failure
                alert("Insertion failed.");
            });
    }); 

});