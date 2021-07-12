public class PhraseFilter implements Filter {

    private String pos;
    private String phr;
    private String name = "Phrase";

    public PhraseFilter(String position, String phrase) {
        pos = position;
        phr = phrase;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if (pos == "start") {
            return qe.getInfo().startsWith(phr);
        } else if (pos == "any") {
            return qe.getInfo().contains(phr);
        } else if (pos == "end") {
            return qe.getInfo().endsWith(phr);
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
