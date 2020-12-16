package edu.missouri;

import edu.missouri.Create.CreateTable;
import edu.missouri.Delete.DeleteFromTable;
import edu.missouri.Delete.DropTable;
import edu.missouri.Insert.InsertToTable;
import edu.missouri.Retrieve.ReadFromTable;

public class Exec {
    private static void basicTest() {
        String tableName = "TestTable";
        System.out.println("Creating table :: " + tableName);

        long id = CreateTable.getInstance().createTable(tableName);
        System.out.println("Successfully created table :: id :: " + id);

        String key = "1";
        String value = "This is a test val";
        System.out.println("Adding to the table :: key :: " + key + " :: value :: " + value);

        InsertToTable.getInstance().writeToRAMCloud(id, key, value);

        System.out.println("Retrieving value for :: key :: " + key);
        ReadFromTable.getInstance().readFromRAMCloud(id, key);

        System.out.println("Deleting :: key :: " + key);
        DeleteFromTable.getInstance().deleteFromTable(id, key);

        System.out.println("Dropping the table :: id :: " + id);
        DropTable.getInstance().deleteTable(tableName);

    }
    public static void main(String[] args) {
        basicTest();
    }
}
