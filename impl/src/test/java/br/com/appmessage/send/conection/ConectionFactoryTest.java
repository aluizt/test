package br.com.appmessage.send.conection;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ConectionFactoryTest {

    @InjectMocks
    private ConectionFactory conectionFactory;

    @Test
    public void should_return_connection_Factory(){

        var conect = conectionFactory.getConection();

        Assert.assertEquals("guest",conect.getUsername());
        Assert.assertEquals("guest",conect.getPassword());
        Assert.assertEquals("localhost",conect.getHost());
    }
}