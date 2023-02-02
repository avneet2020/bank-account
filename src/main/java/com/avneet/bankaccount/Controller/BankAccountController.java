package com.avneet.bankaccount.Controller;

import com.avneet.bankaccount.Models.Customer;
import com.avneet.bankaccount.Models.User;
import com.avneet.bankaccount.Repo.CustomerRepo;
import com.avneet.bankaccount.Repo.UserRepo;
import com.avneet.bankaccount.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAccountController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CustomerRepo customerRepo;


    @GetMapping(value = "/")
    public String GetPage() {
        return "Hello, World!";
    }

    @GetMapping(value = "/users")
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody Response response) {
        Customer customer = new Customer(response.getFirstName(), response.getLastName(), response.getEmail());
        User user = new User(response.getUsername(), response.getPassword(), customer);
        customer.setUser(user);
        userRepo.save(user);
        return "is all goo";
    }
}