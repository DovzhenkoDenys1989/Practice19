package com.company;

public class GameConsole extends MyException implements Powered {
    private final Brand brand;
    private String model;
    private final String serial;
    private final Gamepad firstGamepad;
    private final Gamepad secondGamepad;
    private boolean isOn;
    private Game activeGame;
    private int waitingCounter;

    public GameConsole(Brand brand, String serial) {
        this.brand = brand;
        this.serial = serial;
        firstGamepad = new Gamepad(brand, 1);
        secondGamepad = new Gamepad(brand, 2);
    }

    public Brand getBrand() {
        return brand;
    }

    public String getSerial() {
        return serial;
    }

    public Gamepad getFirstGamepad() {
        return firstGamepad;
    }

    public Gamepad getSecondGamepad() {
        return secondGamepad;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void powerOn() {
        isOn = true;
    }

    @Override
    public void powerOff() {
        isOn = false;
    }

    public void loadGame(Game game) {
        System.out.println("Игра загружается " + game.getName());
        activeGame = game;
    }

    public void playGame() {
        System.out.println("Играем в " + activeGame.getName());
        Gamepad[] gamepads = {firstGamepad, secondGamepad};
        for (Gamepad gamepad : gamepads) {
            if (gamepad.chargeLevel == 0.0) {
                gamepad.powerOff();
                System.out.println("Джойстики разрядились. GAME OVER.");
            } else if (!gamepad.isOn) {
                gamepad.chargeLevel -= 10;
                System.out.println("Заряд джойстика " + gamepad.connectedNumber + " = " + gamepad.chargeLevel + "%");
            }
        }
        checkStatus();
    }

    private void checkStatus() {
        if (firstGamepad.chargeLevel <= 0 & secondGamepad.chargeLevel <= 0) {
            System.out.println("Подключите джойстик " + ++waitingCounter);
            try {
                if (waitingCounter > 5)
                    throw new MyException();
                powerOff();
            } catch (MyException e) {
                System.out.println("Приставка завершает работу из-за отсутствия активности");
            }
        } else {
            System.out.println(waitingCounter = 0);
        }
    }

    public class Gamepad implements Powered {
        private final Brand brand;
        private final String consoleSerial;
        private int connectedNumber;
        private Color color;
        private double chargeLevel = 100.0;
        private boolean isOn;

        public Gamepad(Brand brand, int connectedNumber) {
            this.brand = brand;
            this.connectedNumber = connectedNumber;
            this.consoleSerial = serial;
        }

        public Brand getBrand() {
            return brand;
        }

        public String getConsoleSerial() {
            return consoleSerial;
        }

        public int getConnectedNumber() {
            return connectedNumber;
        }

        public void setConnectedNumber(int connectedNumber) {
            this.connectedNumber = connectedNumber;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public double getChargeLevel() {
            return chargeLevel;
        }

        public void setChargeLevel(double chargeLevel) {
            this.chargeLevel = chargeLevel;
        }

        public boolean isOn() {
            return isOn;
        }

        public void setOn(boolean on) {
            isOn = on;
        }

        @Override
        public void powerOn() {
            isOn = true;
            GameConsole.this.isOn = true;
            if (firstGamepad.isOn) {
                secondGamepad.connectedNumber = 2;
            } else {
                secondGamepad.connectedNumber = 1;
            }
        }

        @Override
        public void powerOff() {
            isOn = false;
            if (!firstGamepad.isOn) {
                secondGamepad.connectedNumber = 1;
            }
        }
    }
}

//        1. Реализация внутреннего класса
//        • Создать класс GameConsole.
//        Описать поля:
//        brand (название производителя, например Sony, Microsoft. Можно оформить
//        enum-ом),
//        model (название модели, например XBOX 360, PS4 PRO),
//        serial (серийный номер приставки, например XC123QeWR),
//        firstGamepad (объект для первого джойстика, который будет реализован как
//        внутренний класс),
//        secondGamepad (объект для второго джойстика),
//        isOn (флаг состояния. True – вкл, false - выкл)
//        • Создать внутренний (нестатический) класс Gamepad.
//        Описать поля:
//        brand (название производителя, например Sony, Microsoft).
//        consoleSerial (серийный номер консоли, к которой подключен джойстик),
//        connectedNumber (порядковый номер джойстика),
//        color (цвет джойстика, можно оформить enum-ом),
//        chargeLevel (значение процента заряда, по умолчанию поставить 100.0)
//        isOn (флаг состояния. True – вкл, false - выкл).
//        • Создать конструктор для класса GameConsole. Передать в него 2 параметра
//        (brand, serial)
//        • Создать конструктор для класса Gamepad. Передать в него параметр (brand и
//        connectedNumber), а полю consoleSerial присвоить значение серийного
//        номера приставки.
//        • Внутри конструктора создать и присвоить 2 джойстика (firstGamepad,
//        secondGamepad). Причем brand можно передать уже существующие для самой
//        консоли, а connectedNumber фиксированными значениями 1 и 2.
//        • Для полей которые не должны меняться (определите их сами :) ), применить
//        модификатор final, и создать геттеры.
//        • Для остальных полей создать геттеры и сеттеры.
//        2. Реализация вложенного статического класса
//        • Создать класс Game.
//        Описать поля (пометить final):
//        name (название игры),
//        ganre (жанр игры, например ACTION, SPORT, RACE. Можно оформить enumом),
//        type (тип носителя, например VIRTUAL, PHYSICAL. Можно оформить enumом прямо внутри класса Game),
//        • Создать приватный конструктор. В конструктор передать поля: name, ganre,
//        type.
//        • Создать getter-ы для полей.
//        • Создать вложенный (статический) класс GameDisk.
//        • Создать поле description (описание игры, пометить final)
//        • Создать поле Game data (final);
//        • В приватный конструктор передать поля: name, ganre, description.
//        • При вызове конструктора создавать экземпляр класса Game и
//        передавать в него параметры name, ganre и фиксированный type
//        соответствующий этому носителю.
//        • Также инициализировать поле с описанием.
//        • Создать геттеры
//        • Создать вложенный (статический) класс VirtualGame.
//        • Создать поле rating (рейтинг игры от 0 до 5)
//        • Создать поле Game data (final);
//        • В приватный конструктор передать поля: name, ganre.
//        • При вызове конструктора создавать экземпляр класса Game и
//        передавать в него параметры name, ganre и фиксированный type
//        соответствующий этому носителю.
//        • Создать необходимые геттеры/сеттеры
//        • В классе Game создать статический метод GameDisk getDisk(…){…} для
//        получения экземпляра класса (Паттерн static factory)
//        • В метод передать параметры name, ganre, description
//        • Внутри метода создать и вернуть экземпляр класса GameDisk
//        • Аналогично, в классе Game создать статический метод VirtualGame
//        getVirtualGame (…){…}
//        • В метод передать параметры name, ganre
//        • Внутри метода создать и вернуть экземпляр класса VirtualGame.
//        3. Создать класс PlayRoom
//        • Создать main метод.
//        • Создать массив с играми на физ. носителях (4 игры). (пользуемся методом
//        getDisk)
//        • Создать массив с играми из виртуального магазина (4 игры). (пользуемся
//        методом getVirtualGame)
//        • Создать GameConsole.
//        4. Реализация анонимного класса (Comparator)
//        • В методе main, отсортировать массив с физическими дисками по жанру.
//        • В методе main, отсортировать массив с виртуальными играми по рейтингу.
//        Для сравнения примитивов можно воспользоваться методом :
//        Integer.compare(a.getRating(), b.getRating())
//        5* Доработать класс GameConsole
//        • Создать интерфейс Powered с методами powerOn и PowerOff
//        • Реализовать данный интерфейс для джойстика и консоли
//        • Добавить функционал, который включает приставку, когда включается
//        джойстик.
//        • Добавить функционал, который делает «второй» джойстик «первым», если
//        первый был выключен.
//        • Добавить поле Game activeGame
//        • Добавить метод loadGame(Game). В нем вывести сообщение «Игра
//        {название} загружается»
//        • Добавить метод playGame(). В нем выводить информацию о текущей игре
//        «Играем в {игра}» и информацию о заряде только активных джойстиков.
//        Внимание! При каждом вызове метода – уменьшать заряд джойстика на
//        10%. Когда заряд уменьшиться до 0 – нужно выключить джойстик.
//        • Добавить приватный метод void checkStatus(). Который будет вызываться
//        каждый раз когда вызывается метод playGame().
//        Добавить новое поле для класса GameConsole – waitingCounter;
//        Если оба джойстика выключены – выводить сообщение «Подключите
//        джойстик» и увеличивать счетчик на 1. Если хотя-бы один джойстик
//        активен – сбрасывать в 0.
//        Если счетчик превысил 5 циклов ожидания – «Выключить» приставку и
//        бросить исключение с текстом «Приставка завершает работу из-за
//        отсутствия активности» (Класс-исключение создать свой.)


