import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class DateTimeFormatterTest {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 加同步 synchronized 可避免错误
    private synchronized String dateFormat(Date date) {
        return simpleDateFormat.format(date);
    }

    private synchronized Date parseDate(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

    /**
     * 可能产生的错误
     *
     * @throws InterruptedException
     */
    @Test
    public void simpleDateFormatTest() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        System.out.println(parseDate(dateFormat(new Date())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

    // 以下是推荐处理方案
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String dateTimeFormat(LocalDateTime dateTime) {
        return dateTimeFormatter.format(dateTime);
    }

    private LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    @Test
    public void DateTimeFormatterTest() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println(parseDateTime(dateTimeFormat(LocalDateTime.now())));
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
