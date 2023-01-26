package ftn.uns.ac.rs.bloodbank.rabbitmq;

public interface ICustomerMq {
    void handler(String message);
}
