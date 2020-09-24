package edu.missouri.Create;


import edu.stanford.ramcloud.RAMCloud;

public class CreateTable {
    public static void main(String[] args) {
        String coordinatorLocator = System.getProperty("ramcloudCoordinatorLocator");

        RAMCloud client = new RAMCloud(coordinatorLocator);
        Long tableId = client.createTable("Test");

    }
}
