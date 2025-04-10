##### Daemon thread. Background threads JVM will not wait for them basically it will terminate them in some time.
##### Intrinsic Locking: these are built into every Object of Java, they are present we can't see them on Synchronized keyword they will be invoked.
##### Explicit Locking: These are more advanced locks. You can control yourself using Lock class java.util.locks.

##### Lock : lock.lock(), lock.tryLock(), lock.unlock() instead of automatic lock in synchronized these are the manual locks that we use. 
1. Lock class Implementation is ReEntrantLock() class 
2. Lock.lock()  --> Thread keeps on waiting till it will get access unlike the thread method (lock.tryLock(1000, TimeUnit.MILLISECONDS))
3. trylock the thread will try once and if not given access then leaves. for 1 second.

# disadwantages of Syncronized;
1. fairness
2. interruptability
3. Blocking
4. Read/write locking(all blocked can't distinguish among them): answer take two locks to differentiate among them.


##### Syncronized keyword will the the intrinsic lock of the object it belongs to ...egg..pen class sync method will aquire the pen's lock
##### similar happens with paper this will aquire lock of paper and ask for pen lock.

##### All threads to Try to aquire the resources in a consistent manner rather than no ordering the sequencing should always be same to avoid any sort of deadlocks.

#### wrong version would be like having a lock of pen and asking for a lock of paper wrong method = > synchronized (paper) {
`public void run() {    
pen.writeWithPenAndPaper(paper);
}`

#### thread Communication ITC inter thread communications.
* Inter Thread communication: (lack of communication
* can make the thread to end in busy waiting state and hence not able to proceed due to deadlock)
* Wait
* notify
* notifyAll()
 
#### Thread safety: guareteen results. no race condition, data corruption not happening.
* lambda expressions.
 

