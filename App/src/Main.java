import ui.UIManager;
import backend.DatabaseManager;

public class Main {

    private static UIManager uiManager;

    public static void main(String[] args) {
        DatabaseManager.connectDB();
        UIManager.createUI();

    }
}
