import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    private static final int DURATION_SLEEP = 15000;
    private static final int THREAD_SLEEP = 2000;
    private static final int COUNT_THREAD = 4;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable<Integer> helloCallable = () -> {
            final int maxIteration = 5;
            int counter = 0;
            try {
                while (!Thread.currentThread().isInterrupted() && counter < maxIteration) {
                    Thread.sleep(THREAD_SLEEP);
                    counter++;
                    System.out.printf("Я %s. Всем привет!\n", Thread.currentThread().getName());
                }
            } catch (InterruptedException ignored) {
            } finally {
                System.out.printf("%s завершен\n", Thread.currentThread().getName());
            }
            return counter;
        };

        List<Callable<Integer>> callableList = new ArrayList<>();

        // Создаем пул задач в цикле
        for (int i = 0; i < COUNT_THREAD; i++) {
            callableList.add(helloCallable);
        }
        // Запускаем задания
        //Получаем один результат
//      ExecutorService executorService1 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//      Integer count = executorService1.invokeAny(callableList);
//      System.out.printf("Самый быстрый поток выполнился %d раз \n", count);
//      executorService1.shutdown();

        //Запускаем задания
        //Получаем все значения и выводим их
        ExecutorService executorService2 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Integer>> result = executorService2.invokeAll(callableList);
        System.out.println("Результаты:");
        result.forEach((i) -> {
            try {
                System.out.println(i.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService2.shutdown();
    }

}
