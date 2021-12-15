package com.mikola.accsys.interfaces;

import com.mikola.accsys.builder.HouseBuilder;
import com.mikola.accsys.model.Apartment;
import com.mikola.accsys.modelservice.ApartmentService;
import com.mikola.accsys.repository.Specification;
import com.mikola.accsys.repository.impl.HouseRepository;
import com.mikola.accsys.repository.impl.NumberSpecification;
import com.mikola.accsys.model.House;
import com.mikola.accsys.modelservice.HouseService;
import com.mikola.accsys.validators.InputValidator;

import java.util.*;


public class UserInterface {

    HouseRepository houseRepository;
    ApartmentService apartmentService;
    HouseService houseService;
    InputValidator inputValidator;

    public UserInterface(HouseRepository houseRepository,
                         ApartmentService apartmentService,
                         HouseService houseService,
                         InputValidator inputValidator) {
        this.houseRepository = houseRepository;
        this.apartmentService = apartmentService;
        this.houseService = houseService;
        this.inputValidator = inputValidator;
    }

    private static final String USER_MENU = """
               0 - выйти из программы
               1 - добавить дом
               2 - удалить дом
               3 - сравнить дома
               4 - сравнить квартиры
               5 - получить информацию о доме
               6 - получить информацию о квартире
               7 - получить информацию о всех квартирах дома
               8 - вернуться к меню
            """;
    private final int ONE_NUMBER = 1;
    private final int TWO_NUMBERS = 2;
    private final int THREE_NUMBERS = 3;


    private Optional<List<Integer>> readLine(int countOfNumbers) {
        Scanner input = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        String inputLine = input.nextLine();
        List<Integer> line;
        Optional<List<Integer>> result;
        if (!inputValidator.checkInputLine(inputLine, countOfNumbers)) {
            line = new ArrayList<>();
        }
        else {
            line = Arrays.stream(inputLine.split(" ")).map(Integer::parseInt).toList();
        }
        result = Optional.of(line);
        return result;
    }

    public int listenQuery() {

        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RESET = "\u001B[0m";

        Scanner input = new Scanner(System.in);
        int query = input.nextInt();
        while (query < 0 || query > 8) {
            System.out.println(ANSI_RED + "Введите число от 0 до 8" + ANSI_RESET);
            query = input.nextInt();
        }

        return query;
    }

    public void showMenu() {
        System.out.println(USER_MENU);
    }

    public void printApartmentInformation(Map<String, Integer> apartmentInformation) {
        System.out.printf("""
                        Информация о квартире № %d:
                        Площадь            : %d
                        Количество жильцов : %d
                        Количество комнат  : %d
                        Этаж               : %d
                        """, apartmentInformation.get("number"), apartmentInformation.get("sqare"),
                apartmentInformation.get("roomer"), apartmentInformation.get("cntRooms"), apartmentInformation.get("floor"));
    }

    public void handleQuery() {

        final String WRONG_DATA = "неверный формат ввода";
        int query = 1;
        int numberOfHouse, numberOfApartment;
        Scanner input = new Scanner(System.in);
        showMenu();

        while (query > 0) {
            System.out.println("Выберите действие");
            query = listenQuery();
            switch (query) {

                case 1 -> {

                    System.out.println("""
                            Введите номер дома, количество квартир и количество этажей
                            (Количество квартир должно быть кратно количеству этажей)
                            """);
                    Optional<List<Integer>> line = readLine(THREE_NUMBERS);
                    if (line.isEmpty()) {
                        System.out.println(WRONG_DATA);
                        break;
                    }
                    List<Integer> data = line.get();
                    numberOfHouse = data.get(0);
                    int floorCount = data.get(1);
                    int apartmentCount = data.get(2);

                    if (houseService.checkNumberOfHouse(numberOfHouse,houseRepository)) {
                        System.out.println("Такой дом уже есть");
                    }
                    if (!houseService.checkQualityApartmentCount(floorCount, apartmentCount)) {
                        System.out.println("Количество квартир должно быть кратно количеству этажей");
                    }

                    HouseBuilder houseBuilder = new HouseBuilder(numberOfHouse, apartmentCount, floorCount);
                    House house = houseBuilder.build();
                    houseRepository.add(house);
                    System.out.println("Дом успешно добавлен");

                }
                case 2 -> {

                    System.out.println("Введите номер дома");
                    Optional<List<Integer>> line = readLine(ONE_NUMBER);
                    if (line.isEmpty()) {
                        System.out.println(WRONG_DATA);
                        break;
                    }
                    List<Integer> data = line.get();
                    numberOfHouse = data.get(0);
                    if (houseService.checkNumberOfHouse(numberOfHouse, houseRepository)) {
                        System.out.println("Такого дома нет");
                    }

                    Specification numberSpecification = new NumberSpecification(numberOfHouse);
                    List<House> houses = houseRepository.query(numberSpecification);
                    houseRepository.remove(houses.get(0));

                }
                case 3 -> {

                    System.out.println("Введите номер первого дома");
                    Optional<List<Integer>> line = readLine(ONE_NUMBER);
                    if (line.isEmpty()) {
                        System.out.println(WRONG_DATA);
                        break;
                    }
                    List<Integer> data = line.get();
                    numberOfHouse = data.get(0);

                    if (houseService.checkNumberOfHouse(numberOfHouse, houseRepository)) {
                        System.out.println("Такого дома нет");
                    }

                    System.out.println("Введите номер второго дома");
                    line = readLine(ONE_NUMBER);
                    if (line.isEmpty()) {
                        System.out.println(WRONG_DATA);
                    }
                    data = line.get();
                    int secondNumberOfHouse = data.get(0);

                    if (houseService.checkNumberOfHouse(numberOfHouse, houseRepository)) {
                        System.out.println("Такого дома нет");
                    }

                    List<House> firstHouse = houseRepository.query(new NumberSpecification(numberOfHouse));
                    List<House> secondHouse = houseRepository.query(new NumberSpecification(secondNumberOfHouse));
                    Map<String, Character> signs = houseService.compareHouse(firstHouse.get(0),secondHouse.get(0));
                    System.out.printf("""
                                    Сравнение домов       №%d  и  №%d
                                    Количество квартир  :     %c
                                    Количество этажей   :     %c
                                    Общая жилая площадь :     %c
                                    """,
                            numberOfHouse,
                            secondNumberOfHouse,
                            signs.get("apartmentCount"),
                            signs.get("floorsCount"),
                            signs.get("sqare"));

                }
                case 4 -> {
//
                    System.out.println("Введите данные для двух квартир(номер дома и квартиры)");
//
                    System.out.println("Для первой :");
                    Optional<List<Integer>> line = readLine(TWO_NUMBERS);
                    if (line.isEmpty()) {
                        System.out.println(WRONG_DATA);
                        break;
                    }
                    List<Integer> data = line.get();
                    numberOfHouse = data.get(0);
                    numberOfApartment = data.get(1);

                    if (!houseService.checkNumberOfHouse(numberOfHouse,houseRepository)) {
                        System.out.println("Такого дома нет");
                    }
                    if (!houseService.checkNumberOfApartment(numberOfHouse, numberOfApartment,houseRepository)) {
                        System.out.println("Такой квартиры нет");
                    }

                    System.out.println("Для второй :");
                    line = readLine(TWO_NUMBERS);
                    if (line.isEmpty()) {
                        System.out.println(WRONG_DATA);
                    }
                    data = line.get();
                    int secondNumberOfHouse = data.get(0);
                    int secondNumberOfApartment = data.get(1);

                    if (!houseService.checkNumberOfHouse(secondNumberOfHouse,houseRepository)) {
                        System.out.println("Такого дома нет");
                    }
                    if (!houseService.checkNumberOfApartment(secondNumberOfHouse, secondNumberOfApartment,houseRepository)) {
                        System.out.println("Такой квартиры нет");
                    }

                    List<House> house = houseRepository.query(new NumberSpecification(numberOfHouse));
                    Apartment firstApartment = houseService.findApartment(numberOfApartment,house.get(0));
                    house = houseRepository.query(new NumberSpecification(numberOfHouse));
                    Apartment secondApartment = houseService.findApartment(secondNumberOfApartment,house.get(0));

                    Map<String, Character> signs = apartmentService.compareApartment(firstApartment,secondApartment);

                    System.out.printf("""
                                    Сравнение квартир    Дом №%d,кв. №%d и Дом №%d,кв. №%d
                                    Площадь            :                %c
                                    Количество жильцов :                %c
                                    Количество комнат  :                %c
                                    Этаж               :                %c
                                    """,
                            numberOfHouse,
                            numberOfApartment,
                            secondNumberOfHouse,
                            secondNumberOfApartment,
                            signs.get("sqare"),
                            signs.get("roomers"),
                            signs.get("cntRooms"),
                            signs.get("floor"));


                }
                case 5 -> {

                    System.out.println("Введите номер дома");
                    Optional<List<Integer>> line = readLine(ONE_NUMBER);
                    if (line.isEmpty()) {
                        System.out.println(WRONG_DATA);
                        break;
                    }
                    List<Integer> data = line.get();
                    numberOfHouse = data.get(0);
                    if (!houseService.checkNumberOfHouse(numberOfHouse, houseRepository)) {
                        System.out.println("Такого дома нет");
                    }
                    List<House> house = houseRepository.query(new NumberSpecification(numberOfHouse));
                    Map<String, Integer> result = houseService.getInfo(house.get(0));
                    System.out.printf("""
                                    Информация о доме №%d
                                    Количество квартир : %d
                                    Количество этажей  : %d
                                    Уникальный id      : %d
                                    """,
                            result.get("number"),
                            result.get("apartmentCount"),
                            result.get("floorsCount"),
                            result.get("id"));
                }
                case 6 -> {

                    System.out.println("Введите номер дома и квартиры");
                    Optional<List<Integer>> line = readLine(TWO_NUMBERS);
                    if (line.isEmpty()) {
                        System.out.println(WRONG_DATA);
                        break;
                    }
                    List<Integer> data = line.get();
                    numberOfHouse = data.get(0);
                    numberOfApartment = data.get(0);
                    if (!houseService.checkNumberOfHouse(numberOfHouse,houseRepository)) {
                        System.out.println("Такого дома нет");
                    }
                    if (!houseService.checkNumberOfApartment(numberOfHouse, numberOfApartment,houseRepository)) {
                        System.out.println("Такой квартиры нет");
                    }

                    List<House> house = houseRepository.query(new NumberSpecification(numberOfHouse));
                    Apartment apartment = houseService.findApartment(numberOfApartment,house.get(0));
                    Map<String, Integer> apartmentInformation = apartmentService.getInfo(apartment);
                    printApartmentInformation(apartmentInformation);

                }
                case 7 -> {
                    System.out.println("Введите номер дома");
                    Optional<List<Integer>> line = readLine(ONE_NUMBER);
                    if (line.isEmpty()) {
                        System.out.println(WRONG_DATA);
                        break;
                    }
                    List<Integer> data = line.get();
                    numberOfHouse = data.get(0);
                    if (!houseService.checkNumberOfHouse(numberOfHouse,houseRepository)) {
                        System.out.println("Такого дома нет");
                    }
                    List<House> house = houseRepository.query(new NumberSpecification(numberOfHouse));

                    Map<String, Integer> apartmentInformation = new HashMap<>();
                    for (Apartment i : house.get(0).getApartments()) {
                        apartmentInformation = apartmentService.getInfo(i);
                        printApartmentInformation(apartmentInformation);
                    }
                }
                case 8 -> {
                    showMenu();
                }
            }
        }
    }
}
