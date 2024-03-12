package lock;

public interface DistributeLockClient {

    boolean tryLock(String key, long waitTime) throws InterruptedException;

    void unLock(String key);

}
