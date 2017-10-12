package tasks;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import main.Task;

public class WalkToOaks extends Task {

	Area OAKAREA = new Area(3029, 3273, 3044, 3282);
	
  public WalkToOaks(Script script) {
    super(script);
  }

  @Override
  
   public boolean verify() {
    return 	((script.getSkills().getStatic(Skill.WOODCUTTING) >= 15) &&
    		(script.getSkills().getStatic(Skill.FISHING) >= FinalFishLevel) &&
    		(!script.getInventory().isFull())) &&
    		!OAKAREA.contains(script.myPlayer());
    
  }
   

  @Override
  public int execute() {
    script.getWalking().webWalk(OAKAREA);
    return 200;
  }

  @Override
  public String describe() {
    return "Walking to Oaks";
  }
}
