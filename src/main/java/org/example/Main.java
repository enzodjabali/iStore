package org.example;
import java.util.Map;
public class Main {

    public static void main(String[] args) {
        manageStore crud = new manageStore();
        crud.dbconnect();
        crud.deleteEmployeeFromStore(4,1);
    }
}

