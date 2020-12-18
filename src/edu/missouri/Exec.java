package edu.missouri;

import edu.missouri.Create.CreateTable;
import edu.missouri.Delete.DeleteFromTable;
import edu.missouri.Delete.DropTable;
import edu.missouri.Insert.InsertToTable;
import edu.missouri.Retrieve.ReadFromTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

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

    private static void keySizeTest() {
        String tableName = "TestTable";
        System.out.println("Creating table :: " + tableName);

        long id = CreateTable.getInstance().createTable(tableName);
        System.out.println("Successfully created table :: id :: " + id);

        String key = "1";
        String value = null;
        try(BufferedReader br = new BufferedReader(new FileReader(new File("data/data.txt")))) {
            value = br.readLine();
            System.out.println("Size of value :: " + value.getBytes("UTF-8").length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Adding to the table :: key :: " + key + " :: value :: " + value);
        InsertToTable.getInstance().writeToRAMCloud(id, key, value);

        System.out.println("Retrieving value for :: key :: " + key);
        ReadFromTable.getInstance().readFromRAMCloud(id, key);

        System.out.println("Deleting :: key :: " + key);
        DeleteFromTable.getInstance().deleteFromTable(id, key);

        System.out.println("Dropping the table :: id :: " + id);
        DropTable.getInstance().deleteTable(tableName);
    }

    private static void loadTest(long n) {
        String tableName = "LoadTable";
        System.out.println("Creating table :: " + tableName);

        long id = CreateTable.getInstance().createTable(tableName);
        System.out.println("Successfully created table :: id :: " + id);

        String value = null;
        try(BufferedReader br = new BufferedReader(new FileReader(new File("data/data.txt")))) {
            value = br.readLine();
            System.out.println("Size of value :: " + value.getBytes("UTF-8").length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Long startTime = System.currentTimeMillis();
        for(long i=0; i<n; i++) {
            String key = String.valueOf(i);

            System.out.println("Adding to the table :: key :: " + key);
            InsertToTable.getInstance().writeToRAMCloud(id, key, value);
        }
        Long writeEndTime = System.currentTimeMillis();
        System.out.println("Time taken to insert :: n :: " + n + " :: entries is :: " + (writeEndTime - startTime) + " ms");

        System.out.println("Retrieving value for :: key :: 0");
        ReadFromTable.getInstance().readFromRAMCloud(id, "0");
        Long readEndTime = System.currentTimeMillis();

        System.out.println("Time taken to read key 0 is :: " + (readEndTime - writeEndTime) + " ms");

        System.out.println("Dropping the table :: id :: " + id);
        DropTable.getInstance().deleteTable(tableName);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("List of available RAMCloud tests:");
        System.out.println("1. Basic test. (Validates basic CRUD operations on RAMCloud)");
        System.out.println("2. Key size test. (Validates the max size value that could be inserted onto RAMCloud)");
        System.out.println("3. Load test. (Loads n keys and n values of size 1Mb onto RAMCloud)");

        System.out.println("\n Choose a test value");

        int input = scanner.nextInt();
        if(input == 1) {
            basicTest();
        } else if(input == 2) {
            keySizeTest();
        } else if(input == 3) {
            System.out.println("Enter the number of keys to be loaded");
            loadTest(scanner.nextInt());
        } else {
            System.out.println("Unexpected input. Please restart the execution.");
        }
    }
}
