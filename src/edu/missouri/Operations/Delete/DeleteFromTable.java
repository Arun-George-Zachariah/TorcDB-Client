package edu.missouri.Operations.Delete;

import edu.missouri.Constants.Constants;
import edu.missouri.Operations.RAMCloudInstance;
import edu.stanford.ramcloud.RAMCloud;
import edu.stanford.ramcloud.Util;

public class DeleteFromTable {
    public static DeleteFromTable instance = null;

    private DeleteFromTable() {

    }

    public static DeleteFromTable getInstance() {
        if(instance == null) {
            Util.loadLibrary(Constants.RAMCLOUD_LIB);
            instance = new DeleteFromTable();
        }
        return instance;
    }

    public void deleteFromTable(long tableId, String key) {
        System.out.println("DeleteFromTable :: deleteFromTable :: tableId :: " + tableId + " :: key :: " + key);

        RAMCloud client = RAMCloudInstance.getInstance().getClient();

        long version = client.remove(tableId, key);
        System.out.println("DeleteFromTable :: deleteFromTable :: version :: " + version);

//        client.disconnect();
    }
}
