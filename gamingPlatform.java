package WereWolf;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class gamingPlatform {

	static String[] identity = { "wolf", "wolf", "wolf", "villager", "villager", "villager", "prophet", "witch",
			"hunter" };
	static Scanner scanner = new Scanner(System.in);
	static int choice;
	static int userIdentity;
	static int inspect = 9;
	static int night = 1;
	static int killChose = 9;
	static int drugChose = 9;
	static int exileNum = 9;
	static boolean alive = true;
	static int drugCheck[] = { 1, 1 };
	static int aliveCheck[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	static int voteEachRun[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static void main(String[] args) {

		System.out.println("Welcome to WEREWOLF GAME.");
		System.out.println("If you want to start the game, ENTER 1.\nIf you want to exit the game, ENTER 2.");

		// invalid input case
		boolean again = true;
		while (again) {
			try {
				choice = scanner.nextInt();
				if (choice != 1 && choice != 2) {
					System.err.println("You should enter 1 or 2, please enter again.");
					choice = scanner.nextInt();
				}
				again = false;
				if (again == false) {
					break;
				}

			} catch (NumberFormatException | InputMismatchException e) {
				System.err.println("You should enter a number, please enter again.");
				scanner.next();
				again = true;
			}
		}

		// if the user want to exit
		if (choice == 2) {
			System.exit(0);
		}

		// randomly give identity
		userIdentity = (int) (Math.random() * 9);
		if (userIdentity < 3) {
			wolfView();
		} else if (userIdentity < 6) {
			villagerView();
		} else if (userIdentity == 6) {
			prophetView();
		} else if (userIdentity == 7) {
			witchView();
		} else {
			hunterView();
		}
	}

	private static void wolfView() {
		System.out.println("Your identity is " + userIdentity + "\nYou are a wolf.\n"
				+ "Your teammate is the other two numbers from 0~2.");

		while (alive) {
			// night time
			randomSee();
			kill();
			randomDrug();
			// day time
			dayTime();
			night++;
		}

	}

	private static void villagerView() {
		System.out.println("Your identity is " + userIdentity + "\nYou are a villager.");

		while (alive) {
			// night time
			randomSee();
			randomKill();
			randomDrug();
			// day time
			dayTime();
			night++;
		}

	}

	private static void prophetView() {
		System.out.println("Your identity is " + userIdentity + "\nYou are a prophet.");

		while (alive) {
			// night time
			see();
			randomKill();
			randomDrug();
			// day time
			dayTime();
			night++;
		}

	}

	private static void witchView() {
		System.out.println("Your identity is " + userIdentity + "\nYou are a witch.");

		while (alive) {
			// night time
			randomSee();
			randomKill();
			drug();
			// day time
			dayTime();
			night++;
		}

	}

	private static void hunterView() {
		System.out.println("Your identity is " + userIdentity + "\nYou are a hunter.");

		while (alive) {
			// night time
			randomSee();
			randomKill();
			randomDrug();
			// day time
			dayTime();
			night++;
		}

	}

	private static void kill() {
		Scanner killWho = new Scanner(System.in);
		System.out.println(
				"It's kill time. Please enter the number you choose. If you decide to abstain from killing, please enter 9");
		// invalid input case
		boolean again = true;
		while (again) {
			try {
				killChose = killWho.nextInt();
				if (killChose > 9) {
					System.err.println("You should enter a number from 0 to 9, please enter again.");
					killChose = killWho.nextInt();
				} else if (aliveCheck[killChose] == 0) {
					System.err.println("You cannot kill someone who already dead, please enter again.");
					killChose = killWho.nextInt();
				}
				again = false;
				if (again == false) {
					break;
				}

			} catch (NumberFormatException | InputMismatchException e) {
				System.err.println("You should enter a number, please enter again.");
				killWho.next();
				again = true;
			}
		}

		if (killChose != 9) {
			aliveCheck[killChose] = 2;
		}

	}

	private static void see() {
		Scanner seeWho = new Scanner(System.in);
		System.out.println("It's see time. Please enter the number you choose.");
		// invalid input case
		boolean again = true;
		while (again) {
			try {
				inspect = seeWho.nextInt();
				if (inspect > 8 || inspect < 0) {
					System.err.println("You should enter a number from 0 to 8, please enter again.");
					inspect = seeWho.nextInt();
				} else if (aliveCheck[inspect] == 0) {
					System.err.println("You cannot see on someone who already died.");
					inspect = seeWho.nextInt();
				} else if (aliveCheck[inspect] == 6) {
					System.err.println("You cannot see yourself.");
					inspect = seeWho.nextInt();
				}
				again = false;
				if (again == false) {
					break;
				}

			} catch (NumberFormatException | InputMismatchException e) {
				System.err.println("You should enter a number, please enter again.");
				seeWho.next();
				again = true;
			}
		}

		if (inspect < 3) {
			System.out.println(inspect + " is a wolf.");
		} else {
			System.out.println(inspect + " is good.");
		}

	}

	private static void drug() {
		Scanner drugWho = new Scanner(System.in);
		if (drugCheck[0] == 1) {
			System.out.println("Today " + killChose
					+ " is dead. If you want to save them, please enter 1. If not, please enter 0.");
			// invalid input case
			int use = 2;
			boolean again = true;
			while (again) {
				try {
					use = drugWho.nextInt();
					if (use > 1 || use < 0) {
						System.err.println("You should enter 0 to 1, please enter again.");
						use = drugWho.nextInt();
					}
					again = false;
					if (again == false) {
						break;
					}

				} catch (NumberFormatException | InputMismatchException e) {
					System.err.println("You should enter a number, please enter again.");
					drugWho.next();
					again = true;
				}
			}
			if (use == 1) {
				drugCheck[0] = 0;
				aliveCheck[killChose] = 1;
			}
		}

		if (drugCheck[1] == 1) {
			System.out.println("Today you can choose a alive number to poison, you can enter the number you choose. "
					+ "If you do not want to use the poison, enter 9");
			// invalid input case
			boolean again = true;
			while (again) {
				try {
					drugChose = drugWho.nextInt();
					if (drugChose > 9 || drugChose < 0) {
						System.err.println("You should enter 0 to 9, please enter again.");
						drugChose = drugWho.nextInt();
					} else if (drugChose != 9 || aliveCheck[drugChose] == 0) {
						System.err.println("You cannot use poison on someone who already died.");
						drugChose = drugWho.nextInt();
					}
					again = false;
					if (again == false) {
						break;
					}

				} catch (NumberFormatException | InputMismatchException e) {
					System.err.println("You should enter a number, please enter again.");
					drugWho.next();
					again = true;
				}
			}

			if (drugChose >= 0 && drugChose <= 8) {
				drugCheck[1] = 0;
				aliveCheck[drugChose] = 0;
			}
		}
		aliveCheck[killChose] = 0;

		if (drugChose == 8) {
			aliveCheck[drugChose] = 2;
		}
	}

	private static void randomKill() {
		killChose = (int) (Math.random() * 9);
		while (aliveCheck[killChose] == 0) {
			killChose = (int) (Math.random() * 9);
		}

		aliveCheck[killChose] = 2;

	}

	private static void randomSee() {
		inspect = (int) (Math.random() * 9);
		while (aliveCheck[inspect] == 0 || aliveCheck[inspect] == 6) {
			inspect = (int) (Math.random() * 9);
		}
	}

	private static void randomDrug() {
		if (drugCheck[0] == 1) {
			int use = (int) (Math.random() * 2);
			if (use == 1) {
				drugCheck[0] = 0;
				aliveCheck[killChose] = 1;
			}
		}

		if (drugCheck[1] == 1) {
			drugChose = (int) (Math.random() * 10);
			while (drugChose != 9 && aliveCheck[drugChose] == 0) {
				drugChose = (int) (Math.random() * 10);
			}
			if (drugChose != 9) {
				drugCheck[1] = 0;
				aliveCheck[drugChose] = 0;
			}
		}
		aliveCheck[killChose] = 0;
		if (drugChose == 8) {
			aliveCheck[drugChose] = 2;
		}
	}

	private static void dayTime() {
		System.out.println("Today is " + night + " day.");
		shoot();
		prophetInfo();
		deadInfo();

		if (checkAlive(userIdentity) == false) {
			System.out.println("You are dead.");
			alive = false;
			System.exit(0);
		}

		if (checkContinue() == false) {
			System.out.println("The game ends.");
			int whichWin = checkWin();
			if (whichWin == 0) {
				System.out.println("Village wins.");
			} else {
				System.out.println("Wolf wins.");
			}
			alive = false;
			System.exit(0);
		}

		vote();
		shoot();

		if (checkContinue() == false) {
			System.out.println("The game ends.");
			int whichWin = checkWin();
			if (whichWin == 0) {
				System.out.println("Village wins.");
			} else {
				System.out.println("Wolf wins.");
			}
			alive = false;
			System.exit(0);
		}
	}

	private static void shoot() {
		if (userIdentity != 8) {
			randomShoot();
		} else {
			if (aliveCheck[8] == 0) {
				int take = 10;
				Scanner shootWho = new Scanner(System.in);
				System.out.println(
						"It's shoot time. Please enter the number you choose. If you do not want to shoot, enter 9");
				// invalid input case
				boolean again = true;
				while (again) {
					try {
						take = shootWho.nextInt();
						if (take > 9 || take < 0) {
							System.err.println("You should enter a number from 0 to 9, please enter again.");
							take = shootWho.nextInt();
						} else if (take != 9 && aliveCheck[take] == 0) {
							System.err.println("You cannot take on someone who already died.");
							take = shootWho.nextInt();
						}
						again = false;
						if (again == false) {
							break;
						}

					} catch (NumberFormatException | InputMismatchException e) {
						System.err.println("You should enter a number, please enter again.");
						shootWho.next();
						again = true;
					}
				}

				if (take != 9) {
					System.out.println("The hunter shoots " + take);
					aliveCheck[take] = 0;
				} else {
					System.out.println("The hunter gives up shooting.");
				}

			}

			if (aliveCheck[8] == 2) {
				aliveCheck[8] = 0;
			}
		}
	}

	private static void randomShoot() {
		if (aliveCheck[8] == 0) {
			int take = (int) (Math.random() * 9);
			while (aliveCheck[take] == 0) {
				take = (int) (Math.random() * 9);
			}
			System.out.println("The hunter choose to shoot " + take);
			aliveCheck[take] = 0;
		}

		if (aliveCheck[8] == 2) {
			aliveCheck[8] = 0;
		}

	}

	private static void prophetInfo() {
		if (aliveCheck[6] != 0) {
			System.out.println("The prophet is 6.");
			if (inspect < 3) {
				System.out.println("The prophet finds out " + inspect + " is a wolf.");
			} else {
				System.out.println("The prophet finds out " + inspect + " is good.");
			}
		}
	}

	private static void deadInfo() {
		if (killChose == drugChose && killChose != 9) {
			System.out.println(killChose + " is dead.");
		} else if (killChose == drugChose && killChose == 9) {
			System.out.println("None is dead.");
		} else if (killChose != drugChose && killChose == 9) {
			System.out.println(drugChose + " is dead.");
		} else if (killChose != drugChose && drugChose == 9) {
			System.out.println(killChose + " is dead.");
		} else {
			System.out.println(killChose + " and " + drugChose + " are dead.");
		}
	}

	private static boolean checkAlive(int myIdentity) {
		if (aliveCheck[myIdentity] == 0) {
			return false;
		}
		return true;
	}

	private static boolean checkContinue() {
		for (int i = 0; i < 9; i += 3) {
			if (!checkAlive(i) && !checkAlive(i + 1) && !checkAlive(i + 2)) {
				return false;
			}
		}
		return true;
	}

	private static int checkWin() {
		if (!checkAlive(0) && !checkAlive(1) && !checkAlive(2)) {
			return 0;
		}
		return 1;
	}

	private static void vote() {
		Scanner vote = new Scanner(System.in);
		int vt = 10;
		System.out.println(
				"It's vote time. Please enter the number you choose. If you decide to abstain from vote, please enter 9");
		// invalid input case
		boolean again = true;
		while (again) {
			try {
				vt = scanner.nextInt();
				if (vt > 9) {
					System.err.println("You should enter a number from 0 to 9, please enter again.");
					vt = scanner.nextInt();
				}
				again = false;
				if (again == false) {
					break;
				}

			} catch (NumberFormatException | InputMismatchException e) {
				System.err.println("You should enter a number, please enter again.");
				scanner.next();
				again = true;
			}
		}
		if (vt != 9) {
			voteEachRun[vt]++;
		}

		exile();
		resetInspect();
		resetKill();
		resetVote();
		resetExile();
	}

	private static void exile() {
		if (inspect < 3) {
			exileNum = inspect;
		} else {
			exileNum = (int) (Math.random() * 9);
			while (aliveCheck[exileNum] == 0) {
				exileNum = (int) (Math.random() * 9);
			}
		}
		System.out.println(exileNum + " is exiled.");
		aliveCheck[exileNum] = 0;
	}

	private static void resetInspect() {
		inspect = 9;
	}

	private static void resetKill() {
		killChose = 9;
	}

	private static void resetVote() {
		for (int i = 0; i < voteEachRun.length; i++) {
			voteEachRun[i] = 0;
		}
	}

	private static void resetExile() {
		exileNum = 9;
	}

}
