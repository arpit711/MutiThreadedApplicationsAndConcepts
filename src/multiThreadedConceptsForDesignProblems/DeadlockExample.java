package multiThreadedConceptsForDesignProblems;
/*
deadlock creation in multiThreaded applications Sample code ChatGPT
*/

//Syncronized keyword will the the intrinsic lock of the object it belongs to ...egg..pen class sync method will aquire the pen's lock
//similar happens with paper this will aquire lock of paper and ask for pen lock.

class Pen {
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to write ");
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this);
    }
}


class Paper {
    public synchronized void writeWithPaperAndPen(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and trying to write ");
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this);
    }

}

class Task1 implements Runnable {
    private final Pen pen;

    private final Paper paper;

    public Task1(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
//        wrong version would be like having a lock of pen and asking for a lock of paper wrong method = > synchronized (paper) {
//        public void run() {
//            pen.writeWithPenAndPaper(paper);
//        }

        synchronized (paper) {
            pen.writeWithPenAndPaper(paper);
        }
    }
}

class Task2 implements Runnable {
    private final Pen pen;

    private final Paper paper;

    public Task2(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        synchronized (pen) {
            paper.writeWithPaperAndPen(pen);
        }
    }
}

public class DeadlockExample {

    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();
        Task1 task1 = new Task1(pen, paper);
        Task2 task2 = new Task2(pen, paper);

        Thread t1 = new Thread(new Task1(pen,paper), "Thread-1");
        Thread t2 = new Thread(new Task2(pen, paper), "Thread-2");

        try{
            t1.start();
//            Thread.sleep(1000);
            t2.start();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().isInterrupted();
        }
    }



}
