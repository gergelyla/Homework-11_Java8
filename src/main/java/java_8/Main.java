package java_8;

import java_8.objectDefinitions.Person;
import java_8.objectDefinitions.PersonRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String inputFilePath="src"+File.separator+"test"+ File.separator+"java"+File.separator+"java_8"+File.separator+"InputFile.txt";
        String outputFilePath="src"+File.separator+"test"+ File.separator+"java"+File.separator+"java_8"+File.separator+"OutputFile.txt";

        List<String> stringPersonList=new ArrayList<>();
        PersonRepository<Person> personList=new PersonRepository<>();
        List<Person> listOfPersonsBornInTargetMonthOrderedAlphabetically = new ArrayList<>();

        personList.readPersonsFromFile(inputFilePath, stringPersonList/*, personList*/);

        listOfPersonsBornInTargetMonthOrderedAlphabetically = personList.filterAndSortListOfPersons(/*personList, */listOfPersonsBornInTargetMonthOrderedAlphabetically);

        personList.writeFilteredListToFile(outputFilePath, listOfPersonsBornInTargetMonthOrderedAlphabetically);
    }

}
