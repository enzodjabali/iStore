package org.example;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        admin crud = new admin();
        crud.dbconnect();
//        crud.userRegister("gabriel", "gabriel@gmail.com", "gabriel");
        if(crud.userConnect("testlol@gmail.com", "hello")){
        } else {
            System.out.println("Ce compte n'existe pas");
        }
    }
}

