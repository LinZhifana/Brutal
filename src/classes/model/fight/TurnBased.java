package classes.model.fight;

import classes.model.characters.fighters.strategies.Defense;
import classes.model.characters.fighters.strategies.Strategy;
import classes.model.characters.fighters.students.Student;
import classes.model.characters.players.Player.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * Represents the turn based fighting style.
 *
 * @version 1.0
 */
public class TurnBased implements FightingStyle {
	
	private FightMessages fightMessages = new FightMessages();
	
    /**
     * Make the teams fight each other. The fighters of each team are sorted by their initiative. Each team has a probability of 1/2 of starting the fight.
     *
     * @see #teamFight(ArrayList, ArrayList)
     * @param team1 The team of the player 1
     * @param team2 The team of the player 2
     */
	// 这里打架
    public void fight(ArrayList<Student> team1, ArrayList<Student> team2) {
        team1.sort(Comparator.comparing(Student::getInitiative));
        Collections.reverse(team1);
        team2.sort(Comparator.comparing(Student::getInitiative));
        Collections.reverse(team2);
        
    	Iterator<Student> t1 = team1.iterator();
    	Iterator<Student> t2 = team2.iterator();
    	
		Student student1 = t1.next();
		Student student2 = t2.next();

    	while(t1.hasNext()&& t2.hasNext()) {
    		Random rand = new Random();
    		
    		if(student1 .getInitiative() > student2.getInitiative()) {
    			act(student1, Team.TEAM1, team1, team2);
    			student1 = t1.next();
    			
    		}else if(student1 .getInitiative() < student2.getInitiative()){
    			act(student2, Team.TEAM2, team2, team1);
    			student2 = t2.next();
    		}else {
    			// 相同时随机出手
    			if (rand.nextInt() < 0.5) {
    				act(student1, Team.TEAM1, team1, team2);
    				student1 = t1.next();
    			}else {
    				act(student2, Team.TEAM2, team2, team1);
    				student2 = t2.next();
    			}
    		}
    		
    	}
    	while(t1.hasNext()) {
    		act(student1, Team.TEAM1, team1, team2);
			student1 = t1.next();
    	}
    	while(t2.hasNext()) {
    		act(student2, Team.TEAM2, team2, team1);
			student2 = t2.next();
    	}
    }
    /**
     * Get the fighter with the lowest amount of credits from a team.
     *
     * @param team A team of Student
     * @return a Student
     */
    private Student getFighterWithMinCredit(ArrayList<Student> team) {
        return Collections.min(team, Comparator.comparing(Student::getCredits));
    }
    
	private void act(Student actor, Team team, ArrayList<Student> yourTeam, ArrayList<Student> enemyTeam) {
		if (enemyTeam.isEmpty()) {
			return ;
		}
		Strategy strategy = actor.getStrategy();
		strategy = strategy instanceof classes.model.characters.fighters.strategies.Random ? strategy.getStrategy()
				: strategy;
		int aIndex;
		int rIndex;
		if (strategy instanceof Defense) {
			Student recipient = this.getFighterWithMinCredit(yourTeam);
			actor.fight(recipient);
			
			if(team == Team.TEAM1) {
				aIndex = yourTeam.indexOf(actor) + 100;
				rIndex = yourTeam.indexOf(recipient) + 100;
			}else {
				aIndex = yourTeam.indexOf(actor) + 200;
				rIndex = yourTeam.indexOf(recipient) + 200;
			}
			fightMessages.addFightMessages(aIndex, rIndex, actor.getCharacter(), recipient.getCharacter(), strategy);
		} else {
			Student recipient = this.getFighterWithMinCredit(enemyTeam);
			actor.fight(recipient);
			this.killFighter(enemyTeam);
			
			if(team == Team.TEAM1) {
				aIndex = yourTeam.indexOf(actor) + 100;
				rIndex = enemyTeam.indexOf(recipient) + 200;
			}else {
				aIndex = yourTeam.indexOf(actor) + 200;
				rIndex = enemyTeam.indexOf(recipient) + 100;
			}
			fightMessages.addFightMessages(aIndex, rIndex, actor.getCharacter(), recipient.getCharacter(), strategy);
		}
	}

    /**
     * Remove the fighters with a credits lower or equal to 0.
     *
     * @param team a team of Student
     */
    // 删除死人
    public boolean killFighter(ArrayList<Student> team) {
    	Student tmpStudent = getFighterWithMinCredit(team);

    	if(tmpStudent.getCredits()<=0) {
    		team.remove(tmpStudent);
    		return true;
    	}
    	return false;
    }
		
	public FightMessages getFightMessages() {
		return fightMessages;
	}  
    
}
