package multiThreadedConceptsForDesignProblems;

public class TestSyncronization {
    public static void main(String[] args) throws InterruptedException {
//        Counter counter = new Counter();
//        MyThread t1 = new MyThread(counter);
//        MyThread t2 = new MyThread(counter);
//        t1.setName("thread 1");
//        t1.start();
//        t2.start();
//        System.out.println(t1.getState() + " " + t1.getName());
//        t1.join();
//        System.out.println(t1.getState() + " " + t1.getName());
//        t2.join();
//        System.out.println(counter.getCounter());


/*output of Below code
        * Thread 1 attempting to withdraw the amount50
        Thread 2 attempting to withdraw the amount50
        Thread 1 proceeding with thread withdrawal
        Thread 2 could not acquire lock
        Thread 1 Completed withdrawal
* */
        BankAccount sbi = new BankAccount();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                sbi.withdraw(50);
            }
        };
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        t1.start();
        t2.start();
    }

}
