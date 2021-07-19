/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author TRAN VAN AN
 */
@Entity
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AccountId;
    private String Password;
    private boolean Status;

    public Account() {
    }

    public Account(String Password, boolean Status) {
        this.Password = Password;
        this.Status = Status;
    }
    
    
    
    public Account(int AccountId, String Password, boolean Status) {
        this.AccountId = AccountId;
        this.Password = Password;
        this.Status = Status;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    
    
   
}
