package de.themdplays.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import de.themdplays.map.WJMap;
import de.themdplays.map.WizardJumperMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moritz on 16.12.2016.
 */
public class LevelHandler {

    private List<WJMap> userMaps, storyMaps;

    public LevelHandler() {
        userMaps = new ArrayList<WJMap>();
        storyMaps = new ArrayList<WJMap>();
        loadStoryLevels();
        loadUserMaps();
    }

    /**
     * Loads all Game internal maps
     */
    private void loadStoryLevels() {
        Gdx.app.log("INFO", "Loading Story Levels");
        for(FileHandle f : Gdx.files.local("levels").list()) {
            if(f.extension().equalsIgnoreCase("wjm")) {
                storyMaps.add(new WJMap(f, f.nameWithoutExtension()));
                Gdx.app.log("Loaded", f.nameWithoutExtension());
            }
        }
        Gdx.app.log("INFO", "Finished Loading Story Levels");
    }


    /**
     * Loads all user created maps
     */
    private void loadUserMaps() {
        Gdx.app.log("INFO", "Loading User Levels");
        for(FileHandle f : Gdx.files.local("maps").list()) {
            if(f.extension().equalsIgnoreCase("wjm")) {
                userMaps.add(new WJMap(f, f.nameWithoutExtension()));
                Gdx.app.log("Loaded", f.nameWithoutExtension());
            }
        }
        Gdx.app.log("INFO", "Finished Loading User Levels");
    }

    /**
     * @return list of story maps
     */
    public List<WJMap> getStoryMaps() {
        return storyMaps;
    }

    /**
     * @return list of user maps
     */
    public List<WJMap> getUserMaps() {
        return userMaps;
    }
}
