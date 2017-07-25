package com.everis.alicante.courses.beca.java_starwars.game.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.SpringLayout.Constraints;

import com.everis.alicante.courses.beca.java_starwars.game.abstractclasses.BattleParticipant;
import com.everis.alicante.courses.beca.java_starwars.game.dao.impl.BattleParticipantDAO;
import com.everis.alicante.courses.beca.java_starwars.game.dao.impl.BattleParticipantDAOImpl;
import com.everis.alicante.courses.beca.java_starwars.game.domain.ATAT;
import com.everis.alicante.courses.beca.java_starwars.game.domain.Destructor;
import com.everis.alicante.courses.beca.java_starwars.game.domain.Jedi;
import com.everis.alicante.courses.beca.java_starwars.game.domain.Sith;
import com.everis.alicante.courses.beca.java_starwars.game.domain.TFighter;
import com.everis.alicante.courses.beca.java_starwars.game.domain.XWing;
import com.everis.alicante.courses.beca.java_starwars.game.utils.FactionEnum;

public class BattleControllerImpl implements BattleController {

	@Override
	public void addBP() {
		
		System.out.println("Insert the name of the battle participant");
		Scanner in = new Scanner(System.in);
		String nameParticipant = in.nextLine();
		int idRandom = (int) (Math.random() * 25 + 1);
		String idBP = nameParticipant + String.valueOf(idRandom);

		System.out.println("Choose your battle participant:");
		System.out.println("                               ");
		System.out.println("EMPIRE                    REBEL");
		System.out.println("                               ");
		System.out.println("WARRIOR                 WARRIOR");
		System.out.println("1. SITH                 2. JEDI");
		System.out.println("                               ");
		System.out.println("EARTH MACHINE     EARTH MACHINE");
		System.out.println("3. ATAT           4. DESTRUCTOR");
		System.out.println("                               ");
		System.out.println("AIR MACHINE         AIR MACHINE");
		System.out.println("5. T-FIGHTER        6. X-WING  ");

		Scanner in2 = new Scanner(System.in);
		Integer option = in2.nextInt();
		BattleParticipant bp;

		switch (option) {
		case 1:
			bp = new Sith(idBP, nameParticipant, 300, 300, FactionEnum.EMPIRE);
			break;
		case 2:
			bp = new Jedi(idBP, nameParticipant, 300, 300, FactionEnum.REBEL);
			break;
		case 3:
			bp = new ATAT(idBP, nameParticipant, 500, 500, FactionEnum.EMPIRE);
			break;
		case 4:
			bp = new Destructor(idBP, nameParticipant, 500, 500, FactionEnum.REBEL);
			break;
		case 5:
			bp = new TFighter(idBP, nameParticipant, 700, 700, FactionEnum.EMPIRE);
			break;
		case 6:
			bp = new XWing(idBP, nameParticipant, 700, 700, FactionEnum.REBEL);
			break;
		default:
			System.out.println("ERROR! NOT VALID OPTION");
			break;
		}

	}

	@Override
	public void deleteBP() {
	
	}

	@Override
	public void listBP() throws IOException {
	
		BattleParticipantDAO daoBp = new BattleParticipantDAOImpl();

		Map<String, BattleParticipant> bp = daoBp.readBP();

		Collection<BattleParticipant> collection = bp.values();

		// for (Iterator<BattleParticipant> iterator = collection.iterator();
		// iterator.hasNext();) {
		// BattleParticipant bps = (BattleParticipant) iterator.next();
		//
		// System.out.println(bps.convertToFormatTxt());
		// }

		collection.stream().forEach(bps -> System.out.println(bps.convertToFormatTxt()));

	}

	@Override
	public void listRebels() throws IOException {
		
		BattleParticipantDAO daoBp = new BattleParticipantDAOImpl();

		Map<String, BattleParticipant> bp = daoBp.readBP();
		;

		Collection<BattleParticipant> collection = bp.values();

		// for (Iterator<BattleParticipant> iterator = collection.iterator();
		// iterator.hasNext();) {
		// BattleParticipant bps = (BattleParticipant) iterator.next();
		//
		// if (bps.getFaction().name().equals("REBEL")) {
		//
		// System.out.println(bps.convertToFormatTxt());
		//
		// }
		// }

		collection.stream().filter(a -> a.getFaction().equals("REBEL")).forEach(bps -> System.out.println(bps.convertToFormatTxt()));

	}

	@Override
	public void listEmpire() throws IOException {
		
		BattleParticipantDAO daoBp = new BattleParticipantDAOImpl();

		Map<String, BattleParticipant> bp = daoBp.readBP();
		;

		Collection<BattleParticipant> collection = bp.values();

		// for (Iterator<BattleParticipant> iterator = collection.iterator();
		// iterator.hasNext();) {
		// BattleParticipant bps = (BattleParticipant) iterator.next();
		//
		// if (bps.getFaction().name().equals("EMPIRE")) {
		//
		// System.out.println(bps.convertToFormatTxt());
		// }
		// }

		collection.stream().filter(a -> a.getFaction().equals("EMPIRE")).forEach(bps -> System.out.println(bps.convertToFormatTxt()));

	}

	@Override
	public void play() throws IOException {
		
		List<BattleParticipant> rebels = listRebelsList();
		List<BattleParticipant> empires = listEmpiresList();
		Battle battle = new Battle();
		battle.fight(empires, rebels);
	}

	private List<BattleParticipant> listEmpiresList() throws IOException {
	
		List<BattleParticipant> bps = new ArrayList<>();

		String line;

		File file = new File("src/resources/BP.txt");
		FileReader reader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(reader);

		while ((line = buffer.readLine()) != null) {

			if (line.contains("SITH")) {
				BattleParticipant bpTemp = new Sith(null, null, 300, 300, FactionEnum.REBEL);
				String[] temp = line.split(";");
				bpTemp.setId(temp[0]);
				bpTemp.setParticipantName(temp[1]);
				bps.add(bpTemp);
			} else if (line.contains("ATAT")) {
				BattleParticipant bpTemp = new ATAT(null, null, 300, 300, FactionEnum.REBEL);
				String[] temp = line.split(";");
				bpTemp.setId(temp[0]);
				bpTemp.setParticipantName(temp[1]);
				bps.add(bpTemp);
			} else if (line.contains("TFIGHTER")) {
				BattleParticipant bpTemp = new TFighter(null, null, 300, 300, FactionEnum.REBEL);
				String[] temp = line.split(";");
				bpTemp.setId(temp[0]);
				bpTemp.setParticipantName(temp[1]);
				bps.add(bpTemp);
			}
		}

		reader.close();
		return bps;
	}

	private List<BattleParticipant> listRebelsList() throws IOException {

		List<BattleParticipant> bps = new ArrayList<>();

		String line;

		File file = new File("src/resources/BP.txt");
		FileReader reader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(reader);

		while ((line = buffer.readLine()) != null) {

			if (line.contains("JEDI")) {
				BattleParticipant bpTemp = new Jedi(null, null, 300, 300, FactionEnum.REBEL);
				String[] temp = line.split(";");
				bpTemp.setId(temp[0]);
				bpTemp.setParticipantName(temp[1]);
				bps.add(bpTemp);
			} else if (line.contains("DESTRUCTOR")) {
				BattleParticipant bpTemp = new Destructor(null, null, 300, 300, FactionEnum.REBEL);
				String[] temp = line.split(";");
				bpTemp.setId(temp[0]);
				bpTemp.setParticipantName(temp[1]);
				bps.add(bpTemp);
			} else if (line.contains("XWING")) {
				BattleParticipant bpTemp = new XWing(null, null, 300, 300, FactionEnum.REBEL);
				String[] temp = line.split(";");
				bpTemp.setId(temp[0]);
				bpTemp.setParticipantName(temp[1]);
				bps.add(bpTemp);
			}
		}

		reader.close();
		return bps;
	}

}