package hammurabi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Hammurabi {

    Scanner scan = new Scanner(System.in);

    public static Integer people = 95;
    public static Integer bushels = 3000;
    public static Integer landOwned = 1000;
    public static Integer valueOfLand = 19;
    public static Integer ratFood = 200;
    public static Integer morePeople = 5;

    public Hammurabi() {
    }

    public static void main(String[] args) {
        runGame();
    }

    public static void runGame() {

//        Integer people = 95;
//        Integer bushels = 3000;
//        Integer landOwned = 1000;
//        Integer valueOfLand = 19;
//        Integer ratFood = 200;
//        Integer morePeople = 5;

        int currentYear = 1;

        Hammurabi ham = new Hammurabi();
        while (currentYear < 11) {

            System.out.println(ham.startOfNewYear(people, bushels, landOwned, valueOfLand, currentYear, morePeople, ratFood));
            landOwned -= ham.acresToSell(landOwned);
            landOwned += ham.acresToBuy(bushels, valueOfLand);
            bushels -= ham.grainToFeed(bushels);
            bushels -= ham.grainToPlant(bushels, landOwned, people);


            // Satisfies the end game condition
            currentYear++;
        }
    }

    public String startOfNewYear(Integer ppl, Integer food, Integer land, Integer value, Integer years, Integer morePpl, Integer ratFood) {
        return "O Great Hammurabi!\n" +
                "You are in year " + years + " of your ten year rule.\n" +
                "In the previous year 0 people starved.\n" +
                "In the previous year 0 people entered the kingdom.\n" +
                "The population is now " + (ppl + morePpl) + ".\n" +
                "We harvested " + food + " bushels at 3 bushels per acre.\n" +
                "Rats destroyed 200 bushels, leaving " + (food - ratFood) + " bushels in storage.\n" +
                "The city owns " + land + " acres of land.\n" +
                "Land is currently worth " + value + " bushels per acre.\n";
    }

    public int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\"" + scan.next() + "\" isn't a number!");
            }
        }
    }

    public Integer acresToSell(Integer ownedLand) {
        while (true) {
            int acresSold = getNumber("How many acres would you like to sell? ");
            if (acresSold > ownedLand) {
                throw new IllegalArgumentException("You may want to reconsider your choice...");
            }
            bushels += acresSold * valueOfLand;
            return acresSold;
        }
    }

    public Integer acresToBuy(Integer food, Integer value){
        while (true){
            int acresBought = getNumber("How many acres would you like to buy? ");
            if ((acresBought * value) > food){
                throw new IllegalArgumentException("You may want to rethink that...");
            }
            bushels -= acresBought * valueOfLand;
            return acresBought;
        }
    }

    public Integer grainToFeed(Integer food) {
        int feed;
        while (true) {
            feed = getNumber("How much food shall your people eat? ");
            if (feed > food) {
                throw new IllegalArgumentException("You don't have enough food for that");
            }
            if (feed < (people  * 20)){
                people -= feed * 20;
            }
            return feed;
        }
    }

    public Integer grainToPlant(Integer food, Integer ownedLand, Integer people){
        int plant;
        while (true){
            plant = getNumber("How much grain should we plant? ");
            if (plant > food){
                throw new IllegalArgumentException("You can't plant what you don't have.");
            }
            return plant;
        }
    }

}