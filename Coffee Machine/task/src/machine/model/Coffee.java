package machine.model;

/**
 * This enum lists the available kinds of coffee and holds their resource requirements.
 */
public enum Coffee {

    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    public final int water;
    public final int milk;
    public final int coffee;
    public final int price;

    private Coffee(int water, int milk, int coffee, int price) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.price = price;
    }


}
