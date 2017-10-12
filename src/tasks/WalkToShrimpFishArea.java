package tasks;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import main.Task;
import utils.Sleep;

public class WalkToShrimpFishArea extends Task {

	Area LumbyShrimpSpot = new Area(3237,3145,3245,3157);
	
  public WalkToShrimpFishArea(Script script) {
    super(script);
  }

  @Override
  
   public boolean verify() {
    return 	((script.getSkills().getStatic(Skill.FISHING) <= FinalFishLevel) &&
    		(!script.getInventory().isFull())) &&
    		!LumbyShrimpSpot.contains(script.myPlayer());
    
  }
   

  @Override
  public int execute() {
	Sleep.sleepUntil(() -> script.getEquipment().equip(EquipmentSlot.WEAPON,"Bronze axe"),2000);
    script.getWalking().webWalk(LumbyShrimpSpot);
    return 200;
  }

  @Override
  public String describe() {
    return "Walking to Lumby fishing spot";
  }
}
