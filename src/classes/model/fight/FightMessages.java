package classes.model.fight;

import java.util.ArrayList;
import java.util.List;

import classes.model.characters.fighters.students.Student.Character;
import classes.model.characters.fighters.strategies.Strategy;
import classes.model.characters.players.Player.Team;

public class FightMessages {
	
    private List<Integer> actors = new ArrayList<>();
    private List<Integer> recipients = new ArrayList<>();
    private List<Character> actorCharacters = new ArrayList<>();
    private List<Character> recipientCharacters = new ArrayList<>();
    private List<Strategy> strategies = new ArrayList<>();
    private int currentIndex = 0;
    private int size = 0;

	// team1 100-199
	// team2 200-299
	public void addFightMessages(int actor, int recipient, Character actorCharacter,
			Character recipientCharacter, Strategy strategy) {
		// 将参数添加到列表
		actors.add(actor);
		recipients.add(recipient);
		actorCharacters.add(actorCharacter);
		recipientCharacters.add(recipientCharacter);
		strategies.add(strategy);
		size = actors.size();
	}
	
	public void addMessageSet(FightMessages otherFightMessages) {
	    for (int i = 0; i < otherFightMessages.size; i++) {
	        actors.add(otherFightMessages.actors.get(i));
	        recipients.add(otherFightMessages.recipients.get(i));
	        actorCharacters.add(otherFightMessages.actorCharacters.get(i));
	        recipientCharacters.add(otherFightMessages.recipientCharacters.get(i));
	        strategies.add(otherFightMessages.strategies.get(i));
	    }
	    size = actors.size();
	}
	
	public int getActorIndex() {
		return actors.get(currentIndex) % 100;
	}

	public int getRecipientIndex() {
		return recipients.get(currentIndex) % 100;
	}

	public Character getActorCharacter() {
		return actorCharacters.get(currentIndex);
	}

	public Character getRecipientCharacter() {
		return recipientCharacters.get(currentIndex);
	}

	public Strategy getStrategie() {
		return strategies.get(currentIndex);
	}

	public int getCurrentIndex() {
		return currentIndex;
	}
	
	public void next() {
		currentIndex++;
	}
	
	public boolean hasNext() {
		return currentIndex < size;
	}
	
	public Team getActorTeam() {
		return actors.get(currentIndex) / 100 == 1? Team.TEAM1 : Team.TEAM2;
	}
	
	public Team getRecipientTeam() {
		return recipients.get(currentIndex) / 100 == 1? Team.TEAM1 : Team.TEAM2;
	}
}
