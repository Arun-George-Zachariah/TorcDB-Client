package edu.missouri.Operations;

import edu.missouri.Constants.Constants;
import edu.stanford.ramcloud.RAMCloud;

public class RAMCloudInstance {
    public static RAMCloudInstance instance = null;
    public static String coordinatorLocator = null;
    public static RAMCloud client = null;

    public static RAMCloudInstance getInstance() {
        if(instance == null) {
            coordinatorLocator = System.getProperty(Constants.RC_COORDINATOR_LOC);
            System.out.println("RAMCloudInstance :: getInstance :: coordinatorLocator :: " + coordinatorLocator);
        }
        return instance;
    }

    public static RAMCloud getClient() {
        if(client == null) {
            System.out.println("RAMCloudInstance :: getInstance :: Creating RAMCloud client with coordinatorLocator :: " + coordinatorLocator);
            client = new RAMCloud(coordinatorLocator);
        }
        return client;
    }
}
