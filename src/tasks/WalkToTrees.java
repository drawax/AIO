package tasks;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import main.Task;

public class WalkToTrees extends Task {

	Area TREESAREA = new Area(3123, 3207, 3136, 3220);
	
  public WalkToTrees(Script script) {
    super(script);
  }

  @Override
  
   public boolean verify() {
    return 	((script.getSkills().getStatic(Skill.WOODCUTTING) < 15) &&
    		(script.getSkills().getStatic(Skill.FISHING) >= FinalFishLevel) &&
    		(!script.getInventory().isFull())) &&
    		!TREESAREA.contains(script.myPlayer());
    
  }
   

  @Override
  public int execute() {
    script.getWalking().webWalk(TREESAREA);
    return 200;
  }

  @Override
  public String describe() {
    return "Walking to trees";
  }
}
