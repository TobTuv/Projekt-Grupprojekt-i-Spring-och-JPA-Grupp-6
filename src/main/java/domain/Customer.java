package domain;

import java.util.List;

public class Customer {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

   // @OneToMany
    private List<Reservation> idk;

    public Customer(long id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
