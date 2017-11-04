/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treatments;

import java.util.Objects;

/**
 *
 * @author Alex
 */
public class Word {
    public String token;
    public int counter;

    
    public Word(){}
    
   
    public Word(String token, int counter) {
        this.token = token;
        this.counter = counter;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    public void incrementCounter()
    {
        this.counter=+1;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.token);
        hash = 73 * hash + this.counter;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Word other = (Word) obj;
        if (this.counter != other.counter) {
            return false;
        }
        if (!Objects.equals(this.token, other.token)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Word{" + "token=" + token + ", counter=" + counter + '}';
    }
    
    
}
