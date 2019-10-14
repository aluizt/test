package br.com.appmessage.send.enuns;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ExchangeTypeTest {
    @Test
    public void should_return_value_of_exchange_type() {
        var direct = ExchangeType.DIRECT.getExchangeName();
        var fanout = ExchangeType.FANOUT.getExchangeName();
        var topic = ExchangeType.TOPIC.getExchangeName();
        var header = ExchangeType.HEADER.getExchangeName();

        Assert.assertEquals("direct", direct);
        Assert.assertEquals("topic", topic);
        Assert.assertEquals("fanout", fanout);
        Assert.assertEquals("header", header);
    }

}