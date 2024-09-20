public class Main {
    public static void main(String[] args){
        NGram n = new NGram(3, "Hej Kristian kristian Kristian jag heter Kristian jag kommer från Bromma och har aldrig någonsin haft tre potatisar i min lilla påse som jag kommer från potatisar med fyra tre två eller två kanske Alfred är också en kompis som jag har som ibland har två potatisar i sin påse men det var ju en lögn han kommer från Tullinge, men originellt från Lund, där han föddes på Lunds Universitetssjukhus och jag gillar glass glass glass glass det är gott med glass det är gott morot vem är jag egentligen kaffe? vaddå Kaffe? Det är så jävla gott med Kult Nougat första ciggen på 10 dagar kaffe med lite mjölk och socker och linie aquavit i min lilla sko i min lilla jävla i min lilla skål i min lilla sko i min lilla påse i min lilla ciggen på 10 dagar sko");

        System.out.println(n.generateSentence(40, new String[]{"men", "det"}));

    }
}
