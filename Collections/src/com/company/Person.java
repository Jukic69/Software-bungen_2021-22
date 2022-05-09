package com.company;

public class Person implements Comparable<Person>{
    private String name;
    private String vorname;
    private int age;

    public Person(String name, String vorname) {
        this.name = name;
        this.vorname = vorname;
    }

    public Person(String name, String vorname, int age) {
        this.name = name;
        this.vorname = vorname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //Gleich, weil Objektreferenz gleich ist.
        //Wenn übergebenes Objekt null ist -> nicht gleich
        // + überprüfen, ob Klassen nicht gleich sind.
        if (o == null || getClass() != o.getClass()) return false;

        //cast auf Person, da o eine Person ist!
        Person person = (Person) o;

        //überprüfe auf Gleichheit...
        if (getAge() != person.getAge()) return false;
        if (!getName().equals(person.getName())) return false;
        return getVorname().equals(person.getVorname());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getVorname().hashCode();
        result = 31 * result + getAge();
        return result;
    }

    @Override
    public int compareTo(Person other) {
        //return Wert:
        //0....this == other
        //-1....this < other
        //1....this > other

        int ret;

        if (this == other) ret = 0;
        else if (other == null || getClass() != other.getClass()) ret = -1;
        else {
            ret = this.name.compareTo(other.name);
            if(ret == 0){// nachname gleich
                ret = this.vorname.compareTo(other.vorname);
                if(ret == 0){
                    ret = this.age - other.age;
                }
            }
        }

        return ret;
    }
}
