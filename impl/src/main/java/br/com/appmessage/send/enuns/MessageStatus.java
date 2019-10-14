package br.com.appmessage.send.enuns;

public enum MessageStatus {
    NEW_MESSAGE("new"),
    READ_MESSAGE("read"),
    RESUBMITTED("resubmitted");

    private String status;

    MessageStatus(String messageStatus){
        this.status = messageStatus;
    }

    public String getStatus() {
        return status;
    }
}
