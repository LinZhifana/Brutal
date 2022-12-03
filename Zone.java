package game;

import java.util.*;

public class Zone {
    private ZoneName zoneName;

    private ArrayList<Student>[] students = new ArrayList[Players.values().length];

    public Zone(ZoneName zoneName) {
        this.zoneName = zoneName;
        for (int i = 0; i < this.students.length; i++) {
            students[i] = new ArrayList<Student>();
        }
    }

    public void addStudent(Student student, Players p) {
        if (!this.students[p.ordinal()].contains(student)) {
            this.students[p.ordinal()].add(student);
        }
    }

    private void delStudent(Players p) {
        for (Student s : this.students[p.ordinal()]) {
            if (s.isDead()) {
                System.out.println(s.toString());
                System.out.println("这个学生死了");
                this.students[p.ordinal()].remove(s);
            }
        }
    }

    private Student findLowest(Players p) {
        ListIterator<Student> it = students[p.ordinal()].listIterator();
        while (it.hasNext() && it.next().isDead()) ;
        Student ret = it.hasPrevious() ? it.previous() : null;
        while (it.hasNext()) {
            Student tmp = it.next();
            if (!tmp.isDead() && ret.getCreditsECTS() > tmp.getCreditsECTS()) {
                ret = tmp;
            }
        }
        return ret;
    }

    public void fight() {
        for (int i = 0; i < Players.values().length; i++) {
            Collections.sort(this.students[i]);
        }

        int sizeA = this.students[Players.A.ordinal()].size(),
                sizeB = this.students[Players.B.ordinal()].size();
        while (sizeA != 0 && sizeB != 0) {
            int pa = 0, pb = 0;
            while (pa < sizeA && pb < sizeB) {
                boolean isAFirst;
                Student a = this.students[Players.A.ordinal()].get(pa);
                Student b = this.students[Players.B.ordinal()].get(pb);
                if (a.compareTo(b) > 0) {
                    isAFirst = true;
                } else if (a.compareTo(b) < 0) {
                    isAFirst = false;
                } else {
                    isAFirst = (int) Math.round(Math.random()) != 0;
                }
                if (isAFirst) {
                    Student low = findLowest(Players.B);
                    if (!a.isDead())
                    {
                        int pos = this.students[Players.B.ordinal()].indexOf(low);
                        low.setCreditsECTS( low.getCreditsECTS() + a.act(low) );
                        this.students[Players.B.ordinal()].set(pos,low);
                    }
                    pa++;
                } else {
                    Student low = findLowest(Players.A);
                    if (!a.isDead())
                    {
                        int pos = this.students[Players.A.ordinal()].indexOf(low);
                        low.setCreditsECTS( low.getCreditsECTS() + b.act(low) );
                        this.students[Players.A.ordinal()].set(pos,low);
                    }
                    pb++;
                }
                delStudent(Players.A);
                delStudent(Players.B);
            }
            while (pa < sizeA) {
                Student a = this.students[Players.A.ordinal()].get(pa);
                Student low = findLowest(Players.B);
                if (!a.isDead())
                {
                    int pos = this.students[Players.B.ordinal()].indexOf(low);
                    low.setCreditsECTS( low.getCreditsECTS() + a.act(low) );
                    this.students[Players.B.ordinal()].set(pos,low);
                }
                pa++;
            }
            delStudent(Players.B);
            while (pb < sizeB) {
                Student b = this.students[Players.B.ordinal()].get(pb);
                Student low = findLowest(Players.A);
                {
                    int pos = this.students[Players.A.ordinal()].indexOf(low);
                    low.setCreditsECTS( low.getCreditsECTS() + b.act(low) );
                    this.students[Players.A.ordinal()].set(pos,low);
                }
                pb++;
            }
            delStudent(Players.A);
        }
    }

    public ArrayList<Student> getStudentsOfPlayerA() {
        return this.students[Players.A.ordinal()];
    }

    public ArrayList<Student> getStudentsOfPlayerB() {
        return this.students[Players.B.ordinal()];
    }

    boolean isDone() {
        return this.students[Players.A.ordinal()].isEmpty() || this.students[Players.B.ordinal()].isEmpty();
    }


    public String toString(){
        StringBuffer str = new StringBuffer("Zone : "+this.zoneName.name()+"\n");
        for(int i = 0; i < Players.values().length;i++){
            for (int j = 0; j < this.students[i].size(); j++) {
                str.append(this.students[i].get(j).toString() + "\n");
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {


    }

}
