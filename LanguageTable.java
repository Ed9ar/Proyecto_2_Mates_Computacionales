public class LanguageTable{

    private String initState;
    private String textFinalState;
    private String texte;

    public LanguageTable(String initState, String textFinalState, String texte) {
        this.initState = initState;
        this.textFinalState =textFinalState;
        this.texte = texte;
    }

    public String getInitState() {
        return initState;
    }

    public void setInitState(String initState) {
        this.initState = initState;
    }

    public String getFinalState() {
        return textFinalState;
    }

    public void setFinalState(String textFinalState) {
        this.textFinalState = textFinalState;
    }

    public String getAlphabet() {
        return texte;
    }

    public void setAlphabet(String texte) {
        this.texte = texte;
    }
}

