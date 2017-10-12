package tasks;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import main.Task;

public class DropTask extends Task {


public DropTask(Script script) {
		super(script);
	}

  
  @Override
  public boolean verify() {
    return script.getInventory().isFull();
  }
  
  @Override
  public int execute() {
	  if(script.getSkills().getStatic(Skill.FISHING) >= FinalFishLevel) {
		  script.getInventory().dropAll();
	  }else {
         script.getInventory().dropAllExcept("Small fishing net");
	  }
    return 300;
  }

  @Override
  public String describe() {
    return "Dropping stuff.";
  }
}