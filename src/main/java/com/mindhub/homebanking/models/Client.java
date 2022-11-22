package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Clientes")
public class Client {

        @Id
        @GeneratedValue
        @GenericGenerator(name="native", strategy = "native")
        private long id;

        private String firstName;
        private String lastName;
        private String email;

        private String password;

        @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
        Set<Account> account = new HashSet<>();


        @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
        Set<ClientLoan> loan = new HashSet<>();

        @OneToMany(mappedBy = "clientCard", fetch = FetchType.EAGER)
        Set<Card> card = new HashSet<>();


        public Client() {
        }

        public Client(String firstName, String lastName, String email, String password) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.password = password;
        }





        public Set<Account> getAccount(){
                return account;
        }

        public Set<Card> getCard(){return  card;}
        public Set<ClientLoan> getClientLoan(){
                return loan;
        }


        public long getId() {
                return id;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }
        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        @Override
        public String toString() {
                return "Cliente{" +
                        "id=" + id +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", email='" + email + '\'' +
                        '}';
        }
}
