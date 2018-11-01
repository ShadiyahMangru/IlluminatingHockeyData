import java.util.*;
import java.io.*;
import java.time.*;

///////////////////////////////////////////////////////////////////////////////////////////////////
class HockeyPlayer implements Comparable<HockeyPlayer>{
	//fields
	private String lastName;
	private String position;
	private String birthplace;
	private int [] stats;
	private ArrayList<HockeyPlayer> roster;
	private ArrayList<HockeyPlayer> rosterSorted;
	private ArrayList<String> rosterBP;
	private final String[] HP_CONSTANTS = {"Last Name", "Position", "Birthplace"};
	private final String[] GOALIE_STATS = {"Shots Against", "Goals Against", "Saves"};
	private final String[] SKATER_STATS = {"Goals", "Assists", "Points", "+/-"};
	private static int sortHPBy;
	
	//constructors
	/**
	* no-argument constructor that initilizes the roster and rosterBP fields
	*/
	public HockeyPlayer(){
		setRoster();
		setRosterBP();
	}
	
	/**
	* constructor that initilalizes a hockey player's unique identifiers
	* @param String last name of player
	* @param String position
	* @param String birthplace of player
	* @param int... position-specific stats of player
	*/
	public HockeyPlayer(String lastName, String position, String birthplace, int... stats){
		this.lastName = lastName;
		this.position = position;
		this.birthplace = birthplace;
		this.stats = stats;
	}
	
	/**
	* constructor that initializes sortHPBy, roster, rosterSorted, and rosterBP fields
	* @param int a number that corresponds to a player stats array index value that we are sorting
	*/
	public HockeyPlayer(int sortHPBy){
		setSortHPBy(sortHPBy);
		setRoster();
		setRosterSorted();
		setRosterBP();
	}

	/**
	* constructor that initializes the sortHPBy, roster, rosterSorted, and rosterBP fields
	* @param int a number that corresponds to a player stats array index value that we are sorting
	* @param ArrayList<HockeyPlayer> players that comprise the current roster (potentially a subset of the team roster)
	*/
	public HockeyPlayer(int sortHPBy, ArrayList<HockeyPlayer> player){
		this(sortHPBy);
		setRoster(player); 
		setRosterSorted();
		setRosterBP();
	}

	/**
	* a method that defines how to sort HockeyPlayers numerically by a stat
	* @param HockeyPlayer the player to which we are comparing
	* @return int -1 if original player less than other player, 0 if original and other player equal, 1 if original player greater than other player (in reference to a specific stat)
	*/
	@Override
    	public int compareTo(HockeyPlayer other) {
    		if(this.getStats().length < sortHPBy){
    			return 1;	
    		}
		else if (this.getStats()[sortHPBy-1] < other.getStats()[sortHPBy-1]) {
			return -1;
		}
		else if (this.getStats()[sortHPBy-1] == other.getStats()[sortHPBy-1]) { 
			return 0;
		}
		else{
			return 1;	
		}
    	}
    	
	
	//setters
	public void setLastName(String lastName){
		this.lastName = lastName;	
	}
	
	public void setPosition(String position){
		this.position = position;	
	}
	
	public void setBirthplace(String birthplace){
		this.birthplace = birthplace;	
	}
	
	public void setStats(int... stats){
		this.stats = stats;
	}
	
	public void setRoster(ArrayList<HockeyPlayer> players){
		roster = new ArrayList<HockeyPlayer>(players);	
	}
	
	public void setRoster(){
		roster = new ArrayList<HockeyPlayer>();
		roster.add(new HockeyPlayer("Ovechkin", "Forward, LW", "Russia", 49, 38, 87, 3, 0));
		roster.add(new HockeyPlayer("Vrana", "Forward, LW", "Czech Republic", 13, 14, 27, 2, 1));
		roster.add(new HockeyPlayer("Gersich", "Forward, LW", "USA", 0, 1, 1, -1, 1));
		roster.add(new HockeyPlayer("Walker", "Forward, LW", "Wales", 1, 0, 1, 1, 1));
		roster.add(new HockeyPlayer("Burakovsky", "Forward, LW", "Austria", 12, 13, 25, 3, 1));
		roster.add(new HockeyPlayer("Kuznetsov", "Forward, C", "Russia", 27, 56, 83, 3, 1));
		roster.add(new HockeyPlayer("Backstrom", "Forward, C", "Sweden", 21, 50, 71, 5, 1));
		roster.add(new HockeyPlayer("Graovac", "Forward, C", "Canada", 0, 0, 0, -3, 1));
		roster.add(new HockeyPlayer("Boyd", "Forward, C", "USA", 0, 1, 1, 2, 0));
		roster.add(new HockeyPlayer("O'Brien", "Forward, C", "Canada", 0, 0, 0, 0, 1));
		roster.add(new HockeyPlayer("Eller", "Forward, C", "Denmark", 18, 20, 38, -6, 1));
		roster.add(new HockeyPlayer("Stephenson", "Forward, C", "Canada", 6, 12, 18, 13, 1));
		roster.add(new HockeyPlayer("Beagle", "Forward, C", "Canada", 7, 15, 22, 3, 0));
		roster.add(new HockeyPlayer("Oshie", "Forward, RW", "USA", 18, 29, 47, 2, 0));
		roster.add(new HockeyPlayer("Wilson", "Forward, RW", "Canada", 14, 21, 35, 10, 0));
		roster.add(new HockeyPlayer("Connolly", "Forward, RW", "Canada", 15, 12, 27, -6, 0));
		roster.add(new HockeyPlayer("Peluso", "Forward, RW", "Canada", 0, 0, 0, 0, 0));
		roster.add(new HockeyPlayer("Smith-Pelly", "Forward, RW", "Canada", 7, 9, 16, -6, 0));
		roster.add(new HockeyPlayer("Chiasson", "Forward, RW", "Canada", 9, 9, 18, 1, 0));
		roster.add(new HockeyPlayer("Carlson", "Defense", "USA", 15, 53, 68, 0, 0));
		roster.add(new HockeyPlayer("Orlov", "Defense", "Russia", 10, 21, 31, 10, 1));
		roster.add(new HockeyPlayer("Niskanen", "Defense", "USA", 7, 22, 29, 24, 0));
		roster.add(new HockeyPlayer("Djoos", "Defense", "Sweden", 3, 11, 14, 13, 1));
		roster.add(new HockeyPlayer("Bowey", "Defense", "Canada", 0, 12, 12, -3, 0));
		roster.add(new HockeyPlayer("Orpik", "Defense", "USA", 0, 10, 10, -9, 1));
		roster.add(new HockeyPlayer("Chorney", "Defense", "Canada", 1, 3, 4, 8, 1));
		roster.add(new HockeyPlayer("Jerabek", "Defense", "Czech Republic", 1, 3, 4, -1, 1));
		roster.add(new HockeyPlayer("Kempny", "Defense", "Czech Republic", 2, 1, 3, 1, 1));
		roster.add(new HockeyPlayer("Ness", "Defense", "USA", 0, 1, 1, 2, 1));
		roster.add(new HockeyPlayer("Holtby", "Goalie", "Canada", 1648, 153, 1495));
		roster.add(new HockeyPlayer("Grubauer", "Goalie", "Germany", 953, 73, 880));
	}
	
	public void setRosterSorted(){
		rosterSorted = new ArrayList<HockeyPlayer>(getRoster());
		Collections.sort(rosterSorted);
	}
	
	public void setRosterBP(){
	try{
		rosterBP = new ArrayList<String>();
		rosterBP.add(roster.get(0).getBirthplace());
		for(int i = 1; i< roster.size(); i++){
			if(rosterBP.contains(roster.get(i).getBirthplace()) == false){
				rosterBP.add(roster.get(i).getBirthplace());	
			}
		}
		Collections.sort(rosterBP);
	}
	catch(NullPointerException np){
		System.out.println("Null Pointer Exception");	
	}
	catch(Exception e){
		System.out.println("Exception: " + e);	
	}
	}
	
	public void setSortHPBy(int sortHPBy){
		this.sortHPBy = sortHPBy;	
	}
	
	//getters
	public String getLastName(){
		return lastName;	
	}
	
	public String getPosition(){
		return position;	
	}
	
	public String getBirthplace(){
		return birthplace;	
	}
	
	public int[] getStats(){
		return stats;	
	}
	
	public ArrayList<HockeyPlayer> getRoster(){
		return roster;	
	}
	
	public ArrayList<HockeyPlayer> getRosterSorted(){
		return rosterSorted;	
	}
	
	public ArrayList<String> getRosterBP(){
		return rosterBP;	
	}
	
	public String[] getHP_CONSTANTS(){
		return HP_CONSTANTS;	
	}
	
	public String[] getGOALIE_STATS(){
		return GOALIE_STATS;	
	}
	
	public String[] getSKATER_STATS(){
		return SKATER_STATS;	
	}
	
	public int getSortHPBy(){
		return sortHPBy;	
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
class Output{
	//fields
	private BufferedReader reader;
	private HockeyPlayer hp; 
	private ArrayList<HockeyPlayer> justG; 
	private ArrayList<HockeyPlayer> justSk; 
	private final String[] MAIN_MENU_OPTS = {"Display Roster", "Sort Stats", "Query Players"};
	private final String MAIN_TITLE = "WELCOME TO 2017-2018 WASHINGTON CAPITALS' (SOME) REGULAR SEASON STATS WIZARD!";
	
	//constuctor
	/**
	* no-argument constructor that initializes the reader and hp fields
	*/
	public Output(){
		setReader();
		setHP();
	}
	
	//setters
	public void setReader(){
		reader = new BufferedReader(new InputStreamReader(System.in));		
	}
	
	public void setHP(){
		hp = new HockeyPlayer();
		hp.setRosterBP();	
	}
	
	public void setHP(HockeyPlayer hp){
		this.hp = hp;	
	}
	
	public void setJustG(){
		justG = new ArrayList<HockeyPlayer>();
		for(int i=0; i<getHP().getRoster().size(); i++){
			if(getHP().getRoster().get(i).getPosition().length() == 6){
				justG.add(getHP().getRoster().get(i));	
			}
		}
	}
	
	public void setJustSk(){
		justSk = new ArrayList<HockeyPlayer>();
		for(int i=0; i<getHP().getRoster().size(); i++){
			if(getHP().getRoster().get(i).getPosition().length() != 6){
				justSk.add(getHP().getRoster().get(i));	
			}
		}
	}

	//getters
	public BufferedReader getReader(){
		return reader;
	}	
	
	public HockeyPlayer getHP(){
		return hp;	
	}
	
	public ArrayList<HockeyPlayer> getJustG(){
		return justG;	
	}
	
	public ArrayList<HockeyPlayer> getJustSk(){
		return justSk;	
	}
	
	public String[] getMAIN_MENU_OPTS(){
		return MAIN_MENU_OPTS;	
	}
	
	public int userChoice(){
		int userChoice = 0;
		try{
			System.out.print("Enter selection: ");
			userChoice = Integer.parseInt(reader.readLine());	
		}
		catch(Exception e){
			System.out.println("Exception in userChoice method: " + e + "\nTry again!");
			userChoice();
		}
		return userChoice;
	}
	
	public void mainTitle(){
		System.out.println("\n**********************************************************************************");
		System.out.println(MAIN_TITLE);
	}
	
	public void mainMenu(){
		Output output = new Output();
		output.mainTitle();
		int choice = output.userOptions(MAIN_MENU_OPTS);
		if(choice == 1){
			Display display = new Display();
			display.printRosterDetails();
			mainMenu();
		}
		else if(choice == 2){
			Sort sort = new Sort();
			sort.userSortOptions();
		}
		else{
			Query query = new Query();
			query.userQueryOptions();
			mainMenu();
		}
	}
	
	public void printSkaterStats(HockeyPlayer skater){
		System.out.println("Position: " + skater.getPosition() + "\tGoals: " + skater.getStats()[0] + "\tAssists: " + skater.getStats()[1] + "\tPoints: " + skater.getStats()[2] + "\t+/-: " + skater.getStats()[3] + "\t\tName: " + skater.getLastName());	
	}
	
	public void printGoalieStats(HockeyPlayer goalie){
		System.out.println("Position: " + goalie.getPosition() + "\tShots Against: " + goalie.getStats()[0] + "\tGoals Against: " + goalie.getStats()[1] + "\tSaves: " + goalie.getStats()[2] + "\t\tName: " + goalie.getLastName());	
	}
	
	//method to determine which option (from given options), user selects
	public int userOptions(String... OPTIONS){
		int userChc = 0;
		System.out.println("\n**********************************************************************************");
		System.out.println("\nSelect an option: ");
		for(int i = 0; i < OPTIONS.length; i++){
			System.out.println((i+1) + "). " + OPTIONS[i]);	
		}
		System.out.println("\n" + (OPTIONS.length + 1) + "). " + "Exit");
		System.out.println("\n*********************************************");
		userChc = userChoice();
			if(userChc < (OPTIONS.length + 1)){
				System.out.println("  You selected:  " + OPTIONS[userChc - 1]);
				return userChc;
			}
			else if(userChc == (OPTIONS.length + 1)){
				System.out.print("  You selected: EXIT");
				System.exit(0);
			}
		return 1;
	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Display extends Output{
	//fields
	private final String ROSTER_HEADER = "THE 2017-2018 REGULAR SEASON ROSTER AND SOME OF THEIR STATS:\n";
	
	//no-argument constructor
	public Display(){
	}
	
	//ouputs skaters (forward and defense) and goalies and some of their stats in a predetermined order
	public void printRoster(){
		for(HockeyPlayer player : getHP().getRoster()){
			if(player.getStats().length == 5){
				printSkaterStats(player);	
			}
			else{
				printGoalieStats(player);
			}
		}
	}
	
	//outputs all players and some of their stats in a predetermined order, then loads main menu of user options
	public void printRosterDetails(){
		System.out.println("*****************************************************************************");
		System.out.println(ROSTER_HEADER);
		Display display = new Display();
		display.printRoster();
		mainMenu();
	}	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Sort extends Output{
	//fields
	private final String [] sortMainMenu = {"Goalie", "Skater"};
	private final String [] sortGoalieStats = getHP().getGOALIE_STATS();
	private final String [] sortSkaterStats = getHP().getSKATER_STATS();
	
	//constructors
	/**
	* no-argument constructor
	*/
	public Sort(){
	}	

	public int userMinMax(String stat, String minOrMax){
		int userMinMax = 0;
		try{
			System.out.print("Enter " + minOrMax + " " + stat + " to include in sort (this number WILL be included): ");
			userMinMax = Integer.parseInt(getReader().readLine());	
		}
		catch(Exception e){
			System.out.println("Exception in userMinMax method: " + e + "\nTry again!");
			userMinMax(stat, minOrMax);
		}
		return userMinMax;
	}
	
	public String setBound(String boundType, String stat, int statMinMax){
		return "As of " + LocalDate.now() + " " + stat + " " + boundType + " = " + statMinMax;	
	}

	//method to output user-defined sorted(some/all) goalie stats
	public void outputSortedGoalieStat(ArrayList<HockeyPlayer> sortedRoster, int userMin, int userMax, int userChoice){
		for(HockeyPlayer g : sortedRoster){
			try{
				if(g.getStats()[userChoice-1] >= userMin){
					if(g.getStats()[userChoice-1] <= userMax){
						printGoalieStats(g);	
					}
				}	
			}
			catch(Exception e){
				System.out.println("Exception: " + e);	
			}
		}	
	}
	
	//method to output user-defined sorted(some/all) skater stats
	public void outputSortedSkaterStat(ArrayList<HockeyPlayer> sortedRoster, int userMin, int userMax, int userChoice){
		for(HockeyPlayer s : sortedRoster){
			try{	
				if(s.getStats()[userChoice-1] >= userMin){
					if(s.getStats()[userChoice-1] <= userMax){
						printSkaterStats(s);	
					}
				}
			}
			catch(Exception e){
				System.out.println("Exception: " + e);	
			}
		}		
	}
	
	public void outputGoaliesSorted(int userChoice){
		setJustG();
		HockeyPlayer g = new HockeyPlayer(userChoice, getJustG());
		int rosterMin = g.getRosterSorted().get(0).getStats()[userChoice - 1];
		int rosterMax = g.getRosterSorted().get(g.getRosterSorted().size() - 1).getStats()[userChoice-1];
		System.out.println(setBound("MIN", sortGoalieStats[userChoice-1], rosterMin));
		System.out.println(setBound("MAX", sortGoalieStats[userChoice-1], rosterMax));
		int userMin = userMinMax(sortGoalieStats[userChoice-1], "MIN");
		int userMax = userMinMax(sortGoalieStats[userChoice-1], "MAX");
		
		System.out.println("\nCAPS GOALIES with " + userMin + " <= " + sortGoalieStats[userChoice-1] + " <= " + userMax + " are:");
		outputSortedGoalieStat(g.getRosterSorted(), userMin, userMax, userChoice);
	}
	
	public void outputSkatersSorted(int userChoice){
		setJustSk();
		HockeyPlayer s = new HockeyPlayer(userChoice, getJustSk());
		int rosterMin = s.getRosterSorted().get(0).getStats()[userChoice - 1];
		int rosterMax = s.getRosterSorted().get(s.getRosterSorted().size() - 1).getStats()[userChoice-1];
		System.out.println(setBound("MIN", sortSkaterStats[userChoice-1], rosterMin));
		System.out.println(setBound("MAX", sortSkaterStats[userChoice-1], rosterMax));
		int userMin = userMinMax(sortSkaterStats[userChoice-1], "MIN");
		int userMax = userMinMax(sortSkaterStats[userChoice-1], "MAX");
		
		System.out.println("\nCAPS SKATERS with " + userMin + " <= " + sortSkaterStats[userChoice-1] + " <= " + userMax + " are:");
		outputSortedSkaterStat(s.getRosterSorted(), userMin, userMax, userChoice);
	}
	
	//a method that outputs a menu to select type of sorted stats to display on screen
	public void userSortOptions(){
		int sortGoalieOrSkater = userOptions(sortMainMenu);
		int userChoice = 0;
		if(sortGoalieOrSkater == 1){ 
			userChoice = userOptions(sortGoalieStats);
			outputGoaliesSorted(userChoice);
		}
		else{ 
			userChoice = userOptions(sortSkaterStats);
			outputSkatersSorted(userChoice);
		}
		mainMenu();
	}
	
}

///////////////////////////////////////////////////////////////////////////////////////////////////
class Query extends Output{
	//fields
	private final String [] queryMainMenu = {"Birthplace", "Shoots", "Position"};
	private String [] queryBP;
	private final String [] queryShoots = {"R", "L"};
	private final String [] queryPositions = {"Forward", "Defense", "Goalie"}; 
	private final String [] queryForwardPos = {"Forward", "Forward, LW", "Forward, C", "Forward, RW"};
	
	/**
	*no-argument constructor that initializes that queryBP field
	*/
	public Query(){
		setQueryBP();
	}
	
	//setter
	public void setQueryBP(){
		queryBP = getHP().getRosterBP().toArray(new String[0]);
	}
	
	//getters
	public String[] getQueryMainMenu(){
		return queryMainMenu;	
	}
	
	public String[] getQueryBP(){
		return queryBP;
	}
	
	public String[] getQueryShoots(){
		return queryShoots;	
	}
	
	public String[] getQueryPositions(){
		return queryPositions;	
	}
	
	public String[] getQueryForwardPos(){
		return queryForwardPos;	
	}
	
	/**
	generalized method to display query results -- parameters tailor method to a specific value in the queryMainMenu final class var
	*@param queryType -- depends on userChoice from querySubMenu selection
	*@param message -- words specific to current query (e.g., insert 'born' when displaying birthplace results)
	*@param choice -- depends on userChoice from queryMainMenu selection
	*@param queryOpts -- these depend on the Query class array fields
	*/
	public void outputQuery(int queryType, String message, int choice, String... queryOpts){
		System.out.println("\n***********************************************************************");
		System.out.println("RESULTS:\n" + message + " " + queryOpts[queryType] + " are:");	
		int counter = 0;
		for(HockeyPlayer player : getHP().getRoster()){
		boolean conditionMet = false;
			switch(choice){
			case 1: if(player.getBirthplace() == queryOpts[queryType]){
					conditionMet = true;	
				}
				break;
			case 2: if(player.getStats().length == 5){
					if(player.getStats()[4] == queryType){
						conditionMet = true;	
					}
				}
				break;
			case 3: if(player.getPosition().contains(queryOpts[queryType])){
					conditionMet = true;
				}
				break;
			}	
			if(conditionMet){
				System.out.println("\t" + player.getLastName());	
				counter++;
			}
		}
		System.out.println("\nTOTAL: " + counter + " " + message + " " + queryOpts[queryType] + ".");
		System.out.println("***********************************************************************");
	}

	//method that runs a Query 'subMenu' to the main program menu
	public void userQueryOptions(){
		int userChcMain = userOptions(queryMainMenu);	
		int userChc = userChcMain;
		Query query = new Query();
		switch(userChc){ //query sub menu
			case 1: userChc = userOptions(queryBP);
				query.outputQuery(userChc-1, "players born in ", userChcMain, queryBP);
				break;
			case 2: userChc = userOptions(queryShoots);
				query.outputQuery(userChc-1, "skaters who shoot ", userChcMain, queryShoots);
				break;
			case 3: userChc = userOptions(queryPositions);
				if(userChc == 1){
					userChc = userOptions(queryForwardPos);
					query.outputQuery(userChc-1, "", userChcMain, queryForwardPos);
				}
				else{
					query.outputQuery(userChc-1, "", userChcMain, queryPositions);
				}
				break;
			case 4: System.out.print("  You selected: EXIT");
				System.exit(0);
				break;
			default: userQueryOptions(); //reload menu bc invalid selection
				break;	
		}
	}

}

///////////////////////////////////////////////////////////////////////////////////////////////////
public class IlluminateHockeyData{
	//main method
	public static void main(String... args){
		Output output = new Output();
		output.mainMenu();
	}
}