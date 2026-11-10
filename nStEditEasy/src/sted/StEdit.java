package sted;

/**
 * Die Klasse <code><b>StEdit.java</b></code> enthaelt Hilfsmethoden fuer die
 * Realisierung von typischen Texteditorfunktionen. Grundkonzept dieser Klasse
 * ist die Speicherung eines kommpletten Textes mit mehreren Zeilen in einer
 * Zeichenkette. Die Zeilenwechsel sind durch das Zeichen <code>'/n'</code>
 * kodiert, so dass der Text mit <code>System.out.println(text)</code>
 * ausgegeben werden kann.
 *
 * @author rla, pps
 * @version 1.6 Nur einfache Aufgaben
 */
public class StEdit {

    private StEdit() {
    }
    /* Es gibt keinen Konstruktor */

    /**
     * Konstanten fuer Unicodeumlaute bei Verwendung von unterschiedlichen
     * Zeichenkodierungen
     */
    public static final char _ae_ = '\u00E4';
    public static final char _oe_ = '\u00F6';
    public static final char _ue_ = '\u00FC';
    public static final char _AE_ = '\u00C4';
    public static final char _OE_ = '\u00D6';
    public static final char _UE_ = '\u00DC';
    public static final char _sz_ = '\u00DF';
    public static final char _SZ_ = '\u1E9E';
    /**
     * Trennzeichen fuer Spalten. Muss sich von den anderen Separatorzeichen und
     * dem '.' unterscheiden! Darf kein Steuerzeichen fuer regulaere Ausdruecke
     * von split enthalten!
     */
    public static char columnSeparator = ';';
    /**
     * Trennzeichen fuer Zeilen. Muss sich von den anderen Separatorzeichen und
     * dem '.' unterscheiden! Darf kein Steuerzeichen fuer regulaere Ausdruecke
     * von split enthalten!
     */
    public static char rowSeparator = '\n';

    /**
     * Ersetzt in der Zeichenkette <b>s</b>, ab der Position <b>start</b> die
     * erste Zeichenkette <b>su</b> durch <b>se</b>.<br>
     * <br><b>Anwendungsbeispiel:</b><PRE>
     * ...
     * String s     = "abcabcabc";
     * int    start = 1
     * String su    = "ab";
     * String se    = "AB";
     * String sret  = ersetzen( s, start, su, se);
     * ...</PRE> liefert das Ergebnis <CODE>sret = "abcABcabc"</CODE>.<br><br>
     * Wenn die gesuchte Zeichenkette nicht gefunden wird, den Wert null hat
     * oder andere Parameter unsinnig sind wird s direkt zurueckgegeben.
     *
     * @param s Ausgangszeichenkette
     * @param start Position ab der nach su in s gesucht wird
     * @param su Zeichenkette nach der gesucht wird
     * @param se Zeichenkette die su ersetzen soll
     * @return Zeichenkette in der su durch se ersetzt ist
     */
    public static String ersetzen(String s, int start, String su, String se) {
        return "123";
    }

    /**
     * Berechnet die Zeilennummer einer Zeichenposition <b>p</b> in der
     * Zeichenkette <b>s</b>.<br>
     * <br><b>Anwendungsbeispiel:</b><PRE>
     * ...
     * // Zeichenposition:    1      |  2          3
     * //          0123456789 01234567890 1234567890123
     * String s = "1. Zeile \n 2. Zeile \n letzte Zeile";
     * int    p = 17
     * int zret = zeilenNummer( s, p);
     * ...</PRE> liefert das Ergebnis <CODE>zret = 2</CODE>.<br><br> Wenn s
     * nicht initialisiert oder p negativ ist, dann soll 0 zurueckgegeben
     * werden.<br> Wenn p ausserhalb von s liegt, dann wird die Nummer der
     * letzten Zeile von s zurueckgegeben.
     *
     * @param s Zeichenkette (Zeilenwechsel mit \n kodiert)
     * @param p Position eines Zeichens in s, dessen Zeilennummer gesucht wird
     * @return Zeilennummer des Zeichens auf Position p
     */
    public static int zeilenNummer(String s, int p) {
        return 123;
    }

    /**
     * Berechnet die Spaltennummer einer Zeichenposition <b>p</b> in der
     * Zeichenkette <b>s</b>.<br>      <br><b>Anwendungsbeispiel:</b><PRE>
     * ...
     * // Zeichenposition:    1      |  2          3
     * //          0123456789 01234567890 1234567890123
     * String s = "1. Zeile \n 2. Zeile \n letzte Zeile";
     * int    p = 17
     * int sret = spaltenNummer( s, p);
     * ...</PRE> liefert das Ergebnis <CODE>sret = 7</CODE>.<br><br> Wenn s
     * nicht initialisiert oder p negativ ist, dann soll 0 zurueckgegeben
     * werden.<br> Wenn p ausserhalb von s liegt, dann wird die Nummer der
     * letzten Spalte in der letzten Zeile von s zurueckgegeben.
     *
     * @param s Zeichenkette (Zeilenwechsel mit \n kodiert)
     * @param p Position eines Zeichens in s, dessen Spaltennummer gesucht wird
     * @return Spaltennummer des Zeichens auf Position p
     */
    public static int spaltenNummer(String s, int p) {
        return 123;
    }

    /**
     * Ersetzt die deutschen Sonderzeichen durch die folgenden
     * Zeichenkombinationen "ae, Ae, oe, Oe, ue, Ue und ss".
     *
     * <br><b>Anwendungsbeispiel:</b><PRE>
     * ...
     * String s = "K&uuml;ster k&ouml;nnen &Auml;nderungen erleben.";
     * String sret = ersetzeSonderzeichen( s);
     * ...</PRE> liefert das Ergebnis
     * <CODE>sret = "Kuester koennen Aenderungen erleben."</CODE>.<br><br> Wenn
     * <b>s</b> nicht initialisiert ist wird null zurueckgegeben. Falls keine
     * Veraenderung noetig ist, wird <b>s</b> unveraendert zurueckgegeben.
     *
     * @return Zeichenkette ohne Sonderzeichen
     * @param s Zeichenkette in der Sonderzeichen ersetzt werden sollen
     */
    public static String ersetzeSonderzeichen(String s) {
        return "123";
    }

    /**
     * Fuegt am Anfang jeder Zeile einen Kommentar "xxxx: " mit der Zeilennummer
     * xxxx ein.<br> Die Zeilennummer ist immer mindestens vierstellig.
     * <br><b>Anwendungsbeispiel:</b><PRE>
     * ...
     * String s = "1. Zeile \n" +
     *            "2. Zeile \n" +
     *            "letzte Zeile";
     * String sret = nummeriereZeilen( s);
     * ...</PRE> liefert das Ergebnis
     * <PRE>zret =
     *"0001: 1. Zeile \n0002: 2. Zeile \n0003: letzte Zeile" </PRE><br> Wenn
     * <b>s</b> nicht initialisiert ist, dann wird null zurueckgegeben.
     *
     * @param s Zeichenkette (Zeilenwechsel mit \n kodiert)
     * @return Text mit Zeilennummern
     */
    public static String nummeriereZeilen(String s) {
        return "123";
    }
    
    private static String nummer(int i){
        return "0001: ";
    }

    /**
     * Erkennt das Schluesselwort <b>wort</b> unabhaengig von Gross- oder
     * Kleinschreibung innerhalb der Zeichenkette <b>s</b> und bringt es fuer
     * die Rueckgabezeichenkette in die angegebene Schreibweise.
     * <br><b>Anwendungsbeispiel:</b><PRE>
     * ...
     * String s = "otto ist ein Vorname. OTTO ist ein Firmenname";
     * String w = "Otto";
     * String sret = format( s, w);
     * ...</PRE> liefert das Ergebnis
     * <CODE>sret = "Otto ist ein Vorname. Otto ist ein Firmenname"</CODE>.<br><br>
     * Wenn <b>s</b> oder <b>wort</b> nicht initialisiert sind wird null
     * zurueckgegeben. Falls keine Veraenderung noetig ist, wird <b>s</b>
     * unveraendert zurueckgegeben.
     *
     * @return Zeichenkette in der eingestellten Schreibweise
     * @param wort Schluesselwort in Musterschreibweise
     * @param s Zeichenkette in der Gross- und Kleinschreibung angepasst werden
     * sollen
     */
    public static String format(String s, String wort) {
        return "123";
    }

    /**
     * Ergaenzt den Anfang jeder Zeile eines Texts <b>txt</b> mit der
     * Zeichenfolge <b>com</b>. Denkbare Anwendungen waeren das auskommentieren
     * in Java mit "//" oder in SQL mit "--". Nach der Zeichenfolge, die den
     * Kommentar einleitet soll immer noch ein Leerzeichen folgen. <br>
     * <br><b>Anwendungsbeispiel:</b><br><pre>
     *   ...
     *   String sql = "CREATE TABLE Klein\n" +
     *                "  Zeichen CHAR(1),\n" +
     *                "  Nr      INTEGER\n" +
     *                ")";
     *   String neu = comment( sql, "--");
     * ...</pre> &rArr;
     * <pre>
     *   neu = "-- CREATE TABLE Klein\n" +
     *         "--   Zeichen CHAR(1),\n" +
     *         "--   Nr      INTEGER\n" +
     *         "-- )";</pre> Wenn der Text nicht initialisiert ist, dann soll "null"
     * zurueckgegeben werden. Wenn der Kommentarstring nicht initialisiert oder
     * aus nicht sichtbaren Zeichen besteht, dann soll der urspruengliche
     * Text-String zurueckgegeben werden.
     *
     * @param txt Textblock dessen Zeilen auskommentiert werden sollen
     * @param com Zeichenfolge die einen Kommentar einleitet
     * @return Textblock aus Kommentaren
     */
    public static String comment(String txt, String com) {
        return "123";
    }

    /**
     * Ermittelt die laengste Zeile eines Textes.
     * <br><br>Anwendungsbeispiel:
     * <pre>Dieser Text hat drei Zeilen.\n
     * Diese zweite Zeile ist die laengste des Textes. Sie hat 67 Zeichen.\n
     * Die dritte Zeile ist kuerzer. Die Antwort ist 67.
     * </pre> Falls der Text nicht initialisiert ist, soll -1 zurückgegeben
     * werden. Zeilenendezeichen \n sollen bei der Laenge nicht beruecksichtigt
     * werden.
     *
     * @param txt Textblock, dessen Zeilenlaengen ermittelt werden sollen
     * @return die Anzahl der Zeichen der laengsten Zeile.
     */
    public static int laengsteZeile(String txt) {
        return 123;
    } // laengsteZeile()

}
