public class Main {

    private static final int DURATION_SLEEP = 15000;

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("thread");

        new MyThread(group).start();
        new MyThread(group).start();
        new MyThread(group).start();
        new MyThread(group).start();

        try {
            Thread.sleep(DURATION_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Завершаю все потоки");
            group.interrupt();
        }

    }

}
