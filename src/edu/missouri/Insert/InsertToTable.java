package edu.missouri.Insert;

import edu.missouri.Constants.Constants;
import edu.missouri.Util.CommonUtil;
import edu.stanford.ramcloud.multiop.*;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public class InsertToTable {
    public static InsertToTable instance = null;

    private InsertToTable() {

    }

    public static InsertToTable getInstance() {
        if(instance == null) {
            instance = new InsertToTable();
        }
        return instance;
    }

    public void writeToRAMCloud(long tableId, byte[] key, byte[] value) {
        System.out.println("InsertToTable :: writeToRAMCloud :: Start");

        ByteBuffer buffer = ByteBuffer.allocateDirect(100);

        MultiWriteObject obj = new MultiWriteObject(tableId, key, value);
        MultiWriteHandler handler = new MultiWriteHandler(buffer, 0, 0);
        Boolean status = (Boolean) CommonUtil.getInstance().invoke(handler, Constants.WRITE_REQUEST, new Class[] {ByteBuffer.class, MultiWriteObject.class}, buffer, obj);

        if(status) {
            System.out.println("InsertToTable :: writeToRAMCloud :: Successful execution.");
        } else {
            System.out.println("InsertToTable :: writeToRAMCloud :: Unsuccessful execution.");
        }
    }
}
