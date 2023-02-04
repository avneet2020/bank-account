package com.avneet.bankaccount.Controller;

import com.avneet.bankaccount.Models.*;
import com.avneet.bankaccount.Repo.*;
import com.avneet.bankaccount.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BankAccountController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CustomerRepo customerRepo;


    @GetMapping(value = "/")
    public String GetPage() {
        return "Welcome to the Bank";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/save")
    public int saveUser(@RequestBody SaveDTO response) {
        Customer customer = new Customer(response.firstName(), response.lastName(), response.email());
        User user = new User(response.username(), response.password(), customer);
        customer.setUser(user);
        userRepo.save(user);
        return 200;
    }

    @GetMapping(value = "/login")
    public Customer login(@RequestBody LoginDTO response) {
        Optional<Customer> customerOptional =
                Optional.ofNullable(userRepo.findByUsernameAndPassword(response.username(), response.password()).getCustomer());
        return customerOptional.orElse(null);
    }

    @PutMapping(value = "/updateCustomer/{id}")
    public int updateCustomer(@PathVariable long id, @RequestBody UpdateCustomerDTO response){
        Customer customer = customerRepo.findById(id).get();
        customer.setFirstName(response.firstName());
        customer.setLastName(response.lastName());
        customer.setBalance(response.balance());
        customer.setEmail(response.email());
        customerRepo.save(customer);
        return 200;

    }
    @PutMapping(value = "/updateBalance/{id}")
    public int updateBalance(@PathVariable long id, @RequestBody UpdateBalanceDTO response){
        Customer customer = customerRepo.findById(id).get();
        customer.setBalance(response.balance());
        customerRepo.save(customer);
        return 200;
    }

    @DeleteMapping(value = "/delete/{id}")
    public int deleteUser(@PathVariable long id){
        User user = userRepo.findById(id).get();
        if (user != null) {
            userRepo.delete(user);
            return 200;
        }
        else
            return 404;
    }

}