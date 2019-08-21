package java_8;

import java_8.objectDefinitions.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.valueOf;

public class Main {
    public static void main(String[] args){
        String inputFilePath="src"+File.separator+"test"+ File.separator+"java"+File.separator+"java_8"+File.separator+"InputFile.txt";
        String outputFilePath="src"+File.separator+"test"+ File.separator+"java"+File.separator+"java_8"+File.separator+"OutputFile.txt";

        List<String> stringPersonList=new ArrayList<>();
        List<Person> personList=new ArrayList<>();
        List<Person> listOfPersonsBornInTargetMonthOrderedAlphabetically = new ArrayList<>();

        readPersonsFromFile(inputFilePath, stringPersonList, personList);

        listOfPersonsBornInTargetMonthOrderedAlphabetically = filterAndSortListOfPersons(personList, listOfPersonsBornInTargetMonthOrderedAlphabetically);

        writeFilteredListToFile(outputFilePath, listOfPersonsBornInTargetMonthOrderedAlphabetically);
    }

    private static void writeFilteredListToFile(String outputFilePath, List<Person> listOfPersonsBornInTargetMonthOrderedAlphabetically) {
        if(!listOfPersonsBornInTargetMonthOrderedAlphabetically.isEmpty()) {
            System.out.println(" ");
            System.out.print("Writing new list to output file... ");
            writePersonInfoToFile(outputFilePath, listOfPersonsBornInTargetMonthOrderedAlphabetically);
            System.out.println("Done ");
        }
    }

    private static List<Person> filterAndSortListOfPersons(List<Person> personList, List<Person> listOfPersonsBornInTargetMonthOrderedAlphabetically) {
        boolean validMonth=false;
        while (!validMonth) {
            System.out.println(" ");
            System.out.println("Enter number of month to be filtered and sorted after: ");
            int targetMonth = userInputNr();
            if (0 < targetMonth && targetMonth < 13) {
                validMonth=true;
                System.out.println("Filtering/sorting month: " + targetMonth);
                listOfPersonsBornInTargetMonthOrderedAlphabetically = filterAndSortOutputList(targetMonth, personList);
                if (listOfPersonsBornInTargetMonthOrderedAlphabetically.isEmpty()) {
                    System.out.println("No person found!");
                } else {
                    System.out.println(" ");
                    System.out.println("List of persons born in month " + targetMonth + " :");
                    System.out.println("------------------------------------------------------------");
                    for (Person person : listOfPersonsBornInTargetMonthOrderedAlphabetically) {
                        System.out.println(person);
                    }
                }
            } else {
                System.out.println("Invalid month");
            }
        }
        return listOfPersonsBornInTargetMonthOrderedAlphabetically;
    }

    private static void readPersonsFromFile(String inputFilePath, List<String> stringPersonList, List<Person> personList) {
        System.out.println("Reading persons from file... ");
        readPersonInfoFromFile(stringPersonList,inputFilePath);
        fillListOfPersons(stringPersonList, personList);
        System.out.println("List of persons read from file: ");
        System.out.println("------------------------------------------------------------");
        for(Person person:personList){
            System.out.println(person);
        }
    }

    private static void writePersonInfoToFile(String outputFilePath, List<Person> listOfPersonsBornInTargetMonthOrderedAlphabetically) {
        List<String> stringPersonListToWriteToFile=new ArrayList<>();
        for(Person member:listOfPersonsBornInTargetMonthOrderedAlphabetically){
            stringPersonListToWriteToFile.add(member.getFirstName()+" "+member.getLastName());
        }
        try {
            Path file = Paths.get(outputFilePath);
            Files.write(file, stringPersonListToWriteToFile, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Output not successful!");
        }
    }

    private static List<Person> filterAndSortOutputList(int targetMonth, List<Person> personList) {
        return personList.stream()
                    .filter(personInMonth -> (personInMonth.getDateOfBirth().getMonth().getValue()==targetMonth))
                    .sorted(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName))
                    .collect(Collectors.toList());
    }

    private static void fillListOfPersons(List<String> stringPersonList, List<Person> personList) {
        for (String member:stringPersonList){
            List listOfPersonsData= Stream.of(member.split(","))
                    .map(fields -> new String(fields))
                    .collect(Collectors.toList());
            Person person=new Person(valueOf(listOfPersonsData.get(0)),valueOf(listOfPersonsData.get(1)),stringToDate(valueOf((listOfPersonsData.get(2)))));
            personList.add(person);
        }
    }

    private static LocalDate stringToDate(String stringDate) throws DateTimeParseException {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate date = LocalDate.parse(stringDate, dateFormatter);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static List<String> readPersonInfoFromFile(List<String> stringPersonList,String inputFilePath) {
        try {
            Scanner fileReader = new Scanner(new FileReader(inputFilePath));
            fileReader.useDelimiter(",");
            while (fileReader.hasNextLine()) {
                stringPersonList.add(fileReader.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input not successful!");
        }
        return stringPersonList;
    }

    public static int userInputNr() {                                                                                       //Metoda de citire Nr de la tastatura
        Scanner input = new Scanner(System.in);
        int value = input.nextInt();
        return value;
    }
}
