package net.selfdotlearn.rx;

import rx.Subscriber;

import java.nio.charset.Charset;

public class SubscriberReceiver {
    private final Subscriber subscriber;

    public SubscriberReceiver(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void handleMessage(byte[] raw) {
        String message = new String(raw, Charset.forName("UTF-8"));
        subscriber.onNext(message);
    }
}
