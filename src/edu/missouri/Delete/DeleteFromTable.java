package edu.missouri.Delete;

import edu.missouri.Constants.Constants;
import edu.stanford.ramcloud.RAMCloud;
import edu.stanford.ramcloud.Util;

public class DeleteFromTable {
    public static DeleteFromTable instance = null;
    public static String coordinatorLocator = null;

    private DeleteFromTable() {

    }

    public static DeleteFromTable getInstance() {
        if(instance == null) {
            Util.loadLibrary(Constants.RAMCLOUD_LIB);
            coordinatorLocator = System.getProperty(Constants.RC_COORDINATOR_LOC);
            instance = new DeleteFromTable();
        }
        return instance;
    }

    public void deleteFromTable(long tableId, String key) {
        System.out.println("DeleteFromTable :: deleteFromTable :: tableId :: " + tableId + " :: key :: " + key);

        RAMCloud client = new RAMCloud(coordinatorLocator);

        long version = client.remove(tableId, key);
        System.out.println("InsertToTable :: deleteFromTable :: version :: " + version);

        client.disconnect();
    }
}
