package com.example.afinal;

public class File {
    public File() {

    }

    private String email, name, phn, tickets;

    public File(String email, String name, String phn, String tickets) {
        this.email = email;
        this.name = name;
        this.phn = phn;
        this.tickets = tickets;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }



}