//package seedu.address.ui;
//
//import static org.testfx.api.FxToolkit.registerPrimaryStage;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.testfx.api.FxRobot;
//import org.testfx.assertions.api.Assertions;
//import org.testfx.framework.junit5.ApplicationExtension;
//import org.testfx.framework.junit5.Start;
//
//import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import seedu.address.model.widget.WidgetObject;
//TODO: Implementation
///**
// * GUI unit test for WidgetViewBox. Test done to ensure the integrity of content displayed does not regress in future
// * refactoring.
// */
//@ExtendWith(ApplicationExtension.class)
//public class WidgetViewBoxTest {
//
//    private static final WidgetObject TEST_OBJECT;
//    private static final String FIRST_LINE;
//    private static final String SECOND_LINE;
//    private static final String THIRD_LINE;
//    private static final String FOURTH_LINE;
//    private static final String FIFTH_LINE;
//    private static final String SIXTH_LINE;
//    private static final String SEVENTH_LINE;
//    private static final String EIGHTH_LINE;
//    private static final String NINTH_LINE;
//
//    static {
//
//        // Widget Object
//        TEST_OBJECT = new WidgetObject();
//        FIRST_LINE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit,";
//        SECOND_LINE = "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
//        THIRD_LINE = "Ut enim ad minim veniam,";
//        FOURTH_LINE = "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
//        FIFTH_LINE = "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla"
//                + "pariatur.";
//        SIXTH_LINE = "Excepteur sint occaecat cupidatat non proident,";
//        SEVENTH_LINE = "sunt in culpa qui officia deserunt mollit anim id est laborum.";
//        EIGHTH_LINE = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque"
//                + "laudantium,";
//
//        NINTH_LINE = "totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto"
//                + "beatae vitae dicta sunt explicabo.";
//        TEST_OBJECT.set(FIRST_LINE, SECOND_LINE, THIRD_LINE, FOURTH_LINE, FIFTH_LINE, SIXTH_LINE, SEVENTH_LINE,
//                EIGHTH_LINE, NINTH_LINE);
//    }
//
//    @BeforeAll
//    public static void setupSpecs() throws Exception {
//        if (Boolean.getBoolean("headless")) {
//            System.setProperty("testfx.robot", "glass");
//            System.setProperty("testfx.headless", "true");
//            System.setProperty("prism.order", "sw");
//            System.setProperty("prism.text", "t2k");
//            System.setProperty("java.awt.headless", "true");
//        }
//        registerPrimaryStage();
//    }
//
//    /**
//     * Will be called with {@code @Before} semantics, i. e. before each test method.
//     *
//     * @param stage - Will be injected by the test runner.
//     */
//    @Start
//    private void start(Stage stage) {
//
//        // Stage initialisation
//        WidgetViewBox widgetViewBox = new WidgetViewBox();
//        widgetViewBox.update(TEST_OBJECT);
//        StackPane stackPane = new StackPane();
//        stackPane.getChildren().add(widgetViewBox.getRoot());
//        stage.setScene(new Scene(stackPane, 100, 100));
//        stage.show();
//    }
//
//    /**
//     * Test text contents rendered matches what was passed.
//     *
//     * @param robot - Will be injected by the test runner.
//     */
//    @Test
//    public void update_matchingContent(FxRobot robot) {
//        Assertions.assertThat(robot.lookup("#header").queryLabeled()).hasText(FIRST_LINE);
//        Assertions.assertThat(robot.lookup("#divOne").queryLabeled()).hasText(SECOND_LINE);
//        Assertions.assertThat(robot.lookup("#textOne").queryLabeled()).hasText(THIRD_LINE);
//        Assertions.assertThat(robot.lookup("#divTwo").queryLabeled()).hasText(FOURTH_LINE);
//        Assertions.assertThat(robot.lookup("#textTwo").queryLabeled()).hasText(FIFTH_LINE);
//        Assertions.assertThat(robot.lookup("#textThree").queryLabeled()).hasText(SIXTH_LINE);
//        Assertions.assertThat(robot.lookup("#divThree").queryLabeled()).hasText(SEVENTH_LINE);
//        Assertions.assertThat(robot.lookup("#textFour").queryTextInputControl()).hasText(EIGHTH_LINE);
//        Assertions.assertThat(robot.lookup("#footer").queryLabeled()).hasText(NINTH_LINE);
//    }
//
//}

