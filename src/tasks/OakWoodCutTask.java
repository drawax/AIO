package tasks;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import main.Task;
import utils.Sleep;

public class OakWoodCutTask extends Task {

  public OakWoodCutTask(Script script) {
		super(script);
	}

  Area OAKAREA = new Area(3029, 3273, 3044, 3282);
  
  @Override
  public boolean verify() {
    return 	((script.getSkills().getStatic(Skill.WOODCUTTING) >= 15) &&
    		(script.getSkills().getStatic(Skill.WOODCUTTING) <= FinalWCLevel) &&
    		(script.getSkills().getStatic(Skill.FISHING) >= FinalFishLevel) ||
    		(!script.getInventory().isFull())) &&
    		OAKAREA.contains(script.myPlayer());
    
  }

  @Override
  public int execute() {

	  Entity tree = script.getObjects().closest(OAKAREA,"Oak");
		if (tree != null && tree.hasAction("Chop down") && !script.myPlayer().isAnimating()) {
			tree.interact("Chop down");
			
		}
		Sleep.sleepUntil(() -> script.myPlayer().isAnimating() || !tree.exists() || script.inventory.isFull(), 5000);
	
    return 300;
  }

  @Override
  public String describe() {
    return "Woodcutting Oaks.";
  }
}
