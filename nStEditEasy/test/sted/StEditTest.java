package sted;

/**
 * @author rla, pps
 * @version fuer StEdit v1.24
 *
 * Die Tests koennen auch ohne besondere jUnit-Ide durch main gestartet werden.
 */

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // Ab jUnit 4.11
public class StEditTest {

    /*--- generiert ---------------------------------------------*/
    public StEditTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Wird vor allen "@Test(timeout = 1000L)"s ausgefuehrt"
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Wird nach allen "@Test(timeout = 1000L)"s ausgefuehrt"
    }

    @Before
    public void setUp() {
        // Wird vor jedem "@Test(timeout = 1000L)" ausgefuehrt"
    }

    @After
    public void tearDown() {
        // Wird nach jedem "@Test(timeout = 1000L)" ausgefuehrt"
    }


    /*--- Tests -------------------------------------------------*/
    @Test(timeout = 1000L)
    public void testErsetzenSimple() {
        System.out.println("testErsetzen");
        assertEquals("xxxExxx", StEdit.ersetzen("xxxSxxx", 0, "S", "E"));
        assertEquals("xxxExxx", StEdit.ersetzen("xxxSSSxxx", 0, "SSS", "E"));
        assertEquals("xxxEFxxx", StEdit.ersetzen("xxxSTTxxx", 0, "STT", "EF"));
        assertEquals("xxxEFFFxxx", StEdit.ersetzen("xxxSTTxxx", 0, "STT", "EFFF"));
        double d = Math.random();
        assertEquals("R" + d, StEdit.ersetzen("r" + d, 0, "r", "R"));
    }

    @Test(timeout = 1000L)
    public void testErsetzenTypical() {
        assertEquals("abcABcabc", StEdit.ersetzen("abcabcabc", 1, "ab", "AB"));
        assertEquals("xSxExxx", StEdit.ersetzen("" + "xSxSxxx", 3, "S", "E"));
        assertEquals("Exxx", StEdit.ersetzen("Sxxx", 0, "S", "E"));
        assertEquals("xxxE", StEdit.ersetzen("xxxS", 0, "S", "E"));
        assertEquals("xxxSxxx", StEdit.ersetzen("xxxSxxx", 4, "S", "E"));
        assertEquals("xxxSxxx", StEdit.ersetzen("xxxSxxx", 4, "SSSSSSSSSSSSSS", "E"));
        assertEquals("xxxxxx", StEdit.ersetzen("xxxxxx", 0, "STT", "EFFF"));
        assertEquals("", StEdit.ersetzen("", 0, "STT", "EFFF"));
        assertEquals("xxxxxx", StEdit.ersetzen("xxxExxx", 0, "E", ""));
        double d = Math.random();
        assertEquals("R" + d, StEdit.ersetzen("r" + d, 0, "r", "R"));
    }

    @Test(timeout = 1000L)
    public void testErsetzenMissingParameter() {
        assertNull(StEdit.ersetzen(null, 0, "STT", "EFFF"));
        assertEquals("xx", StEdit.ersetzen("xx", 0, null, "EFFF"));
        assertEquals("xx", StEdit.ersetzen("xx", 0, "STT", null));
    }

    @Test(timeout = 1000L)
    public void testErsetzenStringIdentity() {
        String s = "Ein String\nmit 2 Zeilen";
        String su = "unbekannt";
        String se = "bekannt";
        String rs = StEdit.ersetzen(s, 0, su, se);
        assertSame(rs, s);

        s = "Ein String\nmit 2 Zeilen";
        su = "2";
        se = "3";
        rs = StEdit.ersetzen(s, 20, su, se);
        assertSame(rs, s);

        s = "Ein String\nmit 2 Zeilen";
        su = "";
        se = "3";
        rs = StEdit.ersetzen(s, 20, su, se);
        assertSame(rs, s);

        double d = Math.random();
        assertEquals("R" + d, StEdit.ersetzen("r" + d, 0, "r", "R"));
    }

    @Test(timeout = 1000L)
    public void testErsetzenCriticalParameter() {
        assertEquals("xxxSxxx", StEdit.ersetzen("xxxSxxx", 0, "", "E"));
        assertEquals("xxxSxxx", StEdit.ersetzen("xxxSxxx", -1, "S", "E"));
        assertEquals("xxxSxxx", StEdit.ersetzen("xxxSxxx", 44, "S", "E"));
        assertEquals("xxx:xxx", StEdit.ersetzen("xxx.xxx", 0, ".", ":"));
        assertEquals("xxx^xxx", StEdit.ersetzen("xxx**xxx", 0, "**", "^"));
        assertEquals("xxx  xxx", StEdit.ersetzen("xxx\txxx", 0, "\t", "  "));
        assertEquals("xxx_xxx", StEdit.ersetzen("xxx.xxx", 0, ".", "_"));
        assertEquals("xxx[0...9]xxx", StEdit.ersetzen("xxx[0-9]xxx", 0, "[0-9]", "[0...9]"));
        double d = Math.random();
        assertEquals("R" + d, StEdit.ersetzen("r" + d, 0, "r", "R"));
    }

    @Test(timeout = 1000L)
    public void testZeilenNummerSimple() {
        System.out.println("testZeilenNummer");
        assertEquals(1, StEdit.zeilenNummer("11111", 2));
        assertEquals(2, StEdit.zeilenNummer("1111\n2222", 7));
    }

    @Test(timeout = 1000L)
    public void testZeilenNummerTypical() {
        assertEquals(2, StEdit.zeilenNummer("1. Zeile \n 2. Zeile \n letzte Zeile", 17));
        assertEquals(4, StEdit.zeilenNummer("1. Zeile \n\n 3. Zeile \n letzte Zeile", 30));
        assertEquals(2, StEdit.zeilenNummer("\n2. Zeile \n 3. Zeile \n letzte Zeile", 3));
    }

    @Test(timeout = 1000L)
    public void testZeilenNummerCritical() {
        assertEquals(3, StEdit.zeilenNummer("1. Zeile \n 2. Zeile \n letzte Zeile", 170));
        assertEquals(2, StEdit.zeilenNummer("1. Zeile \n", 100));
        assertEquals(1, StEdit.zeilenNummer("01\n34", 1));
        assertEquals(1, StEdit.zeilenNummer("01\n34", 2));
        assertEquals(2, StEdit.zeilenNummer("01\n34", 3));
        assertEquals(1, StEdit.zeilenNummer("\n", 0));
        assertEquals(2, StEdit.zeilenNummer("\n", 1));
        assertEquals(2, StEdit.zeilenNummer("\n", 2));
    }

    @Test(timeout = 1000L)
    public void testZeilenNummerParameter() {
        assertEquals(0, StEdit.zeilenNummer(null, 1));
        assertEquals(0, StEdit.zeilenNummer("abc", -1));
    }

    @Test(timeout = 1000L)
    public void testSpaltenNummerSimple() {
        System.out.println("testSpaltenNummer");
        assertEquals(1, StEdit.spaltenNummer("0123", 0));
        assertEquals(4, StEdit.spaltenNummer("0123\n5678", 3));
        assertEquals(5, StEdit.spaltenNummer("0123\n5678", 4));
    }

    @Test(timeout = 1000L)
    public void testSpaltenNummerTypical() {
        assertEquals(1, StEdit.spaltenNummer("012\n456\n89", 4));
        assertEquals(4, StEdit.spaltenNummer("012\n456\n89", 7));
        assertEquals(2, StEdit.spaltenNummer("012\n456\n89", 9));
        assertEquals(8, StEdit.spaltenNummer("1. Zeile \n 2. Zeile \n letzte Zeile", 17));
    }

    @Test(timeout = 1000L)
    public void testSpaltenNummerCritical() {
        assertEquals(13, StEdit.spaltenNummer("1. Zeile \n 2. Zeile \n letzte Zeile", 170));
        assertEquals(10, StEdit.spaltenNummer("1. Zeile \n", 100));
        assertEquals(2, StEdit.spaltenNummer("01\n34", 1));
        assertEquals(3, StEdit.spaltenNummer("01\n34", 2));
        assertEquals(1, StEdit.spaltenNummer("01\n34", 3));
        assertEquals(1, StEdit.spaltenNummer("\n", 0));
        assertEquals(1, StEdit.spaltenNummer("\n", 1));
        assertEquals(1, StEdit.spaltenNummer("\n", 2));
        assertEquals(1, StEdit.spaltenNummer("\n\n", 0));
        assertEquals(1, StEdit.spaltenNummer("\n\n", 1));
        assertEquals(1, StEdit.spaltenNummer("\n\n", 2));
        assertEquals(1, StEdit.spaltenNummer("\n123", 0));
        assertEquals(1, StEdit.spaltenNummer("\n123", 1));
        assertEquals(2, StEdit.spaltenNummer("\n123", 2));
        assertEquals(1, StEdit.spaltenNummer("", 2));
        assertEquals(1, StEdit.spaltenNummer("", 0));
    }

    @Test(timeout = 1000L)
    public void testSpaltenNummerParameter() {
        assertEquals(0, StEdit.spaltenNummer(null, 1));
        assertEquals(0, StEdit.spaltenNummer(null, -7));
        assertEquals(0, StEdit.spaltenNummer("abc", -1));
    }

    @Test(timeout = 1000L)
    public void testErsetzeSonderzeichenSimple() {
        System.out.println("testErsetzeSonderzeichen");
        String s = "" + StEdit._ae_;
        String rs = "ae";
        String ri = StEdit.ersetzeSonderzeichen(s);
        assertEquals(rs, ri);

        s = "" + StEdit._AE_;
        rs = "Ae";
        ri = StEdit.ersetzeSonderzeichen(s);
        assertEquals(rs, ri);

        s = "" + StEdit._oe_;
        rs = "oe";
        ri = StEdit.ersetzeSonderzeichen(s);
        assertEquals(rs, ri);

        s = "" + StEdit._OE_;
        rs = "Oe";
        ri = StEdit.ersetzeSonderzeichen(s);
        assertEquals(rs, ri);

        s = "" + StEdit._ue_;
        rs = "ue";
        ri = StEdit.ersetzeSonderzeichen(s);
        assertEquals(rs, ri);

        s = "" + StEdit._UE_;
        rs = "Ue";
        ri = StEdit.ersetzeSonderzeichen(s);
        assertEquals(rs, ri);

        s = "" + StEdit._sz_;
        rs = "ss";
        ri = StEdit.ersetzeSonderzeichen(s);
        assertEquals(rs, ri);
    }

    @Test(timeout = 1000L)
    public void testErsetzeSonderzeichenTypical() {
        String s = "K" + StEdit._ue_ + "ster k" + StEdit._oe_ + "nnen " + StEdit._AE_ + "nderungen erleben.";
        String rs = "Kuester koennen Aenderungen erleben.";
        String ri = StEdit.ersetzeSonderzeichen(s);
        assertEquals(rs, ri);

        s = "" + StEdit._ae_ + "\n" + StEdit._AE_ + "\n" + StEdit._oe_ + "\n" + StEdit._OE_ + "\n" + StEdit._ue_ + "\n" + StEdit._UE_ + "\n" + StEdit._sz_ + "";
        rs = "ae\nAe\noe\nOe\nue\nUe\nss";
        ri = StEdit.ersetzeSonderzeichen(s);
        assertEquals(rs, ri);

        s = "" + StEdit._AE_ + StEdit._ae_ + "ne m" + StEdit._OE_ + StEdit._oe_ + "ne\nm" + StEdit._UE_ + StEdit._ue_;
        rs = "Aeaene mOeoene\nmUeue";
        ri = StEdit.ersetzeSonderzeichen(s);
        assertEquals(rs, ri);
    }

    @Test(timeout = 1000L)
    public void testErsetzeSonderzeichenCritical() {
        String s = "Kuester koennen Aenderungen erleben.";
        String rs = s;
        String ri = StEdit.ersetzeSonderzeichen(s);
        assertSame(rs, ri);

        s = "" + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_
                + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_
                + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_
                + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_
                + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_
                + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_
                + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_
                + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_
                + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_
                + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_
                + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_ + StEdit._sz_;
        rs = "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
        rs = rs + rs + rs + rs + rs + rs + rs + rs + rs + rs + rs + rs + rs + rs + rs + rs + rs + rs + rs + rs;
        ri = StEdit.ersetzeSonderzeichen(s + s + s + s + s + s + s + s + s + s);
        assertEquals(rs, ri);
    }

    @Test(timeout = 1000L)
    public void testErsetzeSonderzeichenParameter() {
        String s = null;
        String rs;
        String ri = StEdit.ersetzeSonderzeichen(s);
        assertNull(ri);

        s = "";
        rs = s;
        ri = StEdit.ersetzeSonderzeichen(s);
        assertSame(rs, ri);
    }


    @Test(timeout = 1000L)
    public void testNummeriereZeilenSimple() {
        System.out.println("testNummeriereZeilen");
        String s = "Zeile";
        String rs = "0001: Zeile";
        String ri = StEdit.nummeriereZeilen(s);
        assertEquals(rs, ri);

        s = "1. Zeile\n"
                + "2. Zeile";
        rs = "0001: 1. Zeile\n"
                + "0002: 2. Zeile";
        ri = StEdit.nummeriereZeilen(s);
        assertEquals(rs, ri);
    }

    @Test(timeout = 1000L)
    public void testNummeriereZeilenTypical() {
        String s = "1. Zeile \n"
                + "2. Zeile \n"
                + "letzte Zeile";
        String rs = "0001: 1. Zeile \n0002: 2. Zeile \n0003: letzte Zeile";
        String ri = StEdit.nummeriereZeilen(s);
        assertEquals(rs, ri);

        s = "1. Zeile\n"
                + "\n"
                + "3. Zeile";
        rs = "0001: 1. Zeile\n"
                + "0002: \n"
                + "0003: 3. Zeile";
        ri = StEdit.nummeriereZeilen(s);
        assertEquals(rs, ri);
    }

    @Test(timeout = 1000L)
    public void testNummeriereZeilenCritical() {
        String s = "\n"
                + "\n"
                + "\n";
        String rs = "0001: \n0002: \n0003: \n0004: ";
        String ri = StEdit.nummeriereZeilen(s);
        assertEquals(rs, ri);

        StringBuilder sb = new StringBuilder(10000);
        for (int i = 0; i < 10000; ++i) {
            sb.append('\n');
        }
        s = sb.toString(); // s enthaelt 10000 Leerzeilen
        // Nur die letzte Zeile ueberpruefen!
        rs = "10001: ";
        ri = StEdit.nummeriereZeilen(s);
        assertTrue(ri.endsWith(rs));
    }

    @Test(timeout = 1000L)
    public void testNummeriereZeilenParameter() {
        String s = null;
        String rs;
        String ri = StEdit.nummeriereZeilen(s);
        assertNull(ri);

        s = "";
        rs = "0001: ";
        ri = StEdit.nummeriereZeilen(s);
        assertEquals(rs, ri);
    }

    
    @Test(timeout = 1000L)
    public void testFormatSimple() {
        System.out.println("testFormat");
        String s = "otto";
        String w = "Otto";
        String rs = "Otto";
        String ri = StEdit.format(s, w);
        assertEquals(rs, ri);

        s = "otto\nOTTO";
        w = "Otto";
        rs = "Otto\nOtto";
        ri = StEdit.format(s, w);
        assertEquals(rs, ri);

        s = "otto\nOtto";
        w = "Otto";
        rs = "Otto\nOtto";
        ri = StEdit.format(s, w);
        assertEquals(rs, ri);
    }

    @Test(timeout = 1000L)
    public void testFormatTypical() {
        String s = "otto ist ein Vorname. OTTO ist ein Firmenname";
        String w = "Otto";
        String rs = "Otto ist ein Vorname. Otto ist ein Firmenname";
        String ri = StEdit.format(s, w);
        assertEquals(rs, ri);

        s = "testformattypicalTEST\nFORMATTYPICALTeStFoRmAtTyPiCaL";
        w = "testFormatTypical";
        rs = "testFormatTypicalTEST\nFORMATTYPICALtestFormatTypical";
        ri = StEdit.format(s, w);
        assertEquals(rs, ri);

        s = "testFormatTypicalTEST\nFORMATTYPICALTeStFoRmAtTyPiCaL";
        w = "testFormatTypical";
        rs = "testFormatTypicalTEST\nFORMATTYPICALtestFormatTypical";
        ri = StEdit.format(s, w);
        assertEquals(rs, ri);
    }

    @Test(timeout = 1000L)
    public void testFormatCritical() {
        String s = "otto ist ein Vorname. OTTO ist ein Firmenname";
        String w = "Werner";
        String rs = s;
        String ri = StEdit.format(s, w);
        assertSame(rs, ri);

        s = "Dieser Satz enthaelt das StEdithluesselwort, trotzdem gibt es keine Aenderung";
        w = "StEdithluesselwort";
        rs = s;
        ri = StEdit.format(s, w);
        assertSame(rs, ri);
    }

    @Test(timeout = 1000L)
    public void testFormatParameter() {
        String s = null;
        String w = "wort";
        String rs;
        String ri = StEdit.format(s, w);
        assertNull(ri);

        s = "";
        w = "wort";
        rs = s;
        ri = StEdit.format(s, w);
        assertSame(rs, ri);

        s = "Satz";
        w = null;
        ri = StEdit.format(s, w);
        assertNull(ri);

        s = "Satz";
        w = "";
        rs = s;
        ri = StEdit.format(s, w);
        assertSame(rs, ri);

        s = null;
        w = null;
        ri = StEdit.format(s, w);
        assertNull(ri);

        s = "";
        w = "";
        rs = s;
        ri = StEdit.format(s, w);
        assertSame(rs, ri);
    }


    /**
     * Test of comment method, of class StEdit.
     */
    @Test(timeout = 000L)
    public void testCommentSimple() {
        System.out.println("testComment");
        String txt = "System.out.println;";
        String com = "//";
        String expResult = "// System.out.println;";
        String result = StEdit.comment(txt, com);
        assertEquals(expResult, result);

        txt = "DROP TABLE Dummy;";
        com = "--";
        expResult = "-- DROP TABLE Dummy;";
        result = StEdit.comment(txt, com);
        assertEquals(expResult, result);

        txt = "echo Hello";
        com = "#";
        expResult = "# echo Hello";
        result = StEdit.comment(txt, com);
        assertEquals(expResult, result);
    }

    @Test(timeout = 1000L)
    public void testCommentTypical() {
        String txt = "CREATE TABLE Klein(\n"
                + "  Zeichen CHAR(1),\n"
                + "  Nr      INTEGER\n"
                + ")";
        String com = "--";
        String expResult = "-- CREATE TABLE Klein(\n"
                + "--   Zeichen CHAR(1),\n"
                + "--   Nr      INTEGER\n"
                + "-- )";
        String result = StEdit.comment(txt, com);
        assertEquals(expResult, result);

        txt = "System.out.println(\"manuelle Test von StEdit:\");\n"
                + "String sql = \"--CREATE TABLE Klein\\n\"\n"
                + "           + \"--   Zeichen CHAR(1),\\n\"\n"
                + "           + \"  Nr      INTEGER\\n\"\n"
                + "           + \"-- )\";\n"
                + "String neu = uncomment(sql, \"--\");\n"
                + "System.out.println(neu);";
        com = "//";
        expResult = "// System.out.println(\"manuelle Test von StEdit:\");\n"
                + "// String sql = \"--CREATE TABLE Klein\\n\"\n"
                + "//            + \"--   Zeichen CHAR(1),\\n\"\n"
                + "//            + \"  Nr      INTEGER\\n\"\n"
                + "//            + \"-- )\";\n"
                + "// String neu = uncomment(sql, \"--\");\n"
                + "// System.out.println(neu);";
        result = StEdit.comment(txt, com);
        assertEquals(expResult, result);

        txt = "# This file describes the network interfaces available on your system\n"
                + "# and how to activate them. For more information, see interfaces(5).\n"
                + "\n"
                + "# The loopback network interface\n"
                + "auto lo\n"
                + "iface lo inet loopback\n"
                + "\n"
                + "# The primary network interface\n"
                + "\n"
                + "auto eth0\n"
                + "#NetworkManager#iface eth0 inet dhcp";
        com = "#";
        expResult = "# # This file describes the network interfaces available on your system\n"
                + "# # and how to activate them. For more information, see interfaces(5).\n"
                + "# \n"
                + "# # The loopback network interface\n"
                + "# auto lo\n"
                + "# iface lo inet loopback\n"
                + "# \n"
                + "# # The primary network interface\n"
                + "# \n"
                + "# auto eth0\n"
                + "# #NetworkManager#iface eth0 inet dhcp";
        result = StEdit.comment(txt, com);
        assertEquals(expResult, result);
    }

    @Test(timeout = 1000L)
    public void testCommentCritical() {
        String txt = "";
        String com = "//";
        String expResult = "// ";
        String result = StEdit.comment(txt, com);
        assertEquals(expResult, result);

        txt = "\n"
                + " \n"
                + "  \n"
                + "   ";
        com = "--";
        expResult = "-- \n"
                + "--  \n"
                + "--   \n"
                + "--    ";
        result = StEdit.comment(txt, com);
        assertEquals(expResult, result);

        txt = "\n"
                + "#\n"
                + "##\n"
                + "###";
        com = "#";
        expResult = "# \n"
                + "# #\n"
                + "# ##\n"
                + "# ###";
        result = StEdit.comment(txt, com);
        assertEquals(expResult, result);

        txt = "CREATE TABLE Klein(\n"
                + "\n"
                + "\tZeichen CHAR(1)--,\n"
                + "--  Nr      INTEGER\n"
                + ")";
        com = "--";
        expResult = "-- CREATE TABLE Klein(\n"
                + "-- \n"
                + "-- \tZeichen CHAR(1)--,\n"
                + "-- --  Nr      INTEGER\n"
                + "-- )";
        result = StEdit.comment(txt, com);
        assertEquals(expResult, result);
    }

    @Test(timeout = 1000L)
    public void testCommentParameter() {
        String txt = null;
        String com = "//";
        String result = StEdit.comment(txt, com);
        assertNull(result);

        txt = "DROP TABLE Dummy;";
        com = null;
        result = StEdit.comment(txt, com);
        assertSame(txt, result);

        txt = "echo Hello";
        com = "";
        result = StEdit.comment(txt, com);
        assertSame(txt, result);

        txt = "DROP TABLE Dummy;";
        com = " \t";
        result = StEdit.comment(txt, com);
        assertSame(txt, result);
    }


    @Test(timeout = 1000L)
    public void testLaengsteZeileParameter() {
        System.out.println("laengsteZeile");
        String s = null;
        int rs = -1;
        int ri = StEdit.laengsteZeile(s);
        assertEquals(rs, ri);

        s = "";
        rs = 0;
        ri = StEdit.laengsteZeile(s);
        assertEquals(rs, ri);
    }

    @Test(timeout = 1000L)
    public void testLaengsteZeileSimple() {
        String txt = "test";
        int expResult = 4;
        int result = StEdit.laengsteZeile(txt);
        assertEquals(expResult, result);

        txt = "\tHallo!\n";
        expResult = 7;
        result = StEdit.laengsteZeile(txt);
        assertEquals(expResult, result);
    }

    @Test(timeout = 000L)
    public void testLaengsteZeileTypical() {
        String txt = "wie lautet die Antwort\n"
                + "auf die Frage nach dem \n"
                + "Leben, dem Universum und dem ganzen Rest? ";
        int expResult = 42;
        int result = StEdit.laengsteZeile(txt);
        assertEquals(expResult, result);

        System.out.println("laengsteZeile");
        txt = "Dieser Text hat drei Zeilen.\n"
                + "Diese zweite Zeile ist die laengste des Textes. Sie hat 67 Zeichen.\n"
                + "Die dritte Zeile ist kuerzer. Die Antwort ist 67.\n";
        expResult = 67;
        result = StEdit.laengsteZeile(txt);
        assertEquals(expResult, result);
    }

    @Test(timeout = 1000L)
    public void testLaengsteZeileCritical() {
        String txt = "\n\n\n";
        int expResult = 0;
        int result = StEdit.laengsteZeile(txt);
        assertEquals(expResult, result);

        txt = "\n\n\t\t\n";
        expResult = 2;
        result = StEdit.laengsteZeile(txt);
        assertEquals(expResult, result);

        txt = "Die dritte Zeile \n hat 300 Zeichen:\n"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890\n"
                + "Die vierte wieder weniger.";
        expResult = 300;
        result = StEdit.laengsteZeile(txt);
        assertEquals(expResult, result);
    }

}
