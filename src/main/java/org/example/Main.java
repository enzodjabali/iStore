package org.example;

public class Main {
    public static void main(String[] args) {
        crud crud = new crud();
        crud.dbconnect();
        //crud.userRegister("Gaby", "titeux@gmail.com", "psd");
        crud.whiteListUser("gabi@gmail.com");
//        if(crud.userConnect("titeux@gmail.com", "psd")){
//            System.out.println("all good");
//        } else {
//            System.out.println("loser");
//        }
    }
}