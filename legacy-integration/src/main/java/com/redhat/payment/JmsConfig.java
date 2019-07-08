package com.redhat.payment;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.Map;

import static com.ibm.msg.client.wmq.WMQConstants.*;

@Configuration
@ConfigurationProperties
public class JmsConfig {

    private Map<String, String> mq;

    @Bean
    public ConnectionFactory connectionFactory() throws JMSException {

        final JmsConnectionFactory connFactory =
                JmsFactoryFactory.getInstance(WMQ_PROVIDER).createConnectionFactory();

        connFactory.setStringProperty(WMQ_HOST_NAME, mq.get("host"));
        connFactory.setIntProperty(WMQ_PORT, Integer.parseInt(mq.get("port")));
        connFactory.setStringProperty(WMQ_CHANNEL, "DEV.APP.SVRCONN");
        connFactory.setIntProperty(WMQ_CONNECTION_MODE, WMQ_CM_CLIENT);
        connFactory.setStringProperty(WMQ_QUEUE_MANAGER, "QM1");
        connFactory.setStringProperty(WMQ_APPLICATIONNAME, "RedHat");
        connFactory.setBooleanProperty(USER_AUTHENTICATION_MQCSP, true);
        connFactory.setStringProperty(USERID, mq.get("username"));
        connFactory.setStringProperty(PASSWORD, mq.get("password"));

        return connFactory;
    }

    @Bean
    public JmsComponent jmsComponent(final ConnectionFactory connectionFactory) {

        return JmsComponent.jmsComponent(connectionFactory);
    }

    public Map<String, String> getMq() {
        return mq;
    }

    public void setMq(Map<String, String> mq) {
        this.mq = mq;
    }
}