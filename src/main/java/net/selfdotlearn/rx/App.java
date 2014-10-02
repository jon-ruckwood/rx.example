package net.selfdotlearn.rx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import rx.Observable;
import rx.subscriptions.Subscriptions;

public class App {

    public static void main(String[] args) {
        new App().run();
    }

    public void run() {
        final Gson gson = new GsonBuilder().create();

        items().map(json -> gson.fromJson(json, Item.class))
                   .subscribe(System.out::println);
    }

    private Observable<String> items() {
        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            final SimpleMessageListenerContainer container = getMessageContainer(new SubscriberReceiver(subscriber));
            container.start();

            Subscriptions.create(() -> container.shutdown());
        });
    }

    private SimpleMessageListenerContainer getMessageContainer(Object receiver) {
        final MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(receiver);

        final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("");
        connectionFactory.setPassword("");
        connectionFactory.setVirtualHost("");

        final String queueName = "";

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

}
