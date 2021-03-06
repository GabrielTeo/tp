package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalHospifyBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHospifyBook(), new UserPrefs());
    }

    //    @Test
    //    public void execute_newPerson_success() {
    //        Patient validPatient = new PatientBuilder().build();
    //
    //        Model expectedModel = new ModelManager(model.getHospifyBook(), new UserPrefs());
    //        expectedModel.addPerson(validPatient);
    //
    //        assertCommandSuccess(new AddCommand(validPatient), model,
    //                String.format(AddCommand.MESSAGE_SUCCESS, validPatient), expectedModel);
    //    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Patient patientInList = model.getHospifyBook().getPatientList().get(0);
        assertCommandFailure(new AddCommand(patientInList), model, AddCommand.MESSAGE_DUPLICATE_PATIENT);
    }

    @Test
    public void execute_duplicateNric_throwsCommandException() {
        Patient patientInList = model.getHospifyBook().getPatientList().get(0);
        Patient patientWithDuplicateNric = new PatientBuilder().withNric(patientInList.getNric().value).build();
        assertCommandFailure(new AddCommand(patientWithDuplicateNric), model, AddCommand.MESSAGE_DUPLICATE_NRIC);
    }

}
