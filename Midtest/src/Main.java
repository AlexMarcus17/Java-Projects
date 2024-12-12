import repo.ResourceRepo;
import service.ResourceService;
import ui.ResourceUI;

public class Main {
    public static void main(String[] args) {
        ResourceRepo repo = new ResourceRepo();
        ResourceService service = new ResourceService(repo);
        ResourceUI ui = new ResourceUI(service);
        ui.run();
    }
}