public class Viewer {

    public Viewer(){
        Controller controller = new Controller(this);
        Model model = controller.getModel();
        Canvas canvas = new Canvas(model);
    }
}
