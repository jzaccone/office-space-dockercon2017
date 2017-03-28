package netgloo.controllers;

import netgloo.models.Account;
import netgloo.models.AccountDao;
import netgloo.models.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@Controller
public class MainController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * /create  --> Create a new user and save it in the database.
   * 
   * @param email User's email
   * @param name User's name
   * @return A string describing if the user is succesfully created or not.
   */
  @RequestMapping("/create")
  @ResponseBody
  public String create(double balance) {
    Account user = null;
    try {
      user = new Account(balance);
      accountDao.save(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created! (id = " + user.getId() + ")";
  }
  
  /**
   * /delete  --> Delete the user having the passed id.
   * 
   * @param id The id of the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      Account user = new Account(id);
      accountDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * Compute Interest and store remainder in an account that I control
   * 
   * @param id The id for the user to update.
   * @param email The new email.
   * @param name The new name.
   * @return A string describing if the user is succesfully updated or not.
   */
  @RequestMapping(value= "/computeinterest", method = RequestMethod.POST, consumes="application/json")
  @ResponseBody
  
  public String computeInterest(@RequestBody(required = true) Transaction transaction) {
    try {
      Account account = accountDao.findById(12345);
      
      double interest = transaction.getAmount() * transaction.getInterestRate();
      double roundedInterest = Math.floor(interest*100) / 100.0;
      double remainingInterest = interest - roundedInterest;
      
      // Save the interest into an account we control.
      account.setBalance(account.getBalance()+remainingInterest);
      accountDao.save(account);
      
      return "The interest for this transaction is: " + String.format("%.2f", roundedInterest) + " and the remaining interest is: "+ remainingInterest + "\n"; 
      
    }
    catch (Exception ex) {
      return "Error updating the account: " + ex.toString();
    }
  }
  
  @RequestMapping("/")
  @ResponseBody
  public String index() {
    return "Proudly handcrafted by " +
        "<a href='http://netgloo.com/en'>Netgloo</a> :)";
  }


  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private AccountDao accountDao;
  
} // class UserController
