package femr.utd.tests;

import com.google.inject.Inject;
import femr.business.services.core.IMedicationService;
import femr.common.dtos.ServiceResponse;
import femr.common.models.MedicationItem;
import femr.data.models.core.IMedication;
import femr.data.models.mysql.Medication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit Tests for MedicationService
 */
public class MedicationServiceTest extends BaseTest {

    private static IMedicationService service;
    private static MedicationItem newMed;
    private static MedicationItem testDeleteMed;

    @Inject
    public void setService(IMedicationService service) {
        this.service = service;
    }

    @BeforeClass
    public static void initialize(){
        System.out.println("Testing MedicationService!!");
        cleanDB();
    }

    @AfterClass
    public static void cleanDB(){

        //clean the DB
        if (newMed!=null) {

            //remove the new medication
            ServiceResponse<MedicationItem> response2 = service.removeMedication(newMed.getId());
            checkForErrors(response2);

            //assert deletion
            MedicationItem medDeleted = response2.getResponseObject();
            assertNull(medDeleted);
        }
        if (testDeleteMed!=null) {

            //remove the new medication
            ServiceResponse<MedicationItem> response3 = service.removeMedication(testDeleteMed.getId());
            checkForErrors(response3);

            //assert deletion
            MedicationItem medDeleted = response3.getResponseObject();
            assertNull(medDeleted);
        }
    }


    @Test
    public void testRetrieveAllMedications() throws Exception {

        //retrieve all the medications
        ServiceResponse<List<String>> response = service.retrieveAllMedications();

        //check for errors
        checkForErrors(response);

        //get the list of medications
        List<String> medications = response.getResponseObject();

        //assert the number of medications
        int numMeds = 3351 + (newMed!=null ? 1 : 0) ;
        assertEquals(numMeds, medications.size());

    }

    @Test
    public void testCreateMedication(){

        //create the medication
        ServiceResponse<MedicationItem> response = service.createMedication("Medication 1", "formtest", null);

        //check for errors
        checkForErrors(response);

        //get the new medication
        newMed = response.getResponseObject();

        //assert the new medication is not null
        assertNotNull(newMed);

    }

    @Test
    public void testDeleteMedication()
    {
        ServiceResponse<MedicationItem> response = service.createMedication("Medication 2", "formtest2", null);

        checkForErrors(response);

        testDeleteMed = response.getResponseObject();
        assertNotNull(testDeleteMed);

        int id = testDeleteMed.getId();

        ServiceResponse<MedicationItem> response2 = service.deleteMedication(id);

        checkForErrors(response2);

        ServiceResponse<IMedication> response3 = service.retrieveByID(id);

        checkForErrors(response3);

        IMedication checkItem = response3.getResponseObject();

        assertTrue(checkItem.getIsDeleted() == true);
    }



}