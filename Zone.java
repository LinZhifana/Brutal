package game;

import java.util.*;

public class Zone {
    private ZoneName zoneName;
    private boolean isOccupied = false;

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
        for (Student s : this.students[gamer.ordinal()]) {
            if (s.isDead()) {
                System.out.println(s.toString());
                System.out.println("这个学生死了");
                this.students[gamer.ordinal()].remove(s);
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
        while (sizeA != 0 && sizeB != 0) {
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
                delStudent(Gamers.A);
                delStudent(Gamers.B);
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
            delStudent(Gamers.B);
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
        }
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

    public Gamers whoWin(){
        Gamers g = null;
        if(this.students[Gamers.A.ordinal()].isEmpty()){
            g = Gamers.B;
        }else if(this.students[Gamers.B.ordinal()].isEmpty()){
            g = Gamers.A;
        }
        return g;
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
            for (int j = 0; j < this.students[i].size(); j++) {
                str.append(this.students[i].get(j).toString() + "\n");
            }
        }
        return str.toString();
    }


}
