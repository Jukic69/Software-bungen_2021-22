package com.company;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person person, Person otherPerson) {
        int ret = person.getAge() - otherPerson.getAge();

        if (ret == 0){
            ret = person.getName().compareTo(otherPerson.getName());
            if(ret == 0){
                ret = person.getVorname().compareTo(otherPerson.getVorname());
            }
        }
        return ret;
    }
}
