/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function register(){
    
    let name= document.getElementById("name").value;
    let username = document.getElementById("username").value;
    let pwd = document.getElementById("password").value;
    let cnfpwd = document.getElementById("cnfpassword").value;
    
    if(name.length == 0){
        document.getElementById("error-message").textContent = "Name Required";
        document.getElementById("error-message").className = "alert alert-danger";
    }else if(username.length == 0){
        document.getElementById("error-message").textContent = "Username Required";
        document.getElementById("error-message").className = "alert alert-danger";
    }else if(pwd != cnfpwd){
        document.getElementById("error-message").textContent = "Password don't match";
        document.getElementById("error-message").className = "alert alert-danger";
    }else if(pwd.length < 8 || cnfpwd.length < 8){
        document.getElementById("error-message").textContent = "Password should be more then 8 characters";
        document.getElementById("error-message").className = "alert alert-danger";
    }
    else{
        document.getElementById("register-form").submit();
    }
   
}