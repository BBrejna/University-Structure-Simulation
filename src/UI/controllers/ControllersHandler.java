package UI.controllers;

public class ControllersHandler {
    private static final ControllersHandler instance = new ControllersHandler();
    public static ControllersHandler getInstance() {
        return instance;
    }

    private MainController mainController = null;
    private IoController ioController = null;
    private ManageCoursesController manageCoursesController = null;
    private WssController wssController = null;

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainViewController) {
        this.mainController = mainViewController;
    }

    public IoController getIoController() {
        return ioController;
    }

    public void setIoController(IoController ioController) {
        this.ioController = ioController;
    }

    public ManageCoursesController getManageCoursesController() {
        return manageCoursesController;
    }

    public void setManageCoursesController(ManageCoursesController manageCoursesController) {
        this.manageCoursesController = manageCoursesController;
    }

    public WssController getWssController() {
        return wssController;
    }

    public void setWssController(WssController wssController) {
        this.wssController = wssController;
    }
}
