package view;

import controller.Main;
import controller.SplashScreenController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Splash Screen view shown while program boot up
 *
 * @see SplashScreenController associated class controller (MVC pattern)
 */
public class SplashScreenView implements View {

    private static SplashScreenView instance;
    private Stage stage;
    private FadeTransition fadeIn, fadeOut;
    private AtomicBoolean fadeInFinished;

    /**
     * Constructor. When called, shows splash screen
     */
    public SplashScreenView() {
        try {
            stage = new Stage();
            /*
            Group root = new Group();
            root.getChildren().add(new ImageView(Main.APP_LOGO)); */
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/splash_screen.fxml"));

            //Load splash screen with fade in effect
            fadeIn = new FadeTransition(Duration.seconds(2), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            //Finish splash with fade out effect
            fadeOut = new FadeTransition(Duration.seconds(2), root);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeInFinished = new AtomicBoolean(false);
            fadeIn.play();

            fadeIn.setOnFinished((event -> {
                fadeInFinished.set(true);
                System.out.println("FINISHED");
            }));

            //After fade out, load actual content
            fadeOut.setOnFinished((e) -> {
                close();
                new MenuView();
            });

            Scene scene = new Scene(root, 687, 720);
            scene.setFill(Color.TRANSPARENT);

            stage.getIcons().add(Main.APP_LOGO);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.setTitle("Hero Manager - Splash Screen");
            stage.show();

            instance = this;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return instance of this class
     */
    public static SplashScreenView getInstance() {
        return instance;
    }

    /**
     * To close splash screen
     */
    public void close() {
        stage.close();
    }

    /**
     * Allows to set loading bar status on splash screen view
     *
     * @param value percent to set (max: 100)
     */
    public void setLoading(int value) {
        SplashScreenController controller = SplashScreenController.getInstance();
        if (value == 0 || value == 100)
            controller.loadingProgress.setVisible(false);
        else {
            controller.loadingProgress.setVisible(true);
            controller.loadingProgress.setProgress(value / 100.0);
        }

    }

    /**
     * Call fade out animation and open Menu view when finished
     */
    public void openMenu() {
        new Thread(() -> {
            System.out.println("A");
            while (!fadeInFinished.get()) ;
            System.out.println("B");
            fadeOut.play();
        }).start();
    }
}
