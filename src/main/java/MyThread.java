public class MyThread extends Thread {
    private final int durationThreadSleep = 2000;
    private static int counter = 0;
    private static final String nameThread = "Поток ";

    public MyThread(ThreadGroup threadGroup) {
        super(threadGroup, nameThread + nextValue());
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(durationThreadSleep);
                System.out.printf("Я %s. Всем привет!\n", getName());
            }
        } catch (InterruptedException err) {

        } finally {
            System.out.printf("%s завершен\n", getName());
        }
    }

    private static int nextValue() {
        return ++counter;
    }
}
