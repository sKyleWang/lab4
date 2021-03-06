package app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import app.Flamingo;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import pkgCore.Action;
import pkgCore.GamePlay;
import pkgCore.Player;
import pkgCore.Table;
import pkgEnum.eAction;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;

public class BlackJackController implements Initializable {

	private Flamingo FlamingoGame;
	@FXML private Label lblTopName;
	@FXML private Label lblBottomName;
	@FXML private Button btnTop;
	@FXML private Button btnBottom;
	

	@FXML
	private BorderPane mainScreen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setMainApp(Flamingo FlamingoGame) {
		this.FlamingoGame = FlamingoGame;
	}

	@FXML
	public void btnSitLeave_Click(ActionEvent event) {
		Button btn = (Button) event.getSource();

		Action act = new Action();

		act.setAction(btn.getText().equals("Sit")  ? eAction.Sit : eAction.Leave);
		Player p = FlamingoGame.getAppPlayer();
		if (btn.getId().equals("btnTop")) {
			p.setiPlayerPosition(2);

		} else if (btn.getId().equals("btnBottom")) {
			p.setiPlayerPosition(0);
		}

		act.setPlayer(FlamingoGame.getAppPlayer());
		FlamingoGame.SendMessageToClient(act);

		// TODO: Implement this. Create a new 'Action', send the 'Sit' or 'Leave' action
		// to the Hub.

	}

	public void HandleTableState(Table t) {
		Button btn1=null;
		Button btn2=null;
		Label lbl=null;

		for (Player p: t.GetTablePlayers())
		{
			switch (p.getiPlayerPosition())
			{
			case 0:
				btn1=btnTop;
				btn2=btnBottom;
				lbl=lblBottomName;
				break;
			case 2:
				btn2=btnTop;
				btn1=btnBottom;
				lbl=lblTopName;
				break;
			}
			btn1.setVisible(!p.getPlayerID().equals(FlamingoGame.getAppPlayer().getPlayerID()));
			lbl.setText(lbl.getText().equals(p.getPlayerName())?"":p.getPlayerName());
			btn2.setText(btn2.getText().equals("Leave")?"Sit":"Leave");
		}
		
		//	How to handle button text & visibility
		//btnTop.setText("Leave");
		//btnTop.setVisible(false);
		// TODO: Implement this.
	}

	public void HandleGameState(GamePlay gp) {

		// Coming Soon....!
	}

}
