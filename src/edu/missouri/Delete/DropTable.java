package edu.missouri.Delete;

import edu.missouri.Constants.Constants;
import edu.stanford.ramcloud.RAMCloud;
import edu.stanford.ramcloud.Util;

public class DropTable {
    public static DropTable instance = null;
    public static String coordinatorLocator = null;

    private DropTable() {

    }

    public static DropTable getInstance() {
        if(instance == null) {
            Util.loadLibrary(Constants.RAMCLOUD_LIB);
            coordinatorLocator = System.getProperty(Constants.RC_COORDINATOR_LOC);
            instance = new DropTable();
        }
        return instance;
    }

    public void deleteTable(String tableName) {
        System.out.println("DeleteFromTable :: deleteFromTable :: tableName :: " + tableName);

        RAMCloud client = new RAMCloud(coordinatorLocator);

        client.dropTable(tableName);
        System.out.println("InsertToTable :: deleteFromTable :: Successfully dropped tables");

        client.disconnect();
    }
}
