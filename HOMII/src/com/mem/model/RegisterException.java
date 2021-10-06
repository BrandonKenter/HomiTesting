package com.mem.model;

public class RegisterException extends Exception{
	  
      public void registerException(){
      }
      public String getMessage(){
              return ("This email is used, please use another email to register.");
      }
}
