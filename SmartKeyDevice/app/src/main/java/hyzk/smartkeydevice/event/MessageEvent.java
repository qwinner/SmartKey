package hyzk.smartkeydevice.event;

/**
 * Created by wyq on 2016/6/1.
 */
public class MessageEvent {
    public final int key;
    public final String message;

    public MessageEvent(int key, String message) {
        this.key = key;
        this.message = message;
    }
}
