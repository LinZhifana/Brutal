package student;

import Brutal.Zonename;
import student.Student;

import java.lang.reflect.Array;
import java.util.*;

public class Zone {
    private Zonename zoneName;
    private ArrayList<Student> studentsOfPlayerA;
    private ArrayList<Student> studentsOfPlayerB;

    public void addStudent(Student student, char player) {
        if (player == 'a') {
            if (!this.studentsOfPlayerA.contains(student))
                this.studentsOfPlayerA.add(student);
        } else if (player == 'b') {
            if (!this.studentsOfPlayerB.contains(student))
                this.studentsOfPlayerB.add(student);
        }
    }

    private void delStudent() {
        for(Student s:this.studentsOfPlayerA){
            if(s.isDead()){
                this.studentsOfPlayerA.remove(s);
                //print
            }
        }
        for(Student s:this.studentsOfPlayerB){
            if(s.isDead()){
                this.studentsOfPlayerB.remove(s);
                //print
            }
        }
    }

    private Student findLow(ArrayList<Student> students){
        Student ret = null;
        Iterator<Student> it = students.iterator();
        while(it.hasNext()){
            Student tmp = it.next();
            if(!tmp.isDead()){
                ret = tmp;
                break;
            }
        }
        while(it.hasNext()){
            Student tmp = it.next();
            if(!tmp.isDead() && ret.getCreditsECTS() > tmp.getCreditsECTS()){
                ret = tmp;
            }
        }
        return ret;
    }

    public void fight() {
        Collections.sort(this.studentsOfPlayerA);
        Collections.sort(this.studentsOfPlayerB);
        boolean isStop = false;
        while(! isStop){
            Iterator<Student> itA = this.studentsOfPlayerA.iterator();
            Iterator<Student> itB = this.studentsOfPlayerB.iterator();

            if(!itA.hasNext() || !itB.hasNext()) {
                isStop = true;
            }
            while (itA.hasNext() && itB.hasNext()) {
                boolean isAFirst;
                Student a = itA.next();
                Student b = itB.next();
                if(a.compareTo(b) > 0){
                    isAFirst = true;
                } else if (a.compareTo(b) < 0) {
                    isAFirst = false;
                }else{
                    isAFirst = (int)Math.round(Math.random()) != 0;
                }

                if(isAFirst){
                    Student lowestB = findLow(this.studentsOfPlayerB);
                    a.act(lowestB);
                    Student lowestA = findLow(this.studentsOfPlayerA);
                    b.act(lowestA);
                }else{
                    Student lowestA = findLow(this.studentsOfPlayerA);
                    b.act(lowestA);
                    Student lowestB = findLow(this.studentsOfPlayerB);
                    a.act(lowestB);
                }
                delStudent();
            }
            while (itA.hasNext()){
                Student a = itA.next();
                Student lowestB = findLow(this.studentsOfPlayerB);
                a.act(lowestB);
                delStudent();
            }
            while(itB.hasNext()){
                Student b = itB.next();
                Student lowestA = findLow(this.studentsOfPlayerA);
                b.act(lowestA);
                delStudent();
            }
        }

    }

    public ArrayList<Student> getStudentsOfPlayerA() {
        return studentsOfPlayerA;
    }

    public ArrayList<Student> getStudentsOfPlayerB() {
        return studentsOfPlayerB;
    }

    public static void main(String[] args) {

    }

}
