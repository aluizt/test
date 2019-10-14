package br.com.appmessage.send.enuns;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MessageStatusTest {
    @Test
    public void should_return_value_of_message_status() {
        var newMessage = MessageStatus.NEW_MESSAGE.getStatus();
        var readmessage = MessageStatus.READ_MESSAGE.getStatus();
        var resubmitted = MessageStatus.RESUBMITTED.getStatus();

        Assert.assertEquals("new", newMessage);
        Assert.assertEquals("read", readmessage);
        Assert.assertEquals("resubmitted", resubmitted);
    }

}