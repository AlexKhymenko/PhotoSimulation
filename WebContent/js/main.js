function logOut(){
	//window.location = 'PB.html';
	debugger;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/Bank1/rest/jsonServices/logout",
/*        statusCode :{        	
            200: function (data) {//Delete it!
           	 debugger;
           	window.location = 'PB.html';
           	},
            401: function() {
                alert("Error");
             }
        }*/
    });
    sleep(5000);
  //  window.clearTimeout('3000');
    window.location = 'PB.html';
	
}
//Function for delay when Exit
function sleep(milliseconds) {
	  var start = new Date().getTime();
	  for (var i = 0; i < 1e7; i++) {
	    if ((new Date().getTime() - start) > milliseconds){
	      break;
	    }
	  }
	}
function gotoS () { 
	debugger;
	document.getElementById('envelope').style.display='block';document.getElementById('fade').style.display='block';
//    window.location = 'pages/reg.html';
} 

function gotoUser () { 
	//document.getElementById("touser").style.display='block';
	//debugger;
    window.location = 'user.html';
} 

 function submitButton() {
debugger;
        var Auth = {
            email: $('#email').val(),
            pass: $('#pass').val()
        };
        addData(Auth);
}
 function regButton(){
	 debugger;
     var Auth = {
             email: $('#reg_login').val(),
             pass: $('#reg_pass').val(),
       //      name: $('#name').val(),
         }; 
     regUser(Auth);
 }
function addData(data){// pass your data in method
	debugger;
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/Bank1/rest/jsonServices/login",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            statusCode :{
            	
                200: function (data) {
               	 debugger;
           //        alert("Molodec!" + data);
/*                   $(document).ready(function(){
                	    $("#submit").click(function () {
                	          $("#first_panel").hide("slow");
                	        });
                	    $("#submit").click(function () {
                	        $("#second_panel").show();
                	        });
                	  });*/
           /*     var a = 0;
               	 window.a = a;*/
               $("#first_panel").hide();
         		$("#second_panel").show("slow");
               	 
                   //Рабочее
/*                   if(data=="")
                       document.getElementById('touser').style.visibility='hidden';
                   	if(data!=="")
                   		document.getElementById("touser").style.display='block';
                   */
                   
            //        document.location.href = "pages/user.html";
                },
                400 : function(data) {
                    alert("Error");
                 }
            }
        });
}



function regUser(data){// pass your data in method
	//debugger;
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/Bank1/rest/jsonServices/regUser",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            statusCode :{
            	
                200: function (data) {
               	 debugger;
           //        alert("Molodec!" + data);
            //        document.location.href = "pages/user.html";
                },
                401: function() {
                    alert("Error");
                 }
            }
        });
}



function checkSession(){// pass your data in method
	debugger;
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/Bank1/rest/jsonServices",
            //data: JSON.stringify(data),
            //contentType: "application/json",
           // dataType: "json",
            statusCode :{
            	
                200: function (data) {//Delete it!
               	 debugger;
               	 if(data!==""){
               	$("#first_panel").hide();
         		$("#second_panel").show("slow");
               	 }
               	// alert(window.a);
              /* 	 alert(a);
               	 if (a == 0){
               		 alert('Hello!');
               	 }
*/               	 
                //   alert("Molodec!" + data);
              //     Рабочее
/*               	if(data=="")
                   document.getElementById('touser').style.visibility='hidden';
               	if(data!=="")
               		document.getElementById("touser").style.display='block';*/
                    
               	},
                401: function() {
                    alert("Error");
                 }
            }
        });
}
/*$(document).ready(function(){
    $("#successful").click(function () {
          $("#first_pan").hide("slow");
        });
    $("#successful").click(function () {
        $("#second_panel").show();
        });
  });
*/


