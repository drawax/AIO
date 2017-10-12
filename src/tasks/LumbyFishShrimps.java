package tasks;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import main.Task;
import utils.Sleep;

public class LumbyFishShrimps extends Task {

  public LumbyFishShrimps(Script script) {
		super(script);
  }
  
  Area LumbyShrimpSpot = new Area(3237,3145,3245,3157);
  
  @Override
  public boolean verify() {
    return 	((script.getSkills().getStatic(Skill.FISHING) < FinalFishLevel) &&
    		(!script.getInventory().isFull())) &&
    		LumbyShrimpSpot.contains(script.myPlayer());
    
  }

  @Override
  public int execute() {
	if(script.getInventory().contains("Small fishing net")) {
		NPC fishingSpot = script.getNpcs().closest("Fishing spot");
	    if (fishingSpot != null && !script.myPlayer().isAnimating() && script.getMap().canReach(fishingSpot)) {
	    	fishingSpot.interact("Net");
	    }
	    Sleep.sleepUntil(() -> script.inventory.isFull(),5000);
	}
	else {
		GroundItem fishingNet = script.getGroundItems().closest("Small fishing net");
		if (fishingNet != null && script.getMap().canReach(fishingNet)) {
			fishingNet.interact("Take");
		}
	}
    return 300;
  }

  @Override
  public String describe() {
    return "Fishing shrimps.";
  }
}
