package seedu.address.ui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * A clock in the form of a dynamic text.
 */
public class TextClock {
    private final Timeline timeline;

    /**
     * Constructor for the text clock object. Sets the text to a label.
     *
     * @param label A given label to set the time value to.
     */
    public TextClock(Label label) {
        timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.now();
            label.setText(localTime.format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * Starts the clock.
     */
    public void play() {
        timeline.play();
    }

    /**
     * Pauses the clock.
     */
    public void pause() {
        timeline.pause();
    }

}
