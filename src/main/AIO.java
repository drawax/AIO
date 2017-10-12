package main;

import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import tasks.*;
import utils.PaintAPI;


import java.awt.*;
import java.util.ArrayList;



@ScriptManifest(author = "Tepi", info = "Megbassza anyad te kis geci", name = "AIO_Tepi", version = 0.2, logo = "")
public class AIO extends Script {

	
	private ArrayList<Task> wctasks = new ArrayList<>();
	private ArrayList<Task> fishtasks = new ArrayList<>();
	private long startTime;
	PaintAPI paint;

	
	public final String formatTime(final long ms) {
		long s = ms / 1000, m = s / 60, h = m / 60, d = h / 24;
		s %= 60;
		m %= 60;
		h %= 24;

		return d > 0 ? String.format("%02d:%02d:%02d:%02d", d, h, m, s)
				: h > 0 ? String.format("%02d:%02d:%02d", h, m, s) : String.format("%02d:%02d", m, s);
	}

	public final String formatValue(final long l) {
		return (l > 1_000_000) ? String.format("%.2fm", ((double) l / 1_000_000))
				: (l > 1000) ? String.format("%.1fk", ((double) l / 1000)) : l + "";
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart() {
		paint = new PaintAPI();
		paint.exchangeContext(bot);
		fishtasks.add(new WalkToShrimpFishArea(this));
		fishtasks.add(new LumbyFishShrimps(this));
		fishtasks.add(new DropTask(this));
		wctasks.add(new WalkToTrees(this));
		wctasks.add(new WalkToOaks(this));
		wctasks.add(new DropTask(this));
		wctasks.add(new OakWoodCutTask(this));
		wctasks.add(new TreeWoodCutTask(this));
	    startTime = System.currentTimeMillis();
	}

	private void CheckContMessage() {
        RS2Widget lvlUp = widgets.get(233, 11);
        RS2Widget cont = widgets.get(233, 2);

        if (lvlUp != null) {
            cont.interact(new String[]{"Continue"});
        }

        if (cont != null) {
            cont.interact(new String[]{"Continue"});
        }
    }
	
	@Override
	public int onLoop() throws InterruptedException {
		CheckContMessage();
		if (isItFinished()) {
	           stop(true);
	           return 0;
	    }
		if(getSkills().getStatic(Skill.FISHING) < 30) {
			for (Task task : fishtasks) {
			      if (task.verify())
			        return task.execute();
			}
		}else {
			for (Task task : wctasks) {
			      if (task.verify())
			        return task.execute();
			}
		}
		return 150;
	}
	

	private boolean isItFinished() {
		return getSkills().getStatic(Skill.FISHING) >= 30 && getSkills().getStatic(Skill.WOODCUTTING) >= 30;
    }

	@Override
	public void onExit() {
	}

	@Override
	public void onPaint(Graphics2D g) {
		long timePassed = System.currentTimeMillis() - startTime;
		//g.setBackground(null);
		paint.drawMouse(g);

        

		Font font = new Font("Open Sans", Font.BOLD, 18);
		g.setFont(font);
		g.setColor(new Color(255, 165, 0, 222));
		
		g.drawString("Running time: " + formatTime(timePassed), 10, 275);
		
		
	}

}