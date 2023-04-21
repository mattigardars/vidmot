package hi.verkefni.vidmot.framkv;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 The {@code ViewSwitcher} class is responsible for switching between different views in a JavaFX application.
 It uses a cache to store loaded views for faster access when switching between views multiple times.
 */
public class ViewSwitcher {

    private static Map<View, Parent> cache = new HashMap<>();

    private static Scene scene;

    /**
     Sets the scene to be used for view switching.
     @param scene the scene to use
     */
    public static void setScene(Scene scene ) {
        ViewSwitcher.scene = scene;
    }

    /**
     Switches to the specified view, using a cached view root if available.
     @param view the view to switch to
     */
    public static void switchTo(View view) {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }

        try {
            Parent root;

            if (cache.containsKey(view)) {
                System.out.println("Loading from cache");
                root = cache.get(view);
            } else {
                System.out.println("Loading from FXML");

                root = FXMLLoader.load(
                        ViewSwitcher.class.getResource(view.getFileName())
                );


                cache.put(view, root);
            }

            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     Switches to the specified view, without using a cached view root.
     @param view the view to switch to
     */
    public static void switchToNoCache(View view) {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));


            Parent root = loader.load();
            scene.setRoot(root);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load view: " + view, ex);
        }
    }
}


