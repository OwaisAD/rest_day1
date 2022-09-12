/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.EmployeeDTO;
import dtos.PersonDTO;
import javax.persistence.EntityManagerFactory;

import entities.Employee;
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
        /*pf.create(new PersonDTO(new Person("Elon", 34)));
        pf.create(new PersonDTO(new Person("Carl", 14)));
        pf.create(new PersonDTO(new Person("Tom", 52)));*/

        EmployeeFacade ef = EmployeeFacade.getEmployeeFacade(emf);
        ef.create(new EmployeeDTO(new Employee("Bill Gates", "Gates Alleyway, 2000 Lake Elsinore", 450000)));
        ef.create(new EmployeeDTO(new Employee("Elon Musk", "Tesla Park, 99 SpaceX City", 630000)));
        ef.create(new EmployeeDTO(new Employee("Sundar Pichai", "Google Park Vue, 563 BoulevardE", 304000)));


    }
    
    public static void main(String[] args) {
        populate();
    }
}
