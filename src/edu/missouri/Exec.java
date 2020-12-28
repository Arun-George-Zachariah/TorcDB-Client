package edu.missouri;

import edu.missouri.Operations.Create.CreateTable;
import edu.missouri.Operations.Delete.DeleteFromTable;
import edu.missouri.Operations.Delete.DropTable;
import edu.missouri.Operations.Insert.InsertToTable;
import edu.missouri.Operations.Retrieve.ReadFromTable;

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

    private static void loadTest(String tableName, long n) {
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

//            System.out.println("Adding to the table :: key :: " + key);
            InsertToTable.getInstance().writeToRAMCloud(id, key, value + i);
        }
        Long writeEndTime = System.currentTimeMillis();
        System.out.println("Time taken to insert :: n :: " + n + " :: entries is :: " + (writeEndTime - startTime) + " ms");

        System.out.println("Retrieving value for :: key :: 0");
        ReadFromTable.getInstance().readFromRAMCloud(id, "0");
        Long readEndTime = System.currentTimeMillis();

        System.out.println("Time taken to read key 0 is :: " + (readEndTime - writeEndTime) + " ms");

    }

    private static void fetchTest(String tableName, long n, long count) {
        long tableId = ReadFromTable.getInstance().getTableId(tableName);

        Long readStartTime = System.currentTimeMillis();

        for(long i=0; i<count; i++) {
            long randomKey = (long) (Math.random() * (n - 0));

//            System.out.println("Retrieving value for :: key :: " + randomKey);

//            Long startTime = System.currentTimeMillis();
            ReadFromTable.getInstance().readFromRAMCloud(tableId, String.valueOf(randomKey));
//            Long endTime = System.currentTimeMillis();
//            System.out.println("Time taken to read key is :: " + (endTime - startTime) + " ms");
        }

        Long readEndTime = System.currentTimeMillis();
        System.out.println("Total time taken to read :: " + (readEndTime - readStartTime) + " ms");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("List of available RAMCloud tests:");
        System.out.println("1. Basic test. (Validates basic CRUD operations on RAMCloud)");
        System.out.println("2. Key size test. (Validates the max size value that could be inserted onto RAMCloud)");
        System.out.println("3. Load test. (Loads n keys and n values of size 1Mb onto RAMCloud)");
        System.out.println("4. Fetch test (Fetches 1000 random records from RAMCloud).");
        System.out.println("5. Delete test tables.");

        System.out.println("\n Choose a test value");

        int input = scanner.nextInt();
        if(input == 1) {
            basicTest();
        } else if(input == 2) {
            keySizeTest();
        } else if(input == 3) {
            System.out.println("Enter the number of keys to be loaded");
            int n = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the table name");
            String tableName = scanner.nextLine();
            System.out.println("Starting load test on : " + tableName + " with " + n + "entries.");
            loadTest(tableName, n);
        }  else if(input == 4) {
            System.out.println("Enter the number of keys loaded");
            int n = scanner.nextInt();
            System.out.println("Enter the number of keys to retrieve");
            int count = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the table name");
            String tableName = scanner.nextLine();
            System.out.println("Starting fetch test on : " + tableName + " with " + n + "entries.");
            fetchTest(tableName, (n-1), count);
        } else if(input == 5) {
            System.out.println("Enter the table name");
            scanner.nextLine();
            String tableName = scanner.nextLine();
            System.out.println("Dropping the table :: tableName :: " + tableName);
            DropTable.getInstance().deleteTable(tableName);
        } else {
            System.out.println("Unexpected input. Please restart the execution.");
        }
    }
}
