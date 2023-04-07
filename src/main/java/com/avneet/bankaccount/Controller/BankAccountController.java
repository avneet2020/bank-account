package com.avneet.bankaccount.Controller;

import com.avneet.bankaccount.DTO.*;
import com.avneet.bankaccount.Models.*;
import com.avneet.bankaccount.Repository.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5174")
@RestController
public class BankAccountController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @GetMapping("/")
  public String GetPage() {
    return "Welcome to the Bank";
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @PostMapping("/save")
  public int saveUser(@RequestBody SaveDTO response) {
    Customer customer = new Customer(
        response.firstName(),
        response.lastName(),
        response.email());
    User user = new User(response.username(), response.password(), customer);
    customer.setUser(user);
    userRepository.save(user);
    return 200;
  }

  @PostMapping("/login")
  public ResponseEntity<Customer> login(@RequestBody LoginDTO response) {
    Optional<User> userOptional = userRepository.findByUsernameAndPassword(
        response.username(),
        response.password());
    if (userOptional.isPresent()) {
      Customer result = userOptional.get().getCustomer();
      return ResponseEntity.ok(result);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/updateCustomer/{id}")
  public int updateCustomer(
      @PathVariable long id,
      @RequestBody UpdateCustomerDTO response) {
    Customer customer = customerRepository.findById(id).get();
    customer.setFirstName(response.firstName());
    customer.setLastName(response.lastName());
    customer.setBalance(response.balance());
    customer.setEmail(response.email());
    customerRepository.save(customer);
    return 200;
  }


  @CrossOrigin(origins = "http://localhost:5174")
  @PutMapping("/updateBalance/{id}")
  public int updateBalance(
      @PathVariable long id,
      @RequestBody UpdateBalanceDTO response) {
    Customer customer = customerRepository.findById(id).get();
    customer.setBalance(response.balance());
    customerRepository.save(customer);
    return 200;
  }

  @DeleteMapping("/delete/{id}")
  public int deleteUser(@PathVariable long id) {
    User user = userRepository.findById(id).get();
    if (user != null) {
      userRepository.delete(user);
      return 200;
    } else
      return 404;
  }
}
