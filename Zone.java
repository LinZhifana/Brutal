package game;

import java.util.*;

public class Zone {
    private ZoneName zoneName;
    private boolean isOccupied = false;
    private Gamers owner;
    private ArrayList<Student>[] students = new ArrayList[Gamers.values().length];

    public Zone(ZoneName zoneName) {
        this.zoneName = zoneName;
        for (int i = 0; i < this.students.length; i++) {
            students[i] = new ArrayList<Student>();
        }
    }

    public void addStudent(Student student, Gamers gamer) {
        if (!this.students[gamer.ordinal()].contains(student)) {
            this.students[gamer.ordinal()].add(student);
        }
    }

    private void delStudent(Gamers gamer) {
        Iterator<Student> it = this.students[gamer.ordinal()].iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.isDead()) {
                System.out.println("-------------------------------------------------------------------------------\n");
                System.out.println("Cet étudiant est mort\n" + s + "\n");
                it.remove();
                for (Student k : this.students[gamer.ordinal()]) {
                    System.out.println(k);
                }
            }
        }
    }

    private Student findLowest(Gamers gamer) {
        ListIterator<Student> it = students[gamer.ordinal()].listIterator();
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
        for (int i = 0; i < Gamers.values().length; i++) {
            Collections.sort(this.students[i]);

        }
        int sizeA = this.students[Gamers.A.ordinal()].size(),
                sizeB = this.students[Gamers.B.ordinal()].size();
        int pa = 0, pb = 0;
        while (pa < sizeA && pb < sizeB) {
            boolean isAFirst;
            Student a = this.students[Gamers.A.ordinal()].get(pa);
            Student b = this.students[Gamers.B.ordinal()].get(pb);
            if (a.compareTo(b) > 0) {
                isAFirst = true;
            } else if (a.compareTo(b) < 0) {
                isAFirst = false;
            } else {
                isAFirst = (int) Math.round(Math.random()) != 0;
            }
            if (isAFirst) {
                Student low = findLowest(Gamers.B);
                if (!a.isDead()) {
                    int pos = this.students[Gamers.B.ordinal()].indexOf(low);

                    low.setCreditsECTS(low.getCreditsECTS() + a.act(low));
                    this.students[Gamers.B.ordinal()].set(pos, low);
                }
                pa++;
            } else {
                Student low = findLowest(Gamers.A);
                if (!a.isDead()) {
                    int pos = this.students[Gamers.A.ordinal()].indexOf(low);

                    low.setCreditsECTS(low.getCreditsECTS() + b.act(low));
                    this.students[Gamers.A.ordinal()].set(pos, low);
                }
                pb++;
            }
        }
        while (pa < sizeA) {
            Student a = this.students[Gamers.A.ordinal()].get(pa);
            Student low = findLowest(Gamers.B);
            if (!a.isDead()) {
                int pos = this.students[Gamers.B.ordinal()].indexOf(low);
                low.setCreditsECTS(low.getCreditsECTS() + a.act(low));
                this.students[Gamers.B.ordinal()].set(pos, low);
            }
            pa++;
        }
        while (pb < sizeB) {
            Student b = this.students[Gamers.B.ordinal()].get(pb);
            Student low = findLowest(Gamers.A);
            {
                int pos = this.students[Gamers.A.ordinal()].indexOf(low);

                low.setCreditsECTS(low.getCreditsECTS() + b.act(low));
                this.students[Gamers.A.ordinal()].set(pos, low);
            }
            pb++;
        }
        delStudent(Gamers.A);
        delStudent(Gamers.B);
    }

    public ArrayList<Student> getStudents(Gamers gamer) {
        switch (gamer) {
            case A:
                return this.students[Gamers.A.ordinal()];
            case B:
                return this.students[Gamers.B.ordinal()];
        }
        return null;
    }

    public void setStudents(ArrayList<Student> students, Gamers gamer) {
        switch (gamer) {
            case A:
                this.students[Gamers.A.ordinal()] = students;
                break;
            case B:
                this.students[Gamers.B.ordinal()] = students;
                break;
        }
    }

    public ZoneName getZoneName() {
        return zoneName;
    }

    public boolean isDone() {
        return this.students[Gamers.A.ordinal()].isEmpty() || this.students[Gamers.B.ordinal()].isEmpty();
    }

    public void setOwner() {
        if (this.students[Gamers.A.ordinal()].isEmpty())
            this.owner = Gamers.B;
        else if (this.students[Gamers.B.ordinal()].isEmpty())
            this.owner = Gamers.A;
    }

    public Gamers getOwner() {
        return this.owner;
    }

    public void setOccupied() {
        isOccupied = true;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public String toString() {
        StringBuffer str = new StringBuffer("Zone : " + this.zoneName.name() + "\n");
        for (int i = 0; i < Gamers.values().length; i++) {
            str.append(Gamers.values()[i] + "\n");
            for (int j = 0; j < this.students[i].size(); j++) {
                str.append(this.students[i].get(j).toString() + "\n");
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Zone z1 = new Zone(ZoneName.HI);
        ArrayList<Integer> p1 = new ArrayList<>(Arrays.asList(0, 1, 5, 5, 5, 5, 5));
        ArrayList<Integer> p2 = new ArrayList<>(Arrays.asList(0, 0, 4, 4, 4, 4, 4));
        ArrayList<Integer> p3 = new ArrayList<>(Arrays.asList(0, 1, 6, 6, 6, 6, 6));
        ArrayList<Integer> p4 = new ArrayList<>(Arrays.asList(0, 1, 3, 3, 3, 3, 3));

        Student a1 = new Student(p1);
        Student a2 = new Student(p2);
        Student a3 = new Student(p3);
        Student a4 = new Student(p4);
        Student a5 = new Student(p2);
        Student b2 = new Student(p2);
        Student b1 = new Student(p3);
        Student b3 = new Student(p4);
        Student b4 = new Student(p1);
        Student b5 = new Student(p3);

        z1.addStudent(a1, Gamers.A);
        z1.addStudent(a2, Gamers.A);
        z1.addStudent(a3, Gamers.A);
        //z.addStudent(a4, Gamers.A);
        //z.addStudent(a5, Gamers.A);
        z1.addStudent(b1, Gamers.B);
        z1.addStudent(b2, Gamers.B);
        z1.addStudent(b3, Gamers.B);
        z1.addStudent(b4, Gamers.B);
        //z.addStudent(b5, Gamers.A);
        boolean isDone = false;
        while (isDone) {
            z1.fight();
            System.out.println(z1);
            isDone = z1.isDone();
        }
    }
}
