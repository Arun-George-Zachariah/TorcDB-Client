package edu.missouri.Retrieve;

import edu.missouri.Util.CommonUtil;
import edu.stanford.ramcloud.multiop.MultiReadHandler;
import edu.stanford.ramcloud.multiop.MultiReadObject;

import java.nio.ByteBuffer;

public class ReadFromTable {
    public static ReadFromTable instance = null;

    private ReadFromTable() {

    }

    public static ReadFromTable getInstance() {
        if(instance == null) {
            instance = new ReadFromTable();
        }
        return instance;
    }

    public void readFromRAMCloud(long tableId, byte[] key) {
        System.out.println("ReadFromTable :: readFromRAMCloud :: Start");

        ByteBuffer buffer = ByteBuffer.allocateDirect(50);
        MultiReadObject obj = new MultiReadObject(tableId, key);
        MultiReadHandler handler = new MultiReadHandler(buffer, 0, 0);
        Boolean status = (Boolean) CommonUtil.invoke(handler, "writeRequest", new Class[] {ByteBuffer.class, MultiReadObject.class}, buffer, obj);

        if(status) {
            System.out.println("ReadFromTable :: readFromRAMCloud :: Successful execution.");
        } else {
            System.out.println("ReadFromTable :: readFromRAMCloud :: Unsuccessful execution.");
        }
    }
}
