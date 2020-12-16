package edu.missouri;

import edu.missouri.Create.CreateTable;
import edu.missouri.Insert.InsertToTable;

public class Exec {
    public static void main(String[] args) {
        long id = CreateTable.getInstance().createTable("test");
        InsertToTable.getInstance().writeToRAMCloud(id, "1".getBytes(), "This is test".getBytes());
    }
}
