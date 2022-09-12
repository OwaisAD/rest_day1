package facades;

import dtos.EmployeeDTO;
import dtos.PersonDTO;
import entities.Employee;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EmployeeDTO create(EmployeeDTO employeeDTO){
        Employee employeeEntity = new Employee(employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getSalary());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employeeEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EmployeeDTO(employeeEntity);
    }
    public EmployeeDTO getEmployeeById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Employee employee = em.find(Employee.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new EmployeeDTO(employee);
    }

    public Employee getEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name = :employeeName", Employee.class);
        query.setParameter("employeeName", name);
        Employee employee = query.getSingleResult();
        return employee;
    }

    public List<EmployeeDTO> getAllEmployees(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employeeList = query.getResultList();
        return EmployeeDTO.getDtos(employeeList);
    }

    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e ORDER BY e.salary desc", Employee.class);
        List<Employee> employeesList = query.getResultList();
        return employeesList;
    }

    public Employee createEmployee(String name, String address, int salary) {
        EntityManager em = emf.createEntityManager();
        Employee employee = new Employee(name, address, salary);
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            em.close();
        return employee;
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade ef = getEmployeeFacade(emf);


        //ef.getEmployeeById()
        //ef.getAll().forEach(dto->System.out.println(dto));



    }

}
