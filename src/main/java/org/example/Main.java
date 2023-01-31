package org.example;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        crud crud = new crud();
        crud.dbconnect();
//        crud.userRegister("gabriel", "gabriel@gmail.com", "gabriel");
        if(crud.userConnect("testlol@gmail.com", "hello")){
            crud.setAdmin("enzo@enzo.enzo");
        } else {
            System.out.println("Ce compte n'existe pas");
        }
    }
}

