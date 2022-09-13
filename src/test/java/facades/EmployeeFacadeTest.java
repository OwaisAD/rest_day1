package facades;

import dtos.EmployeeDTO;
import entities.Employee;
import entities.Person;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class EmployeeFacadeTest {

    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;

    public EmployeeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = EmployeeFacade.getEmployeeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
            em.persist(new Employee("Carl", "Marinade vej 12, 2000 Karlsund",16000));
            em.persist(new Employee("Elon", "Tesla street 99, 1234 Mexico City",60000));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run

    }


    @Test
    public void testGettingAnEmployeeById() {
        String actual = facade.getEmployeeById(1).getName();
        String expected = "Carl";
        assertEquals(expected,actual);
    }

    @Test
    public void testGettingAnEmployeeByName() {
        int actual = facade.getEmployeeByName("Elon").getSalary();
        int expected = 60000;
        assertEquals(expected, actual);
    }

    @Test
    public void testGettingAllEmployees() {
        int actual = facade.getAllEmployees().size();
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void testGettingEmployeesWithHighestSalary() {
        String actual = facade.getEmployeesWithHighestSalary().get(0).getName();
        String expected = "Elon";
        assertEquals(expected, actual);
    }

    @Test
    public void testCreatingAnEmployee() {
        long actual = facade.createEmployee("Bill","Gates Alleyway",25000).getId();
        long expected = 3;
        assertEquals(expected,actual);
    }

}
