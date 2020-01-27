/* 
 * Main file per il codice javascript
 */
window.__PUBLIC_PATH__ = '/fonts';

function onSubmitLogin(){
    
    var username = document.getElementsByName("username")()[0];
    var password = document.getElementsByName("password")()[0];
    
    alert(username);
    
    return false;
}

function validateResetPasswordForm(){

    var newPassword = document.getElementById("newPassword").value;
    var confirm = document.getElementById("confirm").value;

    if(newPassword.length === 0 || confirm.length === 0)
        return false;

    if(newPassword !== confirm){
        document.getElementById("newPassword").value = "";
        document.getElementById("confirm").value = "";
        return false;
    }
    return true;
}

function validateForgotPasswordForm(){
    
    var email = document.getElementById("email").value;
    if(email.length === 0)
        return false;
        
    return true;
}


