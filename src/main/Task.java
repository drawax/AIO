package main;

import org.osbot.rs07.script.Script;

public abstract class Task {
  // The script instance
  protected Script script;

  public static final int FinalFishLevel = 30;
  public static final int FinalWCLevel = 30;
  
  public Task(Script script) {
    this.script = script;
  }

  /**
   * @return if this Task should execute.
   */
  public abstract boolean verify();

  /**
   * Executes this Task.
   *
   * @return sleep time after this task ends.
   */
  public abstract int execute();

  /**
   * @return a description of the current Task.
   */
  public abstract String describe();
}
