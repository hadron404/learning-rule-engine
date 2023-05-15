package org.example.business.call;


public class Customer {
    private String type;
    private String owner;
    private String callStrategy;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCallStrategy() {
        return callStrategy;
    }

    public void setCallStrategy(String callStrategy) {
        this.callStrategy = callStrategy;
    }
}
