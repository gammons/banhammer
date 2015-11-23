package me.grantammons.rogueEngine.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import me.grantammons.rogueEngine.view.input.InputListener;

public class GamePopupMenu implements InputListener {
    private final Skin skin;
    private final Stage stage;
    private Table table;

    public GamePopupMenu(Stage stage) {
        this.stage = stage;
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton textButton = new TextButton("Attack", skin, "default");
        textButton.setWidth(200);
        textButton.setHeight(50);

        TextButton textButton2 = new TextButton("Move", skin, "default");
        textButton2.setWidth(200);
        textButton2.setHeight(50);

        table = new Table();
        table.row();
        table.add(textButton);
        table.row();
        table.add(textButton2);

        table.setX(150);
        table.setY(150);
        table.setVisible(false);

        stage.addActor(table);
    }

    public void render () {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void notify(int direction) {

    }

    @Override
    public void notifyMouseMoved(int screenX, int screenY) {

    }

    @Override
    public void notifyMouseClicked(int screenX, int screenY, int button) {
        if (button == 1) return;
        Vector3 coords = stage.getCamera().unproject(new Vector3(screenX, screenY, 0));
        table.setX(coords.x);
        table.setY(coords.y);
        table.setVisible(true);
    }

    @Override
    public void notifyMouseScrollDown() {

    }

    @Override
    public void notifyMouseScrollUp() {

    }
}
