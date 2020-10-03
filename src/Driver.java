import controller.ControllerBuilder;
import model.GameEngineImpl;
import view.GameEngineCallbackGUI;

public class Driver {

	public static void main(String[] args) {
		new GameEngineCallbackGUI(new ControllerBuilder(new GameEngineImpl())).show();
	}

}
