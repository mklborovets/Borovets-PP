package main;

import student.Student;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = createStudentArray();


        System.out.println("Список студетів факультету _: ");
        String faculty = scanner.nextLine();
        printStudentsByFaculty(students, faculty);

        System.out.println("\nСписок студентів, народжених після _ року:");
        int year = scanner.nextInt();
        printStudentsBornAfter(students, year);
        scanner.nextLine(); // Очищення буфера введення

        System.out.println("\nСписок студентів групи _:");
        String group = scanner.nextLine();
        printStudentsByGroup(students, group);


        /*System.out.println("Список студентів факультету IT:");
        printStudentsByFaculty(students, "IT");

        System.out.println("\nСписок студентів, народжених після 1999 року:");
        printStudentsBornAfter(students, 1999);

        System.out.println("\nСписок студентів групи IT-21:");
        printStudentsByGroup(students, "IT-21");
        */

    }

    public static Student[] createStudentArray() {
        return new Student[]{
                new Student(1, "Ivanov", "Ivan", "Ivanovich", "2000-01-01", "Kyiv", "123456789", "IT", 2, "IT-21"),
                new Student(2, "Petrov", "Petro", "Petrovich", "1999-05-12", "Lviv", "987654321", "Law", 3, "LW-22"),
                new Student(3, "Sidorov", "Sidr", "Sidorovich", "2001-07-21", "Odessa", "456123789", "IT", 1, "IT-11"),
                new Student(4, "Koval", "Oksana", "Olegivna", "2002-11-15", "Kyiv", "321654987", "Economy", 1, "EC-12"),
                new Student(5, "Shevchenko", "Olena", "Ivanivna", "2000-03-04", "Kharkiv", "654789321", "Medicine", 4, "MD-41"),
                new Student(6, "Bohdan", "Mykola", "Mikolaiovich", "1998-10-23", "Dnipro", "789654123", "Law", 2, "LW-22"),
                new Student(7, "Kravchenko", "Anna", "Petrivna", "2003-02-14", "Chernihiv", "147258369", "IT", 3, "IT-21"),
                new Student(8, "Zhuk", "Dmytro", "Stepanovich", "2001-09-09", "Ternopil", "258369147", "Economy", 2, "EC-12")

        };
    }

    public static void printStudentsByFaculty(Student[] students, String faculty){
        for(Student student : students){
            if(student.getFaculty().equals(faculty)){
                System.out.println(student);
            }
        }
    }

    public static void printStudentsBornAfter(Student[] students, int year){
        for(Student student : students){
            int birthYear = Integer.parseInt(student.getBirthDate().split("-")[0]);
            if(birthYear>year){
                System.out.println(student);
            }
        }
    }

    public static void printStudentsByGroup(Student[] students, String group){
        for(Student student : students){
            if(student.getGroup().equals(group)){
                System.out.println(student);
            }
        }
    }

}
