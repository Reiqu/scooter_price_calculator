package logic;

public class Calculation {
    // Preis pro Minute in Euro
    private float price_per_min;

    // Preis pro Kilometer in Euro
    private float price_per_km;

    // wenn Entsperrpreis noch dazu muss:
    private float price_per_unlock = 0;

    /**
     * Constructor to calculate scooter prices
     * @param price_per_min Preis pro Minute in Euro
     * @param price_per_km Preis pro Kilometer in Euro
     */
    public Calculation(float price_per_min, float price_per_km) {
        this.price_per_min = price_per_min;
        this.price_per_km = price_per_km;
    }

    /**
     * Konstruktor
     * @param price_per_min Preis pro Minute in Euro
     * @param price_per_km Preis pro Kilometer in Euro
     * @param price_per_unlock Preis pro Entsperrung in Euro
     */
    public Calculation(float price_per_min, float price_per_km, float price_per_unlock) {
        this.price_per_min = price_per_min;
        this.price_per_km = price_per_km;
        this.price_per_unlock = price_per_unlock;
    }

    public float getPrice_per_min() {
        return price_per_min;
    }

    public void setPrice_per_min(float price_per_min) {
        this.price_per_min = price_per_min;
    }

    public float getPrice_per_km() {
        return price_per_km;
    }

    public void setPrice_per_km(float price_per_km) {
        this.price_per_km = price_per_km;
    }

    public float getPrice_per_unlock() {
        return price_per_unlock;
    }

    public void setPrice_per_unlock(float price_per_unlock) {
        this.price_per_unlock = price_per_unlock;
    }

    /**
     * Berechnet den Preis pro Kilometer
     * @param km Kilometer, die gefahren wurden
     * @return Endpreis in Euro
     */
    public float calcPricePerKM(float km) {
        return price_per_unlock + km * price_per_km;
    }

    /**
     * Berechnet den Preis pro Minute
     * @param minuten Minuten, welche der Benutzer gefahren hat
     * @return Endpreis in Euro
     */
    public float calcPricePerMin(float minuten) {
        return minuten * price_per_min + price_per_unlock;
    }


}
