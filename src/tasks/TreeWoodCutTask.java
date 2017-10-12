package tasks;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import main.Task;
import utils.Sleep;

public class TreeWoodCutTask extends Task {

  public TreeWoodCutTask(Script script) {
		super(script);
	}

  Area TREESAREA = new Area(3123, 3207, 3136, 3220);
  
  @Override
  public boolean verify() {
    return 	(script.getSkills().getStatic(Skill.WOODCUTTING) < 15 &&
    		(script.getSkills().getStatic(Skill.FISHING) >= FinalFishLevel) ||
    		(!script.getInventory().isFull())) &&
    		TREESAREA.contains(script.myPlayer());
    
  }

  @Override
  public int execute() {

	Entity tree = script.getObjects().closest(TREESAREA,"Tree");
		if (tree != null && tree.hasAction("Chop down") && !script.myPlayer().isAnimating()) {
			tree.interact("Chop down");
			
		}
		Sleep.sleepUntil(() -> script.myPlayer().isAnimating() || !tree.exists() || script.inventory.isFull(), 5000);
	
    return 300;
  }

  @Override
  public String describe() {
    return "Woodcutting Trees.";
  }
}
