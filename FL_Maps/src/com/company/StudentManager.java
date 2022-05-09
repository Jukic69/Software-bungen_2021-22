package com.company;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class StudentManager {
    private Map<String, TreeSet<Student>> students;

    public StudentManager() {
        students = new TreeMap<>();
    }

    //TODO: Klassen hinzufügen
    public void addClass(String className){
        //Klasse nur anlegen, wenn sie zuvor noch nicht existierte!
        if (!students.containsKey(className))
        students.put(className, new TreeSet<>());
    }
    //TODO: Klassen löschen
    public TreeSet<Student> deleteClass(String className){
        return students.remove(className);
    }
    //TODO: Klassen ändern
    public boolean changeClassName(String oldClassName, String newClassName){
        boolean hasChanged = false;

        if (students.containsKey(oldClassName)){
            students.put(newClassName, students.remove(oldClassName));
            hasChanged = true;
        }
        return hasChanged;
    }
    //TODO: Klassen ausgeben
    public void printClasses(){
        for (String s : students.keySet()) {
            System.out.println(s);
        }
    }
    //TODO: Schüler hinzufügen
    public void addStudent(String className, Student student){
        if(students.containsKey(className) && !students.containsValue(student)){
            students.get(className).add(student);
        }
    }
    //TODO: Schüler löschen
    public void deleteStudent(Student student){
        Iterator<TreeSet<Student>>  classIterator = (Iterator<TreeSet<Student>>) students.values();
        while (classIterator.hasNext()){
            TreeSet<Student> classSet = classIterator.next();

            Iterator<Student> studentIterator = classSet.iterator();

            //classSet.removeIf(actStudent -> actStudent.equals(student));
            
            while (studentIterator.hasNext()){
                Student actStudent = studentIterator.next();
                if(actStudent.equals(student))
                    studentIterator.remove();
            }
        }
    }
    //TODO: Schüler ausgeben
    //TODO: Schüler ändern
    //TODO: Sortieren
}
