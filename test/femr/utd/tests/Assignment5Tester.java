package femr.utd.tests;

import com.google.inject.Inject;
import femr.business.services.core.IMedicationService;
import femr.common.ItemModelMapper;
import femr.common.dtos.ServiceResponse;
import femr.common.models.MedicationItem;
import femr.data.models.core.IMedication;
import femr.data.models.core.IMissionTeam;
import femr.data.models.core.IMissionTrip;
import femr.data.models.mysql.Medication;
import femr.data.models.mysql.MissionTeam;
import femr.data.models.mysql.MissionTrip;
import femr.data.models.mysql.PatientEncounter;
import org.joda.time.LocalDateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import femr.common.models.*;
import java.util.Date;
import org.junit.Test;
import org.joda.time.DateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;

import static org.junit.Assert.*;
/**
 * Created by mamd on 4/30/2016.
 */
public class Assignment5Tester
{
    @Test
    public void checkPatientItem()
    {
        ItemModelMapper mapper = new ItemModelMapper();
        PatientItem pItem = mapper.createPatientItem(1, "bob", "smith", "Plano", "123 street", 1, new Date(),"M",0,0,0,0f,"",0,"M");
        assertEquals(pItem.getFirstName(), "bob");
        assertEquals(pItem.getLastName(), "smith");
        assertEquals(pItem.getId(), 1);
    }

    @Test
    public void IsEncounterClosed()
    {
        PatientEncounter pEncounter = new PatientEncounter();

        pEncounter.setDateOfMedicalVisit(DateTime.now());
        pEncounter.setDateOfPharmacyVisit(DateTime.now());

        assertTrue(pEncounter.isClosed());

        pEncounter.setDateOfPharmacyVisit(null);
        assertTrue(!pEncounter.isClosed());

    }

    @Test
    public void missionTripItem()
    {
        ItemModelMapper mapper = new ItemModelMapper();
        IMissionTrip trip = new MissionTrip();
        IMissionTeam team = new MissionTeam();
        team.setName("");
        trip.setMissionTeam(team);
        MissionTripItem item = mapper.createMissionTripItem(trip);
        assertTrue(item != null);

    }



}
