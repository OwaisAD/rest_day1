/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.PersonDTO;
import javax.persistence.EntityManagerFactory;

import entities.Person;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
        pf.create(new PersonDTO(new Person("Elon", 34)));
        pf.create(new PersonDTO(new Person("Carl", 14)));
        pf.create(new PersonDTO(new Person("Tom", 52)));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
