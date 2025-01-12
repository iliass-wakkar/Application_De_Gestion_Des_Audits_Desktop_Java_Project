package utils;

import java.io.*;
import java.util.Scanner;

interface IInputOutputPersonHandler {
void readValue(Person person);
void writeValue(Person person);
}

class inputOutputPersonConsoleHandler implements IInputOutputPersonHandler {
    Scanner sc = new Scanner(System.in);
    @Override
    public void readValue(Person person) {
        System.out.println("Enter the name of the person: ");
        person.setName(sc.nextLine());
        System.out.println("Enter the age of the person: ");
        person.setAge(sc.nextInt());
    }

    @Override
    public void writeValue(Person person) {
        System.out.println("the name: " + person.getName());
        System.out.println("the age: " + person.getAge());
    }

}
class InputOutputPersonFileHandler implements IInputOutputPersonHandler {
    private String filePath=System.getProperty("user.dir") + File.separator + "Data" + File.separator + "persons.txt";;


    @Override
    public void readValue(Person person) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Reading person data from file: " + filePath);
            String name = reader.readLine();
            int age = Integer.parseInt(reader.readLine());

            person.setName(name);
            person.setAge(age);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    @Override
    public void writeValue(Person person) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            System.out.println("Writing person data to file: " + filePath);
            writer.write(person.getName());
            writer.newLine();
            writer.write(String.valueOf(person.getAge()));
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}


//
class Person{
     private  String name;
     private  int age;
     private IInputOutputPersonHandler iInputOutputPersonHandler;
     Person(IInputOutputPersonHandler iInputOutputPersonHandler) {

         this.name = "";
         this.age = 0;
         this.iInputOutputPersonHandler = iInputOutputPersonHandler;
     }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    Person(String name, int age){
         this.name = name;
         this.age = age;
     }
     void  readPerson(){
          iInputOutputPersonHandler.readValue(this);

     }
     void writePerson(){
         iInputOutputPersonHandler.writeValue(this);
     }


}

class Main{
     public static void main(String[] args) {

//         console handling :
         Person personConsole = new Person( new inputOutputPersonConsoleHandler());
         personConsole.readPerson();
         personConsole.writePerson();


         // file handling :
         Person personFile = new Person( new InputOutputPersonFileHandler());
         personFile.readPerson();
         personFile.writePerson();



     }

}


