package edu.missouri.Retrieve;

import edu.missouri.Constants.Constants;
import edu.stanford.ramcloud.RAMCloud;
import edu.stanford.ramcloud.RAMCloudObject;
import edu.stanford.ramcloud.Util;

public class ReadFromTable {
    public static ReadFromTable instance = null;
    public static String coordinatorLocator = null;

    private ReadFromTable() {

    }

    public static ReadFromTable getInstance() {
        if(instance == null) {
            Util.loadLibrary(Constants.RAMCLOUD_LIB);
            coordinatorLocator = System.getProperty(Constants.RC_COORDINATOR_LOC);
            instance = new ReadFromTable();
        }
        return instance;
    }

    public String readFromRAMCloud(long tableId, String key) {
        System.out.println("ReadFromTable :: readFromRAMCloud :: key :: " + key);

        RAMCloud client = new RAMCloud(coordinatorLocator);

        try {
            RAMCloudObject readResults = client.read(tableId, key);

            if(readResults != null) {
                System.out.println("ReadFromTable :: readFromRAMCloud :: Returned value :: " + readResults.getValue() + " :: for the key :: " + readResults.getKey() + " :: with the version :: " + readResults.getVersion());
                return readResults.getValue();
            } else {
                System.out.println("ReadFromTable :: readFromRAMCloud :: Could not retrieve the value.");
                return null;
            }

        } catch (Exception e) {
            System.out.println("ReadFromTable :: readFromRAMCloud :: Exception ::");
            e.printStackTrace();
        } finally {
            client.disconnect();
        }

        return null;
    }
}
