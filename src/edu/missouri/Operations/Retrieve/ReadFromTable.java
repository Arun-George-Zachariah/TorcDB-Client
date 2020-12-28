package edu.missouri.Operations.Retrieve;

import edu.missouri.Constants.Constants;
import edu.missouri.Operations.RAMCloudInstance;
import edu.stanford.ramcloud.RAMCloud;
import edu.stanford.ramcloud.RAMCloudObject;
import edu.stanford.ramcloud.Util;

public class ReadFromTable {
    public static ReadFromTable instance = null;

    private ReadFromTable() {

    }

    public static ReadFromTable getInstance() {
        if(instance == null) {
            Util.loadLibrary(Constants.RAMCLOUD_LIB);
            instance = new ReadFromTable();
        }
        return instance;
    }

    public long getTableId(String tableName) {
//        System.out.println("ReadFromTable :: readFromRAMCloud :: tableName :: " + tableName);

        RAMCloud client = RAMCloudInstance.getInstance().getClient();

        long tableId = client.getTableId(tableName);

//        System.out.println("ReadFromTable :: readFromRAMCloud :: tableId :: " + tableId);
        return tableId;
    }

    public String readFromRAMCloud(long tableId, String key) {
//        System.out.println("ReadFromTable :: readFromRAMCloud :: key :: " + key);

        RAMCloud client = RAMCloudInstance.getInstance().getClient();

        try {
            RAMCloudObject readResults = client.read(tableId, key);

            if(readResults != null) {
//                System.out.println("ReadFromTable :: readFromRAMCloud :: Returned value :: " + readResults.getValue() + " :: for the key :: " + readResults.getKey() + " :: with the version :: " + readResults.getVersion());
                return readResults.getValue();
            } else {
//                System.out.println("ReadFromTable :: readFromRAMCloud :: Could not retrieve the value.");
                return null;
            }

        } catch (Exception e) {
//            System.out.println("ReadFromTable :: readFromRAMCloud :: Exception ::");
            e.printStackTrace();
        } finally {
//            client.disconnect();
        }

        return null;
    }
}
