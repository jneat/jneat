package com.github.jneat.app;

import org.testng.annotations.Test;

/**
 * @author Vladislav Zablotsky
 */
public class CrossLockTest {

    @Test
    public void singleLockTest() {
        String lockId = "lock-id-example";
        CrossLock lockDouble = new CrossLock(lockId);

        assert CrossLock.get(lockId).lock() == true;
        assert lockDouble.lock() == false;

        CrossLock.get(lockId).release();

        assert lockDouble.lock() == true;
        assert CrossLock.get(lockId).lock() == false;

        lockDouble.clear();

        assert CrossLock.get(lockId).lock() == true;
        assert lockDouble.lock() == false;
        assert CrossLock.get(lockId).lock() == true;

        CrossLock.get(lockId).clear();
        lockDouble.clear();
    }

    @Test
    public void multiLockTest() {
        String lock1 = "lock-1-id";
        String lock2 = "lock-2-id";

        CrossLock lock1Obj = new CrossLock(lock1);
        CrossLock lock2Obj = new CrossLock(lock2);

        assert CrossLock.get(lock1).lock() == true;
        assert CrossLock.get(lock2).lock() == true;

        assert lock1Obj.lock() == false;
        assert lock2Obj.lock() == false;

        CrossLock.get(lock1).release();

        assert lock1Obj.lock() == true;
        assert lock2Obj.lock() == false;

        assert CrossLock.get(lock1).lock() == false;

        CrossLock.get(lock2).release();

        assert lock1Obj.lock() == true;
        assert lock2Obj.lock() == true;

        assert CrossLock.get(lock1).lock() == false;
        assert CrossLock.get(lock2).lock() == false;

        lock1Obj.clear();
        lock2Obj.clear();

        assert CrossLock.get(lock1).lock() == true;
        assert CrossLock.get(lock2).lock() == true;

        assert lock1Obj.lock() == false;
        assert lock2Obj.lock() == false;

        CrossLock.get(lock1).clear();
        CrossLock.get(lock2).clear();
        lock1Obj.clear();
        lock2Obj.clear();
    }
}
