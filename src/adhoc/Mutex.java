package adhoc;

import java.util.concurrent.Semaphore;

public class Mutex {
    static Semaphore lock = new Semaphore(1);
}