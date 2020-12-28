package edu.missouri.Operations.Insert;

import edu.missouri.Constants.Constants;
import edu.missouri.Operations.RAMCloudInstance;
import edu.stanford.ramcloud.RAMCloud;
import edu.stanford.ramcloud.Util;

public class InsertToTable {
    public static InsertToTable instance = null;

    private InsertToTable() {

    }

    public static InsertToTable getInstance() {
        if(instance == null) {
            Util.loadLibrary(Constants.RAMCLOUD_LIB);
            instance = new InsertToTable();
        }
        return instance;
    }

    public void writeToRAMCloud(long tableId, String key, String value) {
//        System.out.println("InsertToTable :: writeToRAMCloud :: tableId :: " + tableId + " :: key :: " + key);

        RAMCloud client = RAMCloudInstance.getInstance().getClient();

        long version = client.write(tableId, key, value);
//        System.out.println("InsertToTable :: writeToRAMCloud :: version :: " + version);

//        client.disconnect();
    }
}
