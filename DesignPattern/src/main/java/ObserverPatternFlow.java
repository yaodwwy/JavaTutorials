import java.util.Arrays;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

/**
 * Publisher和Subscriber简单示例
 *
 * @Link http://chillyc.info/2017/jdk9/Java9-Flow-feature/
 */

class MySubscriber<T> implements Flow.Subscriber<T> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        // 反向控制获取数据个数。
        //代码里两处request(1)都不能丢，否则数据无法正常获取
        subscription.request(1);
    }

    @Override
    public void onNext(Object item) {
        System.out.println("Got : " + item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }
}

class MyFilterProcessor<T, K extends T> extends SubmissionPublisher<K> implements Flow.Processor<T, K> {

    private Function<? super T, Boolean> function;
    private Flow.Subscription subscription;

    public MyFilterProcessor(Function<? super T, Boolean> function) {
        super();
        this.function = function;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        if (!function.apply(item)) {
            submit((K)item);
        }
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}


public class ObserverPatternFlow {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        //Register Subscriber
        MySubscriber<String> subscriber = new MySubscriber<>();
        publisher.subscribe(subscriber);
        //Publish items
        System.out.println("Publishing Items...");
        String[] items = {"1", "x", "2", "x", "3", "x"};
        Arrays.asList(items).stream().forEach(i -> publisher.submit(i));
        // 关闭publisher，没有该函数则Subscriber.onComplete()不会被调用。
        publisher.close();
        // 因为是异步的流处理
        // 所以没能提供同步的接口，可以自己在Subcriber中加入同步策略
        Thread.sleep(20000);
    }
}
class ObserverPatternFlowProcessor {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        //Create Processor and Subscriber
        MyFilterProcessor<String, String> filterProcessor = new MyFilterProcessor<>(s -> s.equals("x"));
        //Register Subscriber
        MySubscriber<String> subscriber = new MySubscriber<>();
        publisher.subscribe(filterProcessor);
        filterProcessor.subscribe(subscriber);

        //Publish items
        System.out.println("Publishing Items...");

        String[] items = {"1", "x", "2", "x", "3", "x"};
        Arrays.asList(items).stream().forEach(i -> publisher.submit(i));
        publisher.close();
        Thread.sleep(2000);
    }
}