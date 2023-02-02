package org.istore;

public class Main {

    public static void main(String[] args) {
        StoreManagement crud = new StoreManagement();
        crud.dbconnect();
        crud.deleteEmployeeFromStore(4,1);
    }
}

