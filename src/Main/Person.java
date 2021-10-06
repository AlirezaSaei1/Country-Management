package Main;

import Main.Building.*;
import Main.Exceptions.*;

import java.io.Serializable;
import java.util.Scanner;


public class Person implements Serializable {
    static Scanner scan = new Scanner(System.in);
    String name;
    String lastName;
    String dateOfBirth;
    String birthLocation;
    String job;
    String gender;
    double salary;
    public City city;
    public boolean hired;

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    void addPopulation(Person p, City city) {
        city.population.add(p);
        Country.totalPopulation.add(p);
    }

    Person(String name, String lastName, String dateOfBirth, String birthLocation, String job, String gender, double salary, boolean bool, City city) {
        if(name.equals("") || lastName.equals("")){
            throw new InvalidInput();
        }
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.birthLocation = birthLocation;
        this.job = job;
        this.gender = gender;
        this.salary = salary;
        this.hired = bool;
        this.city = city;
        city.cityPopulation++;
        addPopulation(this, city);
    }

    public static void addNewCitizen(City c) {
        boolean isJobless = false;
        String job = "";
        System.out.println("Enter Person Name : ");
        String name = scan.next();
        System.out.println("Enter Person Last Name : ");
        String lastName = scan.next();
        System.out.println("Gender : ");
        String gender = scan.next();
        System.out.println("Enter Date Of Birth : ");
        String date = scan.next();
        System.out.println("Enter Birth Location : ");
        String location = scan.next();
        System.out.println("What is This Person's Job?\n1: Pilot\n2: Captain\n3: Bus Driver\n4: Staff\n5: Locomotives\n6: Jobless");
        int job1 = scan.nextInt();
        while (job1 < 1 || job1 > 6) {
            System.out.println("Invalid Input! try Again:");
            job1 = scan.nextInt();
        }
        if (job1 == 1) {
            job = "Pilot";
        }
        if (job1 == 2) {
            job = "Captain";
        }
        if (job1 == 3) {
            job = "Bus Driver";
        }
        if (job1 == 4) {
            job = "Staff";
        }
        if (job1 == 5) {
            job = "Locomotives";
        }
        if (job1 == 6) {
            job = "Jobless";
            isJobless = true;
        }
        double salary = 0;
        if (!isJobless) {
            System.out.println("How Does This Person Get? ($)");
            salary = scan.nextDouble();
        }

        new Person(name, lastName, date, location, job, gender, salary, false, c);
    }

    public static void Hire(City city) throws NoPopulation {
        System.out.println("Which Job Do You Need From List Below?");
        System.out.println("1: Pilot");
        System.out.println("2: Captain");
        System.out.println("3: Staff");
        System.out.println("4: Locomotives");
        System.out.println("5: Bus Driver");
        int hire = scan.nextInt();
        switch (hire) {
            case 1: {
                int countPopulation = 0;
                if (city.airports.size() != 0) {
                    int counter = 1;
                    for (Person a : city.population) {
                        if (a.job.equals("Pilot") && !a.hired && a.salary <= city.balance) {
                            System.out.println("(" + counter++ + ") Name:" + a.name + "\tLastName:" + a.lastName + "\tSalary: " + a.salary + "$");
                            countPopulation++;
                        }
                    }
                    if (countPopulation == 0) {
                        throw new NoPopulation();
                    }
                    System.out.println("Select One That You Want To Hire : ");
                    int in = scan.nextInt();
                    int count = 1;
                    for (Person ab : city.population) {
                        if (ab.job.equals("Pilot") && !ab.hired) {
                            if (count == in) {
                                Airport.addPilot(ab, city);
                            }
                            count++;
                        }
                    }
                } else {
                    throw new NoAirport();
                }
                break;
            }
            case 2: {
                int countPopulation = 0;
                if (city.harbour.size() != 0) {
                    int counter = 1;
                    for (Person a : city.population) {
                        if (a.job.equals("Captain") && !a.hired && a.salary <= city.balance) {
                            System.out.println("(" + counter++ + ") Name:" + a.name + "\tLastName:" + a.lastName + "\tSalary: " + a.salary + "$");
                            countPopulation++;
                        }
                    }
                    if (countPopulation == 0) {
                        throw new NoPopulation();
                    }
                    System.out.println("Select One You Want To Hire : ");
                    int in = scan.nextInt();
                    int count = 1;
                    for (Person ab : city.population) {
                        if (ab.job.equals("Captain") && !ab.hired) {
                            if (count == in) {
                                Harbor.addCpt(ab, city);
                            }
                            count++;
                        }
                    }
                } else {
                    throw new NoHarbor();
                }
                break;
            }
            case 3: {
                System.out.println("Add Staff To:\n1: Airport\n2: Bus Terminal\n3: Harbor\n4: Railway Station");
                int selection = scan.nextInt();
                int counter = 1;

                if (selection == 1) {
                    if (city.airports.size() != 0) {
                        for (Airport a : city.airports) {
                            System.out.println("(" + counter++ + ")" + "Name: " + a.name + "\tAddress: " + a.address + "Is International: " + a.international);
                        }
                        System.out.println("Select Which Airport to Hire: ");
                        int in = scan.nextInt();
                        int count = 1;
                        int counter1 = 1;
                        int countPopulation = 0;
                        for (Airport ab : city.airports) {
                            if (count == in) {
                                for (Person a : city.population) {
                                    if (a.job.equals("Staff") && !a.hired && a.salary <= city.balance) {
                                        System.out.println("(" + counter1++ + ") Name:" + a.name + "\tLastName:" + a.lastName + "\tSalary: " + a.salary + "$");
                                        countPopulation++;
                                    }
                                }
                                if (countPopulation == 0) {
                                    throw new NoPopulation();
                                }
                                System.out.println("Select One You Want To Hire : ");
                                int in1 = scan.nextInt();
                                int count1 = 1;
                                for (Person ab1 : city.population) {
                                    if (ab1.job.equals("Staff") && !ab1.hired) {
                                        if (count1 == in1) {
                                            ab.ppl.add(ab1);
                                            ab1.hired = true;
                                            break;
                                        }
                                        count1++;
                                    }
                                }
                            }
                            count++;
                        }
                    } else {
                        throw new NoAirport();
                    }
                }
                if (selection == 2) {
                    if (city.busTerminals.size() != 0) {
                        counter = 1;
                        for (BusTerminal a : city.busTerminals) {
                            System.out.println("(" + counter++ + ")" + "Name: " + a.name + "\tAddress: " + a.address);
                        }
                        System.out.println("Select Which Bus terminal to Hire: ");
                        int in = scan.nextInt();
                        int count = 1;
                        int counter1 = 1;
                        int countPopulation = 0;
                        for (BusTerminal ab : city.busTerminals) {
                            if (count == in) {
                                for (Person a : city.population) {
                                    if (a.job.equals("Staff") && !a.hired && a.salary <= city.balance) {
                                        System.out.println("(" + counter1++ + ") Name:" + a.name + "\tLastName:" + a.lastName + "\tSalary: " + a.salary + "$");
                                        countPopulation++;
                                    }
                                }
                                if (countPopulation == 0) {
                                    throw new NoPopulation();
                                }
                                System.out.println("Select One You Want To Hire : ");
                                int in1 = scan.nextInt();
                                int count1 = 1;
                                for (Person ab1 : city.population) {
                                    if (ab1.job.equals("Staff") && !ab1.hired) {
                                        if (count1 == in1) {
                                            ab.ppl.add(ab1);
                                            ab1.hired = true;
                                            break;
                                        }
                                        count1++;
                                    }
                                }
                            }
                            count++;
                        }
                    } else {
                        throw new NoBusTerminal();
                    }
                }
                if (selection == 3) {
                    if (city.harbour.size() != 0) {
                        counter = 1;
                        for (Harbor a : city.harbour) {
                            System.out.println("(" + counter++ + ")" + "Name: " + a.name + "\tAddress: " + a.address);
                        }
                        System.out.println("Select Which Harbor to Hire: ");
                        int in = scan.nextInt();
                        int count = 1;
                        int counter1 = 1;
                        int countPopulation = 0;
                        for (Harbor ab : city.harbour) {
                            if (count == in) {
                                for (Person a : city.population) {
                                    if (a.job.equals("Staff") && !a.hired && a.salary <= city.balance) {
                                        System.out.println("(" + counter1++ + ") Name:" + a.name + "\tLastName:" + a.lastName + "\tSalary: " + a.salary + "$");
                                        countPopulation++;
                                    }
                                }
                                if(countPopulation==0){
                                    throw new NoPopulation();
                                }
                                System.out.println("Select One You Want To Hire : ");
                                int in1 = scan.nextInt();
                                int count1 = 1;
                                for (Person ab1 : city.population) {
                                    if (ab1.job.equals("Staff") && !ab1.hired) {
                                        if (count1 == in1) {
                                            ab.ppl.add(ab1);
                                            ab1.hired = true;
                                            break;
                                        }
                                        count1++;
                                    }
                                }
                            }
                            count++;
                        }
                    } else {
                        throw new NoHarbor();
                    }
                }
                if (selection == 4) {
                    if (city.railwayStations.size() != 0) {
                        counter = 1;
                        for (RailwayStation a : city.railwayStations) {
                            System.out.println("(" + counter++ + ")" + "Name: " + a.name + "\tAddress: " + a.address);
                        }
                        System.out.println("Select Which Railway Station to Hire: ");
                        int in = scan.nextInt();
                        int count = 1;
                        int counter1 = 1;
                        int countPopulation = 0;
                        for (RailwayStation ab : city.railwayStations) {
                            if (count == in) {
                                for (Person a : city.population) {
                                    if (a.job.equals("Staff") && !a.hired && a.salary <= city.balance) {
                                        System.out.println("(" + counter1++ + ") Name:" + a.name + "\tLastName:" + a.lastName + "\tSalary: " + a.salary + "$");
                                        countPopulation++;
                                    }
                                }
                                if(countPopulation==0){
                                    throw new NoPopulation();
                                }
                                System.out.println("Select One You Want To Hire : ");
                                int in1 = scan.nextInt();
                                int count1 = 1;
                                for (Person ab1 : city.population) {
                                    if (ab1.job.equals("Staff") && !ab1.hired) {
                                        if (count1 == in1) {
                                            ab.ppl.add(ab1);
                                            ab1.hired = true;
                                            break;
                                        }
                                        count1++;
                                    }
                                }
                            }
                            count++;
                        }
                    } else {
                        throw new NoRailwayStation();
                    }
                }
                break;
            }
            case 4: {
                int countPopulation = 0;
                if (city.railwayStations.size() != 0) {
                    int counter = 1;
                    for (Person a : city.population) {
                        if (a.job.equals("Locomotives") && !a.hired && a.salary <= city.balance) {
                            System.out.println("(" + counter++ + ") Name:" + a.name + "\tLastName:" + a.lastName + "\tSalary: " + a.salary + "$");
                            countPopulation++;
                        }
                    }
                    if(countPopulation==0){
                        throw new NoPopulation();
                    }
                    System.out.println("Select One That You Want To Hire : (If The List Is Empty Enter 0)");
                    int in = scan.nextInt();
                    int count = 1;
                    for (Person ab : city.population) {
                        if (ab.job.equals("Locomotives") && !ab.hired) {
                            if (count == in) {
                                RailwayStation.addLocomotives(ab, city);
                            }
                            count++;
                        }
                    }
                } else {
                    throw new NoRailwayStation();
                }
                break;
            }
            case 5: {
                int countPopulation = 0;
                if (city.busTerminals.size() != 0) {
                    int counter = 1;
                    for (Person a : city.population) {
                        if (a.job.equals("Bus Driver") && !a.hired && a.salary <= city.balance) {
                            System.out.println("(" + counter++ + ") Name:" + a.name + "\tLastName:" + a.lastName + "\tSalary: " + a.salary + "$");
                            countPopulation++;
                        }
                    }
                    if(countPopulation==0){
                        throw new NoPopulation();
                    }
                    System.out.println("Select One You Want To Hire : ");
                    int in = scan.nextInt();
                    int count = 1;
                    for (Person ab : city.population) {
                        if (ab.job.equals("Bus Driver") && !ab.hired) {
                            if (count == in) {
                                BusTerminal.addDriver(ab, city);
                            }
                            count++;
                        }
                    }
                } else {
                    throw new NoBusTerminal();
                }
                break;
            }
            default: {
                throw new InvalidInput();
            }
        }
    }
}
