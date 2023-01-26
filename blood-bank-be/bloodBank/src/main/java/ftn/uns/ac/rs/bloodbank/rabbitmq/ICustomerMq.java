package ftn.uns.ac.rs.bloodbank.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.bridge.Message;

public interface ICustomerMq {
    public void handler(String message);
}
