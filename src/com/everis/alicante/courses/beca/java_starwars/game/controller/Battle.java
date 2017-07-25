package com.everis.alicante.courses.beca.java_starwars.game.controller;

import java.util.List;

import com.everis.alicante.courses.beca.java_starwars.game.abstractclasses.BattleParticipant;

public class Battle {

	public void fight(List<BattleParticipant> empire, List<BattleParticipant> rebels) {

		Integer countEmpire = 0;
		Integer countRebel = 0;

		for (BattleParticipant battleParticipantEmpire : empire) {

			for (BattleParticipant battleParticipantRebels : rebels) {

				Double fighter1 = battleParticipantEmpire.getHp()
						+ (battleParticipantEmpire.getPower() * Math.random() * 100);
				Double fighter2 = battleParticipantRebels.getHp()
						+ (battleParticipantRebels.getPower() * Math.random() * 100);

				if (fighter1 > fighter2) {

					countEmpire++;

				} else {

					countRebel++;

				}
			}
		}

		if (countEmpire > countRebel) {

			System.out.println("EMPIRE WINNER. EMPIRE FACTION: " + countEmpire + " REBEL FACTION: " + countRebel);

		} else if (countEmpire < countRebel) {

			System.out.println("REBEL WINNER. REBEL FACTION: " + countRebel + " EMPIRE FACTION: " + countEmpire);

		} else {

			System.out.println("DRAW!. REBEL FACTION: " + countRebel + " EMPIRE FACTION: " + countEmpire);
		}
	}

}
