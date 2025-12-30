var token = ""; 

function login() { 
$.ajax({ 
url: "http://localhost:8080/auth/login", 
type: "POST", 
contentType: "application/json", 
data: JSON.stringify({ 
username: $("#username").val(), 
password: $("#password").val() 
}), 
success: function(res) { 
    console.log("username",  $("#username").val());
    console.log("password",  $("#password").val());
token = res.token; 
console.log("token: ", token)
alert("Login success"); 
} 
}); 
} 


function callAdmin() { 
    
$.ajax({ 
url: "http://localhost:8080/admin/dashboard", 
type: "GET", 
beforeSend: function(xhr) { 
xhr.setRequestHeader( 
"Authorization", 
"Bearer " + token 
); 
}, 
success: function(data) { 
$("#output").text(data); 
alert("admin dashboard success"); 

}, 
error: function(xhr) { 
alert(xhr.status); 
} 
}); 
} 