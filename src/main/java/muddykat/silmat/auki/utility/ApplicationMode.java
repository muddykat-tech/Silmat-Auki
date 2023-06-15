package muddykat.silmat.auki.utility;

public enum ApplicationMode {
    selection,
    trigram;

    public static ApplicationMode selected;

    static {
        ApplicationMode.selected = selection;
    }
}
