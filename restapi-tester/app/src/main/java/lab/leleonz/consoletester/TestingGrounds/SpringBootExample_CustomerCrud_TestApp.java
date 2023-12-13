package lab.leleonz.consoletester.TestingGrounds;

import java.io.IOException;

import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.CustomerCrudBase;
import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models.CreateUpdateCustomerRequest;
import lab.leleonz.consoletester.RestApiCaller.CustomerCrudCaller.Models.Customer;

public class SpringBootExample_CustomerCrud_TestApp implements TestingGroundBase {
    private final CustomerCrudBase springBootCustomerCrud;

    public SpringBootExample_CustomerCrud_TestApp(CustomerCrudBase springBootCustomerCrud) {
        this.springBootCustomerCrud = springBootCustomerCrud;
    }

    @Override
    public void run() throws IOException, InterruptedException {
        System.out.println("This is a non-interactive testing ground");
        System.out.println("This testing ground will call Customer CRUD rest api in SpringBootExample project");
        System.out.println("All tests will run automatically");
        System.out.println();

        System.out.println("===== TESTS STARTING =====");
        System.out.println();

        System.out.println("Test #1: Get all (seeded) customers ->");
        var readAllResult = springBootCustomerCrud.ReadAll();
        if (readAllResult != null && !readAllResult.isEmpty()) {
            for (Customer customer : readAllResult) {
                System.out.println(customer.toString());
            }
        }
        System.out.println();

        System.out.println("Test #2: Register a new customer ->");
        var createResult = springBootCustomerCrud.Create(new CreateUpdateCustomerRequest("James", "Bond", "jamesbond@sis.uk"));
        if (createResult != null) System.out.println(createResult.toString());
        System.out.println();

        System.out.println("Test #3: Read the newly registered customer ->");
        var readResult = springBootCustomerCrud.Read(createResult.id());
        if (readResult != null) System.out.println(readResult.toString());
        System.out.println();

        System.out.println("Test #4: Update the newly registered customer ->");
        var updateResult = springBootCustomerCrud.Update(readResult.id(), new CreateUpdateCustomerRequest("Billy", "Bond", "billybond@sis.uk"));
        if (updateResult != null) System.out.println(updateResult.toString());
        System.out.println();

        System.out.println("Before deregister the new customer, let's get all the customers so far ->");
        readAllResult = springBootCustomerCrud.ReadAll();
        if (readAllResult != null && !readAllResult.isEmpty()) {
            for (Customer customer : readAllResult) {
                System.out.println(customer.toString());
            }
        }
        System.out.println();

        System.out.println("Test #5: Deregister the newly registered customer ->");
        var deleteResult = springBootCustomerCrud.Delete(readResult.id());
        if (deleteResult) System.out.println("Customer has been deregister.");
        else System.out.println("Failed to deregister customer.");
        System.out.println();

        System.out.println("Before ending the test, let's check again all the customers remaining ->");
        readAllResult = springBootCustomerCrud.ReadAll();
        if (readAllResult != null && !readAllResult.isEmpty()) {
            for (Customer customer : readAllResult) {
                System.out.println(customer.toString());
            }
        }
        System.out.println();

        System.out.println("===== TESTS ENDED =====");
    }


}
