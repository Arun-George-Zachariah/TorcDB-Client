package edu.missouri.Operations.Delete;

import edu.missouri.Constants.Constants;
import edu.missouri.Operations.RAMCloudInstance;
import edu.stanford.ramcloud.RAMCloud;
import edu.stanford.ramcloud.Util;

public class DropTable {
    public static DropTable instance = null;

    private DropTable() {

    }

    public static DropTable getInstance() {
        if(instance == null) {
            Util.loadLibrary(Constants.RAMCLOUD_LIB);
            instance = new DropTable();
        }
        return instance;
    }

    public void deleteTable(String tableName) {
        System.out.println("DeleteFromTable :: deleteFromTable :: tableName :: " + tableName);

        RAMCloud client = RAMCloudInstance.getInstance().getClient();

        client.dropTable(tableName);
        System.out.println("InsertToTable :: deleteFromTable :: Successfully dropped tables");

//        client.disconnect();
    }
}
