package pl.maciejpajak.library;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentLibrary {

    public static void main(String[] args) {
        
        ExecutorService executorService = Executors.newFixedThreadPool(Constants.NO_OF_STUDENTS); 
        try {
            Student[] students = new Student[Constants.NO_OF_STUDENTS];
            Book[] books = new Book[Constants.NO_OF_BOOKS];
            
            for (int i = 0 ; i < books.length ; i++) {
                books[i] = new Book(i + 1);
            }
            
            for (int i = 0 ; i < students.length ; i++) {
                students[i] = new Student(i + 1, books);
                executorService.execute(students[i]);
            }
           
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
             executorService.shutdown();
        }
    }
}
