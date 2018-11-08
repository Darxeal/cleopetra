package dk.aau.cs.ds306e18.tournament;

import dk.aau.cs.ds306e18.tournament.model.*;
import dk.aau.cs.ds306e18.tournament.model.format.Format;
import dk.aau.cs.ds306e18.tournament.model.match.Match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestUtilities {

    private static final ArrayList<String> botNames = new ArrayList<String>(Arrays.asList(
            "Boten Anna", "JoeyBot", "MightyBot", "2DayHackBot", "AdversityBot", "Air Bud",
            "Atba2", "Beast from the East", "BeepBoop", "Botimus Prime", "Brick",
            "CunningBot", "Dweller", "Gosling", "Defending!"));

    private static final ArrayList<String> devNames = new ArrayList<>(Arrays.asList(
            "Mathias", "Mikkel", "Ali", "Nicolai", "Falke", "Chris", "Goose", "Tarehart",
            "DTracers"));

    private static final ArrayList<String> teamNames = new ArrayList<>(Arrays.asList(
            "compLexity", "FlipSid3", "Fnatic", "Method", "Team Dignitas", "Team Secret",
            "We Dem Girlz", "Allegiance", "Cloud9", "Evil Geniuses", "G2", "Ghost Gaming",
            "NRG", "Rouge"));

    /** Generates a single bot.
     *  @return a bot. */
    public static Bot generateBot(){
        Random rand = new Random();
        return new Bot(botNames.get(rand.nextInt(botNames.size())),
                devNames.get((rand.nextInt(devNames.size()))), null); //TODO should create path somehow
    }

    /** Generates an arrayList with the requested number of bots.
     * @param numberOfBots the desired number of bots.
     * @return an arrayList containing the requested number of bots. */
    public static ArrayList<Bot> generateBots(int numberOfBots){

        if(numberOfBots < 0)
            throw new IllegalArgumentException();

        Random rand = new Random();
        ArrayList<Bot> bots = new ArrayList<>();

        for(int i = 0; i < numberOfBots; i++)
            bots.add(generateBot());

        return bots;
    }

    /** Generates a team with the given team size.
     * @param teamSize the requested team size.
     * @return a team with the requested number of members. */
    public static Team generateTeam(int teamSize){
        Random rand = new Random();
        int seedValue = rand.nextInt(100);
        return generateTeam(teamSize,seedValue);
    }

    /** Generates a team with the given team size and seed.
     * @param teamSize the requested team size.
     * @param seedValue the teams seed
     * @return a team with the requested number of members with the specified seed. */
    public static Team generateTeam(int teamSize, int seedValue){

        if(teamSize < 0)
            throw new IllegalArgumentException();

        Random rand = new Random();

        return new Team(teamNames.get(rand.nextInt(teamNames.size())), generateBots(teamSize), seedValue, "");
    }

    /** Generates an arrayList with the requested number of teams containing the requested number of bots.
     * @param numberOfTeams the requested number of teams.
     * @param teamSize the requested number of bots on each team.
     * @return an arrayList containing the requested number of teams. */
    public static ArrayList<Team> generateTeams(int numberOfTeams, int teamSize){

        if(numberOfTeams < 0)
            throw new IllegalArgumentException();

        ArrayList<Team> teams = new ArrayList<>();

        for(int i = 0; i < numberOfTeams; i++){
            teams.add(generateTeam(teamSize));
        }

        return teams;
    }

    /** @return the given x factored. x! */
    public static int factorial(int x){

        int result = 0;

        for(int i = x; i > 0; i--)
            result += i;

        return result;
    }

    /** sets all upcoming matches in the given swissStage to have been played.*/
    public static void setAllMatchesPlayed(Format stage) {

        //Set all matches to played
        List<Match> matches = stage.getUpcomingMatches();
        for (Match match : matches) {
            match.setHasBeenPlayed(true);
        }
    }

    /** Generates an arrayList with the requested number of teams containing the requested number of bots with ascending seed values.
     * @param numberOfTeams the requested number of teams.
     * @param teamSize the requested number of bots on each team.
     * @return an arrayList containing the requested number of teams ascending seed values. */
    public static ArrayList<Team> generateSeededTeams(int numberOfTeams, int teamSize){

        if(numberOfTeams < 0)
            throw new IllegalArgumentException();

        ArrayList<Team> teams = new ArrayList<>();

        for(int i = 1; i <= numberOfTeams; i++){
            teams.add(generateTeam(teamSize, i));
        }

        return teams;
    }
}
