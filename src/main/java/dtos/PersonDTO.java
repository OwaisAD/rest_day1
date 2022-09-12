/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;
import entities.RenameMe;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class PersonDTO {
    private long id;
    private String name;
    private int age;

    public PersonDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public static List<PersonDTO> getDtos(List<Person> persons){
        List<PersonDTO> personsdtos = new ArrayList();
        persons.forEach(person->personsdtos.add(new PersonDTO(person)));
        return personsdtos;
    }

    public PersonDTO(Person person) {
        if(person.getId() != null)
            this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
