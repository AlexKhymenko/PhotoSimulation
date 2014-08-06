
// cheking if password and confirm password are equal if yes the block is green if false red
function checkPass()
{
   
    //Store the password field objects into variables ...
    var pass1 = document.getElementById('reg_pass');
    var pass2 = document.getElementById('txtConfirmPassword');
    //Store the Confimation Message Object ...
    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field 
    //and the confirmation field
    if(pass1.value == pass2.value){
        //The passwords match. 
        //Set the color to the good color and inform
        //the user that they have entered the correct password 
        pass2.style.backgroundColor = goodColor;
        

        // message.style.color = goodColor;

       

      /*  pass2.innerHTML = "Passwords Match!";*/


    }else{
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        
    }
} 
// Cheking email on validation via regulard expression if true color green if false red
function checkEmail() {
debugger;
    var email = document.getElementById('reg_login');
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
 var goodColor = "#66cc66";
    var badColor = "#ff6666";
    if (!filter.test(email.value)) {
   email.style.backgroundColor = badColor;
    email.focus;
    return false;
 }
 else email.style.backgroundColor = goodColor;
} 
//Cheking password on validation via regular expression if true color green if false red only can have 1 digit 1 upper case 1 lower case starting from 6

function checkPassword() {

    var pass = document.getElementById('reg_pass');
    var filter =  /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    if (!filter.test(pass.value)) {
   pass.style.backgroundColor = badColor;
    pass.focus;
    return false;
 }
 else pass.style.backgroundColor = goodColor;
} 
// Cheking login via regular expression need to have at least form 6 to 30 digits and only can have  and digits
function checkLogin() { 

    var pass = document.getElementById('login');
    var filter =   /^[a-zA-Z0-9]{6,30}$/;
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    if (!filter.test(pass.value)) {
   pass.style.backgroundColor = badColor;
    pass.focus;
    return false;
 }
 else pass.style.backgroundColor = goodColor;
}