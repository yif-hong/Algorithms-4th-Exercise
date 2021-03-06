package other.my_handler;

/**
 * Created by Yif on 6/30/2018.
 */
public class Looper {
    private static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    private static Looper sMainLooper;
    IMessageQueue messageQueue;

    public Looper() {
        messageQueue = new MessageQueue(2);
    }

    public static void prepare() {
        if (sThreadLocal.get() != null)
            throw new RuntimeException("Only one Looper may be created per thread");
        sThreadLocal.set(new Looper());
    }

    public static void prepareMainLooper() {
        prepare();
        synchronized (Looper.class) {
            if (sMainLooper != null)
                throw new IllegalStateException("The main Looper has already been prepared");
        }
        sMainLooper = myLooper();
    }

    public static Looper getMainLooper() {
        return sMainLooper;
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        final Looper me = myLooper();
        if (me == null)
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        for (; ; ) {
            Message message = null;
            try {
                message = me.messageQueue.next();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (message != null) {
                message.target.dispatchMessage(message);
            }
        }
    }

    public void quit() {
        messageQueue.quit(false);
    }

    public void quitSafely() {
        messageQueue.quit(true);
    }

}
