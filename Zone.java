package student;

import java.util.*;

public class Zone {
    private ZoneName zoneName;

    private ArrayList<Student>[] students = new ArrayList[Players.NUM_PLAYERS.ordinal()];

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
                this.students[p.ordinal()].remove(s);
                //print
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
        for (int i = 0; i < Players.NUM_PLAYERS.ordinal(); i++) {
            Collections.sort(this.students[i]);
        }
        int sizeA = this.students[Players.A.ordinal()].size(),
                sizeB = this.students[Players.B.ordinal()].size();
        while (sizeA != 0 || sizeB != 0) {
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
                    if (!a.isDead()) a.act(b);
                    pa++;
                } else {
                    Student low = findLowest(Players.A);
                    if (!b.isDead()) b.act(a);
                    pb++;
                }
                delStudent(Players.A);
                delStudent(Players.B);
                while (pa < sizeA) {
                    Student low = findLowest(Players.B);
                    if (!a.isDead()) a.act(b);
                    pa++;
                }
                delStudent(Players.B);
                while (pb < sizeB) {
                    Student low = findLowest(Players.A);
                    if (!b.isDead()) b.act(a);
                    pb++;
                }
                delStudent(Players.A);
            }
        }
    }

    public ArrayList<Student> getStudentsOfPlayerA() {
        return this.students[Players.A.ordinal()];
    }

    public ArrayList<Student> getStudentsOfPlayerB() {
        return this.students[Players.B.ordinal()];
    }

    public static void main(String[] args) {
    }

}
