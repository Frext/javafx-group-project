import javafx.scene.Group;
import javafx.scene.Node;

public abstract class Entity {
    protected double x,y;
    protected Group view;

    public Entity(double x, double y, boolean isVisible){
        this.x = x;
        this.y = y;
        this.view = implementView();

        view.setLayoutX(x);
        view.setLayoutY(y);
        view.setVisible(isVisible);
    }

    public abstract void move(double minX, double minY, double maxX, double maxY);

    public abstract Group implementView();

    public Node getView(){
        return view;
    }
}
