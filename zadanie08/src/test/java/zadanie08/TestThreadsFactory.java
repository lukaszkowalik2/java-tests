package zadanie08;

public class TestThreadsFactory implements ThreadsFactory {

    private boolean leftUsed   = false;
    private boolean rightUsed  = false;
    private boolean writeUsed  = false;

    @Override
    public Thread leftReadOnlyThread(Runnable run) {
        if (leftUsed) {
            throw new IllegalStateException("leftReadOnlyThread() can only be called once.");
        }
        leftUsed = true;
        return new Thread(run, "Left-Reader-Thread");
    }

    @Override
    public Thread rightReadOnlyThread(Runnable run) {
        if (rightUsed) {
            throw new IllegalStateException("rightReadOnlyThread() can only be called once.");
        }
        rightUsed = true;
        return new Thread(run, "Right-Reader-Thread");
    }

    @Override
    public Thread writeOnlyThread(Runnable run) {
        if (writeUsed) {
            throw new IllegalStateException("writeOnlyThread() can only be called once.");
        }
        writeUsed = true;
        return new Thread(run, "Writer-Thread");
    }
}
