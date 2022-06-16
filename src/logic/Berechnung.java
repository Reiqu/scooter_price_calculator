package logic;

public class Berechnung {
    // Preis pro Minute in Euro
    private float preis_pro_minute;

    // Preis pro Kilometer in Euro
    private float preis_pro_km;

    // wenn Entsperrpreis noch dazu muss:
    private float preis_pro_entsperrung = 0;

    /**
     * Konstruktor
     * @param preis_pro_minute Preis pro Minute in Euro
     * @param preis_pro_km Preis pro Kilometer in Euro
     */
    public Berechnung(float preis_pro_minute, float preis_pro_km) {
        this.preis_pro_minute = preis_pro_minute;
        this.preis_pro_km = preis_pro_km;
    }

    /**
     * Konstruktor
     * @param preis_pro_minute Preis pro Minute in Euro
     * @param preis_pro_km Preis pro Kilometer in Euro
     * @param preis_pro_entsperrung Preis pro Entsperrung in Euro
     */
    public Berechnung(float preis_pro_minute, float preis_pro_km, float preis_pro_entsperrung) {
        this.preis_pro_minute = preis_pro_minute;
        this.preis_pro_km = preis_pro_km;
        this.preis_pro_entsperrung = preis_pro_entsperrung;
    }

    public float getPreis_pro_minute() {
        return preis_pro_minute;
    }

    public void setPreis_pro_minute(float preis_pro_minute) {
        this.preis_pro_minute = preis_pro_minute;
    }

    public float getPreis_pro_km() {
        return preis_pro_km;
    }

    public void setPreis_pro_km(float preis_pro_km) {
        this.preis_pro_km = preis_pro_km;
    }

    public float getPreis_pro_entsperrung() {
        return preis_pro_entsperrung;
    }

    public void setPreis_pro_entsperrung(float preis_pro_entsperrung) {
        this.preis_pro_entsperrung = preis_pro_entsperrung;
    }

    /**
     * Berechnet den Preis pro Kilometer
     * @param km Kilometer, die gefahren wurden
     * @return Endpreis in Euro
     */
    public float berechnePreisProStrecke(float km) {
        return preis_pro_entsperrung + km * preis_pro_km;
    }

    /**
     * Berechnet den Preis pro Minute
     * @param minuten Minuten, welche der Benutzer gefahren hat
     * @return Endpreis in Euro
     */
    public float berechnePreisProMinute(float minuten) {
        return minuten * preis_pro_minute + preis_pro_entsperrung;
    }


}
