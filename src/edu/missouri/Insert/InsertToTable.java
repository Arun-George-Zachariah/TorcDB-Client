package edu.missouri.Insert;

import edu.missouri.Constants.Constants;
import edu.stanford.ramcloud.RAMCloud;
import edu.stanford.ramcloud.Util;

public class InsertToTable {
    public static InsertToTable instance = null;
    public static String coordinatorLocator = null;

    private InsertToTable() {

    }

    public static InsertToTable getInstance() {
        if(instance == null) {
            Util.loadLibrary(Constants.RAMCLOUD_LIB);
            coordinatorLocator = System.getProperty(Constants.RC_COORDINATOR_LOC);
            instance = new InsertToTable();
        }
        return instance;
    }

    public void writeToRAMCloud(long tableId, String key, String value) {
        System.out.println("InsertToTable :: writeToRAMCloud :: tableId :: " + tableId + " :: key :: " + key + " :: value :: " + value);

        RAMCloud client = new RAMCloud(coordinatorLocator);

        long version = client.write(tableId, key, value);
        System.out.println("InsertToTable :: writeToRAMCloud :: version :: " + version);

        client.disconnect();
    }
}
