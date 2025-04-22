/**
 * Class for representing a currency. A currency is defined by a name and a set of denominations.
 */
 class Currency {
    private final int[] denominations;
    private final String name;

    // preset currencies
    static Currency USD = new Currency("USD", new int[]{20_00, 10_00, 5_00, 1_00, 25, 10, 5, 1});
    static Currency SWD = new Currency("SWD", new int[]{25_00, 10_00, 5_00, 1_00, 20, 8, 5, 1});

    /**
     * Constructor for a currency.
     * @param name the name of the currency
     * @param denominations the denominations of the currency, in descending order as integers (cents).
     */
    Currency(String name, int[] denominations) {
        if (denominations.length < 1) {
            throw new IllegalArgumentException("Must have at least 1 coin/bill denomination");
        }
        if (denominations[denominations.length - 1] != 1) {
            throw new IllegalArgumentException("Currency must have a 1 unit denomination");
        }

        for (int i = 1; i < denominations.length; i++) {
            if (denominations[i] >= denominations[i - 1]) {
                throw new IllegalArgumentException("Denominations must be in descending order and cannot be duplicate");
            }
        }
        this.name = name;
        this.denominations = denominations;
    }


    /**
     * Get the name of the currency.
     * @return the name of the currency
     */
    public String getName() {
        return name;
    }

    /**
     * Get the value of a denomination.
     * @param i the index of the denomination
     * @return the value of the denomination in cents
     */
    public int getDenomination(int i) {
        return denominations[i];
    }

    /**
     * Get the number of denominations in the currency.
     * @return the number of denominations in the currency
     */
    public int numDenominations() {
        return denominations.length;
    }
}
