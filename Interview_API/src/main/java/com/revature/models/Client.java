package com.revature.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;

    @Column(name = "client_name")
    private String clientName;

    public Client() {
    }

    public Client(int clientId, String clientName) {
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public int getClientId() {
        return this.clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Client clientId(int clientId) {
        this.clientId = clientId;
        return this;
    }

    public Client clientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return clientId == client.clientId && Objects.equals(clientName, client.clientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientName);
    }

    @Override
    public String toString() {
        return "{" +
            " clientId='" + getClientId() + "'" +
            ", clientName='" + getClientName() + "'" +
            "}";
    }
}