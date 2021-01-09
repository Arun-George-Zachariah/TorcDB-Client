package edu.missouri.Operations;

import edu.missouri.Constants.Constants;
import edu.stanford.ramcloud.RAMCloud;
import edu.stanford.ramcloud.Util;

public class RAMCloudInstance {
    public static RAMCloudInstance instance = null;
    public static RAMCloud client = null;

    public static RAMCloudInstance getInstance() {
        if(instance == null) {
            Util.loadLibrary("ramcloud_java");
            instance = new RAMCloudInstance();
        }
        return instance;
    }

    public static RAMCloud getClient() {
        if(client == null) {
            System.out.println("RAMCloudInstance :: getInstance :: Creating RAMCloud client with coordinatorLocator :: " + coordinatorLocator);

            String coordinatorLocator = System.getProperty(Constants.RC_COORDINATOR_LOC);
            System.out.println("RAMCloudInstance :: getInstance :: coordinatorLocator :: " + coordinatorLocator);

            int dpdkPort = -1;
            if(System.getProperty(Constants.DPDK_PORT) != null) {
                dpdkPort = Integer.parseInt(System.getProperty(Constants.DPDK_PORT));
            }
            System.out.println("RAMCloudInstance :: getInstance :: dpdkPort :: " + dpdkPort);

            client = new RAMCloud(coordinatorLocator, Constants.RC_CLUSTER_NAME, dpdkPort);

        }
        return client;
    }
}
