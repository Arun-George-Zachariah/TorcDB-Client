package edu.missouri.Create;

import edu.missouri.Constants.Constants;
import edu.stanford.ramcloud.RAMCloud;

public class CreateTable {
    public static String coordinatorLocator = null;
    public static CreateTable instance = null;

    private CreateTable() {

    }

    public static CreateTable getInstance() {
        if(instance == null) {
            coordinatorLocator = System.getProperty(Constants.RC_COORDINATOR_LOC);
            instance = new CreateTable();
        }
        return instance;
    }

    public long createTable(String tableName) {
        System.out.println("CreateTable :: createTable :: tableName :: " + tableName);

        RAMCloud client = new RAMCloud(coordinatorLocator);
        long tableId = client.createTable(tableName);

        client.disconnect();

        System.out.println("CreateTable :: createTable :: tableId :: " + tableId);
        return tableId;
    }
}
