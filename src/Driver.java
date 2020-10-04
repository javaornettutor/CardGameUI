import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;

public class Driver {

	public static void main(String[] args) {
		GameEngine engine = new GameEngineImpl();
		GameEngineCallbackGUI uiCallback = new GameEngineCallbackGUI(engine);
		engine.addGameEngineCallback(uiCallback);
		uiCallback.run();
	}

}
