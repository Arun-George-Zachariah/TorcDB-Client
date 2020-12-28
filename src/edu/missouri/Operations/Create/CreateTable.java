package edu.missouri.Operations.Create;

import edu.missouri.Constants.Constants;
import edu.missouri.Operations.RAMCloudInstance;
import edu.stanford.ramcloud.RAMCloud;

public class CreateTable {
    public static int serverSpan=1;
    public static CreateTable instance = null;

    private CreateTable() {

    }

    public static CreateTable getInstance() {
        if(instance == null) {
            if(System.getProperty(Constants.SERVER_SPAN) != null) {
                serverSpan = Integer.parseInt(System.getProperty(Constants.SERVER_SPAN));
                System.out.println("CreateTable :: getInstance :: serverSpan :: " + serverSpan);
            }
            instance = new CreateTable();
        }
        return instance;
    }

    public long createTable(String tableName) {
        System.out.println("CreateTable :: createTable :: tableName :: " + tableName);

        RAMCloud client = RAMCloudInstance.getInstance().getClient();

        long tableId = client.createTable(tableName, serverSpan);

//        client.disconnect();

        System.out.println("CreateTable :: createTable :: tableId :: " + tableId);
        return tableId;
    }
}
