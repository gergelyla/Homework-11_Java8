package java_8;

import java_8.objectDefinitions.Person;
import java_8.objectDefinitions.PersonRepository;
import org.junit.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryTest {
    private PersonRepository personListRepo;
    private List<String> stringPersonList;
    private List<Person> personList;
    private List<Person> listOfPersonsBornInTargetMonthOrderedAlphabetically;
    private LocalDate testDate;
    private Person person;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("after class");
    }

    @Before
    public void setup() {
        System.out.println("in setup");
        String inputFilePath="src"+ File.separator+"test"+ File.separator+"java"+File.separator+"java_8"+File.separator+"TestInputFile.txt";
        String outputFilePath="src"+File.separator+"test"+ File.separator+"java"+File.separator+"java_8"+File.separator+"TestOutputFile.txt";

        listOfPersonsBornInTargetMonthOrderedAlphabetically=new ArrayList<>();
        personList=new ArrayList<>();
        stringPersonList=new ArrayList<>();
        personListRepo=new PersonRepository();
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @Test
    public void testFillListOfPersonsNotEmptyAfterAdding() {
        stringPersonList.add("Laszlo,Gergely,1980/12/22");
        personListRepo.fillListOfPersons(stringPersonList,personList);
        Assert.assertFalse(personList.isEmpty());
    }

    @Test
    public void testStringToDate() {
        LocalDate testDate = LocalDate.of(2019, 8, 21);
        String textDate="2019/08/21";
        personListRepo.stringToDate(textDate).equals(testDate);
    }

    @Test
    public void testFilterOutputList() {
        stringPersonList.add("Laszlo,Gergely,1980/12/22");
        stringPersonList.add("Ioana,Morar,1970/12/02");
        stringPersonList.add("Tudor,Coman,1995/03/10");
        personListRepo.fillListOfPersons(stringPersonList,personList);
        listOfPersonsBornInTargetMonthOrderedAlphabetically=personListRepo.filterAndSortOutputList(12,personList);
        Assert.assertEquals(2,listOfPersonsBornInTargetMonthOrderedAlphabetically.size());
    }
}
